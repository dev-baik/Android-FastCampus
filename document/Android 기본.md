# Android OS
- Linux 기반의 운영체제
- 오픈소스 기반
- 스마트폰 OS의 70%를 차지함
- 스마트폰 뿐만 아니라 웨어러블 디바이스, TV, 자동차 등 다양한 플랫폼에서 동작할 수 있는 OS
- 버저닝이 디저트 이름으로 되어 있음

# Android 기본 요소
## [앱의 구성요소](https://developer.android.com/guide/components/fundamentals?hl=ko)
- Android 앱의 필수적인 구성요소, 각각은 시스템이나 사용자가 앱에 들어올 수 있는 진입점

### [Activity](https://developer.android.com/guide/components/activities/intro-activities?hl=ko)
- 앱과 사용자가 상호작용을 하기 위한 진입점
    - 앱을 실행을 할 떄는 앱을 전체적으로 호출하는 것이 아니라 앱의 액티비티를 호출
- 모든 앱에는 반드시 1개 이상 존재
- Activity는 사용자와 상호작용을 위한 UI가 있음
    - 앱이 실행되면, 화면이 표시됨
    - 사용자의 입력값을 받음 (화면 클릭, 더블 클릭, 롱클릭, 스와이프, 드래그 앤 드랍 등)
    - 사용자에게 제공하고자 하는 내용을 화면에 표시함
- Lifecycle이 있음

### [Service](https://developer.android.com/guide/components/services?hl=ko)
- 백그라운드에서 오래 실행되는 작업 수행을 위한 컴포넌트
- 사용자가 다른 앱으로 전환하더라도 백그라운드에서 계속 실행
- UI 없음
- 포그라운드 서비스 : 사용자에게 잘 보이는 작업. 포그라운드 서비스의 경우, 반드시 알림을 표시해야 하며, 사용자가 앱과 상호작용하지 않을 때도 계속 실행됨
    - ex) 음악 재생
- 백그라운드 서비스 : 사용자에게 직접 보이지 않는 작업
    - ex) 사용자에게 보이지 않는 작업 (저장소 압축, 게임 업데이트, 파일 압축 등)
    - 앱이 API 레벨 26 이상을 대상으로 할 경우
        - 즉시 실행해야 하는 작업 : Work Manager
        - 지연 작업 : Alarm Manager
- 바인드 서비스 : 앱 컴포넌트가 bindService를 호출해 서비스를 호출하면 서비스가 바인딩 됨. 바인딩 된 서비스는 클라이언트-서버 인터페이스를 제공해 서비스와 상호 작용 함. 여러개가 한꺼번에 바인딩 될 수 있고, 바인딩 된 컴포넌트가 모두 종료되면, 서비스도 종료됨
  ![service_lifecycle](https://github.com/dev-baik/Android-FastCampus/assets/96613859/e6a7031b-5c8e-46b2-bab1-4eb7348f5bd7)

### BroadcastReceiver
- 안드로이드 OS에서 발생하는 이벤트와 정보를 앱에서 수신할 수 있도록 하는 구성요소
- UI 없음
- 예시 : 화면이 꺼졌거나, 배터리가 부족하거나, 사진을 캡처했거나

### ContentProvider
- 파일 시스템, SQLite 데이터베이스, 웹상이나 앱이 액세스할 수 있는 다른 모든 영구 저장위치에 저장 가능한 앱 데이터의 공유형 집합을 관리
- 다른 앱은 콘텐츠 프로바이더를 통해 해당 데이터를 질의하거나, 수정할 수 있음
- 예시 : 연락처 정보, 갤러리 이미지/비디오

### [Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro?hl=ko)
- 앱의 필수적인 정보를 담고 있는 파일
    - 앱의 패키지 이름, 앱의 구성요소, 권한, 필요한 기능

### Intent
- 구성요소간의 통신을 할 수 있게 하는 역할
- 앱에 포함된 구성요소 이외에, 다른 앱의 구성요소와도 통신할 수 있음
    - 명시적 인텐트 : 특정 컴포넌트, 액티비티를 명확히 특정해 실행할 경우
        - 예시) AActivity에서, BActivity 실행을 호출할 경우
    - 암시적 인텐트 : 동작을 특정하긴 했지만, 실행될 대상이 달라질 수 있는 경우
        - 예시) 특정 URL을 실행 이라는 액션을 요청 경우, 웹브라우저 기능을 가진 다수의 앱이 호출 될 수 있는 경우

# Activity Lifecycle
- 앱의 완성도와 안전성을 높이기 위해 반드시 알아야 함
    - 다른 앱으로 전환 시, 비정상 종료 되는 문제
    - 사용자가 앱을 사용하지 않는데, 시스템 리로스가 소비되는 문제
    - 사용자가 앱을 나갔다가 돌아왔을 때, 진행상태가 저장되지 않는 문제
    - 화면이 가로 ↔️ 세로 전환 될 때, 비정상 종료되거나, 진행상태가 저장되지 않는 문제
