package com.shpp.ssierykh.assignment1.screens.contract

import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.model.Contact


fun Fragment.routing(): Routing {
    return requireActivity() as Routing
}
interface Routing {

    fun showMyProfileScreen()
    fun showSignScreen()
    fun showAddOrEditContacts()
    fun showMyContacts()
    fun showContactProfile(contact: Contact)
    fun goBack()
}
