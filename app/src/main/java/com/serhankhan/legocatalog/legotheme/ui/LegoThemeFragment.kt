package com.serhankhan.legocatalog.legotheme.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.serhankhan.legocatalog.AppExecutors

import com.serhankhan.legocatalog.R
import com.serhankhan.legocatalog.databinding.FragmentLegoThemeBinding
import com.serhankhan.legocatalog.di.Injectable
import com.serhankhan.legocatalog.di.ViewModelFactory
import com.serhankhan.legocatalog.di.injectViewModel
import com.serhankhan.legocatalog.vo.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class LegoThemeFragment : Fragment(),Injectable {

    private val TAG = LegoThemeFragment::class.java.simpleName

    @Inject
    lateinit var viewModelFactory:ViewModelFactory

    private lateinit var viewModel: LegoThemeViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentLegoThemeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.themes.observe(viewLifecycleOwner, Observer {response->

            when(response.status) {
                Status.LOADING -> {
                    Log.d(TAG,"Loading")
                }

                Status.SUCCESS->{
                    Log.d(TAG,response.data.toString())
                }

                Status.ERROR->{
                    Log.d(TAG,response.message)
                }
            }

        })
    }

}
