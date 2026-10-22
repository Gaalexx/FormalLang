package ai.neuroframework

import java.io.OutputStream


object PatternRecognizer {

    private val prefix = "192.168.1."
    private val acceptingStates = setOf(11, 12, 13)

    fun accepts(input: String): Boolean {
        var state = 0

        for (symbol in input) {
            state = nextState(state, symbol) ?: return false
        }

        return state in acceptingStates
    }

    private fun nextState(state: Int, symbol: Char): Int? {
        return when (state) {
            in 0..9 -> {
                if (symbol == prefix[state]) state + 1 else null
            }

            in 10..12 -> {
                if (symbol in '0'..'9') state + 1 else null
            }

            else -> null
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