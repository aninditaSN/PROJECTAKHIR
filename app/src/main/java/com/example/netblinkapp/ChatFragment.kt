package com.example.netblinkapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netblinkapp.databinding.FragmentListBinding

class ChatFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var DatamhsAdapter: Adapter
    private lateinit var Datamhs: MutableList<DataMahasiswa>

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)



        recyclerView = view.findViewById(R.id.recyclerview)

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        Datamhs = mutableListOf()
        Datamhs.add(DataMahasiswa("John Wick 4",  R.drawable.johnwik))
        Datamhs.add(DataMahasiswa("John Wick 3",  R.drawable.johnwik3))
        Datamhs.add(DataMahasiswa("IP Man 3",  R.drawable.ipman3))
        Datamhs.add(DataMahasiswa("Blackpanther Wakanda Forever",  R.drawable.bpwk))
        Datamhs.add(DataMahasiswa("Iron Man 3",  R.drawable.ironman3))
        Datamhs.add(DataMahasiswa("Qorin",  R.drawable.qorin))
        Datamhs.add(DataMahasiswa("Spiderman Far From Home",  R.drawable.spdr))
        Datamhs.add(DataMahasiswa("Avengers 4 End Game",  R.drawable.avengers4))
        Datamhs.add(DataMahasiswa("Mr.Bean The Movie",  R.drawable.beanmv))
        Datamhs.add(DataMahasiswa("Jackass The Movie",  R.drawable.jackass))

        DatamhsAdapter = Adapter(Datamhs)
        recyclerView.adapter = DatamhsAdapter

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_list -> showList()
            R.id.menu_grid -> showGrid()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showList() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = DatamhsAdapter
    }

    private fun showGrid() {
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = DatamhsAdapter
    }
}
