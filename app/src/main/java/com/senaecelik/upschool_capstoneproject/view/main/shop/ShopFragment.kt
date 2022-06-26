package com.senaecelik.upschool_capstoneproject.view.main.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentShopBinding
import com.senaecelik.upschool_capstoneproject.view.main.adapter.ProductShopAdapter

class ShopFragment() : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ShopFragmentViewModel(requireContext()) }
    private val allProductAdapter by lazy { ProductShopAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {


                isLoading.observe(viewLifecycleOwner) {
                    if (it){
                        progressBar.visibility = View.VISIBLE
                    }else{
                        progressBar.visibility = View.GONE
                    }
                }
                categoryList.observe(viewLifecycleOwner) {
                    categoryRecyclerView.apply {
                        setHasFixedSize(true)
                        adapter = allProductAdapter.also { adapter ->
                            adapter.updateList(it)

                        }

                    }
                }


            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


