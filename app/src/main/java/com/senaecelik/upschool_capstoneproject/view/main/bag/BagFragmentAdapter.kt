package com.senaecelik.upschool_capstoneproject.view.main.bag

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.databinding.BasketCardViewBinding
import com.squareup.picasso.Picasso

class BagFragmentAdapter : RecyclerView.Adapter<BagFragmentAdapter.ProductViewHolder>() {

    private val productBasketList = ArrayList<Product>()
    var onRemoveBasketClick: (Int) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productBasketItemBinding =
            BasketCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(productBasketItemBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productBasketList[position])
    }

    inner class ProductViewHolder(private var productBasketItemBinding: BasketCardViewBinding) :
        RecyclerView.ViewHolder(productBasketItemBinding.root) {

        fun bind(productBasket: Product) {
            productBasketItemBinding.apply {

                productObject = productBasket

                productBasket.productImage.let {
                    Picasso.get().load(it).into(imageView)
                }

                if(productBasket.saleState == 1){
                    product.paintFlags = product.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    var newPrice: Double = (productBasket.productPrice) / 4
                    product2.text = newPrice.toString()

                }else{
                    product2.visibility = View.GONE
                    tlText.visibility = View.GONE
                }

                productDelete.setOnClickListener {
                    onRemoveBasketClick(productBasket.id)
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