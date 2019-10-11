package com.anncode.offersandcoupons.model

import com.anncode.offersandcoupons.presenter.CouponsPresenter

class CouponInteractorImpl(var couponsPresenter: CouponsPresenter): CouponInteractor {

    private var couponRepository=CouponRepositoryImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>) {
        couponsPresenter.showCoupons(coupons)
    }

    override fun getCouponsAPI() {
        couponRepository.getCouponsAPI()
    }
}