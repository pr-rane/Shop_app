package com.example.shop_app.ui.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop_app.R
import com.example.shop_app.data.repo.product.model.Product
import com.example.shop_app.databinding.ListItemProductBinding
import com.example.shop_app.ui.extensions.loadImage

class ProductAdapter(val onProductClicked: (id: Int)-> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    lateinit var context: Context
    private var products: List<Product>? = null

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
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
                productPrice.text = context.getString(R.string.rupee_format,product.price.toString())
                productRate.rating = product.rating?.rate?.toFloat() ?: 0.0f
                productRateCount.text = product.rating?.count.toString()

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
