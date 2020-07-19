package com.example.android.taskManager.navDrawerFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.taskManager.R
import com.example.android.taskManager.SignInActivity
import com.example.android.taskManager.databinding.FragmentSettingBinding
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.firebase.auth.FirebaseAuth

private lateinit var binding: FragmentSettingBinding

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_setting, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewEmail.setOnClickListener {
            findNavController().navigate(R.id.action_nav_setting_to_verifyChangeEmail)
        }
        binding.cardViewPassword.setOnClickListener {
            findNavController().navigate(R.id.action_nav_setting_to_verifyChangePassword)
        }
        binding.logOutText.setOnClickListener {
            Credentials.getClient(requireActivity()).disableAutoSignIn()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }
    }
}
