package com.whitneygoodey.pizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.whitneygoodey.pizza.databinding.FragmentPickupBinding
import com.whitneygoodey.pizza.model.OrderViewModel


class PickupFragment : Fragment() {

    private var binding: FragmentPickupBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun cancelOrder() {
        //go back to size selection
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_global_startFragment)
    }

    fun goToNextScreen() {
        //go to next screen
        if (binding?.deliverySwitch?.isChecked == true) {
            findNavController().navigate(R.id.action_pickupFragment_to_addressFragment)
        } else {
            findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
        }
    }

}