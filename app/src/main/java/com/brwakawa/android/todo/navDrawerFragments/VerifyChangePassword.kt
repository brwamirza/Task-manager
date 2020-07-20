package com.brwakawa.android.todo.navDrawerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.brwakawa.android.todo.R
import com.brwakawa.android.todo.databinding.FragmentVerifyChangePasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: FragmentVerifyChangePasswordBinding

class VerifyChangePassword : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_verify_change_password, container, false
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
                            findNavController().navigate(R.id.action_verifyChangePassword_to_changePasswordFragment)
                        }
                        else {
                            binding.passwordEditText.error = "Password is incorrect"
                        } } }
            else{
                binding.passwordEditText.error = "Password cannot be blank"
            } }

        binding.cancelText.setOnClickListener {
            findNavController().navigate(R.id.action_verifyChangePassword_to_nav_setting)
        }
    }
}