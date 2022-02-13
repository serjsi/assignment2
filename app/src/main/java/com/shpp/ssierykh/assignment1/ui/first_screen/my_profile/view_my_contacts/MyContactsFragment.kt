package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.data.FakeBaseContacts_old_delete.fakeBase

import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.ui.activity_old.contacts.AdapterContacts
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.ui.first_screen.App
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.MyProfileViewModel


class MyContactsFragment : Fragment() {
    private lateinit var binding: FragmentMyContactsBinding
    private lateinit var adapter: AdapterContactsList


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyContactsBinding.inflate(inflater, container, false)


        // initialize the adapter, and pass the required argument
     //   adapter = AdapterContactsList(viewModel)

        // attach adapter to the recycler view
     //   binding.rvBottomContainer.adapter = adapter

        binding.ivArrowBack.setOnClickListener { onArrowBack() }
        binding.tvAddContacts.setOnClickListener { onAddContact() }

        return binding.root
    }




    private fun onAddContact() {
        routing().showAddOrEditContacts()
    }

    private fun onArrowBack() {
        routing().goBack()
    }
}