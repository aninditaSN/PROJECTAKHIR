package com.example.netblinkapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_DESC
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_ID
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_TIME_END
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_TIME_START
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_TITLE
import com.example.netblinkapp.AddEditActivity.Companion.EXTRA_STATUS
import com.example.netblinkapp.DailyActivityEntity
import com.example.netblinkapp.DetailArchiveActivity

class DailyActivityAdapter(
    private val list: List<DailyActivityEntity>,
    private val context: Context
) :
    RecyclerView.Adapter<DailyActivityAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitleFinalValue)
        val dateStart: TextView = itemView.findViewById(R.id.tvRangeTimeStartValueFinal)
        val dateEnd: TextView = itemView.findViewById(R.id.tvRangeTimeEndValueFinal)
        val desc: TextView = itemView.findViewById(R.id.tvDescValueFinal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.dateStart.text = list[position].timeStart
        holder.dateEnd.text = list[position].timeEnd
        holder.desc.text = list[position].desc

//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, AddEditActivity::class.java)
//            intent.putExtra(EXTRA_ID, list[position].id)
//            intent.putExtra(EXTRA_TITLE, list[position].title)
//            intent.putExtra(EXTRA_TIME_START, list[position].timeStart)
//            intent.putExtra(EXTRA_TIME_END, list[position].timeEnd)
//            intent.putExtra(EXTRA_DESC, list[position].desc)
//            intent.putExtra(EXTRA_STATUS, list[position].isDone)
//            context.startActivity(intent)
//        }

        holder.itemView.setOnClickListener {
            if (list[position].isDone) {
                // Jika isDone = true, tidak membuka AddEditActivity
                val intent = Intent(context, DetailArchiveActivity::class.java)
                intent.putExtra(EXTRA_ID, list[position].id)
                intent.putExtra(EXTRA_TITLE, list[position].title)
                intent.putExtra(EXTRA_TIME_START, list[position].timeStart)
                intent.putExtra(EXTRA_TIME_END, list[position].timeEnd)
                intent.putExtra(EXTRA_DESC, list[position].desc)
                intent.putExtra(EXTRA_STATUS, list[position].isDone)
                context.startActivity(intent)
            } else {
                val intent = Intent(context, AddEditActivity::class.java)
                intent.putExtra(EXTRA_ID, list[position].id)
                intent.putExtra(EXTRA_TITLE, list[position].title)
                intent.putExtra(EXTRA_TIME_START, list[position].timeStart)
                intent.putExtra(EXTRA_TIME_END, list[position].timeEnd)
                intent.putExtra(EXTRA_DESC, list[position].desc)
                intent.putExtra(EXTRA_STATUS, list[position].isDone)
                context.startActivity(intent)
            }
        }
    }
}