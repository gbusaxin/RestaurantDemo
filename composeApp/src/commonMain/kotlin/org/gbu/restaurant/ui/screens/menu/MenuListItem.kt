package org.gbu.restaurant.ui.screens.menu

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.data.entity.MenuItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MenuListItem(
    menuItem: MenuItem,
    onClick: (menuItem: MenuItem) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    Box(modifier = Modifier) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(35.dp),
                    clip = true,
                    ambientColor = Color(0xffCE5A01),
                    spotColor = Color(0xffCE5A01)
                )
                .background(menuItem.bgColor, RoundedCornerShape(35.dp))
                .fillMaxHeight()
                .clickable { onClick(menuItem) }
        ) {
            with(sharedTransitionScope) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = menuItem.bgColor
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(35.dp)),
                    shape = RoundedCornerShape(35.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(16.dp)
                                .fillMaxWidth(0.55f),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Bottom)
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = menuItem.name,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = menuItem.shortDesc,
                                    style = MaterialTheme.typography.displayMedium,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        MenuImageWrapper(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .fillMaxWidth(0.45f)
                                .aspectRatio(1f),
                            alignment = Alignment.BottomEnd,
                            child = {
                                MenuImage(
                                    modifier = Modifier,
                                    image = menuItem.imageLink
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
