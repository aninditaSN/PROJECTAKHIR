package com.example.netblinkapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Ini adalah Activity kalau berdasarkan modul
class MainActivity2 : AppCompatActivity() {

    // deklarasi repository
    private lateinit var dailyActivityRepository: DailyActivityRepository

    // deklarasi recyclerview dan view lainnya
    private lateinit var DailyActivityDao: DailyActivityDao
    private lateinit var rvDailyActivity: RecyclerView
    private lateinit var tvEmptyActivity: View

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //initializeViews()
        //setupRecyclerView()

        // deklarasi repository
        //dailyActivityRepository = DailyActivityRepository(application)

        // fungsi untuk menampilkan data dari database ke recyclerview
//        dailyActivityRepository.getAllDailyActivity().observe(this) { dailyActivity ->
//            if (dailyActivity.isEmpty()) {
//                tvEmptyActivity.visibility = View.VISIBLE
//            } else {
//                tvEmptyActivity.visibility = View.GONE
//                val adapter = DailyActivityAdapter(dailyActivity, this@MainActivity2).apply {
//                    setHasStableIds(true)
//                }
//                rvDailyActivity.adapter = adapter
//            }
//        }

        // fungsi untuk menampilkan activity tambah ketika tombol tambah di klik
        val btnTambah = findViewById<View>(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }

    // fungsi untuk menampilkan menu/ikon arsip di pojok kanan atas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_arsip, menu)
        return true
    }

    // fungsi untuk menampilkan activity arsip ketika ikon arsip di klik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this, ArchiveActivity::class.java))
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    // fungsi untuk mengubah warna action bar dan judul action bar
    private fun initializeViews() {
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.colorMainActivity))
        )
        actionBar?.title = "Daftar Aktivitas"
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