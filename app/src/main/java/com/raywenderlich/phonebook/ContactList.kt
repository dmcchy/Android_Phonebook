package com.raywenderlich.phonebook

// Holds data.
// First 3 arguments are the data,
// "Interactions" contain the texts/calls made
class ContactList (val mobileNumber: String,
                val lastName: String,
                val firstName: String,
                val interactions: ArrayList<String> = ArrayList<String>()
) {

}