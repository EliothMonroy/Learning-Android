package com.anncode.offersandcoupons.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anncode.offersandcoupons.BR
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

class RecyclerCouponsAdapter(private var couponViewModel: CouponViewModel, private var resource: Int) : RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    private lateinit var coupons:List<Coupon>

    fun setCouponsList(coupons : List<Coupon>){
        this.coupons=coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        val binding:ViewDataBinding=DataBindingUtil.inflate(layoutInflater,p1,p0,false)
        return CardCouponHolder(binding)
    }

    override fun getItemCount(): Int {
        return coupons.size
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        p0.setDataCard(couponViewModel,p1)
    }

    override fun getItemViewType(position: Int): Int {
        return resource
    }

    class CardCouponHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setDataCard(couponViewModel: CouponViewModel,position: Int){
            binding.setVariable(BR.model,couponViewModel)
            binding.setVariable(BR.position,position)
            binding.executePendingBindings()
        }

    }

}
