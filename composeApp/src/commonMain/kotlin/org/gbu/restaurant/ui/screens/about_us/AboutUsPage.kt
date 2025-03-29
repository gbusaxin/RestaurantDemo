package org.gbu.restaurant.ui.screens.about_us

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.common.MapComponent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.app_description
import restaurantdemo.composeapp.generated.resources.app_name
import restaurantdemo.composeapp.generated.resources.hot_food_icon

@Composable
fun AboutUsPage() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(Res.drawable.hot_food_icon),
            contentDescription = "application image",
            modifier = Modifier.size(120.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(Res.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(Res.string.app_description),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))


    }
}

@Composable
private fun ContactInfoItem(label: String, value: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = label, style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.width(8.dp))
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun ClickableContactItem(label: String, value: String, url: String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable { /*TODO()*/ }
    ) {
        Text(text = label, color = Color.Blue, textDecoration = TextDecoration.Underline)
    }
}

@Composable
private fun MapViewComponent(modifier: Modifier = Modifier, context: Context,){
//    MapComponent()
}












