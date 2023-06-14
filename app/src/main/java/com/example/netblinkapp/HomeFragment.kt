package com.example.netblinkapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {
    // deklarasi repository
    private lateinit var dailyActivityRepository: DailyActivityRepository

    // deklarasi recyclerview dan view lainnya
    private lateinit var rvDailyActivity: RecyclerView
    private lateinit var tvEmptyActivity: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val pindah = view.findViewById<Button>(R.id.btn_lihat)
        pindah.setOnClickListener{
            val intent = Intent(requireActivity(),MainActivity2::class.java)
            startActivity(intent)
        }
        //initializeViews(view)
        //setupRecyclerView(view)

        // deklarasi repository
//            dailyActivityRepository = DailyActivityRepository(requireActivity().application)

        // fungsi untuk menampilkan data dari database ke recyclerview
//            dailyActivityRepository.getAllDailyActivity()
//                .observe(viewLifecycleOwner) { dailyActivity ->
//                    if (dailyActivity.isEmpty()) {
//                        tvEmptyActivity.visibility = View.VISIBLE
//                    } else {
//                        tvEmptyActivity.visibility = View.GONE
//                        val adapter = DailyActivityAdapter(dailyActivity, requireContext()).apply {
//                            setHasStableIds(true)
//                        }
//                        rvDailyActivity.adapter = adapter
//                    }
//                }

        // fungsi untuk menampilkan activity tambah ketika tombol tambah di klik
//            val btnTambah = view.findViewById<View>(R.id.btnTambah)
//            btnTambah.setOnClickListener {
//                val intent = Intent(requireContext(), AddEditActivity::class.java)
//                startActivity(intent)
//            }

        return view
    }

    // fungsi untuk menampilkan menu/ikon arsip di pojok kanan atas
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_arsip, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // fungsi untuk menampilkan activity arsip ketika ikon arsip di klik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            startActivity(Intent(requireContext(), ArchiveActivity::class.java))
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    // fungsi untuk mengubah warna action bar dan judul action bar
//        private fun initializeViews() {
//            val actionBar = supportActionBar
//            actionBar?.setBackgroundDrawable(
//                ColorDrawable(ContextCompat.getColor(this, R.color.colorMainActivity))
//            )
//            actionBar?.title = "Daftar Aktivitas"
//            rvDailyActivity = findViewById(R.id.rvDailyActivity)
//            tvEmptyActivity = findViewById(R.id.tvEmptyActivity)
//        }

    // fungsi untuk mengatur tampilan recyclerview dengan memberi garis pemisah antar item
    private fun setupRecyclerView(view: View) {
        rvDailyActivity = view.findViewById(R.id.rvDailyActivity)
        rvDailyActivity.layoutManager = LinearLayoutManager(requireContext())
        rvDailyActivity.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}




