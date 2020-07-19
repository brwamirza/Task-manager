package com.example.android.todo.navDrawerFragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.todo.R
import com.example.android.todo.databinding.FragmentFeedbackBinding

private lateinit var binding: FragmentFeedbackBinding

class FeedbackFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_feedback, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendButton.setOnClickListener {
            val email: Array<String> = arrayOf("mirzabrwa@gmail.com")
            val message = binding.feedbackText.text.toString()
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL,email)
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type = "message/rfc822"

            try {
                startActivity(Intent.createChooser(intent,"Choose app to send mail"))
            }
            catch (ex: ActivityNotFoundException){
                Toast.makeText(
                    activity,
                    "No email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } } }