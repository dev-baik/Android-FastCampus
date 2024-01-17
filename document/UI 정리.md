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

### Spinner
```kotlin
binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
    this,
    R.array.blood_types,
    android.R.layout.simple_list_item_1
)
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

### Barrier
```xml
<androidx.constraintlayout.widget.Barrier
    android:id="@+id/barrier"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:barrierDirection="start"
    app:constraint_referenced_ids="edit_image_view, delete_image_view" />
```

### TextInputLayout, TextInputEditText
- counterEnabled : 글자 수 세기 기능 추가
- counterMaxLength : 총 글자 수 제한
- errorEnabled : 에러 표시
```xml
<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/text_text_input_layout"
    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    app:counterEnabled="true"
    app:counterMaxLength="30"
    app:errorEnabled="true"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/title_text_view">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/text_input_edit_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="단어" />
</com.google.android.material.textfield.TextInputLayout>
```
```kotlin
binding.textInputEditText.addTextChangedListener {
    it?.let { text ->
        binding.textTextInputLayout.error = when(text.length) {
            0 -> "값을 입력해주세요"
            1 -> "2자 이상을 입력해주세요"
            else -> null
        }
    }
}
```

### ChipGroup
- singleSelection : 하나만 선택
- selectionRequired : 선택 필수
```xml
<com.google.android.material.chip.ChipGroup
    android:id="@+id/type_chip_group"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    app:layout_constraintTop_toBottomOf="@id/mean_text_input_layout"
    app:selectionRequired="true"
    app:singleSelection="true" />
```
```kotlin
private fun initViews() {
    val types = listOf("명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사")
    binding.typeChipGroup.apply {
        types.forEach { text ->
            addView(createChip(text))
        }
    }

    originWord = intent.getParcelableExtra("originWord")
    originWord?.let { word ->
        binding.textInputEditText.setText(word.text)
        binding.meanTextInputEditText.setText(word.mean)
        val selectedChip = binding.typeChipGroup.children.firstOrNull { (it as Chip).text == word.type } as? Chip
        selectedChip?.isChecked = true
    }
}

private fun createChip(text: String): Chip {
    return Chip(this).apply {
        setText(text)
        isCheckable = true
        isClickable = true
    }
}
```