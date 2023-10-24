## [UI](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md)
- [사용자 인터페이스 및 탐색](https://developer.android.com/guide/topics/ui?hl=ko)
- [LinearLayout](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko#layout-params)
- [ConstraintLayout](https://developer.android.com/training/constraint-layout?hl=ko)
    - [Guideline](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Guideline)
- [Layer](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Layer)
- TextView, Button, EditText, ImageView, RadioButton, CheckBox
- [Spinner](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko#AdapterViews)
- [DatePickerDialog](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#DatePickerDialog)
- [dp, sp](https://developer.android.com/training/multiscreen/screendensities?hl=ko)
- [Style](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Style)
- Theme

## [Kotlin](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EC%A0%95%EB%A6%AC.md)
- [Kotlin 기초](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EA%B8%B0%EC%B4%88.md)
- [Kotlin 중급](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EC%A4%91%EA%B8%89.md)
- val, var, const val
- 복합대입 연산자 (+=)
- [산술 연산자](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EC%A0%95%EB%A6%AC.md#%EC%82%B0%EC%88%A0-%EC%97%B0%EC%82%B0%EC%9E%90)
- isNullOrEmpty
- with

## Android
- [Android 기본](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EA%B8%B0%EB%B3%B8.md)
- [Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro?hl=ko)
- [Activity](https://developer.android.com/guide/components/activities?hl=ko)
- [ActivityLifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko)
- [onSaveInstanceState](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko#save-simple,-lightweight-ui-state-using-onsaveinstancestate)
- [Intent](https://developer.android.com/guide/components/intents-filters?hl=ko)
    - [화면 전환](https://developer.android.com/training/basics/firstapp/starting-activity?hl=ko)
    - [전화 앱 실행](https://developer.android.com/guide/components/intents-common?hl=ko#DialPhone)
- [SharedPreference](https://developer.android.com/training/data-storage/shared-preferences?hl=ko)
- Toast
- R 파일
- [findViewById](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko#id)
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding?hl=ko)
- setOnClickListener, addTextChangedListener
- Log

## 한걸음 더
> 화면의 방향이 변경된다면 어떻게 해야할까요?
1. 값을 유지하라면 어떻게 해야할까요?
    - part1-chapter3 강의 참고
2. 화면 방향에 상관없이 버튼을 보이게 하려면 어떻게 해야할까요?
    - numberTextView의 height 값을 지정하지 말고, weight를 이용

> weight 를 넣을 때 dimension 에 왜 0dp 를 넣으라고 했을까요? [레이아웃 가중치](https://developer.android.com/guide/topics/ui/layout/linear?hl=ko#Weight)
- LinearLayout 의 weight 값이 잘 적용되기 위해선, orientation에 따라, width 또는 height 의 값이 0dp 여야 함
    - orientation: vertical -> layout_height=“0dp”
    - orientation: horizontal -> layout_width="0dp"

> 소수점이 정확하지 않은 이유는 뭘까요? [부동소수점](https://ko.wikipedia.org/wiki/%EB%B6%80%EB%8F%99%EC%86%8C%EC%88%98%EC%A0%90)
- Java 에서는 실수를 표현할 때는 부동 소수점 방식을 사용 하는데 이 때, 오차가 생길 수 있다. 근사값을 이용하기 때문이다. 부정확성을 해결하기 위해, BigDecimal 이라는 자료형을 사용하면 된다.
    - 상세한 이유는 이해하지 못하더라도, 소수점이 정확하지 않을 수 있다는 점을 인지하고, 정확한 계산을 필요로 할 때는 다른 자료형을 사용하면 됨