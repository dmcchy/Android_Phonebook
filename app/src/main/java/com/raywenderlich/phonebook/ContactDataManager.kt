package com.raywenderlich.phonebook

import android.content.Context
import android.preference.PreferenceManager

// Context is the current state of the application,
// in this case we use context to fetch our sharedPreferences
class ContactDataManager(val context: Context) {

    fun saveList(contact: ContactList) {
        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()

        sharedPreferences.putStringSet(contact.mobileNumber, contact.contacts.toHashSet())
        sharedPreferences.apply()
    }

    fun readLists() {

    }
}