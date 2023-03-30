package com.example.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

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



}