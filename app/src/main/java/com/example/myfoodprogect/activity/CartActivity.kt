package com.example.myfoodprogect.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfoodprogect.adapter.CartAdapter
import com.example.myfoodprogect.databinding.ActivityCartBinding
import com.example.myfoodprogect.helper.ChangeNumberItemsListener
import com.example.myfoodprogect.helper.ManagementCart


class CartActivity : BasicActivity() {
    private lateinit var binding:ActivityCartBinding
    private lateinit var managementCart: ManagementCart
    private var tax:Double =0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        setVariable()
        initCartList()
        calculaterCart()

    }

    private fun calculaterCart() {
        val persentTax = 0.02
        val delivery = 15.0
        tax= Math.round((managementCart.getTotalFee()*persentTax)*100)/100.0
        val total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100

        with(binding) {
            totalFeeText.text = "$$itemTotal"
            taxText.text = "$$tax"
            deliveryText.text ="$$delivery"
            totalText.text ="$$total"
        }

    }


    private fun initCartList() {
        binding.cartView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter = CartAdapter(managementCart.getListCart(), this, object  :
            ChangeNumberItemsListener {
            override fun onChanged() {
                calculaterCart()
            }
        })
    }

    private fun setVariable() {
        binding.backButton.setOnClickListener { finish() }
    }
}