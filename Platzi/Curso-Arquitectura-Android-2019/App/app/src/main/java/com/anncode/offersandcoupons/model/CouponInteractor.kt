package com.anncode.offersandcoupons.model

interface CouponInteractor {
    fun getCouponsAPI()
    fun showCoupons(coupons:ArrayList<Coupon>)
}