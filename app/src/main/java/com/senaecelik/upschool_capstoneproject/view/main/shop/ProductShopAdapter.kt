package com.senaecelik.upschool_capstoneproject.view.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.senaecelik.upschool_capstoneproject.databinding.CategoryCardViewBinding

class ProductShopAdapter: RecyclerView.Adapter<ProductShopAdapter.ProductViewHolder>(),
    Filterable {

    private val productList = ArrayList<String>()
    var productFilterList = ArrayList<String>()


    init {
        productFilterList = productList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productBinding =
            CategoryCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(productBinding)
    }

    class ProductViewHolder(private var productBinding: CategoryCardViewBinding): RecyclerView.ViewHolder(productBinding.root){


        fun bind(category: String) {

            productBinding.productModel = category

            if(productBinding.productModel == "mobile"){

            }

        }
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productFilterList[position])
    }

    override fun getItemCount(): Int {
        return productFilterList.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
    fun updateList(list: List<String>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

}