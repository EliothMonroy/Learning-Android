package xyz.eliothmonroy.restconsume.coroutines.presentation.presenter

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import net.grandcentrix.thirtyinch.TiPresenter
import net.grandcentrix.thirtyinch.kotlin.deliverToView
import xyz.eliothmonroy.restconsume.coroutines.domain.CoroutinesInteractor
import xyz.eliothmonroy.restconsume.coroutines.domain.CoroutinesInteractorImpl
import xyz.eliothmonroy.restconsume.coroutines.presentation.view.CoroutinesView
import xyz.eliothmonroy.restconsume.util.common.data.service.ConstantsCommons

class CoroutinesPresenter : TiPresenter<CoroutinesView>() {

    private val coroutinesInteractor:CoroutinesInteractor=CoroutinesInteractorImpl()

    @ExperimentalCoroutinesApi
    fun getLoginResponse(email:String, password:String){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            coroutinesInteractor.getLoginResponse(email, password)
                .flowOn(Dispatchers.IO)
                .collect {result->
                    when(result.mCodeOperation){
                        ConstantsCommons.SUCCESS->{
                            deliverToView { showResult(result.mDescription) }
                        }
                        else->{
                            deliverToView { showResult(result.mDescription) }
                        }
                    }
                }
        }
    }

}