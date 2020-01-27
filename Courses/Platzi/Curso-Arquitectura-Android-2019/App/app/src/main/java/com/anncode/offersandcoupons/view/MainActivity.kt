package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var couponViewModel:CouponViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        //ya no es necesario usarlo
        //rvCoupons = findViewById(R.id.rvCoupons)
        //rvCoupons.layoutManager = LinearLayoutManager(this)

        setupBindings()
    }

    private fun setupBindings(){
        //Indicamos que vamos a usar data binding en está clase
        val activityMainBinding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //obtenemos el ViewModel de CouponViewModel
        couponViewModel= ViewModelProviders.of(this).get(CouponViewModel::class.java)
        //asignamos el ViewModel obtenido al activity
        activityMainBinding.model=couponViewModel
        setupListUpdate()
    }

    private fun setupListUpdate(){
        //Que se obtengan los cupones
        couponViewModel.callCoupons()
        //Obtenemos los cupones, pero al indicar el observe, implica que el código
        //dentro del Observer se ejecutará una vez que hayan cambios en la lista
        couponViewModel.getCoupons().observe(this, Observer {
            couponViewModel.setCouponsInRecyclerAdapter(it)
        })
    }

}
