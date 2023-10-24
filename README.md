## UI
- [사용자 인터페이스 및 탐색](https://developer.android.com/guide/topics/ui?hl=ko)
- [LinearLayout](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko#layout-params)
- TextView
- Button
- [dp, sp](https://developer.android.com/training/multiscreen/screendensities?hl=ko)

## Kotlin
- [Kotlin 기초](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EA%B8%B0%EC%B4%88.md)
- [Kotlin 중급](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EC%A4%91%EA%B8%89.md)
- val, var
- 복합대입 연산자 +=

## Android
- [Android 기본](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EA%B8%B0%EB%B3%B8.md)
- [Activity](https://developer.android.com/guide/components/activities?hl=ko)
- R 파일
- [findViewById](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko#id)
- setOnClickListener
- Log

## 한걸음 더
> 화면의 방향이 변경된다면 어떻게 해야할까요?
1. 값을 유지하라면 어떻게 해야할까요?
    - chapter01-2 참고
2. 화면 방향에 상관없이 버튼을 보이게 하려면 어떻게 해야할까요?
    - numberTextView의 height 값을 지정하지 말고, weight를 이용

> weight 를 넣을 때 dimension 에 왜 0dp 를 넣으라고 했을까요? [레이아웃 가중치](https://developer.android.com/guide/topics/ui/layout/linear?hl=ko#Weight)
- LinearLayout 의 weight 값이 잘 적용되기 위해선, orientation에 따라, width 또는 height 의 값이 0dp 여야 함
    - orientation: vertical -> layout_height=“0dp”
    - orientation: horizontal -> layout_width="0dp"
