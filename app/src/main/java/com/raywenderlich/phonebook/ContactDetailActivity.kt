package com.raywenderlich.phonebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText

class ContactDetailActivity : AppCompatActivity() {

    lateinit var contact: ContactList
    lateinit var contactEntriesRecyclerView: RecyclerView
    lateinit var addInteractionButton: FloatingActionButton

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

        contactEntriesRecyclerView = findViewById<RecyclerView>(R.id.contact_interactions_recyclerview)
        contactEntriesRecyclerView.adapter = ContactInteractionsRecyclerViewAdapter(contact)
        contactEntriesRecyclerView.layoutManager = LinearLayoutManager(this)
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

                val recyclerViewAdapter = contactEntriesRecyclerView.adapter as
                        ContactInteractionsRecyclerViewAdapter
                recyclerViewAdapter.notifyItemInserted(contact.interactions.size)

                dialog.dismiss()
            }
            .create()
            .show()
    }
}
