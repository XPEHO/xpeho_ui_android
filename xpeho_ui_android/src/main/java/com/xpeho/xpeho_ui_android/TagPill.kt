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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun TagPill(
    label: String,
    size: TextUnit = 10.sp,
    backgroundColor: Color = Colors.GREEN_DARK_COLOR,
    labelColor: Color = Color.White
) {
    return Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),

    ) {
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = label.uppercase(),
                fontSize = size,
                fontFamily = Fonts.rubik,
                fontWeight = FontWeight.Medium,
                color = labelColor,
            )
        }
    }
}

@Preview
@Composable
fun TagPillPreview() {
    Surface {
        Box(modifier = Modifier.padding(8.dp)) {
            Row {
                TagPill("protrait chinois")
                Box(modifier = Modifier.width(4.dp))
                TagPill("velo")
                Box(modifier = Modifier.width(4.dp))
                TagPill(
                    "stress",
                    backgroundColor = Colors.XPEHO_COLOR,
                )
            }
        }
    }
}