- 콜백
    - onCreate
        - 필수적으로 구현해야함
        - Activity의 생명주기 중 한 번만 발생해야하는 로직을 실행
            - 멤버 변수 정의
            - UI 구성 (setContentView, xml 레이아웃 파일 정의)
        - saveInstanceState 매개 변수 수신 ➡️ Acitivty 이전 저장 상태가 포함된 Bundle 객체
    - onStart
        - Activity가 사용자에게 표시
        - 앱은 Activity를 포그라운드로 보내 상호작용할 수 있도록 준비
    - onResume
        - Activity가 포그라운드에 표시되어, 사용자와 상호 작용 할 수 있는 상태
        - 앱에서 포커스가 떠날 때 까지 onResume 상태에 머무름
    - onPause
        - 사용자가 활동을 떠나는 첫 번째 신호
        - 매우 짧음
            - 활동이 포그라운드에 있지 않지만, 잠시 후 다시 시작할 작업을 일시 중지 하거나 조정
            - ex) 반투명 Activity가 띄워져 포커스가 없지만 화면에 보이는 상태
            - 이 상태를 통해서, 실행중이지 않을 때 필요하지 않는 리소스를 해지할 수 있음
            - 이 상태에서, 데이터를 저장하거나, 네트워크 호출, DB의 IO 작업을 화면 안됨
                - 매우 짧은 시간이라 메서드가 끝나기 전에 Activity가 종료될 수 있음
    - onStop
        - Activity가 사용자에게 더 이상 표시 되지 않는 상태
        - CPU를 비교적 많이 소모하는 종료 작업을 실행해야함
            - DB 저장
        - Activity가 중단되면, Android OS에서 리소스 관리를 위해, 해당 Activity가 포함된 프로세스를 소멸시킬 수 있음
    - onDestroy
        - Activity가 완전히 종료되기 전에 실행
        - 호출되는 케이스
            - finish 호출되어 Activity가 종료될 때
            - configurationChange (ex 기기 회전, 멀티 윈도우)로 인해, 시스템이 Activity를 일시적으로 소멸 시킬 때

![activity_lifecycle](https://github.com/dev-baik/Android-FastCampus/assets/96613859/3d2398a3-d662-4462-bf94-f1fd9e5f0987)

# View 그려지는 과정
- UI를 그리는 기본 구성요소
- CustomView를 만들기 위함

## [How Android Draws View](https://developer.android.com/guide/topics/ui/how-android-draws?hl=ko)
![img](https://github.com/dev-baik/Android-FastCampus/assets/96613859/d96fefde-0898-45b9-a2bf-9d01a473532d)
- 전위순회 방식을 쓰기 때문에, 부모부터 자식 뷰 순서로 그려지게 됨

- measure
    - 뷰의 크기를 계산
    - 모든 뷰는 각각 자신의 width, height를 계산
    - measure 과정에서 부모 - 자식 뷰간의 크기 정보 전달을 위해 2가지 클래스 사용
        - ViewGroup.LayoutParams : 자식 뷰가 부모 뷰에게 자신이 어떻게 측정되고 위치를 정할지 요청할 때 사용
            - DP, PX... : 자식뷰가 원하는 사이즈
            - MATCH_PARENT : 부모 뷰 사이즈와 똑같이 자식뷰 사이즈 지정
            - WRAP_CONTENT : 부모 뷰 안에서 content를 표현할 수 있는 fit 한 사이즈 조정
        - ViewGroup.MeasureSpecs : 부모 뷰가 자식 뷰에게 요구사항을 전달할 때 사용
            - UNSPECIFIED : 부모 뷰는 자식 뷰가 원하는 사이즈로 결정
            - EXACTLY : 부모 뷰가 자식 뷰의 사이즈를 정확히 지정할 때
            - AT_MOST : 부모 뷰가 자식 뷰의 최대 사이즈를 지정할 때

- layout
    - 뷰의 크기와 위치를 할당
    - 부모 기준의 상대적 위치(left, top, right, bottom)를 계

- draw
    - 뷰를 그리는 단계
        - Canvas : 뷰의 모양을 그리는 객체
        - Paint : 뷰의 색상을 칠하는 객체
    - measure에서 측정한 크기로, layout에서 계산한 위치에 뷰를 그림
    - 이 콜백은 언제든 다시 호출될 수 있음
        - scroll이나 swipe를 하게 되면 뷰는 onDraw 다시 호출
        - 객체 할당과 같이 리소스가 많이 소모되는 로직은 추가하지 말 것

- ViewUpdate : 런타임에 뷰를 다시 그리게 하는 함수
    - invalidate : view에 변화가 생겨서 다시 그려야 할 때
        - color 변화 등
    - requestLayout : view를 처음부터 그려야 할 때
        - 크기가 변화해서 measure부터 다시 해야 할 때
          ![다운로드](https://github.com/dev-baik/Android-FastCampus/assets/96613859/b4c2f631-4b2d-4619-886d-a1b6b204a6d9)
        - textView LongClick 후 text 변경
          ![다운로드 (1)](https://github.com/dev-baik/Android-FastCampus/assets/96613859/b9e2a608-0a07-4f67-917b-6db65f0db961)
