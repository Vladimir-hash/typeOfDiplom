package com.example.myfoodprogect.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodprogect.R
import com.example.myfoodprogect.databinding.ViewholderSizeBinding

class SizeListAdapter(val items:MutableList<String>):
    RecyclerView.Adapter<SizeListAdapter.ViewHolder>(){
        private var selectedPosition = -1
        private  var lastSettledPosition = -1
    private lateinit var context: Context
    inner class ViewHolder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: SizeListAdapter.ViewHolder, position: Int) {

            holder.binding.sizeText.text = items[position]


            holder.binding.root.setOnClickListener{
                lastSettledPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(lastSettledPosition)
                notifyItemChanged(selectedPosition)


            }
        if (selectedPosition == position) {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg3)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.white))
        } else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.black))
        }
    }
    override fun getItemCount(): Int = items.size

}