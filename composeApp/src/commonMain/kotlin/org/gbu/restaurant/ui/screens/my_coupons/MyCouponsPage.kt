package org.gbu.restaurant.ui.screens.my_coupons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.local.entity.Coupon
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.noRippleClickable
import org.gbu.restaurant.ui.screens.my_coupons.viewmodel.MyCouponsEvent
import org.gbu.restaurant.ui.screens.my_coupons.viewmodel.MyCouponsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.best_offer
import restaurantdemo.composeapp.generated.resources.copy_code
import restaurantdemo.composeapp.generated.resources.coupon_icon
import restaurantdemo.composeapp.generated.resources.get_off
import restaurantdemo.composeapp.generated.resources.my_coupons

@Composable
fun MyCouponsPage(
    state: MyCouponsState,
    errors: Flow<UIComponent>,
    events: (MyCouponsEvent) -> Unit,
    popUp: () -> Unit
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(MyCouponsEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.my_coupons),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popUp
    ) {
        Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(Res.string.best_offer),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(8.dp))

            LazyColumn(Modifier.fillMaxSize()) {
                items(state.coupons) {
                    Coupon(it){
                        clipboardManager.setText(AnnotatedString(it.code))
                    }
                }
            }
        }
    }
}

@Composable
fun Coupon(
    coupon: Coupon,
    onExecuteCopyCode: () -> Unit
){
    Box(Modifier.fillMaxWidth().padding(vertical = 8.dp)){
        Box(Modifier.fillMaxWidth().height(180.dp).border(1.dp, Color.DarkGray, MaterialTheme.shapes.medium)){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = coupon.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = coupon.desc,
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    Icon(
                        painter = painterResource(Res.drawable.coupon_icon),
                        contentDescription = "coupon icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(Res.string.get_off, coupon.offPercent),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(Color.DarkGray)
                    .noRippleClickable { onExecuteCopyCode() },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = stringResource(Res.string.copy_code),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}