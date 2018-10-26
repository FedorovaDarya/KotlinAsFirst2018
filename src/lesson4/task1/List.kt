@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson5.task1.findSumOfTwo
import kotlin.math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble { it * it })


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
        if (list.isEmpty()) 0.0
        else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> = if (list.size == 0) list else {
    val average = list.sum() / list.size
    list.replaceAll { it -> it - average }
    list
}


/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double = a.zip(b).sumByDouble { (first, second) -> first * second }


/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double = p.mapIndexed { index, elem -> elem * x.pow(index) }.sum()

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    /*
    list.reverse()
    list.forEachIndexed { index, _ -> list[index] = list.subList(index, list.size).sum() }
    list.reverse()
    */
    for (i in list.size - 1 downTo 0) {
        for (j in 1..i) {
            list[i] += list[i - j]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var nn = n
    var i = 2
    val list = mutableListOf<Int>()
    while (nn != 1 && i <= nn) {
        if (nn % i == 0)
            while (nn % i == 0) {
                list.add(i)
                nn /= i
            }
        i++
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var copyOfn = n
    val digits = mutableListOf<Int>()
    while (copyOfn >= base) {
        digits.add(copyOfn % base)
        copyOfn /= base
    }
    digits.add(copyOfn % base)
    return digits.reversed()
}

@Suppress("IMPLICIT_CAST_TO_ANY")
        /**
         * Сложная
         *
         * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
         * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
         * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
         * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
         */
fun convertToString(n: Int, base: Int): String =
        convert(n, base).map { digit -> if (digit < 10) digit.toString() else (digit + 87).toChar() }.joinToString(separator = "")

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int =
        digits.reversed().foldRightIndexed(0) { idx, d, acc -> acc + d * base.toDouble().pow(idx).toInt() }


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
/*fun charToInt(c: Char): Int = c.hashCode()
    var res = 0
    str.forEachIndexed { index, elem ->
        res += if (charToInt(str[index]) - 48 < 10)
            (charToInt(elem) - 48) * base.toDouble().pow(str.length - index - 1).toInt()
        else (charToInt(elem) - 87) * base.toDouble().pow(str.length - index - 1).toInt()
    }
    return res
}
*/
fun charToInt(i: Char): Int = when (i) {
    in '0'..'9' -> i - '0'
    else -> i - 'a' + 10
}

fun decimalFromString(str: String, base: Int): Int =
        decimal((str.map { charToInt(it) }), base)


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val romanDigits = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    var nn = n
    var result = ""
    val arabianDigits = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var i = 12
    while (nn != 0) {
        while (arabianDigits[i] > nn) i--
        result += romanDigits[i]
        nn -= arabianDigits[i]
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun gender(n: Int): String = if (n in 1000..9999) "female" else "male"

fun repeatingPart(n: Int, mask: Int, gender: String): String {
    var res = ""
    res += when (n / mask / 100) {
        1 -> "сто "
        2 -> "двести "
        3 -> "триста "
        4 -> "четыреста "
        5 -> "пятьсот "
        6 -> "шестьсот "
        7 -> "семьсот "
        8 -> "восемьсот "
        9 -> "девятьсот "
        else -> ""
    }
    if (n / mask % 100 in 10..19) {
        res += when (n / mask % 100) {
            10 -> "десять "
            11 -> "одиннадцать "
            12 -> "двенадцать "
            13 -> "тринадцать "
            14 -> "четырнадцать "
            15 -> "пятнадцать "
            16 -> "шестнадцать "
            17 -> "семнадцать "
            18 -> "восемнадцать "
            19 -> "девятнадцать "
            else -> ""
        }
    } else {
        res += when (n / mask % 100 / 10) {
            2 -> "двадцать "
            3 -> "тридцать "
            4 -> "сорок "
            5 -> "пятьдесят "
            6 -> "шестьдесят "
            7 -> "семьдесят "
            8 -> "восемьдесят "
            9 -> "девяносто "
            else -> ""
        }
        if (n / mask % 100 - n / mask % 10 != 11 && n / mask % 10 == 1) {
            res += if (gender == "female") "одна " else "один "
        }
        res += if (n / mask % 100 - n / mask % 10 != 12 && n / mask % 10 == 2) {
            if (gender == "female") "две " else "два "
        } else {
            when (n / mask % 10) {
                3 -> "три "
                4 -> "четыре "
                5 -> "пять "
                6 -> "шесть "
                7 -> "семь "
                8 -> "восемь "
                9 -> "девять "
                else -> ""
            }
        }
    }
    return res
}

fun defineDeclination(n: Int, mask: Int): String {
    var res = ""
    when (n) {
        in 1000000000..Int.MAX_VALUE -> res += when {
            n / mask % 100 in 5..19 -> "миллиардов "
            n / mask % 10 == 1 -> "миллиард "
            n / mask % 100 in 2..4 -> "миллиарда "
            else -> "миллиардов "
        }
        in 1000000..999999999 -> res += when {
            n / mask % 100 in 5..19 -> "миллионов "
            n / mask % 10 == 1 -> "миллион "
            n / mask % 100 in 2..4 -> "миллиона "
            else -> "миллионов "
        }
        in 1000..999999 -> res += when {
            n / mask % 100 in 5..19 || n / mask % 10 == 0 -> "тысяч "
            n / mask % 10 in 2..4 -> "тысячи "
            n / mask % 10 == 1 -> "тысяча "
            else -> "тысяч "
        }
        else -> res += ""
    }
return res
}

fun russian(n: Int): String {
    var result = ""
    var nn = n
    var mask = 1000000000
    while (mask >= nn) {
        mask /= 1000
    }
    while (mask >= 1) {
        result += repeatingPart(nn, mask, gender(mask)) + defineDeclination(nn, mask)
        nn %= mask
        mask /= 1000
        if (mask == 1 && nn == 0) mask--
    }
    return result.substring(0, result.length - 1)
}