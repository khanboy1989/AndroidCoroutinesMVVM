package com.serhankhan.legocatalog.api

import androidx.lifecycle.LiveData
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LegoService {

    companion object {
        const val BASE_URL = "https://rebrickable.com/api/v3/"
    }

    @GET("lego/themes/")
    fun getThemes(@Query("page") page: Int? = null,
                  @Query("page_size") pageSize: Int? = null,
                  @Query("ordering") order: String? = null):LiveData<ApiResponse<LegoThemeResponse>>


}