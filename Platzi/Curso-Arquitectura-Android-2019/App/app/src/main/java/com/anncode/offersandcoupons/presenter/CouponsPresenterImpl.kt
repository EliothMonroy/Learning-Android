package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponInteractorImpl
import com.anncode.offersandcoupons.view.CouponView

class CouponsPresenterImpl(private var couponView: CouponView):CouponsPresenter {

    private var couponInteractor= CouponInteractorImpl(this)

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        couponView.showCoupons(coupons)
    }

}