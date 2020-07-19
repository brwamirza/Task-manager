package com.example.android.taskManager.navDrawerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.taskManager.R
import com.example.android.taskManager.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_change_password, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.changePasswordButton.setOnClickListener {
            val newPassword = binding.newPasswordEditText.text.toString()
            val confirmedPassword = binding.confirmedPasswordText.text.toString()
            if (newPassword.isNotEmpty() && confirmedPassword.isNotEmpty()) {
                if (newPassword == confirmedPassword) {
                    FirebaseAuth.getInstance().currentUser!!.updatePassword(newPassword)
                    findNavController().navigate(R.id.action_changePasswordFragment_to_nav_setting)
                }
                else {
                    binding.confirmedPasswordText.error = "The two given passwords do not match"
                }
            }
            if (newPassword.isEmpty()) {
                binding.newPasswordEditText.error = "Password cannot be blank"
            }
            if (confirmedPassword.isEmpty()) {
                binding.confirmedPasswordText.error = "Confirm password cannot be blank"
            }
        }

        binding.cancelText.setOnClickListener {
            findNavController().navigate(R.id.action_changePasswordFragment_to_nav_setting)
        }

    }
}