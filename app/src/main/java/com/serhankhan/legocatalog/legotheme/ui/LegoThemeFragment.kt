package com.serhankhan.legocatalog.legotheme.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.serhankhan.legocatalog.AppExecutors

import com.serhankhan.legocatalog.R
import com.serhankhan.legocatalog.databinding.FragmentLegoThemeBinding
import com.serhankhan.legocatalog.di.Injectable
import com.serhankhan.legocatalog.di.ViewModelFactory
import com.serhankhan.legocatalog.di.injectViewModel
import com.serhankhan.legocatalog.ui.VerticalItemDecoration
import com.serhankhan.legocatalog.ui.hide
import com.serhankhan.legocatalog.ui.show
import com.serhankhan.legocatalog.vo.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class LegoThemeFragment : Fragment(), Injectable {

    private val TAG = LegoThemeFragment::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LegoThemeViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        val binding = FragmentLegoThemeBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initializeLegoThemeAdapter(binding)
        return binding.root
    }

    private fun initializeLegoThemeAdapter(binding: FragmentLegoThemeBinding?) {
        val adapter = LegoThemeAdapter()
        binding?.themeRecyclerView?.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true)
        )
        binding?.themeRecyclerView?.adapter = adapter
        subscribeUi(binding, adapter)
    }

    private fun subscribeUi(binding: FragmentLegoThemeBinding?, adapter: LegoThemeAdapter) {
        viewModel.themes.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.LOADING -> {
                    binding?.progressBar?.show()
                }

                Status.SUCCESS -> {
                    Timber.tag(TAG).d(result.data.toString())
                    binding?.progressBar?.hide()
                    result.data?.let { adapter.submitList(it.results) }
                }

                Status.ERROR -> {
                    binding?.progressBar?.hide()
                    binding?.root?.let {
                        Snackbar.make(it, result.message!!, Snackbar.LENGTH_LONG).show()
                    }
                }
            }

        })
    }

}
