package com.example.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.CallDialogScreen.CallDialogFragment
import com.example.test.ContanctListScreen.ContactListAdapter
import com.example.test.ContanctListScreen.ContactListFragment
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(R.id.fcvContainer, CallDialogFragment())
            commit()
        }


        binding.btnCallDialog.setOnClickListener {
            supportFragmentManager.beginTransaction().run {
                setReorderingAllowed(true)
                replace(R.id.fcvContainer, CallDialogFragment())
                commit()
            }
        }
        binding.btnContacts.setOnClickListener {
            supportFragmentManager.beginTransaction().run {
                setReorderingAllowed(true)
                replace(R.id.fcvContainer, ContactListFragment())
                commit()
            }
        }

    }
}