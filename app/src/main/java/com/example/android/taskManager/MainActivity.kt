package com.example.android.taskManager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tracker.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration
    var id = FirebaseAuth.getInstance().currentUser?.uid.toString()
    var email = FirebaseAuth.getInstance().currentUser?.email.toString()
    private lateinit var mHeaderView :View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val username = dataSnapshot.child("username").value.toString()
                mHeaderView.username_text?.text = username
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        FirebaseDatabase.getInstance().reference.child("users").child(id).addListenerForSingleValueEvent(postListener)
        setContentView(R.layout.activity_tracker)
        setSupportActionBar(findViewById(R.id.toolbar))
        drawer = findViewById(R.id.drawer)
        mHeaderView = nav_view.getHeaderView(0)
        mHeaderView.email_text?.text = email

        //we specify only top level destinations here
        appBarConfig = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_setting, R.id.nav_feedback
            ), drawer
        )

        val navController = findNavController(R.id.nav_host_tracker)
        setupActionBarWithNavController(navController, appBarConfig)
        nav_view.setupWithNavController(navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
}

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_tracker).navigateUp(appBarConfig)
                || super.onSupportNavigateUp()
    }
}


