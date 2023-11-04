### Thread
- 스레드 : 작업 공간
- 메인 스레드 (UI 스레드) : 애플리케이션이 실행되면서 안드로이드 시스템이 생성하는 스레드로, UI 를 그리는 역할
- 작업자 스레드 (Worker Thread) : 메인스레드 이외의 스레드
#### 규칙
- UI 스레드를 차단하지 마세요.
  - 앱이 일정시간 동안 반응이 없을 경우 [ANR](https://developer.android.com/training/articles/perf-anr?hl=ko) (Application Not Responding)
- UI 스레드 외부에서 Android UI 도구 키트에 액세스하지 마세요.
  - Exception

<img src="https://github.com/dev-baik/Android-FastCampus/assets/96613859/ffd618bf-e5df-49c5-a619-7e6d723176b8" width="50%" height="50%"/>

#### 해결 방법
- [Activity.runOnUiThread(Runnable)](https://developer.android.com/reference/android/app/Activity#runOnUiThread(java.lang.Runnable))
- [View.post(Runnable)](https://developer.android.com/reference/android/view/View#post(java.lang.Runnable))
- [View.postDelayed(Runnable, long)](https://developer.android.com/reference/android/view/View#postDelayed(java.lang.Runnable,%20long))
- [Handler](https://developer.android.com/reference/android/os/Handler)

```kotlin
private var timer: Timer? = null

timer = timer(initialDelay = 0, period = 100) {
    if (currentCountdownDeciSecond == 0) {
        currentDeciSecond += 1
    
        var minutes = currentDeciSecond.div(10) / 60
        val seconds = currentDeciSecond.div(10) % 60
        val deciSecond = currentDeciSecond % 10
    
        runOnUiThread {
            binding.timeTextView.text = String.format("%02d:%02d", minutes, seconds)
            binding.tickTextView.text = deciSecond.toString()
      
            binding.countDownGroup.isVisible = false
        }
    } else {
        currentCountdownDeciSecond -= 1
        val second = currentCountdownDeciSecond / 10
        val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100
    
        binding.root.post {
            binding.countDownTextView.text = String.format("%02d", second)
            binding.countDownProgressBar.progress = progress.toInt()
        }
    }
    if (currentDeciSecond == 0 && currentCountdownDeciSecond < 31 && currentCountdownDeciSecond % 10 == 0) {
        val toneType = if(currentCountdownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
        ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
          .startTone(toneType, 100)
    }
}
```

### addView
```xml
<ScrollView>
    <LinearLayout
        android:id="@+id/lap_container_linearLayout"
        ... />
</ScrollView>
```
```kotlin
private fun lap() {
    if (currentDeciSecond == 0) return
    val container = binding.lapContainerLinearLayout
    TextView(this).apply {
        textSize = 20f
        gravity = Gravity.CENTER
        val minutes = currentDeciSecond.div(10) / 60
        val seconds = currentDeciSecond.div(10) % 60
        val deciSeconds = currentDeciSecond % 10
        text = "${container.childCount.inc()}. " + String.format(
            "%02d:%02d %01d",
            minutes,
            seconds,
            deciSeconds
        )
        setPadding(30)
    }.let { labTextView ->
        container.addView(labTextView, 0)
    }
}
```

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