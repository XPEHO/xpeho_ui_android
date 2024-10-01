package com.xpeho.xpeho_ui_android

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CollapsableCard(
    label: String,
    headTag: @Composable () -> Unit = {},
    tags: @Composable (() -> Unit)? = null,
    button: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit = {},
    openArrowIcon: @Composable () -> Unit = {
        Icon(
            painterResource(id = R.drawable.chevron_down),
            contentDescription = "Newsletter collapse icon",
            modifier = Modifier
                .size(24.dp)
        )
    },
    closeArrowIcon: @Composable () -> Unit = {
        Icon(
            painterResource(id = R.drawable.chevron_up),
            contentDescription = "Newsletter collapse icon",
            modifier = Modifier
                .size(24.dp)
        )
    },
    size: TextUnit = 18.sp,
    labelColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    collapsable: Boolean = true,
    defaultOpen: Boolean = false,
) {
    val opened = remember { mutableStateOf(defaultOpen) }

    val openIcon = if (opened.value) {
        closeArrowIcon
    } else {
        openArrowIcon
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = Modifier.animateContentSize()
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                // Title row
                Row(
                    modifier = Modifier
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    if (collapsable) {
                                        opened.value = !opened.value
                                    }
                                }
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (collapsable) {
                        icon()
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Text(
                        label,
                        fontSize = size,
                        color = labelColor,
                        fontFamily = Fonts.raleway,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    headTag()
                    Spacer(modifier = Modifier.weight(1f))
                    if (collapsable) {
                        openIcon()
                    } else {
                        icon()
                    }
                }
                // Chips row
                if (opened.value || !collapsable) {
                    Spacer(modifier = Modifier.height(16.dp))
                    if (tags != null) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            tags()
                        }
                    }
                    if (button != null) {
                        if (tags != null) {
                            Spacer(modifier = Modifier.height(22.dp))
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            button()
                        }
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
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    CollapsableCard(
                        label = "Collapsable Card",
                        tags = {
                            TagPill("tag 1")
                            TagPill(
                                "tag 2",
                                backgroundColor = Colors.RED_INFO_COLOR
                            )
                            TagPill(
                                "tag 3",
                                backgroundColor = Colors.XPEHO_COLOR
                            )
                        },
                        button = {
                            ClickyButton(
                                "Collapsable Action",
                                onPress = {
                                    Log.d("XpehoNewsLetterPreview", "onOpenPressed")
                                }
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.briefcase),
                                tint = Colors.XPEHO_COLOR,
                                contentDescription = "Newsletter icon",
                            )
                        },
                        defaultOpen = true,
                    )
                }
                item {
                    CollapsableCard(
                        label = "QVST Title",
                        tags = {
                            TagPill("topic")
                            TagPill(
                                "_ days remaining",
                                backgroundColor = Colors.RED_INFO_COLOR
                            )
                            TagPill("_/_ completed")
                        },
                        button = {
                            ClickyButton(
                                "Complete",
                                onPress = {
                                    Log.d("XpehoNewsLetterPreview", "onOpenPressed")
                                }
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.qvst),
                                tint = Colors.XPEHO_COLOR,
                                contentDescription = "Newsletter icon",
                            )
                        },
                        defaultOpen = true,
                    )
                }
                item {
                    CollapsableCard(
                        label = "Newsletter #__",
                        headTag = {
                            TagPill(label = "__/__/____")
                        },
                        tags = {
                            TagPill("tag 1")
                            TagPill("tag 2")
                            TagPill("tag 3")
                        },
                        button = {
                            ClickyButton(
                                "READ",
                                onPress = {
                                    Log.d("XpehoNewsLetterPreview", "onOpenPressed")
                                }
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.newsletter),
                                tint = Colors.XPEHO_COLOR,
                                contentDescription = "Newsletter icon",
                            )
                        },
                        defaultOpen = true,
                    )
                }
                item {
                    CollapsableCard(
                        label = "Birthday of NAME",
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.birthday),
                                tint = Colors.XPEHO_COLOR,
                                contentDescription = "Newsletter icon",
                            )
                        },
                        tags = {
                            TagPill("__ month")
                            TagPill("__ y")
                        },
                        collapsable = false
                    )
                }
                item {
                    CollapsableCard(
                        label = "Birthday of NAME",
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.birthday),
                                tint = Color.White,
                                contentDescription = "Newsletter icon",
                            )
                        },
                        headTag = {
                            TagPill(
                                label = "head tag",
                                backgroundColor = Color.White,
                                labelColor = Colors.RED_INFO_COLOR
                            )
                        },
                        tags = {
                            TagPill(
                                "tag customized 1",
                                backgroundColor = Color.White,
                                labelColor = Colors.CONTENT_COLOR
                            )
                            TagPill(
                                "tag customized 2",
                                backgroundColor = Color.White,
                                labelColor = Colors.CONTENT_COLOR
                            )
                            TagPill(
                                "tag customized 3",
                                backgroundColor = Color.White,
                                labelColor = Colors.RED_INFO_COLOR
                            )
                            TagPill(
                                "tag customized 4",
                                backgroundColor = Color.White,
                                labelColor = Colors.CONTENT_COLOR
                            )
                        },
                        button = {
                            ClickyButton(
                                "ACTION",
                                backgroundColor = Colors.RED_INFO_COLOR,
                            )
                        },
                        closeArrowIcon = {
                            Icon(
                                painterResource(id = R.drawable.chevron_up),
                                contentDescription = "Newsletter collapse icon",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        },
                        openArrowIcon = {
                            Icon(
                                painterResource(id = R.drawable.chevron_down),
                                contentDescription = "Newsletter collapse icon",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        },
                        backgroundColor = Colors.CONTENT_COLOR,
                        labelColor = Color.White,
                        collapsable = true,
                        defaultOpen = true,
                    )
                }
                item {
                    CollapsableCard(
                        "Collapsable Card Closed",
                        headTag = {
                            TagPill(
                                "Head Tag",
                                backgroundColor = Colors.GREEN_DARK_COLOR
                            )
                        },
                        defaultOpen = false
                    )
                }
            }
        }
    }
}
