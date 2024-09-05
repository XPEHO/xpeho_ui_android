import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.xpeho.xpeho_ui_android.R
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun InputTextField(
    label: String,
    passwordSwitcherIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(id = R.drawable.eye),
            contentDescription = "Password switcher representation with eye icon",
            tint = Color.Black
        )
    },
    defaultInput: String = "",
    labelSize: Float,
    inputSize: Float,
    labelColor: Color = Color.Gray.copy(alpha = 0.7f),
    backgroundColor: Color = Color.White,
    inputColor: Color = Color.Black,
    password: Boolean = false,
    onInput: (String) -> Unit = { input -> println("The input $input is typed") }
) {
    var input by remember { mutableStateOf(defaultInput) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    // Animate label position based on input and focus state
    val labelPosition by animateFloatAsState(
        targetValue = if (isFocused || input.isNotEmpty()) 0f else 0f, label = ""
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
                        fontSize = labelSize.sp,
                        fontFamily = Fonts.rubik,
                        fontWeight = FontWeight.Normal,
                        color = labelColor,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .offset(y = labelPosition * 20.dp)
                    )
                }

                BasicTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        onInput(it)
                    },
                    visualTransformation = if (password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = if (password) KeyboardType.Password else KeyboardType.Text),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = inputSize.sp,
                        fontFamily = Fonts.rubik,
                        fontWeight = FontWeight.Normal,
                        color = inputColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = if (isFocused || input.isNotEmpty()) 20.dp else 0.dp, start = 10.dp)
                        .onFocusChanged { isFocused = it.isFocused }
                )
            }

            if (password) {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
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
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            InputTextField(
                label = "Password",
                labelSize = 14f,
                inputSize = 16f,
                password = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                label = "Username",
                labelSize = 14f,
                inputSize = 16f
            )
        }
    }
}
