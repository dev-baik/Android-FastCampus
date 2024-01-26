## [UI](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md)
- Chapter01-3 : [Guideline](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Guideline), [Style](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Style), [Layer](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Layer), [DatePickerDialog](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#DatePickerDialog), [Spinner](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Spinner)
- Chapter01-4 : [Flow](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#flow), Theme/Color(Light/Dark), [BigDecimal](https://ducktyping.tistory.com/19)
- Chapter01-5 : ProgressBar(Horizontal), [NumberPicker](https://min-wachya.tistory.com/218)
- Chapter01-6 : [Barrier](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#Barrier), [TextInputLayout, TextInputEditText](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#TextInputLayout-TextInputEditText), [ChipGroup](https://github.com/dev-baik/Android-FastCampus/blob/master/document/UI%20%EC%A0%95%EB%A6%AC.md#ChipGroup)(checkedChipId)
- Chapter02-5 : Chip(clearCheck), LottieAnimationView, TextInputLayout(IME_ACTION_SEARCH)
- Chapter02-8 : [BottomSheet](https://m2.material.io/components/sheets-bottom/android#using-bottom-sheets)(behavior_expandedOffset, behavior_fitToContents, behavior_hideable, behavior_peekHeight), [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)(layout_constraintHorizontal_chainStyle, layout_constraintWidth)
- Chapter02-9 : CoordinatorLayout + FloatingActionButton(layout_anchor, layout_anchorGravity), [LottieAnimationView](https://m.blog.naver.com/saqwzx002/222111854733)(scaleX, scaleY, alpha)(withStartAction, withEndAction + start)


## [Kotlin](https://velog.io/@dev-baik/Kotlin)
- [Kotlin 정리](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Kotlin%20%EC%A0%95%EB%A6%AC.md)
- Chapter01-2 : 산술 연산자(plus, minus, times, div, mod/rem), not. [isNullOrEmpty](https://codechacha.com/ko/kotlin-string-null-empty-check/)
- Chapter01-3 : [val/var/const val](https://velog.io/@dev-baik/%EB%B3%80%EC%88%98-%EC%84%A0%EC%96%B8), [let/run/apply/also/with](https://velog.io/@dev-baik/%EB%B2%94%EC%9C%84-%EC%A7%80%EC%A0%95-%ED%95%A8%EC%88%98)
- Chapter01-4 : [StringBuilder](https://velog.io/@dev-baik/String-vs-StringBuilder-vs-StringBuffer)([append, clear](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/)), [DecimalFormat](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md#DecimalFormat)
- Chapter01-6 : [Parcelize](https://developer.android.com/kotlin/parcelize?hl=ko), [data class](https://velog.io/@dev-baik/Data-Class), [indexOfFirst](https://gold.gitbook.io/kotlin/collections/elements-operations/indexoffirst), [firstOrNull](https://gold.gitbook.io/kotlin/collections/elements-operations/firstornull)
- Chapter01-7 : [filterIsInstance](https://blog.yena.io/studynote/2020/01/22/Kotlin-Collection-Filter.html), [toTypedArray](https://www.techiedelight.com/ko/convert-list-to-array-kotlin/)
- Chapter02-2 : [take, takeLast](https://kotlinworld.com/12)
- Chapter02-4 : [count](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/count.html), [orEmpty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/or-empty.html)
- Chapter02-5 : [forEachIndexed](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/for-each-indexed.html), [find](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/find.html), fun List<NewsItem>.transform(): List<NewsModel> { ... }
- Chapter02-6 : [getValue](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/get-value.html)
- Chapter02-7 : [sortWith](https://codechacha.com/ko/kotlin-sorting-list/), LocalDateTime
- Chapter02-9 : System.currentTimeMillis()


## [Android](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md)
- chapter01-2 : [addTextChangedListener](https://hulrud.tistory.com/37), [onSaveInstanceState](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko#save-simple,-lightweight-ui-state-using-onsaveinstancestate), [onRestoreInstanceState](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko#restore-activity-ui-state-using-saved-instance-state)
- chapter01-3 : [SharedPreference](https://developer.android.com/training/data-storage/shared-preferences?hl=ko)(apply, clear), [Intent](https://developer.android.com/guide/components/intents-filters?hl=ko)([화면 전환](https://developer.android.com/training/basics/firstapp/starting-activity?hl=ko), [전화 앱 실행](https://developer.android.com/guide/components/intents-common?hl=ko#DialPhone))
- Chapter01-5 : [Timer](https://magicalcode.tistory.com/entry/%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9C%BC%EB%A1%9C-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C2), [Thread](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md#thread), [ToneGenerator](https://developer.android.com/reference/android/media/ToneGenerator), [Custom AlertDialog](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md#Custom-AlertDialog), [addView](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md#addView)
- Chapter01-6 : [Room](정리중), [registerForActivityResult](https://developer.android.com/training/basics/intents/result?hl=ko)
- Chapter01-7 : requestPermissions, image/*, DiffUtil, viewType, [onCreateOptionsMenu, onOptionsItemSelected](https://github.com/dev-baik/Android-FastCampus/blob/master/document/Android%20%EC%A0%95%EB%A6%AC.md#%EC%98%B5%EC%85%98-%EB%A9%94%EB%89%B4), [TabLayoutMediator](https://dev-baik.tistory.com/entry/ViewPager2%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Tab%EC%9C%BC%EB%A1%9C-%EC%8A%A4%EC%99%80%EC%9D%B4%ED%94%84-%EB%B7%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0), toolbar 
- Chapter01-8 : [BroadcastReceiver(receiver)](https://dev-baik.tistory.com/entry/Broadcast-Receiver-Codelab-PowerReceiver), [Service(MEDIA_PLAYER)](https://dev-baik.tistory.com/entry/Service-Component), [Notification](https://developer.android.com/training/notify-user/build-notification?hl=ko)
- Chapter02-1 : [WebViewClient](https://readystory.tistory.com/181)(supportFragmentManager.fragments), TabLayoutMediator(tab 텍스트 변경)
- Chapter02-2 : [MediaRecorder](https://developer.android.com/guide/topics/media/mediarecorder?hl=ko), Timer([Handler + Runnable](정리중), removeCallbacks), [View(context, attrs, defStyleAttr)](https://velog.io/@dev-baik/View)
- Chapter02-3 : ServerSocket 
- Chapter02-4 : addInterceptor, addTextChangedListener(Handler + Runnable), addOnScrollListener(Paging)
- Chapter02-5 : TikXml(exceptionOnUnreadXml, Xml, Element, PropertyElement)
- Chapter02-6 : Firebase(Authentication, Realtime Database, FCM), FirebaseMessagingService, AdapterDataObserver, OkHttp3(Retrofit X)
- Chapter02-7 : [SETTINGS](https://hellose7.tistory.com/84), [LocationServices](https://developer.android.com/training/location/retrieve-current?hl=ko), [Geocoder](https://developer.android.com/reference/kotlin/android/location/Geocoder), [successCallback, failureCallback], [AppWidget](https://developer.android.com/guide/topics/appwidgets?hl=ko), [PendingIntent](https://velog.io/@haero_kim/Android-PendingIntent-%EA%B0%9C%EB%85%90-%EC%9D%B5%ED%9E%88%EA%B8%B0)(getActivity, getForegroundService), [RemoteViews](https://developer.android.com/guide/topics/appwidgets?hl=ko#CreatingLayout), [백그라운드에서 포그라운드 서비스 실행이 허용되는 경우](https://developer.android.com/about/versions/12/foreground-services?hl=ko#cases-fgs-background-starts-allowed)([startForegroundService](https://developer.android.com/develop/background-work/services/foreground-services?hl=ko), [startForeground](https://developer.android.com/guide/components/services?hl=ko#Foreground))
- Chapter02-8 : Naver Map, Search, [Moshi](https://github.com/square/moshi)(JsonClass, field), Interceptor
- Chapter02-9 : [Kakao Login](https://developers.kakao.com/docs/latest/ko/kakaologin/android), Firebase(FirebaseAuthUserCollisionException), [Google Map](https://developers.google.com/maps/documentation/android-sdk/start?hl=ko)([CameraUpdateFactory](https://developers.google.com/maps/documentation/android-sdk/views?hl=ko#moving_the_camera), [moveCamera, animateCamera](https://developers.google.com/maps/documentation/android-sdk/views?hl=ko#updating_the_camera_view), [set(OnMarker, OnMap)ClickListener](https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap)), [FusedLocationProviderClient](https://developer.android.com/training/location/retrieve-current?hl=ko)([requestLocationUpdates](https://developer.android.com/training/location/retrieve-current?hl=ko#BestEstimate)([locationRequest](https://developer.android.com/training/location/change-location-settings?hl=ko), [LocationCallback](https://developer.android.com/training/location/request-updates?hl=ko))), Realtime Database(addValueEventListener, addChildEventListener), Glide(transform(RoundedCorners), RequestListener\<Bitmap>)