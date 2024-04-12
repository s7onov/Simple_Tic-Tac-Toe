package tictactoe

import kotlin.math.abs

const val x = 'X'
const val o = 'O'

fun main() {
    var turn = 0
    val s = "         ".toCharArray()
    printGrid(s)

    while (!isGameFinished(s)) {
        var movx = 0
        var movy = 0
        do {
            var correct = false
            val input = readln()
            try {
                val data = input.split(" ").map { it.toInt() }
                movx = data[1] - 1
                movy = data[0] - 1
                if (movx in 0..2 && movy in 0..2) {
                    if (s[movx + movy * 3] != '_' &&
                            s[movx + movy * 3] != ' ') println("This cell is occupied! Choose another one!")
                    else correct = true
                } else println("Coordinates should be from 1 to 3!")
            } catch (e: Exception) {
                println("You should enter numbers!")
            }
        } while (!correct)
        turn++
        if (turn % 2 == 1) s[movx + movy * 3] = x
        else s[movx + movy * 3] = o
        printGrid(s)
    }
}

fun printGrid(s: CharArray) {
    println("---------")
    println("| ${s[0]} ${s[1]} ${s[2]} |")
    println("| ${s[3]} ${s[4]} ${s[5]} |")
    println("| ${s[6]} ${s[7]} ${s[8]} |")
    println("---------")
}

fun isGameFinished(s: CharArray): Boolean {
    var isFinished = false
    var output = "Game not finished"
    val row1 = s[0] == s[1] && s[1] == s[2]
    val row2 = s[3] == s[4] && s[4] == s[5]
    val row3 = s[6] == s[7] && s[7] == s[8]
    val col1 = s[0] == s[3] && s[3] == s[6]
    val col2 = s[1] == s[4] && s[4] == s[7]
    val col3 = s[2] == s[5] && s[5] == s[8]
    val diag1 = s[0] == s[4] && s[4] == s[8]
    val diag2 = s[2] == s[4] && s[4] == s[6]
    var xwins = false
    var owins = false
    var xc = 0
    var oc = 0
    for (i in s.indices) {
        if (s[i] == x) xc++
        else if (s[i] == o) oc++
    }
    if (xc + oc == 9){
        output = "Draw"
        isFinished = true
    }
    if (row1 || col1) {
        if (s[0] == x) xwins = true
        else if (s[0] == o) owins = true
    }
    if (row2 || col2 || diag1 || diag2) {
        if (s[4] == x) xwins = true
        else if (s[4] == o) owins = true
    }
    if (row3 || col3) {
        if (s[8] == x) xwins = true
        else if (s[8] == o) owins = true
    }
    if (xwins){
        output = "X wins"
        isFinished = true
    }
    if (owins){
        output = "O wins"
        isFinished = true
    }
    if (xwins && owins || abs(xc - oc) > 1) output = "Impossible"
    if (isFinished) println(output)
    return isFinished
}
