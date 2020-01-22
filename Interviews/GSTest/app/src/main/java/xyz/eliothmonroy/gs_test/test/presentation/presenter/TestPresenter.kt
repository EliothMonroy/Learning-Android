package xyz.eliothmonroy.gs_test.test.presentation.presenter

import net.grandcentrix.thirtyinch.TiPresenter
import net.grandcentrix.thirtyinch.kotlin.deliverToView
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler
import net.grandcentrix.thirtyinch.rx.RxTiPresenterUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import xyz.eliothmonroy.gs_test.test.domain.TestInteractor
import xyz.eliothmonroy.gs_test.test.domain.TestInteractorImpl
import xyz.eliothmonroy.gs_test.test.presentation.view.TestView
import xyz.eliothmonroy.gs_test.util.common.data.service.ConstantsCommons

class TestPresenter : TiPresenter<TestView>() {
    private val rxTiPresenterSubscriptionHandler: RxTiPresenterSubscriptionHandler =
        RxTiPresenterSubscriptionHandler(this)
    private val testInteractor:TestInteractor=TestInteractorImpl()

    fun getLoginResponse(email:String, password:String){
        rxTiPresenterSubscriptionHandler.manageSubscription(
            testInteractor
                .getLoginResponse(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxTiPresenterUtils.deliverLatestToView(this))
                .subscribe({ response->
                    when(response.mCodeOperation){
                        ConstantsCommons.SUCCESS->{
                            deliverToView { showResponse(response.mDescription) }
                        }
                        else->{
                            deliverToView { showResponse(response.mDescription) }
                        }
                    }
                }, {throwable->
                    deliverToView { showResponse("ERROR"+throwable.message)}
                })
        )
    }

    fun getLoginResponseRx(email: String, password: String){
         testInteractor.getLoginResponse(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
             .subscribe({response->
                 when(response.mCodeOperation){
                     ConstantsCommons.SUCCESS->{
                         deliverToView { showResponse(response.mDescription) }
                     }
                     else->{
                         deliverToView { showResponse(response.mDescription) }
                     }
                 }
             },{throwable->
                 deliverToView { showResponse("ERROR"+throwable.message)}
             })
    }

}