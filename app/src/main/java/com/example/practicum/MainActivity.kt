//package com.example.practicum
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.example.practicum.databinding.ActivityMainBinding
//import com.google.android.material.snackbar.Snackbar
//
//data class Question(
//    val textResId: Int,
//    val answer: Boolean
//)
//
//private const val TAG = "MainActivity"
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//    private val questionBank = listOf(
//        Question(R.string.question_India_text, true),
//        Question(R.string.question_USA_text, true),
//        Question(R.string.question_UK_text, false),
//        Question(R.string.question_UAE_text, false),
//        Question(R.string.question_Australia_text, true),
//        Question(R.string.question_Water_text, false)
//    )
//    private var currentIndex = 0
//    private var correctAnswers = 0
//    private val answeredQuestions = mutableSetOf<Int>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate(Bundle?) called")
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        if (savedInstanceState != null) {
//            currentIndex = savedInstanceState.getInt("currentIndex", 0)
//            correctAnswers = savedInstanceState.getInt("correctAnswers", 0)
//            answeredQuestions.addAll(savedInstanceState.getIntegerArrayList("answeredQuestions") ?: emptyList())
//        }
//        updateQuestion()
//
//        binding.trueButton.setOnClickListener {
//            checkAnswer(true)
//        }
//        binding.falseButton.setOnClickListener {
//            checkAnswer(false)
//        }
//        binding.nextButton.setOnClickListener {
//            currentIndex = (currentIndex + 1) % questionBank.size
//            updateQuestion()
//        }
//        binding.previousButton.setOnClickListener {
//            currentIndex = if (currentIndex == 0) questionBank.size - 1 else currentIndex - 1
//            updateQuestion()
//        }
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.d(TAG, "onSaveInstanceState() called")
//        outState.putInt("currentIndex", currentIndex)
//        outState.putInt("correctAnswers", correctAnswers)
//        outState.putIntegerArrayList("answeredQuestions", ArrayList(answeredQuestions))
//    }
//
//    private fun updateQuestion() {
//        val questionTextResId = questionBank[currentIndex].textResId
//        binding.questionTextView.setText(questionTextResId)
//        updateButtonState()
//    }
//
//    private fun checkAnswer(userAnswer: Boolean) {
//        if (currentIndex in answeredQuestions) return // Prevent duplicate answers
//
//        val correctAnswer = questionBank[currentIndex].answer
//        val messageResId = if (userAnswer == correctAnswer) {
//            correctAnswers++
//            R.string.correct_text
//        } else {
//            R.string.incorrect_text
//        }
//
//        Snackbar.make(
//            binding.root,
//            getString(messageResId),
//            Snackbar.LENGTH_SHORT
//        ).setAction(R.string.close_text) {
//            // Optional Action
//        }.show()
//
//        answeredQuestions.add(currentIndex)
//        updateButtonState()
//
//        if (answeredQuestions.size == questionBank.size) {
//            displayFinalScores()
//        }
//    }
//
//    @SuppressLint("StringFormatInvalid")
//    private fun displayFinalScores() {
//        val scorePercentage = (correctAnswers.toDouble() / questionBank.size) * 100
//        Snackbar.make(
//            binding.root,
//            getString(R.string.final_score, scorePercentage.toInt()),
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//
//    private fun updateButtonState() {
//        val isQuestionAnswered = currentIndex in answeredQuestions
//        binding.trueButton.isEnabled = !isQuestionAnswered
//        binding.falseButton.isEnabled = !isQuestionAnswered
//    }
//}


package com.example.practicum

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicum.databinding.ActivityMainBinding

data class Question(
    val textResId: Int,
    val answer: Boolean
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
    private val answeredQuestions = mutableSetOf<Int>()

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
        if (currentIndex in answeredQuestions) return // Prevent duplicate answers

        val correctAnswer = questionBank[currentIndex].answer
        val message = if (userAnswer == correctAnswer) {
            "Correct!"
        } else {
            "Incorrect!"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        answeredQuestions.add(currentIndex)
    }
}
