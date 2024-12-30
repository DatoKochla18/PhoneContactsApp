package com.example.test.ContanctListScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.AddEditContactScreen.AddEditContactFragment
import com.example.test.Model.Contact
import com.example.test.ContactDetailScreen.ContactDetailFragment
import com.example.test.R
import com.example.test.Utils.getContactData
import com.example.test.databinding.FragmentContactListBinding


class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val contactData = getContactData()

    private val contactListAdapter by lazy {
        ContactListAdapter(onEdit = { contact ->
            editContact(contact = contact)
        },
            onDetail = { contact ->
                detailContact(contact)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNewContact()
        updateNewContact()
        removeNewContract()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()
    }

    private fun setUp() {
        binding.apply {
            rvContanct.adapter = contactListAdapter
            rvContanct.layoutManager = LinearLayoutManager(requireContext())
            contactListAdapter.submitList(contactData.toList())
        }

    }

    private fun listeners() {
        binding.apply {
            btnAddContact.setOnClickListener {
                parentFragmentManager.beginTransaction().run {
                    replace(R.id.fcvContainer, AddEditContactFragment.newInstance())
                    addToBackStack(null)
                    commit()
                }
            }

            imgSearch.setOnClickListener {
                etSearchText.visibility =
                    if (etSearchText.visibility == View.GONE) View.VISIBLE else View.GONE
            }

            etSearchText.doAfterTextChanged {
                searchContacts()
            }
        }
    }

    private fun searchContacts() {
        val text = binding.etSearchText.text.toString()
        val filteredList = contactData.filter { it.name.lowercase().contains(text.lowercase()) }

        contactListAdapter.submitList(filteredList)
    }

    private fun editContact(contact: Contact) {

        parentFragmentManager.beginTransaction().run {
            replace(R.id.fcvContainer, AddEditContactFragment.newInstance(contact))
            addToBackStack(null)
            commit()
        }
    }

    private fun detailContact(contact: Contact) {

        parentFragmentManager.beginTransaction().run {
            replace(R.id.fcvContainer, ContactDetailFragment.newInstance(contact))
            addToBackStack("Detail")
            commit()
        }
    }


    private fun addNewContact() {
        parentFragmentManager.setFragmentResultListener("newContact", this) { _, bundle ->
            val contact = bundle.getParcelable<Contact>("contact")
            contact?.let {
                contactData.add(0, it)
            }
            contactListAdapter.submitList(contactData.toList())

        }
    }

    private fun updateNewContact() {
        parentFragmentManager.setFragmentResultListener("updatedContact", this) { _, bundle ->
            val contact = bundle.getParcelable<Contact>("contact")!!
            val idx = contactData.indexOfFirst { it.id == contact.id }
            contactData[idx] = contact
            contactListAdapter.submitList(contactData.toList())
        }
    }

    private fun removeNewContract() {

        parentFragmentManager.setFragmentResultListener("removeContact", this) { _, bundle ->
            val contactId = bundle.getString("contact")
            val idx = contactData.indexOfFirst { it.id == contactId }
            contactData.removeAt(idx)
            contactListAdapter.submitList(contactData.toList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}