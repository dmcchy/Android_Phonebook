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


class ContactInteractionsFragment : Fragment() {


    lateinit var contact: ContactList
    lateinit var contactInteractionsRecyclerView: RecyclerView

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contact = arguments!!.getParcelable(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_contact_interactions, container, false)

        view?.let {
            // We initialize our recycler view here VS in activity.
            contactInteractionsRecyclerView = it.findViewById(R.id.contact_interactions_recyclerview)
            contactInteractionsRecyclerView.adapter = ContactInteractionsRecyclerViewAdapter(contact)
            contactInteractionsRecyclerView.layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            // throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        // This instance expects a "contact" since it's responsible for displaying that.
        fun newInstance(contact: ContactList): ContactInteractionsFragment {
            val fragment = ContactInteractionsFragment()
            val args = Bundle()

            args.putParcelable(MainActivity.INTENT_LIST_KEY, contact)
            fragment.arguments = args

            return fragment
        }


    }
}
