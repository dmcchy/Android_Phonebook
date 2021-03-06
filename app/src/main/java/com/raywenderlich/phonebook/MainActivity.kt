package com.raywenderlich.phonebook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ContactEntriesFragment.OnFragmentInteractionListener,
        ContactInteractionsFragment.OnFragmentInteractionListener
{

    private var largeScreen = false

    private var contactEntriesFragment: ContactEntriesFragment =
        ContactEntriesFragment.newInstance()
    private var contactInteractionsFragment: ContactInteractionsFragment? = null

    companion object {
        val INTENT_LIST_KEY = "list"
        val CONTACT_INTERACTION_REQUEST_CODE = 123
    }

    private val TAG = "MainActivity"
    private val contactDataManager: ContactDataManager = ContactDataManager(this)

    private var fragmentContainer: FrameLayout? = null
    private lateinit var contactEntriesRecyclerView: RecyclerView

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // Persist the new data here.
        title = resources.getString(R.string.app_name)

        // I got it, tell my new fragment that I launched to save it.
        Log.v("HEllo", "lol")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CONTACT_INTERACTION_REQUEST_CODE) {

            data?.let {
                Log.v("Hello", "Must be here")
                // Wrap it in a parcelable entry.
                var contact: ContactList = data.getParcelableExtra(INTENT_LIST_KEY)

                contactEntriesFragment.saveInteractions(contact)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // How do I load the fragments?
        // fragmentContainer = findViewById(R.id.fragment_container)

        contactEntriesFragment = supportFragmentManager.findFragmentById(R.id.contact_entries_fragment)
            as ContactEntriesFragment

        fragmentContainer = findViewById(R.id.fragment_container)

        largeScreen = fragmentContainer != null
        /*supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container,
                contactEntriesFragment,
                getString(R.string.list_fragment_tag)
            )
            .addToBackStack(null)
            .commit()*/


        fab.setOnClickListener { view ->
            showCreateListDialogue()
        }

        // Initialize the RecyclerView here.
//        val contacts = contactDataManager.readLists()
//
//        contactEntriesRecyclerView = findViewById(R.id.contacts_recyclerview)
//        contactEntriesRecyclerView.layoutManager = LinearLayoutManager(this)
//        contactEntriesRecyclerView.adapter = ContactEntriesRecyclerViewAdapter(contacts, this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialogue() {
        val dialogTitle = "Contact Details"
        val positiveButtonTitle = "Create"

        val builder = AlertDialog.Builder(this)

        // New one - to add multiple fields.
        val linearLayout = LinearLayout(this)

        linearLayout.orientation = LinearLayout.VERTICAL

        val mobileNumberText = EditText(this)
        val lastNameText = EditText(this)
        val firstNameText = EditText(this)

        mobileNumberText.inputType = InputType.TYPE_CLASS_TEXT
        mobileNumberText.hint = "Mobile Number"

        lastNameText.inputType = InputType.TYPE_CLASS_TEXT
        lastNameText.hint = "Last Name"

        firstNameText.inputType = InputType.TYPE_CLASS_TEXT
        firstNameText.hint = "First Name"

        linearLayout.addView(mobileNumberText)
        linearLayout.addView(lastNameText)
        linearLayout.addView(firstNameText)

        builder.setTitle(dialogTitle)
        builder.setView(linearLayout)


        /**
         * This is the modal function which serves 2 purposes:
         * 1. Save the new entries in the recyclerViewAdapter so it can be immediately rendered on the screen.
         * 2. Save the new entries in the context so it can be persisted on the next reload.
         */
        builder.setPositiveButton(positiveButtonTitle) {dialog, i ->
            // Hold off here.
            var contact = ContactList(
                mobileNumberText.text.toString(),
                lastNameText.text.toString(),
                firstNameText.text.toString()
            )

            contactEntriesFragment.addContact(contact)
            dialog.dismiss()

            // Add list later.
        }

        builder.create().show()
    }

    private fun showContactDetail(contact: ContactList) {

        var isNotLargeScreen = !largeScreen

        // Override to always show small screen if uncommented.
        // isNotLargeScreen = !largeScreen || true

        if (isNotLargeScreen) {
            val contactDetailIntent = Intent(this, ContactDetailActivity::class.java)

            contactDetailIntent.putExtra(INTENT_LIST_KEY, contact)

            startActivityForResult(contactDetailIntent, CONTACT_INTERACTION_REQUEST_CODE)
        } else {
            title = contact.mobileNumber

            contactInteractionsFragment = ContactInteractionsFragment.newInstance(contact)

            contactInteractionsFragment?.let { contactInteractionsFragment ->
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment_container,
                        contactInteractionsFragment,
                        getString(R.string.contact_fragment_tag)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onContactItemClicked(contact: ContactList) {
        showContactDetail(contact)
    }
}
