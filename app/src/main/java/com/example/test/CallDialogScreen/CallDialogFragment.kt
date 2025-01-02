package com.example.test.CallDialogScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.Model.Contact
import com.example.test.Model.Dialog
import com.example.test.R
import com.example.test.Utils.Database
import com.example.test.Utils.Database.dialogData
import com.example.test.Utils.getDialogData
import com.example.test.databinding.FragmentCallDialogBinding

class CallDialogFragment : Fragment() {
    private var _binding: FragmentCallDialogBinding? = null
    private val binding get() = _binding!!

    private val dialogAdapter by lazy {
        DialogAdapter(dialogList = dialogData, onClick = { dialog ->
            addNumberToText(dialog)
        })
    }

    private val dialogResultAdapter by lazy {
        DialogResultAdapter(onClick = { contact ->
            binding.txtCallNumber.text = contact.phoneNumber
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallDialogBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()

    }

    private fun setUp() {
        setUpDialogAdapter()
        setUpDialogResultAdapter()


    }

    private fun search(text: String) {

        if (text.isEmpty()) {
            dialogResultAdapter.submitList(mutableListOf<Contact>())
        } else {
            dialogResultAdapter.submitList(
                Database.contactData.filter {
                    it.phoneNumber.contains(text)
                }
            )
        }
    }

    private fun listeners() {

        binding.btnBackSpace.apply {
            setOnClickListener {
                removeNumberToText()
            }
            setOnLongClickListener {
                binding.txtCallNumber.text = ""
                true
            }
        }
        binding.txtCallNumber.doAfterTextChanged { editText ->
            val searchText = editText?.toString() ?: ""
            search(searchText)
        }

    }

    private fun setUpDialogAdapter() {
        binding.rvDialog.apply {
            adapter = dialogAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

    }

    private fun setUpDialogResultAdapter() {
        binding.rvDialogResult.apply {
            adapter = dialogResultAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun addNumberToText(dialog: Dialog) {
        val oldText = binding.txtCallNumber.text.toString()
        val newText = oldText + dialog.id

        binding.txtCallNumber.text = newText
    }

    private fun removeNumberToText() {
        val oldText = binding.txtCallNumber.text.toString()
        if (oldText.isNotEmpty()) {
            val newText = oldText.substring(0, oldText.length - 1)
            binding.txtCallNumber.text = newText

        }
    }


}