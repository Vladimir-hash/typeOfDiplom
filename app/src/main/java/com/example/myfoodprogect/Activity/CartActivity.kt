package com.example.myfoodprogect.Activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodprogect.Adapter.CartAdapter
import com.example.myfoodprogect.Helper.ChangeNumberItemsListener
import com.example.myfoodprogect.databinding.ActivityCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BasicActivity() {
    private lateinit var binding:ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax:Double =0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculaterCart()

    }

    private fun calculaterCart() {
        val persentTax = 0.02
        val delivery = 15.0
        tax= Math.round((managmentCart.getTotalFee()*persentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding) {
            totalFeeText.text = "$$itemTotal"
            taxText.text = "$$tax"
            deliveryText.text ="$$delivery"
            totalText.text ="$$total"
        }

    }


    private fun initCartList() {
        binding.cartView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter = CartAdapter(managmentCart.getListCart(), this, object  : ChangeNumberItemsListener{
            override fun onChanged() {
                calculaterCart()
            }
        })
    }

    private fun setVariable() {
        binding.backButton.setOnClickListener { finish() }
    }
}