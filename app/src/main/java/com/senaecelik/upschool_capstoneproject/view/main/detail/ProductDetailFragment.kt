package com.senaecelik.upschool_capstoneproject.view.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.common.onSNACK
import com.senaecelik.upschool_capstoneproject.databinding.FragmentProductDetailBinding
import com.squareup.picasso.Picasso


class ProductDetailFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ProductDetailViewModel(requireContext()) }
    private val args: ProductDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,


    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.productModel

        binding.apply {

          productModel = product

           backButton.setOnClickListener{
              it.findNavController().navigateUp()
           }
           Picasso.get().load(product.productImage).into(productImageView)

            buttonAddBasket.setOnClickListener {

                firebaseAuth.currentUser?.uid?.let { user ->
                    viewModel.addBasket(
                        user,
                        product.productName,
                        product.productPrice,
                        product.productDescription,
                        product.productCategory,
                        product.productImage,
                        product.productRate,
                        product.productCount,
                        product.saleState
                    )
                    viewModel.isProductAddedBasket.observe(viewLifecycleOwner) {
                        if (it)
                            Toast.makeText(requireContext(), "Eklendi", Toast.LENGTH_SHORT).show()
                        else  Toast.makeText(requireContext(), "Eklenmedi", Toast.LENGTH_SHORT).show()
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