package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private val _closet = MutableLiveData<List<Shoe>>()
    val closet: LiveData<List<Shoe>>
        get() = _closet

    init {
        _closet.value = mutableListOf()
    }

    fun addShoe(shoe: Shoe) {
        (_closet.value as MutableList<Shoe>).add(shoe)
    }

}