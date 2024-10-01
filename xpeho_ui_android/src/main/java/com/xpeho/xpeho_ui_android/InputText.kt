package com.xpeho.xpeho_ui_android

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun InputText(
    label: String,
    passwordSwitcherIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(id = R.drawable.eye),
            contentDescription = "Password switcher representation with eye icon",
            tint = Color.Black,
        )
    },
    defaultInput: String = "",
    labelSize: TextUnit,
    inputSize: TextUnit,
    labelColor: Color = Color.Gray.copy(alpha = 0.7f),
    backgroundColor: Color = Color.White,
    inputColor: Color = Color.Black,
    password: Boolean = false,
    onInput: (String) -> Unit = { input -> println("The input $input is typed") }
) {
    var input by remember { mutableStateOf(defaultInput) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val visualTransform = if (password && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val keyBoardType = if (password) {
        KeyboardType.Password
    } else {
        KeyboardType.Text
    }

    val animatedLabelSize by animateFloatAsState(
        targetValue = if (isFocused || input.isNotEmpty()) labelSize.value - 3f else labelSize.value, label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                if (label.isNotEmpty()) {
                    Text(
                        text = label,
                        fontSize = animatedLabelSize.sp,
                        fontFamily = Fonts.raleway,
                        fontWeight = FontWeight.Medium,
                        color = labelColor,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                BasicTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        onInput(it)
                    },
                    visualTransformation = visualTransform,
                    keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = inputSize,
                        fontFamily = Fonts.rubik,
                        fontWeight = FontWeight.Normal,
                        color = inputColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = if (isFocused || input.isNotEmpty()) 20.dp else 0.dp,
                            start = 10.dp
                        )
                        .onFocusChanged { isFocused = it.isFocused }
                )
            }

            if (password) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    passwordVisible = true
                                    tryAwaitRelease()
                                    passwordVisible = false
                                }
                            )
                        }
                ) {
                    passwordSwitcherIcon()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputTextPreview() {
    Surface(
        color = Colors.BACKGROUND_COLOR,
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            InputText(
                label = "Input Text",
                labelSize = 14.sp,
                inputSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text Hiddenable",
                labelSize = 14.sp,
                inputSize = 16.sp,
                password = false,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text With input",
                defaultInput = "input",
                labelSize = 14.sp,
                inputSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text Hiddenable With input",
                defaultInput = "input",
                labelSize = 14.sp,
                inputSize = 16.sp,
                password = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text Customed",
                labelSize = 14.sp,
                inputSize = 16.sp,
                inputColor = Color.White,
                labelColor = Colors.RED_INFO_COLOR,
                backgroundColor = Colors.CONTENT_COLOR,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text Customed With input",
                defaultInput = "input",
                labelSize = 14.sp,
                inputSize = 16.sp,
                inputColor = Color.White,
                labelColor = Colors.RED_INFO_COLOR,
                backgroundColor = Colors.CONTENT_COLOR,
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputText(
                label = "Input Text Hiddenable Customed With input",
                defaultInput = "input",
                inputColor = Color.White,
                labelColor = Colors.RED_INFO_COLOR,
                backgroundColor = Colors.CONTENT_COLOR,
                labelSize = 14.sp,
                inputSize = 16.sp,
                password = true,
                passwordSwitcherIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "Password switcher representation with eye icon",
                        tint = Color.White
                    )
                }
            )
        }
    }
}