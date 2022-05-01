package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.contact_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.screens.SwitchNavigationGraph.featureNavigationEnabled
import com.shpp.ssierykh.assignment1.screens.activity_old.contacts.ContactsProfileActivity
import com.shpp.ssierykh.assignment1.screens.contract.routing
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.MyContactsFragment
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.factory

import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.extensions.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class ContactProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    private val viewModel: ContactProfileViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUser(requireArguments().getString(ARG_EMAIL_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)


       setDataProfile()

        binding.apply {

            btViewMyContacts.setOnClickListener { onOpenMyContacts() }
        }
        return binding.root

    }

    private fun setDataProfile() {
        lifecycleScope.launchWhenStarted {
            viewModel.profilContact.onEach { profilContactNew ->
                // Update the UI, in this case, a TextView.
                binding.apply {
                    ivPhotoProfile.loadImageGlade(profilContactNew.photoAddress)
                    tvName.text = profilContactNew.name
                    tvCareer.text = profilContactNew.career
                    tvHomeAddress.text = profilContactNew.home
                }

            }
                .collect()
        }
    }


    private fun onOpenMyContacts() {
        if (featureNavigationEnabled) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_myContactsFragmentGraph)
        }else routing().showMyContacts()
    }

    companion object {

        private const val ARG_EMAIL_ID = "ARG_EMAIL_ID"

        fun newInstance(emailId: String): ContactProfileFragment {
            val fragment = ContactProfileFragment()
            fragment.arguments = bundleOf(ARG_EMAIL_ID to emailId)
            return fragment
        }

    }

}

