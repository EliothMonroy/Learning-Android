package xyz.eliothmonroy.sesion8.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import xyz.eliothmonroy.sesion8.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavigation()
    }

    private fun setNavigation() {
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_songs -> {
                    supportActionBar?.title = "Songs"
                    val songsFragment = SongsFragments()
                    openFragment(songsFragment)
                    true
                }
                R.id.navigation_albums -> {
                    supportActionBar?.title = "Albums"
                    val albumsFragment = AlbumsFragment()
                    openFragment(albumsFragment)
                    true
                }
                R.id.navigation_artists -> {
                    supportActionBar?.title = "Artists"
                    val artistsFragment = ArtistFragment()
                    openFragment(artistsFragment)
                    true
                }
                else->{false}

            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}
