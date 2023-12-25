package com.example.shop_app.ui.fragments.product

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shop_app.R
import com.example.shop_app.databinding.FragmentProductBinding
import com.example.shop_app.ui.base.UiState
import com.example.shop_app.ui.extensions.loadImage
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private val viewModel: ProductViewModel by activityViewModels()
    private var binding: FragmentProductBinding? = null
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater,container,false)

        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            productId = it.getInt(resources.getString(R.string.arg_product_id))
        }
        productId?.let {
            viewModel.fetchProduct(it)
        }

        lifecycleScope.launch {
            viewModel.product.collect{
                when(it){
                    is UiState.Not_Started ->
                        productId?.let {
                            viewModel.fetchProduct(it)
                        }
                    is UiState.Success->{
                        it.data.let {product ->
                            binding?.apply {
                                productName.text = product.title
                                productImage.loadImage(product.image)
                                productPrice.text = getString(R.string.rupee_format,product.price)
                                productDescription.text = product.description
                                productRate.rating = product.rating?.rate?.toFloat() ?: 0.0f
                                productRateCount.text = product.rating?.count.toString()
                            }
                        }
                    }
                    is UiState.Error -> {
                        binding?.productLoader?.visibility = View.GONE
                        Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        binding?.productLoader?.visibility = View.VISIBLE
                    }
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

}