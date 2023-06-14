package com.example.netblinkapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netblinkapp.ItemViewHolder
import com.example.netblinkapp.R
import com.example.netblinkapp.DataMahasiswa

class Adapter (private val listMahasiswa: List<DataMahasiswa>) :
RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val mahasiswa = listMahasiswa[position]
        holder.bind(mahasiswa)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

}