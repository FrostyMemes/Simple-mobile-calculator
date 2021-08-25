package com.example.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ActionMenuView
import android.widget.Button
import android.widget.TextView
import com.example.example.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun addChar (view: View) {
        var clickedButton: Button = view as Button
        binding.ResultField.setText(binding.ResultField.text.toString()+clickedButton.text)
    }

    fun clickProcent(view: View){

    }
    fun clickResult(view: View){
        var calculator = PolishRecordParser(binding.ResultField.text.toString())
        binding.ResultField.setText(calculator.solve().toString())
    }

    fun clickClear(view: View){
        binding.ResultField.text = ""
    }

}