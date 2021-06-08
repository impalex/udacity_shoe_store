package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeListFragment : Fragment() {

    private val args by navArgs<ShoeListFragmentArgs>()
    private val viewModel by activityViewModels<ShoeListViewModel>()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.newShoe?.run {
            viewModel.addShoe(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        binding.addFab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeInfoFragment())
        }
        viewModel.closet.observe(viewLifecycleOwner, { updateShoeList(it) })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java) as InputMethodManager).hideSoftInputFromWindow(requireView().windowToken,0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arguments?.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_action) {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateShoeList(shoes: List<Shoe>) {
        // NOTE Ok... I feel stupid. There should be a RecyclerView, not a LinearLayout :-/
        // Well, as you wish... :-/
        shoes.forEach {
            val shoeBinding = DataBindingUtil.inflate<ItemShoeBinding>(layoutInflater, R.layout.item_shoe, binding.shoeListLayout, false)
            shoeBinding.shoe = it
            binding.shoeListLayout.addView(shoeBinding.root)
        }
    }

}