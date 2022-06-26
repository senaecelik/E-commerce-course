package com.senaecelik.upschool_capstoneproject.view.main.fav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentFavoriteBinding
import java.text.NumberFormat
import java.util.*


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { FavoriteFragmentViewModel(requireContext()) }
    private val productBasketAdapter by lazy { ProductFavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,


    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            backButton.setOnClickListener{
                it.findNavController().navigateUp()
            }
        }
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

                favRecyclerView.setHasFixedSize(true)

                productBasketAdapter.onRemoveBasketClick = {
                    viewModel.deleteProductFromBasket(it)
                }

                productBasket.observe(viewLifecycleOwner) { list ->
                    productBasketAdapter.updateList(list)
                    productFavRecyclerAdapter = productBasketAdapter
                    emptyBasketText.visibility = View.GONE

                    if (list.isNotEmpty()) {
                        progressBar.visibility = View.GONE
                        emptyShop.visibility = View.GONE
                    }else{

                        progressBar.visibility = View.GONE
                        emptyShop.visibility = View.VISIBLE
                        emptyBasketText.visibility = View.VISIBLE
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