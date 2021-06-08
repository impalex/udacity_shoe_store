package com.udacity.shoestore.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val email = MutableLiveData<String>().apply { value = "" }
    val password = MutableLiveData<String>().apply { value = "" }
    val canContinue = MediatorLiveData<Boolean>().apply {
        CanContinueValidator { postValue(it) }.apply {
            addSource(email, this)
            addSource(password, this)
        }
    }
    // Do we need more validators? Email? Password strength?

    private inner class CanContinueValidator(private val consumer: (Boolean) -> Unit): Observer<Any> {
        override fun onChanged(t: Any?) {
            consumer(!email.value.isNullOrBlank() && !password.value.isNullOrBlank())
        }

    }

}