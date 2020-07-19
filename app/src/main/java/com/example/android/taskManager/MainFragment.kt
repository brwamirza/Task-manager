package com.example.android.taskManager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.taskManager.databinding.FragmentTrackerBinding
import com.example.android.taskManager.viewPagerAdapter.init
import kotlinx.android.synthetic.main.fragment_tracker.*

private lateinit var binding: FragmentTrackerBinding

class TrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tracker, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(this,viewPager,tabLayout)
        extended_fab.setOnClickListener {
            val intent = Intent(context, CreateActivity::class.java)
            startActivity(intent)
        }
    }
}