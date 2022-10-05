package com.example.my_flashcard2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcardQuestion = findViewById<TextView>(R.id.flashcardquestion)
        val flashcardAnswer = findViewById<TextView>(R.id.answer)

        flashcardQuestion.setOnClickListener {
            flashcardAnswer.visibility=View.VISIBLE
            flashcardQuestion.visibility=View.INVISIBLE

            Toast.makeText(this, "Question was clicked", Toast.LENGTH_SHORT).show()
        }
        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility=View.INVISIBLE
            flashcardQuestion.visibility=View.VISIBLE
        }
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->

            val data: Intent? = result.data

            if (data != null){
                val questionString = data.getStringExtra("QUESTION_KEY")
                val answerString = data.getStringExtra("ANSWER_KEY")

                flashcardQuestion.text = questionString
                flashcardAnswer.text = answerString

                Log.i("Onwell: MainActivity", "question: &questionString")
                Log.i("Onwell: MainActivity", "question: &questionString")
            }
            else{
                Log.i("Onwell: MainActivity", "Returned null data from AddCardActivity")
            }
        }
        val addQuestionButton = findViewById<ImageView>(R.id.imageView)
        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}