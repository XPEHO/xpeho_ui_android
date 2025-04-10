# xpeho_ui_android

This is an Android implementation of the Xpeho UI made with Jetpack Compose.

## Import the package

To import the package in your android project, follow theses steps:

- Add the following code to your gradle file on your project _(pluginManagement and dependencyREsolutionManagement blocks)_ :

  ```gradle
  maven {
      url 'https://maven.pkg.github.com/XPEHO/xpeho_ui_android'
      credentials {
          username = ""
          password = ""
      }
  }
  ```

- As you can see we don't set username and password, that's because we can't compute from an other file in that type of block so we add the following code after these two blocks :

  ```gradle
  // Update credentials
  gradle.settingsEvaluated {
    pluginManagement.repositories {
        maven {
            url 'https://maven.pkg.github.com/XPEHO/xpeho_ui_android'
            credentials {
                username = System.getenv('GITHUB_USER') ?: ""
                password = System.getenv('GITHUB_TOKEN') ?: ""
            }
        }
    }

    dependencyResolutionManagement.repositories {
        maven {
            url 'https://maven.pkg.github.com/XPEHO/xpeho_ui_android'
            credentials {
                username = System.getenv('GITHUB_USER') ?: ""
                password = System.getenv('GITHUB_TOKEN') ?: ""
            }
        }
    }
  }
  ```

- Add the needed environment variables :

  - On a mac/linux you can add them to your `.zshrc`/`.bashrc`

    ```zshrc
    #Github
    export GITHUB_USER="your_github_username"
    export GITHUB_TOKEN="your_github_token"
    ```

    To fill the GITHUB_TOKEN, make sure to generate a PAT (Personal Access Token) on your github account following the [documentation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic).

    You need to allow the following rights :
    ![repos_right](doc/repos_right.png)
    ![packages_right](doc/packages_right.png)

- Now we can add in our **dependencies** block of the `build.gradle` file the following implementation :

  ```gradle
  //XpehoUI
  implementation "com.xpeho.packages:xpeho_ui_android:1.0.0"
  ```

- So the implementation is done you can now sync your gradle files and import the package in any file like this:

  ```java
  import com.xpeho.xpeho_ui_android.*
  ```

## Use the package

### Assets

#### Icons

You can access icons of the library that are in the [drawable directory](xpeho_ui_android/src/main/res/drawable) using the following lines :

```kotlin
import com.xpeho.xpeho_ui_android.R.drawable as XpehoRes

XpehoRes.ICON_NAME
```

Make sure to replace ICON_NAME by the file name of the drawable you want to use.

#### Colors

Colors of the library :

- XPEHO_COLOR : ![#A0CE4E](https://via.placeholder.com/15/A0CE4E/000000?text=+)
- XPEHO_DARK_COLOR : ![#88AF41](https://via.placeholder.com/15/88AF41/000000?text=+)
- GREEN_DARK_COLOR : ![#3F6D34](https://via.placeholder.com/15/3F6D34/000000?text=+)
- RED_INFO_COLOR : ![#D25656](https://via.placeholder.com/15/D25656/000000?text=+)
- DISABLED_COLOR : ![#E6ECEF](https://via.placeholder.com/15/E6ECEF/000000?text=+)
- CONTENT_COLOR : ![#212121](https://via.placeholder.com/15/212121/000000?text=+)
- BACKGROUND_COLOR : ![#F2F6F9](https://via.placeholder.com/15/F2F6F9/000000?text=+)
- GRAY_LIGHT_COLOR : ![#EEEEEE](https://via.placeholder.com/15/EEEEEE/000000?text=+)

You can access them using the following lines :

```kotlin
import com.xpeho.xpeho_ui_android.foundation.Colors as XpehoColors

XpehoColors.COLOR_NAME
```

Make sure to replace COLOR_NAME by the name of the color you want to use.

#### Fonts

Fonts of the library :

- Rubik : rubik
- Roboto : roboto
- Raleway : raleway

You can access these fonts using the following lines:

```kotlin
import com.xpeho.xpeho_ui_android.foundation.Fonts as XpehoFonts

Text(
    "Text",
    fontFamily = XpehoFonts.FONT_NAME,
    fontWeight = FontWeight.FONT_WEIGHT
)
```

Make sure to replace FONT_NAME and FONT_WEIGHT by the name of the font and the weight you want to use.

### Available components

#### ClickyButton

![ClickyButton Preview](xpeho_ui_android/doc/clickybutton.png)

Usage :

```kotlin
import com.xpeho.xpeho_ui_android.ClickyButton

ClickyButton(
    label = String,
    size = TextUnit,
    backgroundColor = Color,
    labelColor = Color,
    verticalPadding = Dp,
    horizontalPadding = Dp,
    enabled = Boolean,
    onPress = () -> Unit
)
```

#### TagPill

![TagPill Preview](xpeho_ui_android/doc/tag_pill.png)

Usage:

```kotlin
import com.xpeho.xpeho_ui_android.TagPill

TagPill(
    label = String,
    size = TextUnit,
    backgroundColor = Color,
    labelColor = Color,
    icon = @Composable() -> Unit? {},
)
```

#### CollapsableCard

![CollapsableCard Preview](xpeho_ui_android/doc/collapsablecard.png)

Usage:

```kotlin
import com.xpeho.xpeho_ui_android.CollapsableCard

CollapsableCard(
    title = String,
    date = String,
    keywords = List<String>,
    onOpenPressed = () -> Unit,
    collapsed = Boolean
)
```

#### InputText

![InputText Preview](xpeho_ui_android/doc/inputtext.png)

usage:

```kotlin
import com.xpeho.xpeho_ui_android.InputText

InputText(
    label = String,
    passwordSwitcherIcon = () -> Unit,
    defaultInput = String,
    labelSize = Float,
    inputSize = Float,
    labelColor = Color,
    backgroundColor = Color,
    inputColor = Color,
    password = Boolean,
    focusRequester = FocusRequester,
    keyboardAction = ImeAction,
    onKeyboardAction = () -> Unit,
    onInput = (String) -> Unit,
    isReadOnly = Boolean
)
```

#### ChoiceSelector

![ChoiceSelector](xpeho_ui_android/doc/choiceselector.png)

Usage:

```kotlin
import com.xpeho.xpeho_ui_android.ChoiceSelector

ChoiceSelector(
    label = String,
    choicesAvailable = List<String>,
    defaultSelectedChoice = String,
    size = Float,
    backgroundColor = Color,
    choiceColor = Color ,
    checkIconColor = Color,
    separatorColor = Color,
    onPress = (String) -> Unit
)
```

### FilePreviewButton

![FilePreviewButton](xpeho_ui_android/doc/filepreviewbutton.png)

Usage:

```kotlin
import com.xpeho.xpeho_ui_android.FilePreviewButton

FilePreviewButton(
    labelStart: String,
    labelEnd: String,
    imagePreview: @Composable()-> Unit,
    tags: List<@Composable()-> Unit> = listOf({TagPill()},),
    height: Dp,
    labelSize: TextUnit,
    backgroundColor: Color,
    labelColor: Color,
    enabled: Boolean,
    labelPosition: LabelPosition,
    onPress: () -> Unit,
    arrowIcon: @Composable() -> Unit,
)
```

## Edit the package

- Clone the repository using `git clone`
- Launch the folder in Android Studio
- Make your changes
- Send a Pull Request

## Deploy the package

A workflow has been created on this repository to automatically deploy the package in artifacts on creating a new release.

The only thing you need to do so is to create a new release on the repository following the [GitHub documentation](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository).

You can check the deployed packages by looking at the repository page, it should have your packages on the packages section.

## Testing the package

### Snapshot testing

https://developer.android.com/studio/preview/compose-screenshot-testing?hl=fr
