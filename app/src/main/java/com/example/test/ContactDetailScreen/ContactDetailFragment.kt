package com.example.test.ContactDetailScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test.AddEditContactScreen.AddEditContactFragment
import com.example.test.Model.Contact
import com.example.test.R
import com.example.test.Utils.CONTACT
import com.example.test.Utils.EDIT
import com.example.test.Utils.REMOVE_CONTACT
import com.example.test.databinding.FragmentContactDetailBinding

class ContactDetailFragment : Fragment() {

    private val args: ContactDetailFragmentArgs by navArgs()
    private lateinit var contact: Contact
    private var _binding: FragmentContactDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = args.contact

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()
    }

    private fun setUp() {
        binding.apply {
            txtName.text = contact.name
            txtMobileValue.text = contact.phoneNumber
        }
    }

    private fun listeners() {
        binding.apply {
            btnRemove.setOnClickListener {
                val bundle = bundleOf(CONTACT to contact?.id)

                parentFragmentManager.run {
                    setFragmentResult(REMOVE_CONTACT, bundle)
                    parentFragmentManager.popBackStack()
                }

            }
            btnEdit.setOnClickListener {
                findNavController().navigate(
                    ContactDetailFragmentDirections.actionContactDetailFragmentToAddEditContactFragment(
                        contact = contact
                    ),
                    NavOptions.Builder()
                        .setPopUpTo(R.id.contactDetailFragment, true)
                        .build()
                )


            }
            btnArrowBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }
}