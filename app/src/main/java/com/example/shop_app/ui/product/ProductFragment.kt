package com.example.shop_app.ui.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop_app.R
import com.example.shop_app.databinding.FragmentProductBinding
import com.example.shop_app.extensions.loadImage

class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private var _binding: FragmentProductBinding? = null
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        arguments?.let {
            productId = it.getInt(resources.getString(R.string.arg_product_id))
        }

        productId?.let {
            viewModel.fetchProduct(it)
        }

        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.product.observe(viewLifecycleOwner){
            _binding?.apply {
                productName.text = it.title
                productImage.loadImage(it.image)
                productPrice.text = it.price.toString()
                productDescription.text = it.description.toString()
                productRate.rating = it.rating.count.toFloat()
                productRateCount.text = it.rating.rate.toString()

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}