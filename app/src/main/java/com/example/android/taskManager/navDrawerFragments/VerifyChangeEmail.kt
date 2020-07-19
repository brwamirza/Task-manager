package com.example.android.taskManager.navDrawerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.taskManager.R
import com.example.android.taskManager.databinding.FragmentVerifyChangeEmailBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: FragmentVerifyChangeEmailBinding
class VerifyChangeEmail : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verify_change_email, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.verifyPasswordButton.setOnClickListener {
            val password = binding.passwordEditText.text
            val email = FirebaseAuth.getInstance().currentUser!!.email.toString()
            val user = FirebaseAuth.getInstance().currentUser!!

            if (password.toString().isNotEmpty()){
                val credential = EmailAuthProvider
                    .getCredential(email, password.toString())

                user.reauthenticate(credential)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            findNavController().navigate(R.id.action_verifyChangeEmail_to_changeEmailFragment)
                        }
                        else {
                            binding.passwordEditText.error = "Password is incorrect"
                        } } }
            else{
                binding.passwordEditText.error = "Password cannot be blank"
            } }

        binding.cancelText.setOnClickListener {
            findNavController().navigate(R.id.action_verifyChangeEmail_to_nav_setting)
        }
    }
}