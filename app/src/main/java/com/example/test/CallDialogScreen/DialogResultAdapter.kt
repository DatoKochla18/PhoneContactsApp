package com.example.test.CallDialogScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ContanctListScreen.contactListDiffUtil
import com.example.test.Model.Contact
import com.example.test.databinding.ItemDialogResultBinding

class DialogResultAdapter(val onClick: (Contact) -> Unit) :
    ListAdapter<Contact, DialogResultAdapter.DialogResultViewHolder>(
        contactListDiffUtil
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogResultViewHolder {
        val binding =
            ItemDialogResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogResultViewHolder, position: Int) {
        holder.onBind()
    }

    inner class DialogResultViewHolder(private val binding: ItemDialogResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val contact = getItem(adapterPosition)
            binding.apply {
                txtName.text = contact.name
                txtPhoneNumber.text = contact.phoneNumber
                root.setOnClickListener {
                    onClick(contact)
                }
            }


        }
    }
}