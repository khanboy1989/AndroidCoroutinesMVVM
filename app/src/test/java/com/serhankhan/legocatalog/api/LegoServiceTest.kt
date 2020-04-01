package com.serhankhan.legocatalog.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.serhankhan.legocatalog.ui.util.LiveDataCallAdapterFactory
import com.serhankhan.legocatalog.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertThat

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class LegoServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: LegoService

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(LegoService::class.java)

    }



    @Test
    fun requestThemes(){
        runBlocking {
            enqueueResponse("themes.json")
            val response = (service.getThemes().getOrAwaitValue() as ApiSuccessResponse).body

            val request = mockWebServer.takeRequest()
            assertThat(request.path, `is`("/lego/themes/"))

            val theme = response.results[0]

            assertThat(theme.name,`is`("Technic"))
            assertThat(theme.id,`is`(1))
            assertThat(theme.parentId,`is`(nullValue()))

        }
    }


    @After
    fun stopService() {
        mockWebServer.shutdown()
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        inputStream?.let {
            val source = Okio.buffer(Okio.source(it))
            val mockResponse = MockResponse()
            for ((key, value) in headers) {
                mockResponse.addHeader(key, value)
            }
            mockWebServer.enqueue(mockResponse.setBody(
                source.readString(Charsets.UTF_8))
            )
        }

    }

}