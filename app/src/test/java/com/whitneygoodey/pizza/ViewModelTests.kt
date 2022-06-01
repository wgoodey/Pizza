package com.whitneygoodey.pizza

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.whitneygoodey.pizza.model.OrderViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class ViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun size_small() {
        val viewModel = OrderViewModel()
        viewModel.size.observeForever {}
        viewModel.setSize("Small")
        assertEquals("Small", viewModel.size.value)
    }

    @Test
    fun size_medium() {
        val viewModel = OrderViewModel()
        viewModel.size.observeForever {}
        viewModel.setSize("Medium")
        assertEquals("Medium", viewModel.size.value)
    }

    @Test
    fun size_large() {
        val viewModel = OrderViewModel()
        viewModel.size.observeForever {}
        viewModel.setSize("Large")
        assertEquals("Large", viewModel.size.value)
    }

    @Test
    fun price_small() {
        val viewModel = OrderViewModel()
        viewModel.cost.observeForever {}
        viewModel.setSize("Small")
        assertEquals("$10.00", viewModel.cost.value)
    }

    @Test
    fun price_small_delivered() {
        val viewModel = OrderViewModel()
        viewModel.cost.observeForever {}
        viewModel.setSize("Small")
        viewModel.setDelivery(true)
        assertEquals("$15.75", viewModel.cost.value)
    }
}