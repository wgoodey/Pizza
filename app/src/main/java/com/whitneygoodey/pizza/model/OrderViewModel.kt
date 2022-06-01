package com.whitneygoodey.pizza.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val SMALL_PRICE = 10.00
private const val MEDIUM_PRICE = 15.00
private const val LARGE_PRICE = 20.00
private const val DELIVERY_FEE = 5.75
private const val EXTRA_CHARGE = 0.50
private const val LEAD_TIME = 30

class OrderViewModel : ViewModel() {

    //size
    private val _size = MutableLiveData<String>()
    val size : LiveData<String> = _size

    //crust
    private val _crust = MutableLiveData<String>()
    val crust: LiveData<String> = _crust

    //sauce
    private val _sauce = MutableLiveData<String>()
    val sauce: LiveData<String> = _sauce

    //subtotal
    private val _cost = MutableLiveData<Double>()
    val cost : LiveData<String> = Transformations.map(_cost) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    //pickup time options
    var pickupOptions: List<String> = listOf()

    //pickup date and time
    private val _pickupTime = MutableLiveData<String>()
    val pickupTime: LiveData<String> = _pickupTime

    //name
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    //address
    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    //name
    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    //address
    private val _zip = MutableLiveData<String>()
    val zip: LiveData<String> = _zip

    //address
    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    //delivery
    private val _delivery = MutableLiveData<Boolean>()
    val delivery: LiveData<Boolean> = _delivery

    //toppings
    private var _toppings: MutableMap<String, String> = mutableMapOf()




    init {
        resetOrder()
    }


    fun setSize(pizzaSize: String) {
        _size.value = pizzaSize
        updateCost()
    }

    fun setCrust(crustType: String) {
        _crust.value = crustType
    }

    fun setSauce(sauceType: String) {
        _sauce.value = sauceType
    }

    fun addTopping(topping: String) {
        _toppings[topping] = topping
    }

    fun removeTopping(topping: String) {
        _toppings[topping] =  when (topping) {
            "Cheese" -> "NO CHEESE"
            else -> ""
        }
    }

    fun hasTopping(topping: String): Boolean {
        return _toppings[topping] != ""
    }

    fun getToppings(): Map<String, String> {
        return _toppings.filterNot { it.value == "" }.toMap()
    }

    fun hasExtraTopping(topping: String): Boolean {
        return _toppings[topping] == "Extra $topping"
    }

    fun addExtra(topping: String) {
        _toppings[topping] = "Extra $topping"
        updateCost()
    }

    fun subExtra(topping: String) {
        _toppings[topping] = topping
        updateCost()
    }

    fun getCostOfExtras(): String {
        return NumberFormat.getCurrencyInstance().format(EXTRA_CHARGE)
    }

    fun getCostOfDelivery(): String {
        return NumberFormat.getCurrencyInstance().format(DELIVERY_FEE)
    }

    fun setDelivery(selection: Boolean) {
        _delivery.value = selection
        updateCost()
    }

    fun setPickupOptions() {
        val options = mutableListOf<String>()
        val time = Calendar.getInstance()
        val formatter = SimpleDateFormat("hh:mm a")
        //use this formatter if implementing business hours
//        val formatter = SimpleDateFormat("hh:mm a d/M ")

        //add time for pizza production
        time.add(Calendar.MINUTE, LEAD_TIME)

        while (options.size < 4) {
            options.add(formatter.format(time.time))
            time.add(Calendar.HOUR, 1)
        }

        pickupOptions = options
        _pickupTime.value = pickupOptions[0]
    }

    fun setPickupTime(time: String) {
        _pickupTime.value = time
        Log.d("Pickup", _pickupTime.value.toString())
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setAddress(address: String) {
        _address.value = address
    }

    fun setCity(city: String) {
        _city.value = city
    }

    fun setZip(zip: String) {
        _zip.value = zip
    }

    fun setPhone(phone: String) {
        _phone.value = phone
    }

    fun getFullAddress(): String{
        val sb = StringBuilder()
        sb.append("${address.value} \n")
        sb.append("${city.value}, ${zip.value} \n")
        sb.append("${phone.value}")

        return sb.toString()
    }

    private fun updateCost() {
        var price = when (_size.value) {
            "Small" -> SMALL_PRICE
            "Medium" -> MEDIUM_PRICE
            else -> LARGE_PRICE
        }

        val count = _toppings.count { it.value.startsWith("Extra") }
        if (count > 0) {
            price += (count * EXTRA_CHARGE)
        }

        if (_delivery.value == true) {
            price += DELIVERY_FEE
        }

        _cost.value = price
    }

    fun resetOrder() {
        _size.value = ""
        _crust.value = "Original hand tossed"
        _sauce.value = "Tomato sauce"
        _cost.value = 0.0
        _pickupTime.value = ""
        _delivery.value = false
        _name.value = ""
        _address.value = ""
        _city.value = ""
        _zip.value = ""
        _phone.value = ""


        _toppings = mutableMapOf(
            "Cheese" to "Cheese",
            "Pepperoni" to "",
            "Sausage" to "",
            "Bacon" to "",
            "Anchovies" to "",
            "Mushrooms" to "",
            "Peppers" to "",
            "Artichoke hearts" to ""
        )
    }


}