package com.example.netblinkapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

// Ini adalah Activity 4 (keempat) kalau berdasarkan modul
class DetailArchiveActivity : AppCompatActivity() {
    // deklarasi repository
    private lateinit var mDailyActivityRepository: DailyActivityRepository
    private var getId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_archive)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDailyActivityRepository = DailyActivityRepository(application)

        getId = intent.getIntExtra(AddEditActivity.EXTRA_ID, 0)
        setActionBarProperties(R.color.colorMainActivity, "Detail Arsip Aktivitas")
        fillActivityData()
    }
    override fun onSupportNavigateUp(): Boolean {
        recreate();
        finish()
        return true
    }
    // fungsi untuk menampilkan menu/ikon hapus di pojok kanan atas
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    // fungsi untuk menampilkan judul dan warna pada action bar
    private fun setActionBarProperties(colorId: Int, title: String) {
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, colorId))
        )
        actionBar?.title = title
    }
    // fungsi untuk menghapus data dari database
    private fun deleteDataFromDatabase(id: Int) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                mDailyActivityRepository.deleteDailyActivityById(id)
            }
            showToast("Aktivitas berhasil dihapus")
            navigateToMainActivity()
        }
    }
    // fungsi untuk memasukkan data ke dalam view berdasarkan data yang dikirimkan dari adapter
    private fun fillActivityData() {
        val tampilID = findViewById<TextView>(R.id.tvIDFinalValue)
        val tampilTitle = findViewById<TextView>(R.id.tvTitleFinalValue)
        val tampilStartTime = findViewById<TextView>(R.id.tvRangeTimeStartValueFinal)
        val tampilEndTime = findViewById<TextView>(R.id.tvRangeTimeEndValueFinal)
        val tampilDesc = findViewById<TextView>(R.id.tvDescValueFinal)

        tampilID.setText(intent.getIntExtra(AddEditActivity.EXTRA_ID, 0).toString())
        tampilTitle.setText(intent.getStringExtra(AddEditActivity.EXTRA_TITLE))
        tampilStartTime.setText(intent.getStringExtra(AddEditActivity.EXTRA_TIME_START))
        tampilEndTime.setText(intent.getStringExtra(AddEditActivity.EXTRA_TIME_END))
        tampilDesc.setText(intent.getStringExtra(AddEditActivity.EXTRA_DESC))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            deleteDataFromDatabase(getId)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    // fungsi untuk menampilkan toast
    private fun showToast(message: String) {
        Toast.makeText(this@DetailArchiveActivity, message, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToMainActivity() {
        finish()
    }
}