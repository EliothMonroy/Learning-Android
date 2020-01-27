package xyz.eliothmonroy.restconsume.coroutines.presentation.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.grandcentrix.thirtyinch.TiFragment
import xyz.eliothmonroy.restconsume.R
import xyz.eliothmonroy.restconsume.coroutines.presentation.presenter.CoroutinesPresenter
import xyz.eliothmonroy.restconsume.coroutines.presentation.view.CoroutinesView

/**
 * A simple [Fragment] subclass.
 */
class CoroutinesFragment : TiFragment<CoroutinesPresenter, CoroutinesView>(),CoroutinesView {

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coroutines, container, false)
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.getLoginResponse("castilloreyesjuan@gmail.com","12345678")
    }

    override fun providePresenter(): CoroutinesPresenter {
        return CoroutinesPresenter()
    }

    override fun showResult(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    companion object{
        val TAG=CoroutinesFragment::class.java.simpleName
    }

}
