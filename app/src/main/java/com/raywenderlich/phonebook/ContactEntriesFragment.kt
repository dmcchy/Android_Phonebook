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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContactEntriesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContactEntriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ContactEntriesFragment : Fragment(),
    ContactEntriesRecyclerViewAdapter.ContactEntriesRecyclerViewClickListener
{
    override fun contactItemClicked(contact: ContactList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Transfer your data manager and recyclerview here.
    lateinit var contactDataManager: ContactDataManager
    lateinit var contactEntriesRecyclerView: RecyclerView

    private var listener: OnFragmentInteractionListener? = null


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
