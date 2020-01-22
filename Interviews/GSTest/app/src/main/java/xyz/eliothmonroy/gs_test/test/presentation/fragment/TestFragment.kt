package xyz.eliothmonroy.gs_test.test.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import net.grandcentrix.thirtyinch.TiFragment
import xyz.eliothmonroy.gs_test.R
import xyz.eliothmonroy.gs_test.test.presentation.presenter.TestPresenter
import xyz.eliothmonroy.gs_test.test.presentation.view.TestView

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : TiFragment<TestPresenter,TestView>(),TestView {

    override fun providePresenter(): TestPresenter {
        return TestPresenter()
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getLoginResponse()
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun getLoginResponse() {
        //presenter.getLoginResponse("castilloreyesjuan@gmail.com","12345678")
        presenter.getLoginResponseRx("castilloreyesjuan@gmail.com","12345678")
    }

    override fun showResponse(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    companion object{
        val TAG=TestFragment::class.java.simpleName
    }
}
