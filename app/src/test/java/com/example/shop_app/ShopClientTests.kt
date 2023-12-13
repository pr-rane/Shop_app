package com.example.shop_app
class ShopClientTests {

    @Test
    fun `get all products`() {
        runBlocking {
            val products = ShopClient.publicApi.getAllProducts()
            Assert.assertNotNull(products.body())
        }
    }

    @Test
    fun `get product by id`() {
        runBlocking {
            val product = ShopClient.publicApi.getProductById(1)
            Assert.assertNotNull(product.body())
        }
    }
    @Test
    fun `get jewelery products`() {
        runBlocking {
            val products = ShopClient.publicApi.getProductsByCategory("jewelery")
            Assert.assertNotNull(products.body())
        }
    }

    @Test
    fun `add product`() {
        runBlocking {
            val product = Product(
                title = "test product",
                price = 13.5,
                description = "lorem ipsum set",
                image = "https://i.pravatar.cc",
                category = "electronic",
                id = null,
                rating = null
                )
            val id = ShopClient.authApi.addProduct(product)
            Assert.assertNotNull(id.body())
        }
    }

    @Test
    fun `get login data`(){
        runBlocking {
            val login = LoginData("mor_2314", "83r5^_")
            val loginuser = ShopClient.publicApi.loginUser(login)
            Assert.assertNotNull(loginuser.body())
        }
    }


}