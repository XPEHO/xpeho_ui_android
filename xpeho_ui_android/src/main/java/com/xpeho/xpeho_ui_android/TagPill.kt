package com.xpeho.xpeho_ui_android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors

@Composable
fun TagPill(content: @Composable () -> Unit) {
    return Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Colors.tagPillColor,
        ),

    ) {
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun TagPillPreview() {
    Surface {
        Box(modifier = Modifier.padding(8.dp)) {
            Row {
                TagPill {
                    CustomText(
                        text = "protrait chinois",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
                Box(modifier = Modifier.width(4.dp))
                TagPill {
                    CustomText(
                        text = "velo",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
                Box(modifier = Modifier.width(4.dp))
                TagPill {
                    CustomText(
                        text = "stress",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}