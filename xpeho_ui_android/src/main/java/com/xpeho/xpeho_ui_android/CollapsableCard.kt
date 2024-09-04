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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun CollapsableCard(
    label: String,
    headTag: @Composable () -> Unit = {},
    tags: @Composable (() -> Unit)? = null,
    button: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit = {},
    openArrowIcon: ImageVector = Icons.Default.KeyboardArrowUp,
    closeArrowIcon: ImageVector = Icons.Default.KeyboardArrowDown,
    size: TextUnit = 18.sp,
    labelColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    collapsable: Boolean = true,
    defaultOpen: Boolean = false,
) {
    val opened = remember { mutableStateOf(defaultOpen) }

    val openIcon = if (opened.value) {
        openArrowIcon
    } else {
        closeArrowIcon
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
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
                        Icon(
                            openIcon,
                            contentDescription = "Newsletter collapse icon",
                            modifier = Modifier
                                .size(24.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (collapsable) {
                                                opened.value = !opened.value
                                            }
                                        }
                                    )
                                }
                        )
                    } else {
                        icon()
                    }
                }
                // Chips row
                if (opened.value || !collapsable) {
                    if (tags != null) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            tags()
                        }
                    }
                    if (button != null) {
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
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CollapsableCard(
                    label = "Titre de QVST",
                    tags = {
                        TagPill("Thème")
                        TagPill("n jours restants")
                        TagPill("n/t complétés")
                    },
                    button = {
                        ClickyButton(
                            "Compléter",
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
                )
                CollapsableCard(
                    label = "Titre de QVST",
                    tags = {
                        TagPill("Thème")
                        TagPill("n jours restants")
                        TagPill("n/t complétés")
                    },
                    button = {
                        ClickyButton(
                            "Compléter",
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
                CollapsableCard(
                    label = "Titre de QVST",
                    tags = {
                        TagPill("Thème")
                        TagPill("n jours restants")
                        TagPill("n/t complétés")
                    },
                    button = {
                        ClickyButton(
                            "Compléter",
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
                    collapsable = false
                )
                CollapsableCard(
                    label = "Newsletter #32",
                    headTag = {
                        TagPill(label = "12/05/2024")
                    },
                    tags = {
                        TagPill("Portrait chinois")
                        TagPill("vélo")
                        TagPill("stress")
                    },
                    button = {
                        ClickyButton(
                            "OUVRIR",
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
                CollapsableCard(
                    label = "Newsletter #33",
                    headTag = {
                        TagPill(label = "12/05/2024")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.newsletter),
                            tint = Colors.XPEHO_COLOR,
                            contentDescription = "Newsletter icon",
                        )
                    },
                )
                CollapsableCard(
                    label = "Anniversaire de NOM",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.birthday),
                            tint = Colors.XPEHO_COLOR,
                            contentDescription = "Newsletter icon",
                        )
                    },
                    tags = {
                        TagPill("__MOIS")
                        TagPill("__ANS")
                    },
                    collapsable = false
                )
            }
        }
    }
}
