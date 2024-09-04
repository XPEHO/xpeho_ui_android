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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts
import java.util.concurrent.CancellationException

@Composable
fun ClickyButton(
    label: String,
    size: TextUnit = 14.sp,
    backgroundColor: Color = Colors.XPEHO_COLOR,
    labelColor: Color = Color.White,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 16.dp,
    enabled: Boolean = true,
    onPress: () -> Unit = {},
) {

    val touchedDown = remember { mutableStateOf(false) }

    var color = backgroundColor

    var topPadding = 0.dp
    var bottomBorder = 6.dp

    if (touchedDown.value) {
        topPadding = 4.dp
        bottomBorder = 2.dp
    }

    if (!enabled) {
        color = Color.LightGray
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
                .background(color = color)
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
                        .background(color = color)
                        .padding(
                            top = verticalPadding,
                            bottom = verticalPadding,
                            start = horizontalPadding,
                            end = horizontalPadding
                        )
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            label.uppercase(),
                            fontSize = size,
                            fontFamily = Fonts.rubik,
                            fontWeight = FontWeight.Medium,
                            color = labelColor,
                        )
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
            ClickyButton(
                "Annuler",
                backgroundColor = Color.Red
            )
            Box(modifier = Modifier.height(16.dp))
            ClickyButton(
                "Consulter",
                onPress = { /*TODO*/ }
            )
            Box(modifier = Modifier.height(16.dp))
            ClickyButton("Disabled", enabled = false)
        }
    }
}