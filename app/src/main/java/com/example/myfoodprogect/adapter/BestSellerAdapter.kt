package com.example.myfoodprogect.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myfoodprogect.activity.DetailActivity
import com.example.myfoodprogect.model.ItemsModel
import com.example.myfoodprogect.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {
    private var context: Context? = null
    class ViewHolder (val binding: ViewholderBestSellerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder{
        context = parent.context
        val binding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ){
        holder.binding.TitleText.text = items[position].title
        holder.binding.priceText.text = "$" + items[position].price.toString()
        holder.binding.ratingText.text = items[position].rating.toString()

        val requestOption = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOption)
            .into(holder.binding.picBestSeller)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

}


