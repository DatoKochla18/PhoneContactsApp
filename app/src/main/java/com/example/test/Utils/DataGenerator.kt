package com.example.test.Utils

import com.example.test.Model.Contact
import com.example.test.Model.Dialog

fun getContactData(): MutableList<Contact> {
    return mutableListOf(
        Contact(name = "Dato", phoneNumber = "591167223", img = null),
        Contact(name = "Gio", phoneNumber = "557745906", img = null),
        Contact(name = "Elene", phoneNumber = "599806781", img = null),
        Contact(name = "Lizi", phoneNumber = "593140380", img = null),
        Contact(name = "Mamuka", phoneNumber = "557410317", img = null)
    )
}

fun getDialogData(): MutableList<Dialog> {
    return mutableListOf(

        Dialog("1", ""),
        Dialog("2", "ABC"),
        Dialog("3", "DEF"),
        Dialog("4", "GHI"),
        Dialog("5", "JKL"),
        Dialog("6", "MNO"),
        Dialog("7", "PQRS"),
        Dialog("8", "TUV"),
        Dialog("9", "WXYZ"),
        Dialog("#", ""),
        Dialog("0", "+"),
        Dialog("*", "")
    )
}