package com.example.shop_app.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shop_app.MainActivity
import com.example.shop_app.ShopApplication
import com.example.shop_app.databinding.FragmentLoginBinding
import com.example.shop_app.viewmodels.HomeViewModel
import com.example.shop_app.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private  var _binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val api = ShopClient.publicApi
//        val productsRepo = ProductsRepo(api)
//        viewModel = ViewModelProvider(this,ViewModelFactory(productsRepo)).get(LoginViewModel::class.java)
//        viewModel = (requireActivity().application as ShopApplication).activityComponent.getLoginVM()
        val viewModelFactory = (activity as MainActivity).viewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        _binding?.apply {
            loginButton.setOnClickListener{
                viewModel.login(
                    editUserName.text.toString(),
                    editPassword.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}