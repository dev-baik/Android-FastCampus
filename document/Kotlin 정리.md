### 함수
```kotlin
fun main() {
    test1(id = "a", name = "b", nickname = "c") // bca
  
    val result = test2(1, c = 5) // 9
    println(result)
}

// Unit 생략 가능
fun test1(name: String, nickname: String, id: String): Unit {
    println(name + nickname + id)
}초

// Default Parameter, Single Expression Function
fun test2(a: Int, b: Int = 3, c: Int = 4): Int = a + b + c
```

### 변수 (val, var)
```kotlin
fun main() {
    // val = value = 값
    val a: Int = 3
    // 타입 추론
    val name = "a"

    // var = variable = 변경가능한
    var b: Int = 10
    b = 20
}
```

### 클래스
```kotlin
fun main() {
    val user = User("a")
    println(user.age) // 100

    Kid("아이", 3, "male") // 초기화 중 입니다.\n부 생성자 호출
}

open class User(open val name: String, open var age: Int = 100)

// constructor 생략 가능
class Kid constructor(override val name: String, override var age: Int) : User(name, age) {
    var gender: String = "female"

    init {
        println("초기화 중 입니다.")
    }

    constructor(name: String, age: Int, gender: String) : this(name, age) {
        this.gender = gender
        println("부 생성자 호출")
    }
}
```

### 조건식
```kotlin
fun main() {
    max(10, 3) // 10
    isHoliday("금") // 안좋아
}

fun max(a: Int, b: Int) {
    // 삼항연산자 불가능
    val result = if (a > b) a else b
    println(result)
}

fun isHoliday(dayOfWeek: String) {
    // else 필수
    // in 2..4 -> {}
    // in listOf("월", "화") -> {}
    val result = when(dayOfWeek) {
        "토", "일" -> if (dayOfWeek == "토") "좋아" else "너무 좋아"
        else -> "안좋아"
    }
    println(result)
}
```

### 반복문
```kotlin
fun main() {
    // 1 2 3 4 5 6 7 8 9 10
    for(i in IntRange(1, 10)) {
        print(i)
        print(" ")
    }
    
    // 1 2 3 4 5 6 7 8 9
    for(i in 1 until 10) {
        print(i)
        print(" ")
    }
    
    // 1 3 5 7 9
    for(i in 1..10 step(2)) {
        print(i)
        print(" ")
    }
    
    // 10 9 8 7 6 5 4 3 2 1
    for(i in 10 downTo 1) {
        print(i)
        print(" ")
    }
    
    // 10 8 6 4 2
    // for(i in 10..1 step(-2))
    for(i in 10 downTo 1 step(2)) {
        print(i)
        print(" ")
    }
}
```

### 컬렉션 (list, map, set)
```kotlin
fun main() {
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.add(6)
    list.addAll(listOf(7, 8, 9))

    // Immutable List
    val list1 = listOf(1, 2, 3, 4)
    println(list1.map { it * 10 }.joinToString("/")) // 10/20/30/40
    // 다양한 타입을 넣을 수 있음
    val diverseList = listOf(1, "안녕", 1.78, true)

    println(list.joinToString(", ")) // 1, 2, 3, 4, 5

    val map = mapOf((1 to "안녕"), (2 to "hello"))
    val map1 = mutableMapOf((1 to "안녕"), (2 to "hello"))
    map1.put(3, "응") // map1[3] = "응"
}
```

### Null
```kotlin
fun main() {
    var nickname: String? = null

    // 엘비스 연산자
    var result = nickname?: "값이 없음"
    print(result) // 값이 없음
}
```

### 타입체크와 캐스팅
```kotlin
fun main() {
    println(check("안녕")) // 문자열
    println(check(true)) // 몰라요

	cast("안녕") // 안녕
    cast(10) // null -> 실패

    println(smartcast("안녕")) // 2
    println(smartcast(10)) // 9
}

fun check(a: Any): String {
    return if(a is String) {
        "문자열"
    } else if (a is Int) {
        "숫자"
    } else {
        "몰라요"
    }
}

fun cast(a: Any) {
    val result = (a as? String) ?: "실패"
    println(result)
}

fun smartcast(a: Any): Int {
    return if(a is String) {
        a.length
    } else if(a is Int) {
        a.dec()
    } else {
        -1
    }
}
```

### String template
```kotlin
fun main() {
    val a = 10
    val name = "안녕"
    val isHigh = true
    
    println(a.toString() + name + isHigh.toString()) // 10안녕true
    
    println(String.format("%s %d", name, a)) // 안녕 10
    
    println("$a ${name.length} $isHigh") // 10 2 true
}
```

