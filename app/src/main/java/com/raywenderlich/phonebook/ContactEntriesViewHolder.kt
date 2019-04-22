package com.raywenderlich.phonebook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ContactEntriesViewHolder (itemView: View):
    RecyclerView.ViewHolder(itemView) {

    // These need to be text views.
    val contactNumber = itemView.findViewById<TextView>(R.id.contactNumber)
        as TextView

    val contactFirstName = itemView.findViewById<TextView>(R.id.contactFirstName)
        as TextView

    val contactLastName = itemView.findViewById<TextView>(R.id.contactLastName)
        as TextView
}