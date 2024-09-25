# xpeho_ui_android

This is an Android implementation of the Xpeho UI made with Jetpack Compose.

## Getting started

TODO

## Usage

TODO

## Available components

### ClickyButton

![alt text](xpeho_ui_android/doc/clickybutton.png)

Usage : 

```
ClickyButton(
    label: String,
    size: TextUnit,
    backgroundColor: Color,
    labelColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    enabled: Boolean,
    onPress: () -> Unit,
)
```

### TagPill

![alt text](xpeho_ui_android/doc/tagpill.png)

Usage:

```
TagPill(
    label: String,
    size: TextUnit,
    backgroundColor: Color,
    labelColor: Color
)
```

### CollapsableCard

![alt text](xpeho_ui_android/doc/collapsablecard.png)

Usage:

```
CollapsableCard(
    title: String,
    date: String,
    keywords: List<String>,
    onOpenPressed: () -> Unit,
    collapsed: Boolean,
)
```

### InputText

![alt text](xpeho_ui_android/doc/inputtext.png)

usage: 

```
InputText(
    label: String,
    passwordSwitcherIcon: () -> Unit,
    defaultInput: String,
    labelSize: Float,
    inputSize: Float,
    labelColor: Color,
    backgroundColor: Color, 
    inputColor: Color,
    password: Boolean,
    onInput: (String) -> Unit,
)
```

### ChoiceSelector

![alt text](xpeho_ui_android/doc/choiceselector.png)

usage: 

```
    label: String,
    choicesAvailable: List<String>,
    defaultSelectedChoice: String,
    size: Float,
    backgroundColor: Color,
    choiceColor: Color ,
    checkIconColor: Color,
    separatorColor: Color,
    onPress: (String) -> Unit,
```

## Testing

### Snapshot testing

https://developer.android.com/studio/preview/compose-screenshot-testing?hl=fr