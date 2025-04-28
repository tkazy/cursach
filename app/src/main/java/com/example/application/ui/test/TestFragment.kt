package com.example.application.ui.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.application.R
import com.example.application.data.Word
import com.example.application.data.WordDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class TestFragment : Fragment() {
    private lateinit var database: WordDatabase

    private lateinit var questionField: TextView
    private lateinit var resultField: TextView
    private lateinit var rightAnswer:TextView
    private lateinit var count: TextView
    private lateinit var variant1: Button
    private lateinit var variant2: Button
    private lateinit var variant3: Button
    private lateinit var variant4: Button
    private lateinit var nextBtn: Button

    private lateinit var currentWord: Word
    private var currentWordIndex = 0
    private var rightAnswers = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()

        questionField = view.findViewById(R.id.question)
        resultField = view.findViewById(R.id.result)
        rightAnswer = view.findViewById(R.id.right_answer)
        resultField.text = "Выберете правильный перевод:"
        count = view.findViewById(R.id.count)
        count.text = "Верных ответов: " + rightAnswers.toString()
        variant1 = view.findViewById(R.id.variant1)
        variant2 = view.findViewById(R.id.variant2)
        variant3 = view.findViewById(R.id.variant3)
        variant4 = view.findViewById(R.id.variant4)
        nextBtn = view.findViewById(R.id.next)

        GlobalScope.launch(Dispatchers.IO) {
            database = Room.databaseBuilder(requireActivity().applicationContext, WordDatabase::class.java, "english_words")
                .createFromAsset("database/english_words.db")
                .build()

            val wordDAO = database.wordDAO()
            val words = wordDAO.getRandomWords()

            Log.d("RRR", words.toString())

            requireActivity().runOnUiThread {
                updateUI(words)
            }

            nextBtn.setOnClickListener {
                if(currentWordIndex == words.size - 2) {
                    nextBtn.text = "Завершить"
                }

                if (currentWordIndex < words.size - 1) {

                    variant1.isEnabled = true
                    variant2.isEnabled = true
                    variant3.isEnabled = true
                    variant4.isEnabled = true

                    resultField.text = "Выберете правильный перевод:"
                    rightAnswer.text = ""

                    currentWordIndex++
                    updateUI(words)

                } else {
                    if(FirebaseAuth.getInstance().currentUser != null) {
                        firestore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get()
                            .addOnSuccessListener {
                                var tests = it.getDouble("tests")!!.toInt()
                                var answers = it.getDouble("answers")!!.toInt()
                                tests++
                                answers += count.text.toString().replace("Верных ответов: ", "").toInt()

                                firestore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).update(
                                    mapOf(
                                        "tests" to tests,
                                        "answers" to answers
                                    )
                                )

                                Log.d("RRR", "$tests $answers")
                            }
                    }
                    findNavController().navigate(R.id.action_testFragment_to_navigation_dashboard)
                }
            }
        }

        variant1.setOnClickListener {
            variant2.isEnabled = false
            variant3.isEnabled = false
            variant4.isEnabled = false

            if(variant1.text.toString() == currentWord.wordRussian) {
                Log.d("RRR", "Правильно")
                resultField.text = "Правильно"
                rightAnswers++
            } else {
                resultField.text = "Неправильно"
                rightAnswer.text = "Верно: " + currentWord.wordRussian
            }
            count.text = "Верных ответов: " + rightAnswers.toString()
        }
        variant2.setOnClickListener {
            variant1.isEnabled = false
            variant3.isEnabled = false
            variant4.isEnabled = false

            if(variant2.text.toString() == currentWord.wordRussian) {
                Log.d("RRR", "Правильно")
                resultField.text = "Правильно"
                rightAnswers++
            } else {
                resultField.text = "Неправильно"
                rightAnswer.text = "Верно: " + currentWord.wordRussian
            }
            count.text = "Верных ответов: " + rightAnswers.toString()
        }
        variant3.setOnClickListener {
            variant1.isEnabled = false
            variant2.isEnabled = false
            variant4.isEnabled = false

            if(variant3.text.toString() == currentWord.wordRussian) {
                Log.d("RRR", "Правильно")
                resultField.text = "Правильно"
                rightAnswers++
            } else {
                resultField.text = "Неправильно"
                rightAnswer.text = "Верно: " + currentWord.wordRussian
            }
            count.text = "Верных ответов: " + rightAnswers.toString()
        }
        variant4.setOnClickListener {
            variant1.isEnabled = false
            variant2.isEnabled = false
            variant3.isEnabled = false

            if(variant4.text.toString() == currentWord.wordRussian) {
                Log.d("RRR", "Правильно")
                resultField.text = "Правильно"
                rightAnswers++
            } else {
                resultField.text = "Неправильно"
                rightAnswer.text = "Верно: " + currentWord.wordRussian
            }
            count.text = "Верных ответов: " + rightAnswers.toString()
        }
    }

    private fun updateUI(words: List<Word>) {
        currentWord = words[currentWordIndex]
        questionField.text = currentWord.wordEnglish

        val filteredList = words.toMutableList().apply { removeAt(currentWordIndex) }
        val randomIndices = mutableListOf<Int>()
        while (randomIndices.size < 3) {
            val randomIndex = Random.nextInt(filteredList.size)
            if (!randomIndices.contains(randomIndex)) {
                randomIndices.add(randomIndex)
            }
        }
        randomIndices.map { filteredList[it] }.toList()

        var answers : List<String> = listOf(filteredList[randomIndices[0]].wordRussian,
            filteredList[randomIndices[1]].wordRussian,
            filteredList[randomIndices[2]].wordRussian,
            currentWord.wordRussian)
        answers = answers.shuffled()
        Log.d("RRR", answers.toString())

        variant1.text = answers[0]
        variant2.text = answers[1]
        variant3.text = answers[2]
        variant4.text = answers[3]
    }
}