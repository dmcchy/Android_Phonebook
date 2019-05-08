package com.raywenderlich.phonebook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ContactEntriesRecyclerViewAdapter(
    val contacts: ArrayList<ContactList>,
    val clickListener: ContactEntriesRecyclerViewClickListener // This is an interface locally declared
): RecyclerView.Adapter<ContactEntriesViewHolder>()
{


    // Unused old entries.
    var contactEntries = arrayOf(
        arrayOf("Demby", "Abella", "09176799255"),
        arrayOf("Chester", "Abreu", "09176799255"),
        arrayOf("Gel", "Dumoran", "09176799255"),
        arrayOf("Paolo", "Aayan", "09176799255")
    )

    interface ContactEntriesRecyclerViewClickListener {
        fun contactItemClicked(contact: ContactList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactEntriesViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_view_holder, parent, false)

        return ContactEntriesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactEntriesViewHolder, position: Int) {
        holder.contactFirstName.text = contacts[position].firstName
        holder.contactLastName.text = contacts[position].lastName
        holder.contactNumber.text = contacts[position].mobileNumber

        holder.itemView.setOnClickListener() {
            clickListener.contactItemClicked(contacts[position])
        }
    }

    fun addContact(contact: ContactList) {
        contacts.add(contact)

        notifyDataSetChanged()
    }

}