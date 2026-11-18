package uni.projects

object PatternRecognizer {

    private val prefix = "192.168.1."
    private val acceptingStates = setOf(11, 12, 13)

    fun accepts(input: String): Boolean {
        var state = 0

        for (symbol in input) {
            state = nextState(state, symbol) ?: return false
        }

        return if (state in acceptingStates){
            println("Конечное состояние S${state}")
            true
        }
        else{
            println("Состояние S${state} не принимающее")
            false
        }
    }

    private fun nextState(state: Int, symbol: Char): Int? {
        return when (state) {
            in 0..9 -> {
                if (symbol == prefix[state]) {
                    println("Состояние S${state} пройдено")
                    state + 1
                } else {
                    println("Состояние S${state} не пройдено")
                    null
                }
            }

            in 10..12 -> {
                if (symbol in '0'..'9') {
                    println("Состояние S${state} пройдено")
                    state + 1
                }else{
                    println("Состояние S${state} не пройдено")
                    null
                }
            }

            else -> {
                println("Состояния S${state} не существует")
                null
            }
        }
    }
}

fun main(){
    while(true){
        println("Введите IP, который нужно проверить или нажмите Enter для выхода из программы...")

        val string = readln()
        if(string.isBlank()){
            println("Выход из программы.")
            break
        }

        if(PatternRecognizer.accepts(string)){
            println("Строка соответствует паттерну")
        }
        else{
            println("Строка не соответствует паттерну")
        }
    }
}