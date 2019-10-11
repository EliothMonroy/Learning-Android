package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponsPresenter {
    fun showCoupons(coupons:ArrayList<Coupon>)
    fun getCoupons()
}