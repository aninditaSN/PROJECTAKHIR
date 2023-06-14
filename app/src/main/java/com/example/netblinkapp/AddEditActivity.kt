package com.example.netblinkapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.properties.Delegates

// ini adalah activity 2 (kedua) kalau berdasarkan modul
class AddEditActivity : AppCompatActivity() {

    // deklarasi repository
    private lateinit var mDailyActivityRepository: DailyActivityRepository

    // deklarasi id untuk menentukan apakah data akan diupdate atau ditambahkan
    private var getId by Delegates.notNull<Int>()
    private var status by Delegates.notNull<Boolean>()

    // deklarasi variabel beserta tipe view yang akan digunakan
    private lateinit var edtTitle: EditText
    private lateinit var edtDesc: EditText
    private lateinit var editStartTime: EditText
    private lateinit var editEndTime: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDailyActivityRepository = DailyActivityRepository(application)

        getId = intent.getIntExtra(EXTRA_ID, 0)
        status = intent.getBooleanExtra(EXTRA_STATUS, true)

        initializeViews()

        if (getId != 0) {
            if(status != true){
                setActionBarProperties(R.color.colorEditDailyActivity, "Edit Aktivitas")
                fillActivityData()
            }else{
                val intent = Intent(this@AddEditActivity, DetailArchiveActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            setActionBarProperties(R.color.colorAddDailyActivity, "Tambah Aktivitas")
        }

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val desc = edtDesc.text.toString().trim()
            val startTime = editStartTime.text.toString().trim()
            val endTime = editEndTime.text.toString().trim()

            if (title.isEmpty()) {
                showError(edtTitle, "Title tidak boleh kosong")
            } else if (desc.isEmpty()) {
                showError(edtDesc, "Deskripsi tidak boleh kosong")
            } else if (startTime.isEmpty()) {
                showError(editStartTime, "Start Time tidak boleh kosong")
            } else if (endTime.isEmpty()) {
                showError(editEndTime, "End Time tidak boleh kosong")
            } else {
                if (getId != 0) {
                    updateDataToDatabase(getId, title, desc, startTime, endTime)
                } else {
                    insertDataToDatabase(title, desc, startTime, endTime)
                }
            }
        }
    }

    // membuat tombol kembali ke activity sebelumnya
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // membuat tombol more (3 titik) pada action bar jika id != 0
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getId != 0) menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    // membuat fungsi untuk menentukan action dari tombol more (3 titik) pada action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done -> {
                val title = edtTitle.text.toString().trim()
                val desc = edtDesc.text.toString().trim()
                val startTime = editStartTime.text.toString().trim()
                val endTime = editEndTime.text.toString().trim()

                updateDataToDatabase(getId, title, desc, startTime, endTime, true)
            }
            R.id.action_delete -> {
                deleteDataFromDatabase(getId)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    // fungsi untuk mengindentifikasi id view yang digunakan
    private fun initializeViews() {
        edtTitle = findViewById(R.id.edtTitle)
        edtDesc = findViewById(R.id.edtDesc)
        editStartTime = findViewById(R.id.editStartTime)
        editEndTime = findViewById(R.id.editEndTime)
    }

    // fungsi untuk menampilkan judul dan warna pada action bar
    private fun setActionBarProperties(colorId: Int, title: String) {
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, colorId))
        )
        actionBar?.title = title
    }

    // fungsi untuk memasukkan data ke dalam view berdasarkan data yang dikirimkan dari adapter
    private fun fillActivityData() {
        edtTitle.setText(intent.getStringExtra(EXTRA_TITLE))
        edtDesc.setText(intent.getStringExtra(EXTRA_DESC))
        editStartTime.setText(intent.getStringExtra(EXTRA_TIME_START))
        editEndTime.setText(intent.getStringExtra(EXTRA_TIME_END))
    }

    // fungsi untuk menampikan error jika ada data yang kosong
    private fun showError(editText: EditText, errorMessage: String) {
        editText.error = errorMessage
    }

    // fungsi untuk update data ke dalam database
    private fun updateDataToDatabase(
        id: Int,
        title: String,
        desc: String,
        startTime: String,
        endTime: String,
        isDone: Boolean = false
    ) {
        val dailyActivity = DailyActivityEntity(id, startTime, endTime, title, desc, isDone)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                mDailyActivityRepository.update(dailyActivity)
            }
            showToast("Data berhasil diubah")
            navigateToMainActivity()
        }
    }

    // fungsi untuk memasukkan data ke dalam database
    private fun insertDataToDatabase(
        title: String,
        desc: String,
        startTime: String,
        endTime: String
    ) {
        val dailyActivity = DailyActivityEntity(
            timeStart = startTime, timeEnd = endTime, title = title, desc = desc
        )
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                mDailyActivityRepository.insert(dailyActivity)
            }
            showToast("Data berhasil dimasukkan")
            navigateToMainActivity()
        }
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

    // fungsi untuk menampilkan toast
    private fun showToast(message: String) {
        Toast.makeText(this@AddEditActivity, message, Toast.LENGTH_SHORT).show()
    }

    // fungsi untuk berpindah ke MainActivity
    private fun navigateToMainActivity() {
        val intent = Intent(this@AddEditActivity, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    // fungsi untuk menampilkan date picker jika time start di klik
    fun showTimeStart(view: View?) {
        showDateTimePicker(editStartTime)
    }

    // fungsi untuk menampilkan date picker jika time end di klik
    fun showTimeEnd(view: View) {
        showDateTimePicker(editEndTime)
    }

    // fungsi untuk menampilkan date picker
    private fun showDateTimePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                    val formattedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear)
                    val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    val dateTime = "$formattedDate $formattedTime"

                    editText.setText(dateTime)
                }, hour, minute, true)
                timePickerDialog.show()
            }, year, month, day)
        datePickerDialog.show()
    }

    // fungsi untuk mengambil data dari intent
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_TIME_START = "extra_time_start"
        const val EXTRA_TIME_END = "extra_time_end"
        const val EXTRA_STATUS = "status"
    }
}