### 람다
```kotlin
fun main() {
    // 1. 익명 함수
    val a = fun() { println("hello") }

    // 2. 변수처럼 사용되서, 함수의 argument가 되거나 return이 될 수 있음 (마지막 줄)
    val b : (Int) -> Int = { it * 10 }
    print(b) // Function1<java.lang.Integer, java.lang.Integer> : 람다 식을 의미
    print(b(10)) // 100

    // i, j 중 하나의 타입은 필수
    val c = { i : Int, j : Int -> i * j }
    val d : (Int, String, Boolean) -> String = { _, b, _ -> b }
    
    hello(10, b)
}

fun hello(a: Int, b: (Int) -> Int): (Int) -> Int {
    println(a) // 10
    println(b(20)) // 200
    return b
}
```

### SAM(Single Abstract Method) : 단일 추상 메소드
함수형 인터페이스를 인자로 받는 Java 함수를 호출할 경우, 인터페이스 객체 대신 람다를 넘길 수 있음

### SAM 인터페이스
- 추상 메소드가 단 1개 잇는 인터페이스
- 함수형 인터페이스 라고도 한다 (Functional Interface)

```kotlin
val view = View(this)

view.setOnClickListener(
    // Java는 View.OnClickListener 인터페이스(함수형 인터페이스)를 구현한 것을 받음
    new View.OnClickLstener() {
        @Override
        public void onClick(view: View) {
            //
        }
    }

    // Kotlin은 함수형 인터페이스 대신 람다 식만을 넘길 수 있음
    view.setOnClickListener({ println("안녕") })
)
```

### 확장함수(Extension Funcition)
기존에 정의되어 있는 클래스에 함수를 추가하는 기능

```kotlin
fun main() {
    Test().hello()
    Test().hi()
}

class Test() {
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}

fun Test.hi() = print("하이")
```

### 범위 지정 함수(Scope function)
- 코틀린 표준 라이브러리에서 제공하는 확장함수
- 목적 : 간결성, 명료성, 유지보수 용이성
- 정의 : 객체의 컨텍스트 내에서 실행 가능한 코드 블록을 만드는 함수
    - 호출 시, 임시 범위가 생성되며, 이 범위 안에서는 이름 없이 객체에 접근 가능
    - context : 문맥, 맥락, 전후 사정
- 수신 객체 (receiver) : 확장 함수가 호출되는 대상이 되는 값 (객체)
- 수신 객체 지정 람다 (lambda with receiver) : 수신 객체를 명시하지 않고, 람다의 본문 안에서 해당 객체의 메서드를 호출할 수 있게 하는 것
- 차이점
    - 수신 객체 접근 방법 : this, it
    - Return 값 : 수신 객체, 마지막 행 (lambda result)

<table>
    <tr>
        <td colspan="4">코틀린 범위 지정 함수</td>
    </tr>
    <tr>
        <td>수신 객체</td>
        <td colspan="2">확장 함수로 호출</td>
        <td>함수의 인자</td>
    </tr>
    <tr>
        <td>this (생략 가능)</td>
        <td>apply</td>
        <td>run</td>
        <td>with</td>
    </tr>
    <tr>
        <td>it (생략 불가능)</td>
        <td>also</td>
        <td>let</td>
        <td></td>
    </tr>
    <tr>
        <td>return</td>
        <td>수신 객체</td>
        <td colspan="2">람다 식의 마지막 행</td>
    </tr>
</table>

- let : null 체크를 해야 할 때(?.let), 지역 변수를 명시적으로 표현해야 할 때
```kotlin
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
)

fun main() {
    var user: User? = User("영희", 10, true)
    val age = user?.let {
        user.age
    }
    println(age) // 10
}
```
- run : 객체를 초기화하고 리턴 값이 있을 때
```kotlin
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
)

fun main() {
    val kid = User("아이", 4, false)
    val kidAge = kid.run {
        age
    }
    println(kidAge) // 4
}
```
- apply : 객체 초기화
```kotlin
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
    var hasGalsses: Boolean = true,
)

fun main() {
    val kid = User("아이", 4, false)
    val kidName = kid.apply {
        name
    }
    println(kidName) // 수신객체 자기 자신(kid) 반환
  
    val female = User("슬기", 20, true, true)
    val femaleValue = female.apply {
        hasGalsses = false
    }
    print(femaleValue.hasGalsses) // false
}
```
- also : 수신 객체를 명시적으로 사용하고 싶을 때, 로그를 남길 때
```kotlin
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
    var hasGalsses: Boolean = true,
)

fun main() {
    val male = User("민수", 17, false, true)
    val maleValue = male.also {
        println(it.name) // 민수
    }
}
```
- with : 객체 초기화, 람다 리턴 값이 필요 없을 때
```kotlin
class User(
    val name: String,
    val age: Int,
    val gender: Boolean,
    var hasGalsses: Boolean = true,
)

fun main() {
    val male = User("민수", 17, false, true)
    val result = with(male) {
        hasGalsses = false
        true
    }
}
```

