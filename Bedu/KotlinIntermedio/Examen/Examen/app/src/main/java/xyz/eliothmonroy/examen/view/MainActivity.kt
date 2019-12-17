package xyz.eliothmonroy.examen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import xyz.eliothmonroy.examen.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigation()

        setFragment(HomeFragment(),false)


    }

    private fun setNavigation() {
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home ->{
                    supportActionBar?.title=getString(R.string.title_fragment_home)
                    setFragment(HomeFragment(),true)
                    true
                }
                R.id.navigation_cart ->{
                    supportActionBar?.title=getString(R.string.title_fragment_cart)
                    setFragment(CartFragment(),true)
                    true
                }
                R.id.navigation_profile ->{
                    supportActionBar?.title=getString(R.string.title_fragment_profile)
                    setFragment(ProfileFragment(),true)
                    true
                }
                R.id.navigation_settings ->{
                    supportActionBar?.title=getString(R.string.title_fragment_setting)
                    setFragment(SettingsFragment(),true)
                    true
                }
                else->false
            }
        }
    }

    private fun setFragment(fragment:Fragment,addToBackStack:Boolean){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if(addToBackStack){
            transaction.addToBackStack(null)
        }else{
            transaction.disallowAddToBackStack()
        }
        transaction.commit()
    }

}
