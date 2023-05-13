package io.github.synergy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.synergy.controller.InfoController
import io.github.synergy.dto.EmergencyContact
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class ContactsViewModel(private val infoController: InfoController): ViewModel() {
    private val _contacts: MutableLiveData<List<EmergencyContact>> = MutableLiveData(emptyList())
    val contacts: LiveData<List<EmergencyContact>> = _contacts

    init {
        viewModelScope.launch {
            fetchContacts()
        }
    }

    suspend fun fetchContacts() {
        val contacts = infoController.fetchContacts()
        _contacts.postValue(contacts)
    }

    suspend fun addContact(name: String, number: String) {
        if (infoController.addContact(EmergencyContact(name, number))) {
            val data = (_contacts.value ?: emptyList()).toMutableList()
            data.add(EmergencyContact(name, number))
            _contacts.postValue(data)
        }
    }

    suspend fun deleteContact(contact: EmergencyContact) {
        if (infoController.deleteContact(contact)) {
            val data = (_contacts.value ?: emptyList()).toMutableList()
            data.removeIf { it == contact }
            _contacts.postValue(data)
        }
    }

    class Factory(private val infoController: InfoController): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
                return ContactsViewModel(infoController) as T
            }
            return super.create(modelClass)
        }
    }
}