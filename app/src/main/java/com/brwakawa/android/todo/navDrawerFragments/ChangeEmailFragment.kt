package com.brwakawa.android.todo.navDrawerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.brwakawa.android.todo.R
import com.brwakawa.android.todo.databinding.FragmentChangeEmailBinding
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: FragmentChangeEmailBinding

class ChangeEmailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_change_email, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeEmailButton.setOnClickListener {
            val newEmail = binding.newEmailEditText.text.toString()
            if (newEmail.isNotEmpty()){
                FirebaseAuth.getInstance().currentUser!!.updateEmail(newEmail)
                findNavController().navigate(R.id.action_changeEmailFragment_to_nav_setting)
            }
            else{
                binding.newEmailEditText.error = "New email cannot be empty"
            }
        }

        binding.cancelText.setOnClickListener {
            findNavController().navigate(R.id.action_changeEmailFragment_to_nav_setting)
        }

    }
}