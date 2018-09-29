@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.*



/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int =
        if (n < 10) 1
        else digitNumber(n / 10) + digitNumber(n % 10)


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1) return 1
    if (n == 2) return 1
    var a = 1
    var b = 1
    var R = 0
    for (i in 3..n) {
        R = a + b
        a = b
        b = R
    }
    return R
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var mm = m
    var nn = n
    while (mm != nn) {
        if (mm > nn) mm -= nn
        else nn -= mm
    }
    return m * n / mm
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) return 2
    else {
        for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
            if (n % i == 0) return i
        }
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var result = true
    for (i in 2..min(m, n))
        if (m % i == 0 && n % i == 0) result = false
    return result
}
/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val mm = sqrt(m.toDouble()).toInt()
    val nn = sqrt(n.toDouble()).toInt()
    return (mm * mm == m || nn * nn == n) || nn != mm

}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var ghost = x
    while (ghost != 1)
        if (ghost % 2 == 0) {
            ghost /= 2
            steps++
        } else {
            ghost = ghost * 3 + 1
            steps++
        }
    return steps
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val ghost = x % (2 * PI)
    var R = 0.0
    var step = 1
    var t = eps * 2
    while (abs(t) >= eps) {
        if (step % 2 == 1) {
            t = ghost.pow(2 * step - 1) / factorial(2 * step - 1)
            R += t
            step++
        } else {
            t = ghost.pow(2 * step - 1) / factorial(2 * step - 1)
            R -= t
            step++
        }
    }
    return R
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val ghost = x % (2 * PI)
    var R = 1.0
    var step = 1
    var t = eps * 2
    while (abs(t) >= eps) {
        if (step % 2 == 1) {
            t = ghost.pow(2 * step ) / factorial(2 * step)
            R -= t
            step++
        } else {
            t = ghost.pow(2 * step) / factorial(2 * step)
            R += t
            step++
        }
    }
    return R
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var res = 0
    var ghost = n
    while (ghost >= 10) {
        res += ghost % 10
        ghost /= 10
        res *= 10
    }
    res += ghost % 10
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val revertedGhost = revert(n)
    return revertedGhost == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var prev = n % 10
    var ghost = n / 10
    while (ghost != 0) {
        if (prev != ghost % 10) return true
        prev = ghost % 10
        ghost /= 10
    }
    return false
}
fun numOfSymbols(n: Int): Int {
    var ghost = n
    var R = 0
    while (ghost != 0) {
        R++
        ghost /= 10
    }
    return R
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var curr = 1
    var numOfSymbols = 1

    while (numOfSymbols < n) {
        curr++
        numOfSymbols += numOfSymbols(curr * curr)
    }
    curr *= curr                                  // чтобы не вводить новую переменную делаем из числа его квадрат
    while (numOfSymbols != n) {
        numOfSymbols--
        curr /= 10

    }
return curr % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var curr: Int
    var step = 0
    var kolvo = 0
    while (kolvo < n) {
        step++
        curr = fib(step)
        kolvo += numOfSymbols(curr)
    }
    curr = fib(step)
    while (kolvo != n) {
        kolvo--
        curr /= 10
    }
    return curr % 10
}
