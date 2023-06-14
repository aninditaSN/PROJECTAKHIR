package com.example.netblinkapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.netblinkapp.DataMahasiswa

class ItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.idgambar)
    private val textView: TextView = itemView.findViewById(R.id.idnama)


    fun bind(Datamhs: DataMahasiswa) {
        imageView.setImageResource(Datamhs.gambar)
        textView.text = Datamhs.nama
    }
}