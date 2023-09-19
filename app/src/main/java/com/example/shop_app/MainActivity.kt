package com.example.shop_app

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shop_app.databinding.ActivityMainBinding
import com.example.shop_app.di.ActivityComponent
import com.example.shop_app.di.DaggerActivityComponent
import com.example.shop_app.di.DaggerAppComponent
import com.example.shop_app.viewmodels.LoginViewModel
import com.example.shop_app.viewmodels.GalleryViewModel
import com.google.android.material.navigation.NavigationView
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object{
        const val PREF_FILE_AUTH = "prefs_app_auth"
        const val PREF_KEY_TOKEN = "token"
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var galleryViewModel: GalleryViewModel
    lateinit var authViewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedPreferences: SharedPreferences

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(PREF_FILE_AUTH, MODE_PRIVATE)
        val appComponent = (application as ShopApplication).appComponent
        activityComponent = DaggerActivityComponent
            .factory().create(appComponent)
        activityComponent.inject(this)
//        val api = ShopClient.providesShopAPI()
//        val productsRepo = ProductsRepo(api)
//        galleryViewModel =
//            ViewModelProvider(this,ViewModelFactory(productsRepo)).get(GalleryViewModel::class.java)
//
//        authViewModel = ViewModelProvider(this,ViewModelFactory(productsRepo)).get(LoginViewModel::class.java)
        authViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        galleryViewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]

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
                R.id.nav_home,  R.id.nav_gallery,
                R.id.nav_login
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { item ->
            // Handle navigation item clicks here
            when(item.title){
                "Home" -> navController.navigate(R.id.nav_home)
                "Login" -> navController.navigate(R.id.nav_login)
                else ->
                    if (item.groupId == R.id.category_group){
                        loadCategoryFragment(item.title.toString())
                    }
            }
            // Close the drawer after handling the item click
            drawerLayout.closeDrawer(GravityCompat.START)
            true

        }

        authViewModel.user.observe(this){
            updateMenu(it)
            it?.let { t->
                sharedPreferences.edit{
                    putString(PREF_KEY_TOKEN,t)
                }
            } ?: run{
                sharedPreferences.edit{
                    remove(PREF_KEY_TOKEN)
                }
            }
            navController.navigateUp()
        }

        galleryViewModel.categories.observe(this) {
            val menu = navView.menu.getItem(1)?.subMenu
            if(menu!=null){
                for (category in it) {
                    menu.add(R.id.category_group, Random().nextInt(), Menu.NONE, category)
                }
            }
        }
    }
    
    private fun updateMenu(user: String?){
        when(user){
            is String -> {
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main)

            }
            else ->{
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_guest)
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