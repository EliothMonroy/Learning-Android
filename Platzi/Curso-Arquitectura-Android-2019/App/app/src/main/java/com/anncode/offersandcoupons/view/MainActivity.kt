package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.presenter.CouponsPresenterImpl

class MainActivity : AppCompatActivity(), CouponView {

    private lateinit var couponsPresenter:CouponsPresenterImpl
    private lateinit var rvCoupons: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        couponsPresenter=CouponsPresenterImpl(this)

        rvCoupons = findViewById(R.id.rvCoupons)
        rvCoupons.layoutManager = LinearLayoutManager(this)

        getCoupons()
    }

    override fun getCoupons() {
        couponsPresenter.getCoupons()
    }

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        rvCoupons.adapter=RecyclerCouponsAdapter(coupons,R.layout.card_coupon)
    }
}
