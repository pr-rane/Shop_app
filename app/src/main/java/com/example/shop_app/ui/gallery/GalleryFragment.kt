package com.example.shop_app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shop_app.ShopApplication
import com.example.shop_app.databinding.FragmentGalleryBinding
import com.example.shop_app.viewmodels.GalleryViewModel


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val api = ShopClient.publicApi
//        val productsRepo = ProductsRepo(api)
//        galleryViewModel =
//            ViewModelProvider(this,ViewModelFactory(productsRepo)).get(GalleryViewModel::class.java)


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        adapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_list_item_1
        )
        _binding?.gridCategories?.adapter = adapter

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel = (requireActivity().application as ShopApplication).applicationComponent.getGalleryVM()

        galleryViewModel.categories.observe(viewLifecycleOwner){
            adapter.clear()
            adapter.addAll(it)
        }
        _binding?.apply {
            // Implement On Item click listener
            // Implement On Item click listener
            gridCategories.setOnItemClickListener { parent, view, position, id ->
                val productId = parent.getItemAtPosition(position) as String
                findNavController().navigate(
                    com.example.shop_app.R.id.action_gallery_openHome,
                    bundleOf(
                        resources.getString(com.example.shop_app.R.string.arg_category_name) to productId
                    )
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}