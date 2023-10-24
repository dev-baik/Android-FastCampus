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