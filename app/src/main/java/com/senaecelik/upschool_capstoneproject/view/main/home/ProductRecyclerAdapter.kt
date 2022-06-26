package com.senaecelik.upschool_capstoneproject.view.main.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.senaecelik.upschool_capstoneproject.databinding.ProductLayoutRowBinding
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.squareup.picasso.Picasso
import io.grpc.Context

class ProductRecyclerAdapter : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    private val productList = ArrayList<Product>()
    var onItemClick: (Product) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productBinding =
            ProductLayoutRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(productBinding)
    }

   inner class ProductViewHolder(private var productBinding: ProductLayoutRowBinding) :
        RecyclerView.ViewHolder(productBinding.root) {


        fun bind(product: Product) {

            productBinding.productModel = product

            product.productImage.let {
                Picasso.get().load(it).into(productBinding.imageView)

            }
            productBinding.productCard.setOnClickListener {
                val transition =
                    HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product)
                Navigation.findNavController(it).navigate(transition)

            }

            productBinding.buttonHeart.setOnClickListener {
                onItemClick(product)

            }
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    fun updateList(list: List<Product>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

}