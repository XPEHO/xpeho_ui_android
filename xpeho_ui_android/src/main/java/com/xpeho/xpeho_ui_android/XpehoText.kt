package com.xpeho.xpeho_ui_android

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.XpehoUIFonts

@Composable
fun XpehoText(
    text: String,
    color: Color,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    capitalize: Boolean = true
) {
    var textValue = text

    if (capitalize) {
        textValue = text.uppercase()
    }

    val fontFamilyValue = when (fontWeight) {
        FontWeight.Normal ->  XpehoUIFonts.rubikRegular
        FontWeight.Medium ->  XpehoUIFonts.rubikMedium
        FontWeight.SemiBold ->  XpehoUIFonts.rubikSemiBold
        FontWeight.Bold ->  XpehoUIFonts.rubikBold
        FontWeight.ExtraBold ->  XpehoUIFonts.rubikExtraBold
        else ->  XpehoUIFonts.rubikMedium
    }

    Text(
        text = textValue,
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamilyValue,
    )
}

@Preview
@Composable
fun XpehoTextPreview() {
    Surface {
        XpehoText(
            text = "Hello Android",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
        )
    }
}