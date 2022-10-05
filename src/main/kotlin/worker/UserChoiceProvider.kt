package worker

import kotlin.NumberFormatException

class UserChoiceProvider {
    fun provideInRange(from: Int = 0, to: Int = Int.MAX_VALUE): Int {
        var input: String
        var value: Int
        while (true) {
            print("Ваш выбор: ")
            input = readLine()!!

            try {
               value = input.toInt()
            } catch (ex: NumberFormatException) {
                println("Неверный формат ввода. Введите целое число.")
                continue
            }

            if(value in from..to) {
                return value
            }
            println("Введите число от $from до $to")
        }

    }
}
