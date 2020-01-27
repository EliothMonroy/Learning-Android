class MyClass {
    fun test() {
        val str: String = "Result: ..."
        val result = str.let {//Change for run, apply, also with
            println("Receiver: "+this) // Receiver
            println("Argument: "+it) // Argument
            42 // Block return value
        }
        println(result)
    }
}

fun main() {
    MyClass().test()
}