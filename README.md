# xpeho_ui_android

This is an Android implementation of the Xpeho UI made with Jetpack Compose.

## Getting started

TODO

## Usage

TODO

## Available components

### ClickyButton

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

```
TagPill(
    label: String,
    size: TextUnit,
    backgroundColor: Color,
    labelColor: Color
)
```

### CollapsableCard

```
CollapsableCard(
    title: String,
    date: String,
    keywords: List<String>,
    onOpenPressed: () -> Unit,
    collapsed: Boolean,
)
```

## Testing

### Snapshot testing

https://developer.android.com/studio/preview/compose-screenshot-testing?hl=fr