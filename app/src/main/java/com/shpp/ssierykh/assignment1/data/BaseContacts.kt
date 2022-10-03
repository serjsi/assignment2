package com.shpp.ssierykh.assignment1.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shpp.ssierykh.assignment1.activity_and_splash.MainActivity
import com.shpp.ssierykh.assignment1.model.Contact

import com.shpp.ssierykh.assignment1.utils.Constants
import java.util.ArrayList


typealias ContactListener = (contacts: List<Contact>) -> Unit


class BaseContacts
{
    private var contacts = mutableListOf<Contact>()
    private val listeners = mutableSetOf<ContactListener>()


    init {
        loadContacts()

    }

    private fun loadContacts() {
        contacts = mutableListOf(

            Contact("FrankWells@mail.ua", Constants.PHOTO_FAKE_1, "Frank Wells", "Baker"),
            Contact(
                "JasminBailey@mail.ua",
                Constants.PHOTO_FAKE_2,
                "Jasmin Bailey",
                "Business owner"
            ),
            Contact("AlainaWalters@mail.ua", Constants.PHOTO_FAKE_3, "Alaina Walters", "Cameraman"),
            Contact("DaisyGordon@mail.ua", Constants.PHOTO_FAKE_4, "Daisy Gordon", "Cashier"),
            Contact("FrederickPope@mail.ua", Constants.PHOTO_FAKE_5, "Frederick Pope", "Chef"),
            Contact("ThomasPaul@mail.ua", Constants.PHOTO_FAKE_6, "Thomas Paul", "Civil servant"),
            Contact("RichardTodd@mail.ua", Constants.PHOTO_FAKE_7, "Richard Todd", "Cleaner"),
            Contact(
                "SharonAnderson@mail.ua",
                Constants.PHOTO_FAKE_8,
                "Sharon Anderson",
                "Distributor"
            ),
            Contact("RobertHarmon@mail.ua", Constants.PHOTO_FAKE_9, "Robert Harmon", "Engineer"),
            Contact("RuthJohnson@mail.ua", Constants.PHOTO_FAKE_10, "Ruth Johnson", "Financier"),
            Contact("JulietMcDonald@mail.ua", Constants.PHOTO_FAKE_11, "Juliet McDonald", "Fitter"),
            Contact("ThomasHampton@mail.ua", Constants.PHOTO_FAKE_12, "Thomas Hampton", "Guard"),
            Contact("ValentineCraig@mail.ua", Constants.PHOTO_FAKE_13, "Valentine Craig", "Hunter"),
            Contact("EdwinLittle@mail.ua", Constants.PHOTO_FAKE_14, "Edwin Little", "Jeweller"),
        )
        contacts.sortBy { contactRecyclerView -> contactRecyclerView.name }
    }

    fun getContacts(): List<Contact> {
        return contacts
    }

    fun getContactForEmail(emailFind: String): Contact? {
        return contacts.firstOrNull { it.email == emailFind }
    }

    fun setContact(contactNew: Contact) {
        val contact = getContactForEmail(contactNew.email)
        if (contactNew.photoAddress != contact?.photoAddress) {
            contact?.photoAddress = contactNew.photoAddress
        }
        if (contactNew.name != contact?.name) {
            contact?.name = contactNew.name
        }
        if (contactNew.career != contact?.career) {
            contact?.career = contactNew.career
        }
        if (contactNew.phone != contact?.phone) {
            contact?.phone = contactNew.phone
        }
        if (contactNew.home != contact?.home) {
            contact?.home = contactNew.home
        }
        if (contactNew.birth != contact?.birth) {
            contact?.birth = contactNew.birth
        }
        notifyChanges()
    }

    fun deleteContact(contact: Contact) {
        val indexToDelete = contacts.indexOfFirst { it.email == contact.email }
        if (indexToDelete != -1) {
            contacts = ArrayList(contacts)
            contacts.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addContact(user: Contact) {
        contacts = ArrayList(contacts)
        contacts.add(user)
        contacts.sortBy { contactRecyclerView -> contactRecyclerView.name }
        notifyChanges()
    }


    fun addListener(listener: ContactListener) {
        listeners.add(listener)
        listener.invoke(contacts)
    }

    fun removeListener(listener: ContactListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(contacts) }

    }


}