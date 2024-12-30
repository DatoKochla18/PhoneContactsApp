package com.example.test.ContanctListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.Model.Contact
import com.example.test.databinding.ItemContanctBinding

class ContactListAdapter(
    val onEdit: (Contact) -> Unit,
    val onDetail: (Contact) -> Unit
) :
    ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(contactListDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            ItemContanctBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ContactViewHolder(private val binding: ItemContanctBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val contact = getItem(adapterPosition)

            binding.apply {
                txtname.text = contact.name
                txtphoneNumber.text = contact.phoneNumber

                imbEdit.setOnClickListener {
                    onEdit(contact)
                }

                root.setOnClickListener {
                    onDetail(contact)
                }
            }
        }
    }

}