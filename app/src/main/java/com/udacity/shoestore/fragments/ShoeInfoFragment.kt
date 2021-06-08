package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeInfoBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeInfoViewModel

class ShoeInfoFragment : Fragment() {

    private val viewModel by viewModels<ShoeInfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding = DataBindingUtil.inflate<FragmentShoeInfoBinding>(inflater, R.layout.fragment_shoe_info, container, false)
        binding.shoeInfoViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.cancelButton.setOnClickListener { findNavController().navigateUp() }

        binding.saveButton.setOnClickListener {
            val shoe = Shoe(viewModel.name.value ?: "", viewModel.size.value ?: 0.0, viewModel.company.value ?: "", viewModel.description.value ?: "")
            findNavController().navigate(ShoeInfoFragmentDirections.actionShoeInfoFragmentToShoeListFragment(shoe))
        }

        return binding.root
    }

}