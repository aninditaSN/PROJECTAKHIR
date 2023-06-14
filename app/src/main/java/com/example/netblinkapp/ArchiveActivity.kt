package com.example.netblinkapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netblinkapp.DailyActivityAdapter
import com.example.netblinkapp.DailyActivityRepository

// Ini adalah Activity 3 (ketiga) kalau berdasarkan modul
class ArchiveActivity : AppCompatActivity() {
    // deklarasi repository
    private lateinit var dailyActivityRepository: DailyActivityRepository

    // deklarasi recyclerview dan view lainnya
    private lateinit var rvDailyActivity: RecyclerView
    private lateinit var tvEmptyActivity: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeViews()
        setupRecyclerView()

// deklarasi repository
        dailyActivityRepository = DailyActivityRepository(application)

        // fungsi untuk menampilkan data dari database ke recyclerview
        dailyActivityRepository.getAllArchiveDailyActivity().observe(this) { dailyActivity ->
            if (dailyActivity.isEmpty()) {
                tvEmptyActivity.visibility = View.VISIBLE
                rvDailyActivity.visibility = View.INVISIBLE
            } else {
                tvEmptyActivity.visibility = View.GONE
                val adapter = DailyActivityAdapter(dailyActivity, this@ArchiveActivity).apply {
                    setHasStableIds(true)
                }
                rvDailyActivity.adapter = adapter
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        recreate()
        finish()
        return false
    }

    // fungsi untuk mengubah warna action bar dan judul action bar
    private fun initializeViews() {
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.colorDetailArchive))
        )
        actionBar?.title = "Arsip Aktivitas"
        rvDailyActivity = findViewById(R.id.rvDailyActivity)
        tvEmptyActivity = findViewById(R.id.tvEmptyActivity)
    }
    // fungsi untuk mengatur tampilan recyclerview dengan memberi garis pemisah antar item
    private fun setupRecyclerView() {
        rvDailyActivity.layoutManager = LinearLayoutManager(this)
        rvDailyActivity.addItemDecoration(
            DividerItemDecoration(
                rvDailyActivity.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}