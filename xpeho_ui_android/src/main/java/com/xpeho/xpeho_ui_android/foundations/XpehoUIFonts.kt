package com.xpeho.xpeho_ui_android.foundations

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.xpeho.xpeho_ui_android.R

class XpehoUIFonts {
    companion object {
        val rubikRegular = FontFamily(
            Font(R.font.rubik_regular, FontWeight.Normal, FontStyle.Normal)
        )
        val rubikMedium = FontFamily(
            Font(R.font.rubik_medium, FontWeight.Medium, FontStyle.Normal)
        )
        val rubikSemiBold = FontFamily(
            Font(R.font.rubik_semibold, FontWeight.SemiBold, FontStyle.Normal)
        )
        val rubikBold = FontFamily(
            Font(R.font.rubik_bold, FontWeight.Bold, FontStyle.Normal)
        )
        val rubikExtraBold = FontFamily(
            Font(R.font.rubik_extrabold, FontWeight.ExtraBold, FontStyle.Normal)
        )
    }
}