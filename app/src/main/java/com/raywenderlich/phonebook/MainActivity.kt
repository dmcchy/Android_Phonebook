package com.raywenderlich.phonebook

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val contactDataManager: ContactDataManager = ContactDataManager(this)

    private lateinit var contactEntriesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()

            showCreateListDialogue()
        }

        // Initialize the RecyclerView here.
        contactEntriesRecyclerView = findViewById(R.id.contacts_recyclerview)
        contactEntriesRecyclerView.layoutManager = LinearLayoutManager(this)
        contactEntriesRecyclerView.adapter = ContactEntriesRecyclerViewAdapter()

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

//    private fun showCreateListDialogue_OLD() {
//        val dialogTitle = "Contact Details"
//        val positiveButtonTitle = "Create"
//
//        val builder = AlertDialog.Builder(this)
//        val listTitleEditText = EditText(this)
//
//        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT
//
//        builder.setTitle(dialogTitle)
//        builder.setView(listTitleEditText)
//
//        builder.setPositiveButton(positiveButtonTitle) {dialog, i ->
//
//        }
//
//        builder.create().show()
//
//        // New one - to add multiple fields.
//        val linearLayout = LinearLayout(this)
//
//        val listTitleEditText2 = EditText(this)
//        val listTitleEditText3 = EditText(this)
//
//        linearLayout
//
//    }

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

        builder.setPositiveButton(positiveButtonTitle) {dialog, i ->
            // Here is where I put the listener once I click my button
            // I will put the items inside a contact list.
            var contact = ContactList(
                mobileNumberText.text.toString(),
                lastNameText.text.toString(),
                firstNameText.text.toString()
            )

            contactDataManager.saveList(contact)

            // Log.v(TAG, mobileNumberText.text.toString())
            // Log.v(TAG, lastNameText.text.toString())
            // Log.v(TAG, firstNameText.text.toString())
        }

        builder.create().show()

    }
}
