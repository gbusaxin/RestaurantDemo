package org.gbu.restaurant.decompose.menudetail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.MenuDetailsViewModel

class MenuDetailComponentImpl(
    private val componentContext: ComponentContext,
    private val onBackClicked: () -> Unit,
    override val menuItemId: Long
) : MenuDetailComponent, ComponentContext by componentContext {

    override val viewModel: MenuDetailsViewModel
        get() = instanceKeeper.getOrCreate { MenuDetailsViewModel() }

    override fun onBack() {
        onBackClicked()
    }
}