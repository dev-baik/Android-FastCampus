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

### 변수 (val, var)가
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

    Kid("아이", 3, "male") // 100\n초기화 중 입니다.\n부 생성자 호출
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