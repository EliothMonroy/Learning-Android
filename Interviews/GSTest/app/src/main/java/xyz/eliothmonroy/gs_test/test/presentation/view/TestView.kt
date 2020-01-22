package xyz.eliothmonroy.gs_test.test.presentation.view

import net.grandcentrix.thirtyinch.TiView

interface TestView: TiView {
    fun getLoginResponse()
    fun showResponse(message:String)
}