package com.xpeho.xpeho_ui_android

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun CollapsableCard(
    title: String,
    date: String,
    keywords: List<String> = listOf(),
    onOpenPressed: () -> Unit = {},
    collapsed: Boolean = false,
) {

    val collapsedValue = remember { mutableStateOf(collapsed) }

    val collapseIcon = if (collapsedValue.value) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.Default.KeyboardArrowUp
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Title row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id =R.drawable.newsletter),
                        tint = Colors.XPEHO_COLOR,
                        contentDescription = "Newsletter icon",
                    )
                    Text(
                        title,
                        color = Color.Black,
                        fontFamily = Fonts.rubik,
                        fontWeight = FontWeight.Normal
                    )
                    TagPill(date)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        collapseIcon,
                        contentDescription = "Newsletter collapse icon",
                        modifier = Modifier
                            .size(24.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        collapsedValue.value = !collapsedValue.value
                                    }
                                )
                            }
                    )
                }
                // Chips row
                if(!collapsedValue.value) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        keywords.map {
                            TagPill(it, size = 10.sp)
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        ClickyButton(
                            "ouvrir",
                            onPress = onOpenPressed
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CollapsableCardPreview() {
    Surface(
        color = Color.LightGray,
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CollapsableCard(
                    title = "Newsletter #32",
                    date = "12/05/2024",
                    keywords = listOf("Portrait chinois", "vélo", "stress"),
                    collapsed = false,
                    onOpenPressed = {
                        Log.d("XpehoNewsLetterPreview", "onOpenPressed")
                    }
                )
                CollapsableCard(
                    title = "Newsletter #31",
                    date = "01/04/2024",
                    collapsed = true,
                )
                CollapsableCard(
                    title = "Newsletter #30",
                    date = "13/03/2024",
                    collapsed = true,
                )
                CollapsableCard(
                    title = "Newsletter #29",
                    date = "05/02/2024",
                    collapsed = true,
                )
            }
        }
    }
}