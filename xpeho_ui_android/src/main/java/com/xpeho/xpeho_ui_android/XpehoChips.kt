package com.xpeho.xpeho_ui_android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.XpehoUIColors

@Composable
fun XpehoChips(content: @Composable () -> Unit) {
    return Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = XpehoUIColors.chipsColor,
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
fun XpehoChipsPreview() {
    Surface {
        Box(modifier = Modifier.padding(8.dp)) {
            Row {
                XpehoChips {
                    XpehoText(
                        text = "protrait chinois",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
                Box(modifier = Modifier.width(4.dp))
                XpehoChips {
                    XpehoText(
                        text = "velo",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
                Box(modifier = Modifier.width(4.dp))
                XpehoChips {
                    XpehoText(
                        text = "stress",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}