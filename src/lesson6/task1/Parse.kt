@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import lesson4.task1.decimalFromString
import java.lang.NumberFormatException
import kotlin.math.abs

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val strSplitted = str.split(" ")
    if (strSplitted.size != 3) return ""
    val months = listOf("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val month = if (strSplitted[1] in months) months.indexOf(strSplitted[1]) + 1
    else 0
    val year = strSplitted[2].toIntOrNull()
    val day = if (year != null && strSplitted[0].toInt() <= daysInMonth(month, year)) strSplitted[0].toInt()
    else 0
    return if (day == 0 || month == 0 || year == null) ""
    else String.format("%02d.%02d.%d", day, month, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parsed = digital.split(".")
    if (parsed.size != 3) return ""
    val year = parsed[2].toIntOrNull()
    val months = listOf("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val month = if (parsed[1].toIntOrNull() in 1..12) months[parsed[1].toInt() - 1]
    else ""
    val day = if (year != null && parsed[0].toInt() <= daysInMonth(parsed[1].toInt(), year)) parsed[0].toInt()
    else 0
    return if (day == 0 || month == "") ""
    else String.format("%d %s %d", day, month, year)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */

fun flattenPhoneNumber(phone: String): String {
    var result = ""
    val regexForbidden = """[^-+)\s\d(]""".toRegex()
    if (regexForbidden.containsMatchIn(phone)) return ""
    if ("+" in phone && phone.lastIndexOf('+') != 0) return ""
    if (phone.contains(regexForbidden)) return ""
    else {
        val searchHere = "+0123456789"
        phone.forEach { ch ->
            if (ch in searchHere) result += ch
        }
    }
    return result
}


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val regexForbidden = """[^-%\s\d]""".toRegex()
    val regexNumbers = Regex("""[0-9]""")
    if (regexForbidden.containsMatchIn(jumps) || !regexNumbers.containsMatchIn(jumps)) return -1
    var resultStr = ""
    var resultInt = 0
    var i = 0
    while (i < jumps.length) {
        val ch = jumps[i]
        if (ch.isDigit()) {
            resultStr += ch.toString()
            var j = i + 1
            while (j < jumps.length) {
                if (jumps[j] in "0123456789") {
                    resultStr += jumps[j]
                    j++
                } else {
                    j += resultStr.length
                    break
                }
            }
            i += resultStr.length
            if (decimalFromString(resultStr, 10) > resultInt) {
                resultInt = decimalFromString(resultStr, 10)
            }
            resultStr = ""
        } else {
            i++
        }
    }
    return resultInt
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */


fun bestHighJump(jumps: String): Int {
    val regexForbidden = """([^-%+\s\d]|\d(\s)+\d|[+-]\s*[+-])""".toRegex()
    val regexNumbers = Regex("""[0-9]""")
    if (regexForbidden.containsMatchIn(jumps) || !regexNumbers.containsMatchIn(jumps)) return -1
    val jumpsToChars = jumps.split(Regex("""(?<=[-%+])\s"""))
    var resultInt = -1
    try {
        for (i in 0 until jumpsToChars.size) {
            if ("+" in jumpsToChars[i] && jumpsToChars[i].split(" ")[0].toInt() > resultInt)
                resultInt = jumpsToChars[i].split(" ")[0].toInt()
        }
    } catch (e: NumberFormatException) {
        return -1
    }
    return resultInt
}


/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val forbidden = """([^-+\d\s]|\d(\s)+\d|[+-]\s*[+-]|(\d[+-])|([+-]\d))""".toRegex()
    val regexNumbers = """[0-9]""".toRegex()
    if (forbidden.containsMatchIn(expression) || !regexNumbers.containsMatchIn(expression))
        throw IllegalArgumentException("нарушение формата")
    val toUnits = expression.split(" ")
    var result = toUnits[0].toInt()
    if (toUnits.size > 2) {
        for (i in 2 until (toUnits.size / 2) * 2 + 1 step 2) {   //такое ранжирование для случая,
            if (regexNumbers.containsMatchIn(toUnits[i])) {       // если последнее не число, а [-+]
                when (toUnits[i - 1]) {
                    "+" -> result += toUnits[i].toInt()
                    "-" -> result -= toUnits[i].toInt()
                }
            }
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {

    val regexForbidden = """[^а-яА-Я\sёЁ]""".toRegex()
    if (regexForbidden.containsMatchIn(str)) throw IllegalArgumentException()
    val words = str.toLowerCase().split(" ")
    val indexes = mutableListOf<Int>()
    var curr: Char
    var prev = str[0]
    if (str.isNotEmpty()) indexes.add(0)
    println(indexes)
    for (i in 1 until str.length) {
        curr = str[i]
        if (prev == ' ' && curr != ' ') indexes.add(i)
        prev = curr
    }
    for (i in 0 until words.size - 1)
        if (words[i] == words[i + 1]) return indexes[i]
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val regexForbidden = """([^а-яА-ЯёЁ;.\d\s]|([а-яА-ЯёЁ](\d|;))|\d([а-яА-ЯёЁ]|\s))""".toRegex()
    if (regexForbidden.containsMatchIn(description)) return ""
    val units = description.split("; ").map { it.split(" ") }
    var maxVal = -1.0
    var name = ""
    try {
        for (item in units) {
            if (item[1].toDouble() > maxVal) {
                maxVal = item[1].toDouble()
                name = item[0]
            }
        }

    } catch (e: IndexOutOfBoundsException) {
    }
    return name
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
