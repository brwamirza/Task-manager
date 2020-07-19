package com.example.android.todo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.android.todo.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var id:String
    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
         id = FirebaseAuth.getInstance().currentUser?.uid.toString()
        ref = FirebaseDatabase.getInstance().reference.child("users").child(id).child("tasks")
        filled_exposed_dropdown.apply {
            setAdapter(
                ArrayAdapter(
                    context,
                    R.layout.dropdown_menu_popup_item,
                    resources.getStringArray(R.array.dropdown_string_items)
                )) }

        //getting data from intent
        val message = intent.getStringExtra("message")
        val taskStatus = intent.getStringExtra("taskStatus")
        yourTaskInput.setText(message)
        filled_exposed_dropdown.setText(taskStatus)

        closeImage.setOnClickListener {
            supportFinishAfterTransition()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            closeImage.isVisible = false
        }

        update_button.setOnClickListener {
            updateItem()
        }

        delete_button.setOnClickListener {
            deleteItem()
        }
    }

    private fun deleteItem() {
        val taskId = intent.getStringExtra("id")
        ref.child(taskId!!.toString()).removeValue()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun updateItem() {
        val taskId = intent.getStringExtra("id")
        val task = yourTaskInput.text.toString()
        val status = filled_exposed_dropdown.text.toString()

        if (status.isEmpty()){
            filled_exposed_dropdown.error = "please enter a task"
            return
        }
        else if (task.isEmpty()){
            yourTaskText.error = "please enter a task"
            return
        }
        else{
            val post = Post(id = taskId,message = task,taskStatus = status)
            ref.child(taskId!!.toString()).setValue(post)
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }
}