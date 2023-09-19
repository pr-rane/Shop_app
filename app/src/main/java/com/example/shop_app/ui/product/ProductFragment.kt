package com.example.shop_app.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shop_app.MainActivity
import com.example.shop_app.R
import com.example.shop_app.ShopApplication
import com.example.shop_app.databinding.FragmentProductBinding
import com.example.shop_app.extensions.loadImage
import com.example.shop_app.viewmodels.GalleryViewModel
import com.example.shop_app.viewmodels.ProductViewModel

class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private var _binding: FragmentProductBinding? = null
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater,container,false)
//        val api = ShopClient.publicApi
//        val productsRepo = ProductsRepo(api)
//        viewModel = ViewModelProvider(this,ViewModelFactory(productsRepo)).get(ProductViewModel::class.java)
//        viewModel = (requireActivity().application as ShopApplication).activityComponent.getProductVM()
        val viewModelFactory = (activity as MainActivity).viewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

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
                productRate.rating = it.rating?.count?.toFloat() ?: 0.0f
                productRateCount.text = it.rating?.rate.toString()

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}