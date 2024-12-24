package com.example.practicum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicum.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
data class Question(
    val textResId: Int, // The resource ID of the question string
    val answer: Boolean // The correct answer (true or false)
)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_India_text, true),
        Question(R.string.question_USA_text, true),
        Question(R.string.question_UK_text, false),
        Question(R.string.question_UAE_text, false),
        Question(R.string.question_Australia_text, true),
        Question(R.string.question_Water_text, false)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateQuestion()

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        binding.previousButton.setOnClickListener {
            currentIndex = if (currentIndex == 0) questionBank.size - 1 else currentIndex - 1
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_text
        } else {
            R.string.incorrect_text
        }

        Snackbar.make(
            binding.root,
            getString(messageResId),
            Snackbar.LENGTH_SHORT
        ).setAction(R.string.close_text) {
            // Action after clicking "Close"
        }.show()
    }
}
