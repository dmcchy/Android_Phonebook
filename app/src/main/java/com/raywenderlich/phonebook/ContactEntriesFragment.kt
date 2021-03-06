package com.raywenderlich.phonebook

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ContactEntriesFragment : Fragment(),
    ContactEntriesRecyclerViewAdapter.ContactEntriesRecyclerViewClickListener
{

    // Transfer your data manager and recyclerview here.
    lateinit var contactDataManager: ContactDataManager
    lateinit var contactEntriesRecyclerView: RecyclerView

    private var listener: OnFragmentInteractionListener? = null

    override fun contactItemClicked(contact: ContactList) {
        listener?.onContactItemClicked(contact)
    }

    fun saveInteractions(contact: ContactList) {
        contactDataManager.saveList(contact)
        updateContactEntries()
    }

    fun updateContactEntries() {
        val contactEntries = contactDataManager.readLists()
        contactEntriesRecyclerView.adapter = ContactEntriesRecyclerViewAdapter(
            contactEntries,
            this
        )
    }

    fun addContact(contact: ContactList) {
        contactDataManager.saveList(contact)

        val recyclerViewAdapter = contactEntriesRecyclerView.adapter as
                ContactEntriesRecyclerViewAdapter
        recyclerViewAdapter.addContact(contact)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize data here?
        val contacts = contactDataManager.readLists()

        view?.let {
            contactEntriesRecyclerView = it.findViewById(R.id.contacts_recyclerview)
            contactEntriesRecyclerView.layoutManager = LinearLayoutManager(activity)
            contactEntriesRecyclerView.adapter = ContactEntriesRecyclerViewAdapter(contacts, this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context

            // I think this is the first lifecycle
            contactDataManager = ContactDataManager(context)
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onContactItemClicked(contact: ContactList)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ContactEntriesFragment().apply {

            }
    }
}
