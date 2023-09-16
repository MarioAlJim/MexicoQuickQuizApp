package com.wolf.mexicoquickquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wolf.mexicoquickquizapp.databinding.ActivityMainBinding


data class QuizItem (
    val question: String,
    val options: List<Pair<String, Boolean>>
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quizItems: List<QuizItem>
    private val numbers = mutableListOf(0, 1, 2, 3, 4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numbers.shuffle()

        createQuestionList()

        showQuizItem(numbers[0])

        binding.btnAnswer1.setOnClickListener { checkAnswer(0) }
        binding.btnAnswer2.setOnClickListener { checkAnswer(1) }
        binding.btnAnswer3.setOnClickListener { checkAnswer(2) }
        binding.btnAnswer4.setOnClickListener { checkAnswer(3) }
    }

    private fun createQuestionList () {
        quizItems = listOf(
            QuizItem(
                "¿Dónde tuvo lugar el Grito de Dolores?",
                listOf(
                    Pair("Ciudad de México", false),
                    Pair("Dolores Hidalgo", true),
                    Pair("Monterrey", false),
                    Pair("Tijuana", false)
                )
            ),
            QuizItem(
                "¿Qué día es el Grito de Independencia?",
                listOf(
                    Pair("15 de septiembre", false),
                    Pair("16 de agosto", false),
                    Pair("16 de septiembre", false),
                    Pair("17 de septiembre", true)
                )
            ),
            QuizItem(
                "¿Quién dio el grito de dolores?",
                listOf(
                    Pair("Ignacio Allende", false),
                    Pair("Josefa Ortiz de Domínguez", false),
                    Pair("Jose Maria Morelos", false),
                    Pair("Miguel Hidalgo", true)
                )
            ),
            QuizItem(
                "¿Cuántos años duró la lucha de la Independencia de México?",
                listOf(
                    Pair("10 años", false),
                    Pair("12 años", false),
                    Pair("11 años", true),
                    Pair("4 años", false)
                )
            ),
            QuizItem(
                "¿En qué año se consuma la independencia de México?",
                listOf(
                    Pair("1821", true),
                    Pair("1820", false),
                    Pair("1910", false),
                    Pair("1921", false)
                )
            )
        )
    }

    private fun showQuizItem(number: Int) {
        binding.apply {
            tvQuestion.text = quizItems[number].question
            btnAnswer1.text = quizItems[number].options[0].first
            btnAnswer2.text = quizItems[number].options[1].first
            btnAnswer3.text = quizItems[number].options[2].first
            btnAnswer4.text = quizItems[number].options[3].first
        }
    }

    private fun checkAnswer(number: Int) {
        val answer = quizItems[numbers[0]].options[number].second
        showMessage(answer)
        numbers.removeAt(0)
        if (numbers.isEmpty()) {
            /*numbers.addAll(listOf(1, 2, 3, 4, 5))
            numbers.shuffle()*/
        }
        showQuizItem(numbers[0])
    }

    private fun showMessage (answer: Boolean) {
        if (answer) {
            Toast.makeText(this, "¡RESPUESTA CORRECTA! ✅", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "¡RESPUESTA INCORRECTA! ❌", Toast.LENGTH_LONG).show()
        }
    }
}