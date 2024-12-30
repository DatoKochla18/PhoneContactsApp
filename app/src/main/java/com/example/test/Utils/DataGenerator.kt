package com.example.test.Utils

import com.example.test.Model.Contact

fun getContactData(): MutableList<Contact> {
    return mutableListOf(
        Contact(name = "Dato", phoneNumber = "591167223", img = null),
        Contact(name = "Gio", phoneNumber = "557745906", img = null),
        Contact(name = "Elene", phoneNumber = "599806781", img = null),
        Contact(name = "Lizi", phoneNumber = "593140380", img = null),
        Contact(name = "Mamuka", phoneNumber = "557410317", img = null)
    )
}