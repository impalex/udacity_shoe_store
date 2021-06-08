package com.udacity.shoestore.viewmodels

import androidx.lifecycle.*

class ShoeInfoViewModel: ViewModel() {

    val name = MutableLiveData<String>().apply { value = "" }
    val company = MutableLiveData<String>().apply { value = "" }
    val description = MutableLiveData<String>().apply { value = "" }
    val sizeString = MutableLiveData<String>().apply { value = "" }
    val size = Transformations.map(sizeString) { it.toDoubleOrNull() }
    val canSave = MediatorLiveData<Boolean>().apply {
        SaveValidator { postValue(it) }.apply {
            addSource(name, this)
            addSource(company, this)
            addSource(description, this)
            addSource(size, this)
        }
    }

    private inner class SaveValidator(private val consumer: (Boolean) -> Unit): Observer<Any?> {
        override fun onChanged(t: Any?) {
            consumer(!name.value.isNullOrBlank() && !company.value.isNullOrBlank() && !description.value.isNullOrBlank() && size.value != null)
        }

    }

}