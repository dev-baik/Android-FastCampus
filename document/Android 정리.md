### DecimalFormat
```kotlin
fun numberClicked(view: View) {
    val numberString = (view as? Button)?.text?.toString() ?: ""
    val numberText = if(operatorText.isEmpty()) firstNumberText else secondNumberText

    numberText.append(numberString)
    updateEquationTextView()
}

private val decimalFormat = DecimalFormat("#,###.0")
fun equalClicked(view: View) {
    if(firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()) {
        Toast.makeText(this, "올바르지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
        return
    }
    val firstNumber = firstNumberText.toString().toBigDecimal()
    val secondNumber = secondNumberText.toString().toBigDecimal()

    val result = when(operatorText.toString()) {
        "+" -> decimalFormat.format(firstNumber + secondNumber)
        "-" -> decimalFormat.format(firstNumber - secondNumber)
        else -> "잘못된 수식 입니다."
    }.toString()

    binding.resultTextView.text = result
}
```