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
        var tokenPriority: Int
        var i: Int = 0
        while (i < function.length) {
            if (!numbers.contains(function[i].toString()) && !lastSigh)
            {
                tokenPriority = getPriority(function[i].toString())
                when (tokenPriority){
                    4->{
                        while (tokens.peek() != "(") {
                            result.add(tokens.peek())
                            tokens.pop()
                        }
                        tokens.pop()
                    }
                    in 1..3->{
                        if (!tokens.isEmpty()) {
                            if (getPriority(tokens.peek()) > tokenPriority) {
                                do {
                                    result.add(tokens.peek())
                                    tokens.pop()
                                    if (tokens.isEmpty())
                                        break
                                } while (getPriority(tokens.peek()) > tokenPriority)
                            }
                        }
                        tokens.push(function[i].toString())
                    }
                    0->{
                        tokens.push("(")
                        lastSigh = true
                    }
                }
            }
            else{
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
            i++
        }

        while (tokens.isNotEmpty()) {
            result.add(tokens.pop())
        }
    }

    private fun getPriority(token: String): Int {
        when (token) {
            ")" -> return 4
            "^" -> return 3
            "*" -> return 2
            "/" -> return 2
            "+" -> return 1
            "-" -> return 1
            "(" -> return 0
        }
        return -1
    }

    fun printPolishRecord() {
        for (i in result)
            print("$i ")
    }

}

