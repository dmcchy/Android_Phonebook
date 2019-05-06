package com.raywenderlich.phonebook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ContactInteractionsRecyclerViewAdapter(val contact: ContactList):
    RecyclerView.Adapter<ContactInteractionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, holder: Int): ContactInteractionViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.interactions_view_holder, parent, false)

        return ContactInteractionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contact.interactions.size
    }

    override fun onBindViewHolder(holder: ContactInteractionViewHolder, position: Int) {
        if (holder != null) {
            holder.contactInteractionView.text = contact.interactions[position]
        }
    }
}