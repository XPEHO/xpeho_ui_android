package com.xpeho.xpeho_ui_android

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

enum class LabelPosition {
    TOP, BOTTOM
}
@Composable
fun FilePreviewButton(
    labelStart: String = "File Preview Button",
    labelEnd: String = "Info",
    imagePreview: @Composable () -> Unit = {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = "Image preview",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    },
    tags: List<@Composable () -> Unit> = listOf(
        { TagPill("TAG PILL 1") },
        { TagPill("TAG PILL 2") },
        { TagPill("TAG PILL 3") },
        { TagPill("TAG PILL 4") }
    ),
    height: Dp = 250.dp,
    labelSize: TextUnit = 18.sp,
    backgroundColor: Color = Colors.BACKGROUND_COLOR,
    labelColor: Color = Color.Black,
    enabled: Boolean = true,
    labelPosition: LabelPosition = LabelPosition.TOP,
    onPress: () -> Unit = { println("Button pressed") },
    arrowIcon: @Composable (Modifier) -> Unit = { modifier ->
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Arrow icon",
            tint = Color.White,
            modifier = modifier
        )
    }
) {
    var pressed by remember { mutableStateOf(false) }

    // Animation for arrow offset
    val arrowOffset by animateFloatAsState(
        targetValue = if (pressed) 40f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )
    val arrowAlpha by animateFloatAsState(
        targetValue = if (pressed) 0f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .pointerInput(Unit) {
                    if (enabled) {
                        detectTapGestures(
                            onPress = {

                                pressed = true
                                onPress()

                                tryAwaitRelease()
                                pressed = false

                            }
                        )
                    }
                }
        ) {
            if (labelPosition == LabelPosition.TOP) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = labelStart,
                        fontSize = labelSize,
                        color = labelColor,
                        fontFamily = Fonts.raleway
                    )
                    Text(
                        text = labelEnd,
                        fontSize = labelSize,
                        color = labelColor,
                        fontFamily = Fonts.raleway
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            ) {
                imagePreview()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Colors.CONTENT_COLOR
                                ),
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .horizontalScroll(rememberScrollState())
                        .clipToBounds()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        tags.forEach { tag ->
                            Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                                tag()
                            }
                        }
                    }

                    // Arrow Icon with animation
                    arrowIcon(
                        Modifier
                            .offset(x = arrowOffset.dp)
                            .alpha(arrowAlpha)
                    )
                }
            }

            if (labelPosition == LabelPosition.BOTTOM) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = labelStart,
                        fontSize = labelSize,
                        color = labelColor,
                        fontFamily = Fonts.raleway
                    )
                    Text(
                        text = labelEnd,
                        fontSize = labelSize,
                        color = labelColor,
                        fontFamily = Fonts.raleway
                    )
                }
            }
        }

        // Gray overlay when disabled
        if (!enabled) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Colors.DISABLED_COLOR.copy(alpha = 0.65f))
            )
        }
    }
}


@Preview
@Composable
fun FilePreviewButtonPreview() {
    Surface(
        color = Color.LightGray,
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                FilePreviewButton(
                    height = 200.dp,
                    tags = listOf(
                        { TagPill("TAG PILL 1", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 2", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 3", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 4", backgroundColor = Colors.XPEHO_COLOR) },
                    ),
                    labelPosition = LabelPosition.TOP,
                )
                Spacer(modifier = Modifier.height(16.dp))
                FilePreviewButton(
                    height = 200.dp,
                    labelStart = "File Preview Button Customized",
                    tags = listOf(
                        { TagPill("TAG PILL CUSTOM 1", backgroundColor = Colors.RED_INFO_COLOR) },
                        { TagPill("TAG PILL CUSTOM 2", backgroundColor = Colors.RED_INFO_COLOR) },
                        { TagPill("TAG PILL CUSTOM 3", backgroundColor = Colors.RED_INFO_COLOR) },
                        { TagPill("TAG PILL CUSTOM 4", backgroundColor = Colors.RED_INFO_COLOR) },
                    ),
                    labelColor = Color.White,
                    backgroundColor = Colors.CONTENT_COLOR,
                    labelPosition = LabelPosition.BOTTOM,
                    arrowIcon =  { modifier ->
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = "Arrow icon",
                            tint = Colors.RED_INFO_COLOR,
                            modifier = modifier
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                FilePreviewButton(
                    enabled = false,
                    labelStart = "File Preview Button Disabled",
                    height = 200.dp,
                    tags = listOf(
                        { TagPill("TAG PILL 1", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 2", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 3", backgroundColor = Colors.XPEHO_COLOR) },
                        { TagPill("TAG PILL 4", backgroundColor = Colors.XPEHO_COLOR) },
                    ),
                    labelPosition = LabelPosition.TOP,
                )
            }
        }
    }
}

