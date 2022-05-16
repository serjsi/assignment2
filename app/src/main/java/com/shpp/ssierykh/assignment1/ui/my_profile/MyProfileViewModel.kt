package com.shpp.ssierykh.assignment1.ui.my_profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class MyProfileViewModel(
    private val baseContacts: BaseContacts
) : ViewModel() {
    private val _profileContact = MutableStateFlow(Contact())
    val profilContact: StateFlow<Contact> = _profileContact.asStateFlow()


    fun setContact(myContactEmail: String) {
        var myContact = baseContacts.getContactForEmail(myContactEmail)
        Log.d("my profilq", "___________________________$myContact")
        if (myContact != null) {
            _profileContact.value = myContact
        } else {
            val name = parsingEmailToName(myContactEmail)
            baseContacts.addContact(Contact(myContactEmail, "", name))
            _profileContact.value =
                baseContacts.getContactForEmail(myContactEmail)!!
        }
    }
}