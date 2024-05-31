package com.example.myfoodprogect.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myfoodprogect.Model.ItemsModel
import com.example.myfoodprogect.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
    private var context: Context? = null
    class Viewholder (val binding: ViewholderBestSellerBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  BestSellerAdapter.Viewholder{
        context = parent.context
        val binding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.Viewholder, position: Int ){
        holder.binding.titleText.text = items[position].title
        holder.binding.priceText.text = "$" + items[position].price.toString()
        holder.binding.ratingText.text = items[position].rating.toString()

        val requestOption = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOption)
            .into(holder.binding.picBestSeller)
    }

    override fun getItemCount(): Int = items.size

}


