package com.shpp.ssierykh.assignment1.ui.edit_profile

import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddOrEditContactsViewModel(
    private val baseContacts: BaseContacts
) : ViewModel() {


    //StateFlow
    private val _profileContact = MutableStateFlow(Contact())
    val profilContact: StateFlow<Contact> = _profileContact.asStateFlow()


    fun loadContact(emailID: String?) {
        if (emailID != null) {
            _profileContact.value = baseContacts.getContacts().firstOrNull { it.email == emailID }!!
        }
    }

    fun setContact(contact: Contact) {
        baseContacts.addContact(contact)
    }

    fun setPhotoProfile(photoUri: String){
        val data = _profileContact.value
        _profileContact.value = Contact(data.email,photoUri,data.name,data.career,data.home)
    }
}