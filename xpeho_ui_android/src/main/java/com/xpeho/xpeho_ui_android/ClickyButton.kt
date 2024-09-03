package com.xpeho.xpeho_ui_android

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xpeho.xpeho_ui_android.foundations.Colors
import java.util.concurrent.CancellationException

@Composable
fun ClickyButton(
    enabled: Boolean = true,
    pressed: Boolean = false,
    color: Color = Colors.xpehoColor,
    onPress: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val touchedDown = remember { mutableStateOf(false) }

    var backgroundColor = color

    var topPadding = 0.dp
    var bottomBorder = 6.dp

    if (touchedDown.value || pressed) {
        topPadding = 4.dp
        bottomBorder = 2.dp
    }

    if (!enabled) {
        backgroundColor = Color.LightGray
    }

    Surface(
        modifier = Modifier
            .width(195.dp)
            .height(38.dp)
            .padding(top = topPadding)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (!enabled) return@detectTapGestures
                        touchedDown.value = true

                        val released = try {
                            tryAwaitRelease()
                        } catch (c: CancellationException) {
                            Log.e("ClickyButton", "Canceled")
                            false
                        }
                        if (released) {
                            // ACTION_UP
                            touchedDown.value = false
                            onPress()
                        } else {
                            // CANCELED
                            touchedDown.value = false
                        }
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = backgroundColor)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.Black.copy(alpha = 0.15f))
                    .padding(
                        top = 2.dp,
                        bottom = bottomBorder,
                        start = 2.dp,
                        end = 2.dp
                    )
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(color = backgroundColor)
                        .padding(top = 6.dp, bottom = 6.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        content()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClickyButtonPreview() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ClickyButton(color = Color.Red) {
                CustomText(text = "Annuler", color = Color.White)
            }
            Box(modifier = Modifier.height(16.dp))
            ClickyButton(
                onPress = { /*TODO*/ }
            ) {
                CustomText(text = "Consulter", color = Color.White)
            }
            Box(modifier = Modifier.height(16.dp))
            ClickyButton(pressed = true) {
                CustomText(text = "Pressed", color = Color.White)
            }
            Box(modifier = Modifier.height(16.dp))
            ClickyButton(enabled = false) {
                CustomText(text = "Disabled", color = Color.Gray)
            }
            Box(modifier = Modifier.height(16.dp))
            ClickyButton(enabled = false, pressed = true) {
                CustomText(text = "Disapress", color = Color.Gray)
            }
        }
    }
}