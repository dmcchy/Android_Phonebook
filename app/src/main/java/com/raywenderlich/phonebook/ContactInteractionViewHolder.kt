package com.raywenderlich.phonebook

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ContactInteractionViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {

    val contactInteractionView = itemView.findViewById<TextView>(R.id.textview_interaction)
        as TextView
}