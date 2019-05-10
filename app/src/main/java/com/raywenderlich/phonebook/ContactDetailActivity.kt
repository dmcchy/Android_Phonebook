package com.raywenderlich.phonebook

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.widget.EditText

class ContactDetailActivity : AppCompatActivity() {

    lateinit var contact: ContactList
    lateinit var contactInteractionsRecyclerView: RecyclerView
    lateinit var addInteractionButton: FloatingActionButton

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, contact)

        // Log.v("Hello with size", contact.interactions.size.toString())

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        addInteractionButton = findViewById(R.id.add_interaction_button)
        addInteractionButton.setOnClickListener {
            showCreateInteractionDialog()
        }

        // Title of this activity should be the mobile number?
        contact = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = contact.firstName

        contactInteractionsRecyclerView = findViewById(R.id.contact_interactions_recyclerview)
        contactInteractionsRecyclerView.adapter = ContactInteractionsRecyclerViewAdapter(contact)
        contactInteractionsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showCreateInteractionDialog() {
        val interactionEditText = EditText(this)
        interactionEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.interactions_to_add)
            .setView(interactionEditText)
            .setPositiveButton(R.string.add_interaction) {dialog, _ ->
                val interaction = interactionEditText.text.toString()
                contact.interactions.add(interaction)

                val recyclerViewAdapter = contactInteractionsRecyclerView.adapter as
                        ContactInteractionsRecyclerViewAdapter
                recyclerViewAdapter.notifyItemInserted(contact.interactions.size)

                dialog.dismiss()
            }
            .create()
            .show()
    }
}
