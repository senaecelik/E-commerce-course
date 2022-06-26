package com.senaecelik.upschool_capstoneproject.view.main.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.databinding.BasketCardViewBinding
import com.senaecelik.upschool_capstoneproject.databinding.FavCardViewBinding
import com.senaecelik.upschool_capstoneproject.databinding.ProductLayoutRowBinding
import com.squareup.picasso.Picasso

class ProductFavoriteAdapter : RecyclerView.Adapter<ProductFavoriteAdapter.ProductViewHolder>() {

    private val productBasketList = ArrayList<Product>()
    var onRemoveBasketClick: (Int) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productFavItemBinding =
            FavCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(productFavItemBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productBasketList[position])
    }

    inner class ProductViewHolder(private var productFavItemBinding: FavCardViewBinding) :
        RecyclerView.ViewHolder(productFavItemBinding.root) {

        fun bind(productFav: Product) {
            productFavItemBinding.apply {

                productModel = productFav

                productFav.productImage.let {
                    Picasso.get().load(it).into(imageView)

                }

               productDelete.setOnClickListener {
                    onRemoveBasketClick(productFav.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productBasketList.size
    }

    fun updateList(list: List<Product>) {
        productBasketList.clear()
        productBasketList.addAll(list)
        notifyDataSetChanged()
    }

}