package com.example.test.AddEditContactScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.example.test.Model.Contact
import com.example.test.Utils.CONTACT
import com.example.test.Utils.DETAIL
import com.example.test.Utils.NEW_CONTACT
import com.example.test.Utils.UPDATED_CONTACT
import com.example.test.databinding.FragmentAddContactBinding


class AddEditContactFragment : Fragment() {
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    private var contact: Contact? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)
        contact = arguments?.getParcelable(CONTACT)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        listeners()


    }

    private fun setUp() {
        contact?.let {

            binding.etName.setText(it.name)
            binding.etPhoneNumber.setText(it.phoneNumber)

        }

    }

    private fun listeners() {
        binding.btnConfirm.setOnClickListener {
            contact?.let {
                editContact(it)
            } ?: addContact()
        }

    }

    private fun editContact(contact: Contact) {
        val name: String = binding.etName.text.toString()
        val phoneNumber: String = binding.etPhoneNumber.text.toString()

        val editedContact =
            Contact(id = contact.id, name = name, phoneNumber = phoneNumber, img = null)
        val bundle = bundleOf(CONTACT to editedContact)

        parentFragmentManager.run {
            setFragmentResult(UPDATED_CONTACT, bundle)
            popBackStack()
            popBackStack(DETAIL, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun addContact() {

        val name: String = binding.etName.text.toString()
        val phoneNumber: String = binding.etPhoneNumber.text.toString()

        val contact = Contact(name = name, phoneNumber = phoneNumber, img = null)
        val bundle = bundleOf(CONTACT to contact)

        parentFragmentManager.setFragmentResult(NEW_CONTACT, bundle)
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(contact: Contact? = null) = AddEditContactFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CONTACT, contact)
            }
        }
    }
}