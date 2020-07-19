package com.example.android.taskManager

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.taskManager.model.Post
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var dropDownText: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        textInputLayout = findViewById(R.id.text_input_layout)
        dropDownText = findViewById(R.id.filled_exposed_dropdown)

        dropDownText.apply {
            setAdapter(
                ArrayAdapter(
                    context,
                    R.layout.dropdown_menu_popup_item,
                    resources.getStringArray(R.array.dropdown_string_items)
                )) }

        closeImage.setOnClickListener {
            supportFinishAfterTransition()
        }

        update_button.setOnClickListener {
            addItem()
        }
    }

    private fun addItem() {
        val task = yourTaskInput.text.toString()
        val status = filled_exposed_dropdown.text.toString()
        val id = FirebaseAuth.getInstance().currentUser?.uid.toString()
        if (status.isEmpty()){
            filled_exposed_dropdown.error = "please enter a task"
            return
        }
        else if (task.isEmpty()){
            yourTaskText.error = "please enter a task"
            return
        }
        else {
            val ref = FirebaseDatabase.getInstance().reference.child("users").child(id).child("tasks")
            val taskId = ref.push().key
            val post = Post(id = taskId,message = task,taskStatus = status)
            ref.child(taskId.toString()).setValue(post)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}