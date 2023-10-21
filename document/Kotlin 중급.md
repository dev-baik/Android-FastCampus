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