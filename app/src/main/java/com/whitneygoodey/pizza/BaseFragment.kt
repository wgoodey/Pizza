package com.whitneygoodey.pizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.whitneygoodey.pizza.databinding.FragmentBaseBinding
import com.whitneygoodey.pizza.model.OrderViewModel


class BaseFragment : Fragment() {

    private var binding: FragmentBaseBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            baseFragment = this@BaseFragment
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
        findNavController().navigate(R.id.action_baseFragment_to_toppingsFragment)
    }


}