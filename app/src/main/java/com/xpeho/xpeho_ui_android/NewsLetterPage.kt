package com.xpeho.xpeho_ui_android

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun NewsLetterPage(
    newsLetterList: List<NewsLetter> = emptyList()
) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            newsLetterList.mapIndexed { index, newsLetter ->
                item {
                    val collapsed = index > 0
                    val context = LocalContext.current
                    val intent = remember {
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(newsLetter.link)
                        }
                    }

                    CollapsibleCard(
                        title = newsLetter.title,
                        date = formatDate(newsLetter.date),
                        keywords = newsLetter.keywords,
                        collapsed = collapsed,
                        onOpenPressed = {
                            Log.d("XpehoNewsLetterPreview", "onOpenPressed")
                            context.startActivity(intent)
                        }
                    )
                }
            }

        }
    }
}

fun formatDate(date: Date?): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(date)
}


data class NewsLetter(
    val title: String,
    val date: Date,
    val keywords: List<String>,
    val link : String
)

@Preview
@Composable
fun NewsLetterPagePreview() {

    val newsLetterList = (1..32).toList().map {

        val date = Calendar.getInstance()

        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) - it)

        NewsLetter(
            title = "Newsletter #$it",
            date = date.time,
            keywords = listOf("Portrait chinois", "vélo", "stress"),
            link = "https://xpeho.com/newsletter/$it"
        )
    }

    NewsLetterPage(
        newsLetterList = newsLetterList
    )
}