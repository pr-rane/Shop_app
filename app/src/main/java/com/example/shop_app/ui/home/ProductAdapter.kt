package com.example.shop_app.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api.models.entities.Product
import com.example.shop_app.databinding.ListItemProductBinding
import com.example.shop_app.extensions.loadImage

class ProductAdapter(val onProductClicked: (id: Int)-> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<Product>? = null

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemProductBinding.inflate(inflater,parent,false)
        return ProductViewHolder(binding.root)
    }

    override fun getItemCount(): Int =products?.size?:0

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        ListItemProductBinding.bind(holder.itemView).apply {
            val product = products?.get(position)

            product?.let {
                productName.text = product.title
                productImage.loadImage(product.image)
                productPrice.text = product.price.toString()
                productRate.rating = product.rating?.count?.toFloat() ?: 0.0f
                productRateCount.text = product.rating?.rate.toString()

                root.setOnClickListener {
                    product.id?.let {
                         onProductClicked(it)
                    }
                }

            }

        }
    }

    fun updateProductList(productList: List<Product>) {
        products = emptyList()
        products = productList
        notifyDataSetChanged()
    }


}
