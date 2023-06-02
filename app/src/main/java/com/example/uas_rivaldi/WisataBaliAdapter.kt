package com.example.uas_rivaldi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WisataBaliAdapter  (
    private  val context : Context,
    private  val bali : List<DataWisataBali>,
    private val listener : (DataWisataBali) -> Unit)
    : RecyclerView.Adapter<WisataBaliAdapter.BaliViewHolder>() {

    class BaliViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.item_img)
        private val name = view.findViewById<TextView>(R.id.title_item)


        fun bindView(hewan: DataWisataBali, listener: (DataWisataBali) -> Unit) {
            image.setImageResource(hewan.img)
            name.text = hewan.title
            itemView.setOnClickListener { listener(hewan) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaliViewHolder =
        BaliViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bali, parent, false))

    override fun getItemCount(): Int = bali.size

    override fun onBindViewHolder(holder: BaliViewHolder, position: Int) {
        holder.bindView(bali[position], listener)
    }

}