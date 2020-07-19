package com.example.android.todo.tabLayoutFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.todo.DetailActivity
import com.example.android.todo.adapter.getAdapter
import com.example.android.todo.R
import com.example.android.todo.adapter.OnItemClickListener
import com.example.android.todo.databinding.FragmentInprogressBinding
import com.example.android.todo.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_inprogress.*

private lateinit var binding: FragmentInprogressBinding

class InProgressFragment : Fragment(),OnItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_inprogress, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val mBaseQuery = FirebaseDatabase.getInstance().reference.child("users").child(id).child("tasks")
        mBaseQuery.keepSynced(true)
        val mQueryInprogress = mBaseQuery
            .orderByChild("taskStatus")
            .equalTo("INPROGRESS")
        mAdapter = getAdapter(mQueryInprogress,this,this)

        // Init RecyclerView
        recyclerViewInProgress.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onItemClick(post: Post, rootView: View) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("message",post.message)
        intent.putExtra("taskStatus",post.taskStatus)
        intent.putExtra("id",post.id)
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                Pair(rootView, getString(R.string.transition_root)))
        ActivityCompat.startActivity(requireActivity(),intent,options.toBundle())
    }
}