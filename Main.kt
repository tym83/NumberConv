package converter
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

const val SHIFT_HIGHER_DECI = 10
const val COUNT = 5

fun main() {
    firstMenu()
}

fun fromDeci (targetBase: BigInteger, deciNum: BigInteger) : String {
    var resultNum = ""
    var sourceNum = deciNum

    while (true) {
        val divideOnBase = sourceNum.rem(targetBase)
        val j = if (divideOnBase > BigInteger.valueOf(9)) convNumToLetter(divideOnBase) else divideOnBase
        resultNum += j

        if (sourceNum / targetBase == BigInteger.valueOf(0)) break
        sourceNum /= targetBase

    }

    return resultNum.reversed()
}

fun toDeci (sourceBase: BigInteger, numForConv : String): BigInteger {
    var deciNum = BigInteger.valueOf(0)

    for (ch in numForConv) {
        val j = if (ch.isLetter()) convLetterToNum(ch) else ch.toString()
        deciNum *= sourceBase
        deciNum += j.toBigInteger()
    }

    return deciNum
}

fun fromDeciFractal (targetBase: BigInteger, deciFractal : BigDecimal) : String {
    var decimalFractal = deciFractal
    val base = targetBase.toBigDecimal()
    var resultFractal = ""

    for (i in 1..5) {
        val (whole, partial) = (decimalFractal * base).toString().split(".")
        decimalFractal = BigDecimal("0.$partial")

        resultFractal += if (whole.toInt() >= SHIFT_HIGHER_DECI) convNumToLetter(whole.toBigInteger()) else whole
    }

    return resultFractal
}

fun toDeciFractal (sourceBase: BigInteger, fractal: String) : BigDecimal {
    var deciFractal = BigDecimal(0).setScale(5, RoundingMode.DOWN)

    for (ch in fractal.indices) {
        val j = if (fractal[ch].isLetter()) convLetterToNum(fractal[ch]) else fractal[ch].toString()
        deciFractal += BigDecimal(j).setScale(COUNT, RoundingMode.UNNECESSARY) / BigDecimal(sourceBase.pow(ch + 1))
    }

    return deciFractal
}

fun firstMenu() {
    println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
    val firstAnswer = readLine()!!

    if ("/exit" in firstAnswer) {
        return
    } else {
        val (sourceBase, targetBase) = firstAnswer.split(" ").map { it.toBigInteger() }
        secondMenu(sourceBase, targetBase)
    }
}

fun secondMenu(sourceBase : BigInteger, targetBase : BigInteger) {
    println("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")
    val numForConv = readLine()!!.lowercase()

    if (numForConv == "/back") firstMenu() else printResult(sourceBase, targetBase, numForConv)
}

fun printResult(sourceBase : BigInteger, targetBase : BigInteger, numForConv: String) {
    if ('.' in numForConv) {
        val (whole, fractal) = numForConv.split(".")
        val wholePart = fromDeci(targetBase, toDeci(sourceBase, whole))
        val fractalPart = fromDeciFractal(targetBase, toDeciFractal(sourceBase, fractal))
        println("Conversion result: $wholePart.$fractalPart")
    } else {
        val deciNum = toDeci(sourceBase, numForConv)
        println("Conversion result: ${fromDeci(targetBase, deciNum)}")
    }
    secondMenu(sourceBase, targetBase)
}

fun convLetterToNum(ch : Char) : String {
    val alphabet = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    return (alphabet.indexOf(ch) + SHIFT_HIGHER_DECI).toString()
}

fun convNumToLetter(num : BigInteger): Char {
    val alphabet = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    return alphabet[num.toInt() - SHIFT_HIGHER_DECI]
}