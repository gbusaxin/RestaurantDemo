package org.gbu.restaurant.decompose.contactsinfo

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.ContactsViewModel

class ContactInfoComponentImpl(
    private val componentContext: ComponentContext,
    private val onOtpSentToUser: () -> Unit
) : ContactInfoComponent, ComponentContext by componentContext {

    override val contactsViewModel: ContactsViewModel
        get() = instanceKeeper.getOrCreate { ContactsViewModel() }

    override fun onOtpSent() {
        onOtpSentToUser()
    }

}