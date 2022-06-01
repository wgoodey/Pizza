package com.whitneygoodey.pizza

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.whitneygoodey.pizza.databinding.FragmentSummaryBinding
import com.whitneygoodey.pizza.model.OrderViewModel


class SummaryFragment : Fragment() {

    private var binding: FragmentSummaryBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment

            when (sharedViewModel.delivery.value) {
                true -> deliveryLabel.text = requireContext().getString(R.string.deliver_at, sharedViewModel.pickupTime.value)
                else -> {
                    deliveryLabel.text = requireContext().getString(R.string.pickup_at, sharedViewModel.pickupTime.value)
                    addressText.visibility = View.GONE
                }
            }

        }
    }

    fun getToppings(): String {
        val toppingsList = sharedViewModel.getToppings()
        val sb = StringBuilder()
        toppingsList.forEach { sb.append("${it.value}\n") }
        Log.d("Toppings", sb.toString())
        return sb.toString()
    }

    private fun getSummary(): String {
        val sb = StringBuilder()
        sb.append("Hey, Pizza Emporium. I'd like to place an order!\n\n")
        sb.append("Name: ${sharedViewModel.name.value}\n")
        sb.append("Crust: ${sharedViewModel.size.value} ${sharedViewModel.crust.value}\n")
        sb.append("Sauce: ${sharedViewModel.sauce.value}\n")
        sb.append("Toppings: \n${getToppings()}\n")
        sb.append("${binding?.deliveryLabel?.text}\n")
        if (sharedViewModel.delivery.value == true) {
            sb.append(sharedViewModel.getFullAddress())
        }

        return sb.toString()
    }

    fun cancelOrder() {
        //go back to size selection
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_global_startFragment)
    }

    fun submitOrder() {
        //verify that name field is completed
        if (sharedViewModel.name.value.equals("")) {
            Toast.makeText(activity, getString(R.string.name_required), Toast.LENGTH_LONG).show()
        } else {
            val orderSummary = getSummary()

            val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT,  getString(R.string.new_order))
                .putExtra(Intent.EXTRA_TEXT, orderSummary)

            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}