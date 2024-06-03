package com.example.myfoodprogect.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myfoodprogect.Adapter.PicListAdapter
import com.example.myfoodprogect.Adapter.SizeListAdapter
import com.example.myfoodprogect.Model.ItemsModel
import com.example.myfoodprogect.databinding.ActivityDetailBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BasicActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        initList()

    }

    private fun initList() {
        val sizeList = ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter = SizeListAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter = PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item= intent.getParcelableExtra("object")!!
        binding.TitleText.text = item.title
        binding.descriptionText.text = item.description
        binding.priceText.text = "$" + item.price
        binding.ratingText.text = "${item.rating} "
        binding.sellerNameText.text = item.sellerName

        binding.addToCartButton.setOnClickListener{
            item.numberInCart = numberOrder
            managmentCart.insertItems(item)
        }

        binding.backButton.setOnClickListener{ finish()}
        binding.backButton.setOnClickListener{
        startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }

        Glide.with(this)
            .load(item.sellerPic)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(binding.picSeller)

        binding.messageToSellerButton.setOnClickListener {
            val sendIntent =Intent(Intent.ACTION_VIEW)
            sendIntent.setData(Uri.parse("sms"+item.sellerTell))
            sendIntent.putExtra("sms_body", "type your message")
            startActivity(intent)
        }
        binding.callToSellerButon.setOnClickListener {
            val phone = item.sellerName.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

    }
}