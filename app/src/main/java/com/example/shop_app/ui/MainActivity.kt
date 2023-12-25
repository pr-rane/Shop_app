package com.example.shop_app.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shop_app.R
import com.example.shop_app.data.repo.user.responses.LoginResponse
import com.example.shop_app.databinding.ActivityMainBinding
import com.example.shop_app.utils.UiState
import com.example.shop_app.ui.connection.ConnectivityViewModel
import com.example.shop_app.ui.fragments.auth.LoginViewModel
import com.example.shop_app.ui.fragments.gallery.GalleryViewModel
import com.example.shop_app.ui.fragments.home.HomeViewModel
import com.example.shop_app.ui.fragments.product.ProductViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        const val PREF_FILE_AUTH = "prefs_app_auth"
        const val PREF_KEY_TOKEN = "token"
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val authViewModel: LoginViewModel by viewModels()
    private val  connectivityViewModel: ConnectivityViewModel by viewModels()
    private val  galleryViewModel: GalleryViewModel by viewModels()
    private val  homeViewModel: HomeViewModel by viewModels()
    private val  productViewModel: ProductViewModel by viewModels()

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(PREF_FILE_AUTH, MODE_PRIVATE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery,
                R.id.nav_login
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { item ->
            // Handle navigation item clicks here
            when(item.title){
                "Home" ->{
                    navController.navigate(R.id.nav_home)
                    Toast.makeText(applicationContext,"$item.groupId",Toast.LENGTH_LONG).show()
                }
                "Login" -> navController.navigate(R.id.nav_login)
                "Logout" -> authViewModel.logout()
                else ->
                    if (item.groupId == R.id.category_group){
                        loadCategoryFragment(item.title.toString())
                    }
            }
            // Close the drawer after handling the item click
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

        connectivityViewModel.isConnected.observe(this@MainActivity) { hasConnection ->
            binding.appBarMain.actionBarMessage.visibility = if (!hasConnection) View.VISIBLE else View.GONE
            if (hasConnection)
            {
                homeViewModel.updateProductListOnConnectionReestablish()
                productViewModel.updateProductListOnConnectionReestablish()
                galleryViewModel.updateProductListOnConnectionReestablish()
            }
        }

        lifecycleScope.launch {
            authViewModel.user.collect {
                when (it) {
                    is UiState.Success -> {
                        updateMenu(it.data)
                        it.data.token?.let { token ->
                           authViewModel.saveUserToken(token)
                        } ?: run {
                           authViewModel.clearUserToken()
                        }
                        navController.navigateUp()
                    }
                    is UiState.Error -> {
                        updateMenu(LoginResponse())
                        Toast.makeText(this@MainActivity,"Error While Logging in",Toast.LENGTH_SHORT).show()
                        authViewModel.clearUserToken()
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            galleryViewModel.categories.collect {
                val menu = navView.menu.getItem(1)?.subMenu
                menu?.also { submenu ->
                    when (it) {
                        is UiState.Not_Started ->galleryViewModel.getCategories()
                        is UiState.Success -> {
                            for (category in it.data) {
                                submenu.add(R.id.category_group, Random().nextInt(), Menu.NONE, category)
                            }
                        }
                        is UiState.Error -> {
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        else ->{}
                    }
                }
            }
        }
    }
    
    private fun updateMenu(user: LoginResponse){
        binding.apply {
            if (user.token.isNullOrEmpty()|| user.token.isBlank()){
                navView.apply {
                    menu.findItem(R.id.nav_login).isVisible = true
                    menu.findItem(R.id.nav_logout).isVisible = false
                }
            }else{
                navView.apply {
                    menu.findItem(R.id.nav_login).isVisible = false
                    menu.findItem(R.id.nav_logout).isVisible = true
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun loadCategoryFragment(categoryName: String) {

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(
            R.id.action_gallery_openHome,
            bundleOf(
                resources.getString(R.string.arg_category_name) to categoryName
            )
        )
    }
}