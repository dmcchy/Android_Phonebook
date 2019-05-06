package com.raywenderlich.phonebook

import android.os.Parcel
import android.os.Parcelable

// Holds data.
// First 3 arguments are the data,
// "Interactions" contain the texts/calls made
class ContactList (
    val mobileNumber: String,
    val lastName: String,
    val firstName: String,
    val interactions: ArrayList<String> = ArrayList<String>()): Parcelable
{
    constructor(source: Parcel) : this(
        // Wow, so depending on the number of args, in this object
        // will determine the number of string source readStrings?
        source.readString(),
        source.readString(),
        source.readString(),
        source.createStringArrayList()
    ) {
        
    }

    override fun writeToParcel(desc: Parcel, flags: Int) {
        desc.writeString(mobileNumber)
        desc.writeString(lastName)
        desc.writeString(firstName)
        desc.writeStringList(interactions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactList> {
        override fun createFromParcel(parcel: Parcel): ContactList = ContactList(parcel)
        override fun newArray(size: Int): Array<ContactList?> = arrayOfNulls(size)
    }

}