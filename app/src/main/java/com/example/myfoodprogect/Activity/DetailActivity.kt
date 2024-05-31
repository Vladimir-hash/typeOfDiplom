package com.example.myfoodprogect.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.myfoodprogect.Model.ItemsModel
import com.example.myfoodprogect.R
import com.example.myfoodprogect.databinding.ActivityDetailBinding
import com.example.myfoodprogect.databinding.ViewholderPicListBinding
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : BasicActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate((layoutInflater))
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()

    }
    private fun getBundle() {
        item= intent.getParcelableExtra("object")!!
        binding.titleText.text = item.title
        binding.descriptionText.text = item.description
        binding.priceText.text = "$" + item.price
        binding.ratingText.text = "${item.rating} Rating"
        binding.sellerNameText.text = item.sellerName

        binding.addToCartButton.setOnClickListener{
            item.numberInCart = numberOrder
            managmentCart.insertItems(item)
        }

        binding.backButton.setOnClickListener{ finish()}
        binding.backButton.setOnClickListener{

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