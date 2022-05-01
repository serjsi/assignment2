package com.shpp.ssierykh.assignment1.screens.first_screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController

import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.ActivityStartBinding
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.screens.SwitchNavigationGraph.featureNavigationEnabled
import com.shpp.ssierykh.assignment1.screens.contract.Routing
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.MyProfileFragment
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.MyProfileViewModel
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.edit_profile.AddOrEditContactsDialogFragment
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.MyContactsFragment
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.contact_profile.ContactProfileFragment
import com.shpp.ssierykh.assignment1.screens.first_screen.sign.SignFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Array.newInstance
import javax.xml.validation.SchemaFactory.newInstance


private lateinit var binding: ActivityStartBinding
private lateinit var navController: NavController

@AndroidEntryPoint
class StartActivity : AppCompatActivity(), Routing {
    private val viewModel: MyProfileViewModel by viewModels()

    // actions to be launched when activity is active
    private val actions = mutableListOf<() -> Unit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!featureNavigationEnabled) {
            binding = ActivityStartBinding.inflate(layoutInflater).also { setContentView(it.root) }
        }
        setContentView(R.layout.activity_start)

        // Initially display the first fragment in main activity
        if (savedInstanceState == null && !featureNavigationEnabled) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, SignFragment())
                .commit()
        }

    }

    override fun showMyProfileScreen() {
        replaceFragment(MyProfileFragment())
    }

    override fun showSignScreen() {
        replaceFragment(SignFragment())
    }

    override fun showAddOrEditContacts() {
        replaceFragment(AddOrEditContactsDialogFragment())
    }

    override fun showMyContacts() {
        replaceFragment(MyContactsFragment())
    }

    override fun showContactProfile(contact: Contact) {
   //     runWhenActive {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host_fragment, ContactProfileFragment.newInstance(contact.email))
                .commit()
 //       }
    }


    override fun goBack() {
        onBackPressed()
    }

    /**
     * Avoiding [IllegalStateException] if navigation action has been called after restoring app from background.
     * For example: press "Delete" button in details screen, minimize app and then restore it.
     */
    private fun runWhenActive(action: () -> Unit) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            // activity is active -> just execute the action
            action()
        } else {
            // activity is not active -> add action to queue
            actions += action
        }
    }

}

// Extension function to replace fragment
fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        .replace(R.id.nav_host_fragment, fragment)
        .addToBackStack(null)
        .commit()

}