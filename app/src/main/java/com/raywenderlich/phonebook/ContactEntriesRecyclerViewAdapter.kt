package com.raywenderlich.phonebook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ContactEntriesRecyclerViewAdapter:
    RecyclerView.Adapter<ContactEntriesViewHolder>() {


    var contactEntries = arrayOf(
        arrayOf("Demby", "Abella", "09176799255"),
        arrayOf("Chester", "Abreu", "09176799255"),
        arrayOf("Gel", "Dumoran", "09176799255"),
        arrayOf("Paolo", "Aayan", "09176799255")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactEntriesViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_view_holder, parent, false)

        return ContactEntriesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return contactEntries.size
    }

    override fun onBindViewHolder(holder: ContactEntriesViewHolder, position: Int) {
        if (holder != null) {
            holder.contactFirstName.text = contactEntries[position][0]
            holder.contactLastName.text = contactEntries[position][1]
            holder.contactNumber.text = contactEntries[position][2]
        }
    }

}