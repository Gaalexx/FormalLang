package uni.projects

class ShopMemory(private val rules: Map<String, List<List<String>>>){

    private fun movement(word: String, ptr: Int, stack: ArrayDeque<String>): Boolean {
        if(stack.isEmpty()){
            return word.length == ptr
        }
        else{
            if(word.length == ptr){
                return false
            }
        }
        if(word[ptr].toString() == stack.last()){
            stack.removeLast()
            return movement(word, ptr+1, stack)
        }

        val element = stack.removeLast()
        val rulesForElement = rules[element] ?: return false

        var flag = false

        for(rule in rulesForElement){
            val newStack = ArrayDeque(stack)
            for(letter in rule.reversed()){
                newStack.addLast(letter)
            }
            flag = flag || movement(word, ptr, newStack)
        }

        return flag
    }

    fun accepts(word: String): Boolean {
        return movement(word, 0, ArrayDeque<String>(listOf("S")))
    }
}

fun main(){

    val rules = mapOf(
        "S" to listOf(listOf("U_a", "K")),
        "K" to listOf(listOf("A", "B")),
        "A" to listOf(listOf("U_b", "C")),
        "B" to listOf(listOf("U_c", "A")),
        "C" to listOf(listOf("U_d", "D")),
        "D" to listOf(listOf("f"), listOf("U_c", "T")),
        "T" to listOf(listOf("U_x", "M"), listOf("x")),
        "M" to listOf(listOf("U_x", "M"), listOf("x")),
        "U_a" to listOf(listOf("a")),
        "U_b" to listOf(listOf("b")),
        "U_c" to listOf(listOf("c")),
        "U_d" to listOf(listOf("d")),
        "U_x" to listOf(listOf("x"))
    )

    val shopMemory = ShopMemory(rules)

    while(true){
        println("Введите слово, которое надо проверить...")

        val string = readln()
        if(string.isBlank()){
            println("Выход из программы.")
            break
        }

        if(shopMemory.accepts(string)){
            println("Соответствие грамматике")
        }
        else{
            println("Несоответствие грамматике")
        }
    }
}

