package com.whitneygoodey.pizza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.whitneygoodey.pizza.databinding.FragmentToppingsBinding
import com.whitneygoodey.pizza.model.OrderViewModel


class ToppingsFragment : Fragment() {

    private var binding: FragmentToppingsBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentToppingsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            toppingsFragment = this@ToppingsFragment

            //set checkBox listeners
            cheeseBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.cheese)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraCheeseSwitch.isChecked) {
                            extraCheeseSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            pepperoniBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.pepperoni)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraPepperoniSwitch.isChecked) {
                            extraPepperoniSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            sausageBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.sausage)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraSausageSwitch.isChecked) {
                            extraSausageSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            baconBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.bacon)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraBaconSwitch.isChecked) {
                            extraBaconSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            anchoviesBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.anchovies)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraAnchoviesSwitch.isChecked) {
                            extraAnchoviesSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            mushroomsBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.mushrooms)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraMushroomsSwitch.isChecked) {
                            extraMushroomsSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            peppersBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.peppers)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraPeppersSwitch.isChecked) {
                            extraPeppersSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }
            artichokesBox.setOnCheckedChangeListener { _, isChecked ->
                val topping = requireContext().getString(R.string.artichokes)

                when (isChecked) {
                    true -> sharedViewModel.addTopping(topping)
                    false -> {
                        sharedViewModel.removeTopping(topping)
                        if (extraArtichokesSwitch.isChecked) {
                            extraArtichokesSwitch.isChecked = false
                            sharedViewModel.subExtra(topping)
                        }
                    }
                }
            }



            //set extra switch listeners
            extraCheeseSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        cheeseBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.cheese))
                    }
                    false -> if (cheeseBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.cheese))
                }
            }
            extraPepperoniSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        pepperoniBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.pepperoni))
                    }
                    false -> if (pepperoniBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.pepperoni))
                }
            }
            extraSausageSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        sausageBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.sausage))
                    }
                    false -> if (sausageBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.sausage))
                }
            }
            extraBaconSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        baconBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.bacon))
                    }
                    false -> if (baconBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.bacon))
                }
            }
            extraAnchoviesSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        anchoviesBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.anchovies))
                    }
                    false -> if (anchoviesBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.anchovies))
                }
            }
            extraMushroomsSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        mushroomsBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.mushrooms))
                    }
                    false -> if (mushroomsBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.mushrooms))
                }
            }
            extraPeppersSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        peppersBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.peppers))
                    }
                    false -> if (peppersBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.peppers))
                }
            }
            extraArtichokesSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        artichokesBox.isChecked = true
                        sharedViewModel.addExtra(requireContext().getString(R.string.artichokes))
                    }
                    false -> if (artichokesBox.isChecked)
                        sharedViewModel.subExtra(requireContext().getString(R.string.artichokes))
                }
            }

        }
    }

    fun cancelOrder() {
        //go back to size selection
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_global_startFragment)
    }

    fun goToNextScreen() {
        //set pickup options then go to next screen
        sharedViewModel.setPickupOptions()
        findNavController().navigate(R.id.action_toppingsFragment_to_pickupFragment)
    }
}