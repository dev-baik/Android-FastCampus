### Guideline
```xml
<androidx.constraintlayout.widget.Guideline
    android:id="@+id/guideline"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    app:layout_constraintGuide_percent="0.4" />
```

### Style
`res/values/styles.xml` ➡️ `style="@style/Value"`
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Title" parent="Widget.AppCompat.TextView">
        <item name="android:textColor">@color/purple_200</item>
        <item name="android:textSize">24sp</item>
        <item name="android:layout_marginTop">36dp</item>
        <item name="android:textStyle">bold</item>
    </style>

    <style name="Value">
        <item name="android:textColor">@color/black</item>
        <item name="android:textSize">20sp</item>
        <item name="android:gravity">end</item>
    </style>
</resources>
```

### Layer
```xml
<androidx.constraintlayout.helper.widget.Layer
    android:id="@+id/birth_date_layer"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:constraint_referenced_ids="birth_date_value_text_view,birth_date_image_view"
    tools:ignore="MissingConstraints" />
```

### DatePickerDialog
```kotlin
binding.birthDateLayer.setOnClickListener {
    val listener = OnDateSetListener { date, year, month, dayOfMonth ->
        binding.birthDateTextView.text = "$year-${month.inc()}-$dayOfMonth"
    }
    DatePickerDialog(
        this,
        listener,
        2000,
        1,
        1
    ).show()
}
```

### Flow
- flow_maxElementsWrap : 래핑 하기 전에 요소 수를 지정 (가로)
- flow_wrapMode : 요소가 체인 대신 일련의 행과 열에 배치g
- flow_horizontalGap : 요소 사이의 간격 (가로)
```xml
<androidx.constraintlayout.helper.widget.Flow
    android:id="@+id/key_pad_flow"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:padding="8dp"
    app:flow_maxElementsWrap="4"
    app:flow_wrapMode="chain"
    app:flow_horizontalGap="8dp"
    app:layout_constraintHeight_percent="0.7"
    app:layout_constraintVertical_bias="1"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:constraint_referenced_ids="button1, button2, button3, buttonClear, button4, button5, button6, buttonPlus, button7, button8, button9, buttonMinus, button0, buttonEqual"/>

<Button
    android:id="@+id/button0"
    style="@style/numberKeypad"
    android:text="0"
    android:onClick="numberClicked"
    app:layout_constraintHorizontal_weight="1"
    tools:ignore="MissingConstraints" />

<Button
    android:id="@+id/buttonEqual"
    style="@style/operatorKeypad"
    android:text="="
    android:onClick="equalClicked"
    app:layout_constraintHorizontal_weight="3"
    tools:ignore="MissingConstraints" />
```