### 초기화 지연 latinit, lazy
- 정의 : 변수를 선언할 때 값을 지정하지 않고, 나중에 지정할 수 있는 방법
- 목적 : 메모리를 효율적으로 사용하기 위해서, null safe 한 value를 사용하기 위해서

#### lateinit, var
- 변수 타입을 지정해줘야함
- primitive 타입은 사용할 수 없음 Ex) `lateinit var age: Int`
- 선언 후, 나중에 초기화 해줘도 됨
```kotlin
lateinit var text: String
lateinit var age: Integer

fun main() {
    // println(text) // Error: Not been initialized
    text = "name"
    age = Integer(10)
    println(text)
}
```

#### lazy, val
- 선언과 동시에 초기화를 해야함
- 호출 시점에 초기화가 이루어짐
```kotlin
val test : Int by lazy {
    println("초기화 중")
    100
}

fun main() {
    println("메인 함수 실행")
    println("초기화 한 값 $test")
    println("두번째 호출 $test")
}

//// 결과
// 메인 함수 실행
// 초기화 중
// 초기화 한 값 100
// 두번째 호출 100
```

### data, sealed class
#### Data class : 데이터를 담기 위한 클래스
- toString(), hashCode(), equals(), copy() 메서드를 자동으로 생성
    - override 하면, 직접 구현한 코드를 사용 가능
- 1개 이상의 프로퍼티가 있어야함
- 데이터 클래스는 abstract, open, sealed, inner를 붙일 수 없음
- 상속이 불가능
```kotlin
class Person(
    val name: String,
    val age: Int,
)

data class Dog(
    val name: String,
    val age: Int,
)

data class Dog2(
    val name: String,
    val age: Int,
) {
    override fun toString(): String {
        return "직접 구현 $name"
    }
}

fun main() {
    val person = Person("수지", 24)
    val dog = Dog("해피", 24)
    val dog2 = Dog("해피", 24)
  
    println(person.toString()) // 참조값
    println(dog.toString()) // Dog(name=해피, age=24)
    println(dog2.toString()) // 직접 구현 해피
    println(dog.copy(age = 3).toString()) // Dog(name=해피, age=3)
}
```

#### Sealed class : 추상 클래스로, 상속받은 자식 클래스의 종류를 제한
- 컴파일러가 sealed 클래스의 자식 클래스가 어떤 것인지 암
- when과 함께 쓰일 때, 장점을 느낄 수 있음
```kotlin
abstract class Cat
class BlueCat : Cat()
class RedCat : Cat()
class GreenCat : Cat()

fun main() {
    val cat: Cat = BlueCat()
    val result = when(cat) {
        is BlueCat -> "Blue"
        is RedCat -> "Red"
        is GreenCat -> "green"
        else -> "none"
    }
    println(result)
}
```
```kotlin
sealed class Cat
class BlueCat : Cat()
class RedCat : Cat()
class GreenCat : Cat()
class WhiteCat : Cat()

fun main() {
    val cat: Cat = BlueCat()
    val result = when (cat) {
        is BlueCat -> "Blue"
        is RedCat -> "Red"
        is GreenCat -> "green"
        is WhiteCat -> "white"
    }
    println(result)
}
```

### object, companion object
#### Object : 클래스를 정의함과 동시에 객체를 생성
- 싱글톤을 쉽게 만들 수 있는 키워드
- 생성자 사용 불가
- 프로퍼티, 메서드, 초기화 블록은 사용 가능
- 다른 클래스나, 인터페이스를 상속받을 수 있음
```kotlin
fun main() {
    Counter.countUp()
    Counter.countUp()
  
    println(Counter.count) // 싱글톤 일 때: 2 vs 싱글톤 아닐때: 0
  
    Counter.hello() // Hello
}

object Counter : Hello() {
    init {
        println("카운터 초기화") // 한번만 호출됨
    }
    var count = 0
    fun countUp() {
        count++
    }
}

open class Hello() {
    fun hello() = println("Hello")
}
```

#### Companion Object : 동반 객체
- Java의 static과 동일한 역할
- 클래스 내에 하나만 생성할 수 있음
```kotlin
class Book {
    // object Companion { ... }
    companion object Novel {
        const val NAME = "hello"

        fun create() = Book()
    }
}
```