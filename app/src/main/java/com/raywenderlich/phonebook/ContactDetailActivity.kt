package com.raywenderlich.phonebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ContactDetailActivity : AppCompatActivity() {

    lateinit var contact: ContactList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        // Title of this activity should be the mobile number?
        contact = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = contact.firstName
    }
}
