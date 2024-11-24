package utils


fun readIntNotNull() = readlnOrNull()?.toIntOrNull() ?: -1

fun readNextInt(prompt: String?): Int {
    do {
        try {
            print(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\tPlease enter a number.")
        }
    } while (true)
}

fun readDoubleNotNull() = readlnOrNull()?.toDoubleOrNull() ?: -1.0

fun readNextDouble(prompt: String?): Double {
    do {
        try {
            print(prompt)
            return readln().toDouble()
        } catch (e: NumberFormatException) {
            System.err.println("\tPlease enter a decimal number.")
        }
    } while (true)
}

fun readFloatNotNull() = readlnOrNull()?.toFloatOrNull() ?: -1.0f

fun readNextFloat(prompt: String?): Float {
    do {
        try {
            print(prompt)
            return readln().toFloat()
        } catch (e: NumberFormatException) {
            System.err.println("\tPlease enter a decimal number.")
        }
    } while (true)
}

fun readNextLine(prompt: String?): String {
    print(prompt)
    return readln()
}

fun readNextChar(prompt: String?): Char {
    do {
        try {
            print(prompt)
            return readln().first()
        } catch (e: Exception) {
            System.err.println("\tPlease enter a character.")
        }
    } while (true)
}