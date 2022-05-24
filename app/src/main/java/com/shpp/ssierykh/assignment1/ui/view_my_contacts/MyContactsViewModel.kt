package com.shpp.ssierykh.assignment1.ui.view_my_contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.data.ContactListener
import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding

import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.utils.Event


class MyContactsViewModel(
    private val baseContacts: BaseContacts
) : ViewModel(), ContactClickListener {

    private lateinit var lastDeleteContact: Contact

    private var _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private val _actionShowDetails = MutableLiveData<Event<Contact?>>()
    val actionShowDetails: MutableLiveData<Event<Contact?>> = _actionShowDetails

    private val _actionShowSnackBar = MutableLiveData<Event<String>>()
    val actionShowSnackBar: MutableLiveData<Event<String>> = _actionShowSnackBar


    override fun onContactDetails(contact: Contact) {
        _actionShowDetails.value = Event(contact)
    }

    override fun onContactDelete(contact: Contact) {
        baseContacts.deleteContact(contact)
        _actionShowSnackBar.value = Event(contact.name)
        lastDeleteContact = contact
    }

    fun restoreLastDeletingContact() {
        onContactAdd(lastDeleteContact)
    }

    private fun onContactAdd(contact: Contact) {
        baseContacts.addContact(contact)
    }


    override fun onCleared() {
        super.onCleared()
        baseContacts.removeListener(listener)

    }

    private val listener: ContactListener = {
        _contacts.value = baseContacts.getContacts()
    }

    init {
        baseContacts.addListener(listener)
        loadUser()
        _contacts.value = baseContacts.getContacts()
    }


    private fun loadUser(): List<Contact> {
        return baseContacts.getContacts()
    }


    fun swipeDeleteItem(binding: FragmentMyContactsBinding) {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder2: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDirection: Int) {
                val contact = viewHolder.itemView.tag as Contact
                onContactDelete(contact)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvBottomContainer)
    }


}
