package org.gbu.restaurant.decompose.contactsinfo

import org.gbu.restaurant.viewmodels.ContactsViewModel

interface ContactInfoComponent {

    val contactsViewModel: ContactsViewModel

    fun onOtpSent()

}