package com.senaecelik.upschool_capstoneproject.view.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { HomeViewModel(requireContext()) }
    private val allProductAdapter by lazy { ProductRecyclerAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            emailText.text= firebaseAuth.currentUser?.email
        }
        initObservers()

    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {


                productList.observe(viewLifecycleOwner) { list ->
                    productListRecycler.apply {
                        setHasFixedSize(true)
                        adapter = allProductAdapter.also { adapter ->
                            adapter.updateList(list)

                        }

                    }


                }



                isLoading.observe(viewLifecycleOwner) {
                    if (it){
                        progressBar.visibility = View.VISIBLE
                    }else{
                        progressBar.visibility = View.GONE
                    }
                }

                allProductAdapter.onItemClick ={
                    viewModel.addProductToFav(it)
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


