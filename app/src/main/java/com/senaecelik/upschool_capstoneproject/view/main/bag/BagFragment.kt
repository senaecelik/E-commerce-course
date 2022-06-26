package com.senaecelik.upschool_capstoneproject.view.main.bag

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentBagBinding
import com.senaecelik.upschool_capstoneproject.view.main.fav.FavoriteFragmentViewModel
import java.text.NumberFormat
import java.util.*


class BagFragment : Fragment() {



    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { BagViewModel(requireContext()) }
    private val bagAdapter by lazy { BagFragmentAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bag, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        firebaseAuth.currentUser?.let {
            viewModel.getBagProduct(it.uid)
        }

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

                productBasket.observe(viewLifecycleOwner) { list ->
                    booksBasketRecycleView.setHasFixedSize(true)
                    emptyBasketText.visibility = View.GONE
                    booksBasketRecycleView.adapter = bagAdapter
                    bagAdapter.updateList(list)

                    if (list.isNotEmpty()) {
                        totalPriceText.visibility = View.VISIBLE
                        totalText.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        goToPayButton.visibility =View.VISIBLE
                        emptyShop.visibility = View.GONE
                        goToPayButton.setOnClickListener {
                            findNavController().navigate(R.id.action_bagFragment_to_successFragment)
                        }
                    }else{
                        goToPayButton.visibility= View.GONE
                        emptyShop.visibility = View.VISIBLE
                        emptyBasketText.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        totalPriceText.visibility = View.GONE
                        totalText.visibility = View.GONE
                    }


                    var totalPrice = 0f
                    var normalPrice = 0f
                    var salePrice = 0f

                    for (i in list) {
                        if (i.saleState == 1) {
                            i.productPrice.let { price ->

                                salePrice +=  (price.toFloat()) / 4
                            }
                        } else {
                            i.productPrice.let {
                                normalPrice += it.toFloat()
                            }
                        }

                        totalPrice = salePrice + normalPrice
                    }

                    binding.totalPriceText.text =
                        NumberFormat.getCurrencyInstance(Locale("tr", "TR")).format(totalPrice)
                }


                isLoading.observe(viewLifecycleOwner) {
                    if (it){
                        progressBar.visibility = View.VISIBLE
                    }else{
                        progressBar.visibility = View.GONE
                    }
                }



                firebaseAuth.currentUser?.uid?.let {uid->
                    bagAdapter.onRemoveBasketClick = {
                        viewModel.deleteProductFromBasket(it, uid)
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