package xyz.eliothmonroy.restconsume.coroutines.presentation.view

import net.grandcentrix.thirtyinch.TiView

interface CoroutinesView: TiView {
    fun showResult(message:String)
}