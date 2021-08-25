package com.example.example


import java.util.ArrayDeque

class PolishRecordParser(function: String) {

    private val numbers: List<String> = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")
    private var polishRecord: MutableList<String> = mutableListOf()

    init {
        getPolishRecord(function)
    }

    private fun getPolishRecord(function: String) {

        var tokens = ArrayDeque<String>()
        var numCombination: String?
        var negativeNumber: Boolean = true
        var tokenPriority: Int
        var i: Int = 0
        while (i < function.length) {
            if (!numbers.contains(function[i].toString()) && !negativeNumber)
            {
                tokenPriority = getPriority(function[i].toString())
                when (tokenPriority){
                    4->{
                        while (tokens.peek() != "(") {
                            polishRecord.add(tokens.peek())
                            tokens.pop()
                        }
                        tokens.pop()
                    }
                    in 1..3->{
                        if (!tokens.isEmpty()) {
                            if (getPriority(tokens.peek()) > tokenPriority) {
                                do {
                                    polishRecord.add(tokens.peek())
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
                        negativeNumber = true
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
                polishRecord.add(numCombination)
                negativeNumber = false
            }
            i++
        }

        while (tokens.isNotEmpty()) {
            polishRecord.add(tokens.pop())
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

    public fun solve(): Double{

        var firstNumber: Double
        var secondNumber: Double
        var tokens = ArrayDeque<Double>()

        for (i in 0..(polishRecord.count()-1))
        {
            if(getPriority(polishRecord[i])==-1) tokens.push(polishRecord[i].toDouble())
            else{
                secondNumber = tokens.pop()
                firstNumber = tokens.pop()
                when(polishRecord[i]){
                    "*"->tokens.push(firstNumber*secondNumber)
                    "/"->tokens.push(firstNumber/secondNumber)
                    "+"->tokens.push(firstNumber+secondNumber)
                    "-"->tokens.push(firstNumber-secondNumber)
                }
            }
        }

        return tokens.pop()
    }

    fun printPolishRecord() {
        for (i in polishRecord)
            print("$i ")
    }

}


