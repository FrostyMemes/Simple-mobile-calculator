package com.example.example

import android.R.bool
import android.R.string
import java.util.*
import java.util.ArrayDeque

class PolishRecordParser(function: String) {

    private val numbers: List<String> = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",")
    private var result: Vector<String> = Vector()
    private var tokens = ArrayDeque<String>()

    init {
        getPolishRecord(function)
    }

    private fun getPolishRecord(function: String) {
        var numCombination: String?
        var lastSigh: Boolean = false
        var i: Int = 0
        while (i < function.length) {
            when (function[i]) {
                '(' -> {
                    tokens.push("(")
                    lastSigh = true
                }
                ')' -> {
                    while (tokens.peek() != "(") {
                        result.add(tokens.peek())
                        tokens.pop()
                    }
                    tokens.pop()
                }
                '*' -> {
                    if (!tokens.isEmpty()) {
                        if (getPriority(tokens.peek()) > 1) {
                            do {
                                result.add(tokens.peek())
                                tokens.pop()
                                if (tokens.isEmpty())
                                    break

                            } while (getPriority(tokens.peek()) > 1)
                        }
                    }
                    tokens.push("*")
                    lastSigh = true
                }
                '/' -> {
                    if (!tokens.isEmpty()) {
                        if (getPriority(tokens.peek()) > 1) {
                            do {
                                result.add(tokens.peek())
                                tokens.pop()
                                if (tokens.isEmpty())
                                    break

                            } while (getPriority(tokens.peek()) > 1)
                        }
                    }
                    tokens.push("/")
                    lastSigh = true
                }
                '+' -> {
                    if (!tokens.isEmpty()) {
                        if (getPriority(tokens.peek()) > 0) {
                            do {
                                result.add(tokens.peek())
                                tokens.pop()
                                if (tokens.isEmpty())
                                    break
                            } while (getPriority(tokens.peek()) > 0)
                        }
                    }
                    tokens.push("+")
                    lastSigh = true
                }

                '-' -> {
                    if (i == 0) {
                        numCombination = ""
                        numCombination += "-"
                        i++
                        do {
                            numCombination += function[i].toString()
                            i++
                            if (i == function.length)
                                break

                        } while (numbers.contains(function[i].toString()))
                        i--
                        result.add(numCombination)
                        lastSigh = false
                    } else {
                        if (lastSigh) {
                            numCombination = ""
                            numCombination += "-"
                            i++
                            do {
                                numCombination += function[i].toString()
                                i++
                                if (i == function.length)
                                    break

                            } while (numbers.contains(function[i].toString()))
                            i--
                            result.add(numCombination)
                            lastSigh = false
                        } else {
                            if (!tokens.isEmpty()) {
                                if (getPriority(tokens.peek()) > 0) {
                                    do {
                                        result.add(tokens.peek())
                                        tokens.pop()
                                        if (tokens.isEmpty())
                                            break

                                    } while (getPriority(tokens.peek()) > 0)
                                }
                            }
                            tokens.push("-")
                            lastSigh = true
                        }
                    }
                }
                else -> {
                    if (numbers.contains(function[i].toString())) {
                        numCombination = ""
                        do {
                            numCombination += function[i].toString()
                            i++
                            if (i == function.length)
                                break
                        } while (numbers.contains(function[i].toString()))
                        i--
                        result.add(numCombination)
                        lastSigh = false
                    }
                }
            }
            i++
        }
        while (tokens.isNotEmpty()) {
            result.add(tokens.pop())
        }
    }

    private fun getPriority(token: String): Int {
        when (token) {
            "^" -> return 3
            "*" -> return 2
            "/" -> return 2
            "+" -> return 1
            "-" -> return 1
            "(" -> return 0
        }
        return 0
    }

    fun printPolishRecord() {
        for (i in result)
            print("$i ")
    }

}
