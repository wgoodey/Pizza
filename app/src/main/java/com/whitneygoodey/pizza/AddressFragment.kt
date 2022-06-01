package com.whitneygoodey.pizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.whitneygoodey.pizza.databinding.FragmentAddressBinding
import com.whitneygoodey.pizza.databinding.FragmentPickupBinding
import com.whitneygoodey.pizza.model.OrderViewModel


class AddressFragment : Fragment() {

    private var binding: FragmentAddressBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentAddressBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addressFragment = this@AddressFragment
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
        //verify that all fields are completed
        if (sharedViewModel.name.value.equals("")) {
            Toast.makeText(activity, getString(R.string.name_required), Toast.LENGTH_LONG).show()
        } else if (sharedViewModel.address.value.equals("")) {
            Toast.makeText(activity, getString(R.string.address_required), Toast.LENGTH_LONG).show()
        } else if (sharedViewModel.city.value.equals("")) {
            Toast.makeText(activity, getString(R.string.address_required), Toast.LENGTH_LONG).show()
        } else if (sharedViewModel.zip.value.equals("")) {
            Toast.makeText(activity, getString(R.string.address_required), Toast.LENGTH_LONG).show()
        } else if (sharedViewModel.phone.value.equals("")) {
            Toast.makeText(activity, getString(R.string.phone_required), Toast.LENGTH_LONG).show()
        } else {
            //go to next screen
            findNavController().navigate(R.id.action_addressFragment_to_summaryFragment)
        }
    }

}