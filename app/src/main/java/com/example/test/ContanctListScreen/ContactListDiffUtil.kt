package com.example.test.ContanctListScreen

import androidx.recyclerview.widget.DiffUtil
import com.example.test.Model.Contact

val contactListDiffUtil = object : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}