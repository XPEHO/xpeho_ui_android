import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xpeho.xpeho_ui_android.R
import com.xpeho.xpeho_ui_android.foundations.Colors
import com.xpeho.xpeho_ui_android.foundations.Fonts

@Composable
fun ChoiceSelector(
    label: String = "Choice Selector",
    choicesAvailable: List<String>,
    defaultSelectedChoice: String? = null,
    size: TextUnit = 18.sp,
    backgroundColor: Color = Color.White,
    choiceColor: Color = Color.Black,
    checkIconColor: Color = Color.Green,
    separatorColor: Color = Colors.GRAY_LIGHT_COLOR,
    onPress: (String) -> Unit = { choice ->
        println("The choice $choice is pressed")
    }
) {
    var selectedChoice by remember { mutableStateOf(defaultSelectedChoice) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 32.dp)
    ) {
        choicesAvailable.forEachIndexed { index, choice ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            selectedChoice = choice
                            onPress(choice)
                        }
                        .padding(vertical = 8.dp)
                        .semantics {
                            this.contentDescription = "$label: $choice"
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = choice,
                        fontSize = size,
                        fontFamily = Fonts.raleway,
                        color = choiceColor,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Check icon",
                        tint = if (choice == selectedChoice) checkIconColor else Color.Transparent,
                        modifier = Modifier.size(24.dp)
                    )
                }

                if (index < choicesAvailable.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        color = separatorColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChoiceSelectorPreview() {

    Surface(
        color = Colors.BACKGROUND_COLOR,
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            ChoiceSelector(
                label = "Select an option",
                choicesAvailable = listOf(
                    "Choice 1",
                    "Choice 2",
                    "Choice 3",
                    "Choice 4",
                    "Choice 5"
                ),
                defaultSelectedChoice = "Choice 2",
                backgroundColor = Color.White,
                choiceColor = Color.Black,
                checkIconColor = Colors.XPEHO_COLOR,
                separatorColor = Colors.GRAY_LIGHT_COLOR,
                onPress = { choice -> println("Selected: $choice") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            ChoiceSelector(
                label = "Select an option",
                choicesAvailable = listOf(
                    "Choice 1",
                    "Choice 2",
                    "Choice 3",
                    "Choice 4",
                    "Choice 5"
                ),
                defaultSelectedChoice = "Choice 2",
                backgroundColor = Color.Black,
                choiceColor = Color.White,
                checkIconColor = Colors.XPEHO_COLOR,
                separatorColor = Color.Gray,
                onPress = { choice -> println("Selected: $choice") }
            )
        }
    }

}
