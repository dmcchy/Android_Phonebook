package com.raywenderlich.phonebook

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log

// Context is the current state of the application,
// in this case we use context to fetch our sharedPreferences
class ContactDataManager(val context: Context) {

    fun saveList(contact: ContactList) {
        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
            .edit()

        val key = contact.mobileNumber + ',' + contact.firstName + ',' + contact.lastName

        Log.v("Hello r length is B: ", contact.interactions.size.toString())

        sharedPreferences.putStringSet(key, contact.interactions.toHashSet())
        sharedPreferences.apply()
    }

    // : ArrayList<ContactList>
    fun readLists(): ArrayList<ContactList> {
        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
        val sharedPreferencesContents = sharedPreferences.all

        // Log.v("ContactDataManager", sharedPreferencesContents.size.toString())

        val contactLists = ArrayList<ContactList>()

        for (contactList in sharedPreferencesContents) {

            // Log.v("ContactDataManager", contactList.value.toString())

            val contactListDetails = contactList.key.split(',')

            val firstName = contactListDetails[1]
            val lastName = contactListDetails[2]
            val mobileNumber = contactListDetails[0]

            val itemsHashList = contactList.value as HashSet<String>
            val contactList = ContactList(mobileNumber, lastName, firstName, ArrayList(itemsHashList) )

            contactLists.add(contactList)
        }

        return contactLists
    }
}