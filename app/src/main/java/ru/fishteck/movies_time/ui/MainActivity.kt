package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.fishteck.movies_time.*

class MainActivity : AppCompatActivity() {

    private var popularMoviesFragment: PopularMoviesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            popularMoviesFragment = PopularMoviesFragment.newInstance()
            popularMoviesFragment?.apply {
                replaceFragment(
                        fragment = this,
                        fragmentTag = PopularMoviesFragment.TAG
                )
            }
        } else {
            popularMoviesFragment =
                    supportFragmentManager
                            .findFragmentByTag(PopularMoviesFragment.TAG) as? PopularMoviesFragment
        }



        findViewById<ImageButton>(R.id.main_bottom_nav_btn_home).setOnClickListener {
            replaceFragment(
                    fragment = PopularMoviesFragment.newInstance(),
                    fragmentTag = PopularMoviesFragment.TAG
            )

        }

        findViewById<ImageButton>(R.id.main_bottom_nav_btn_profile).setOnClickListener {

            supportFragmentManager.popBackStack()
            replaceFragment(
                    fragment = ProfileFragment.newInstance(),
                    fragmentTag = ProfileFragment.TAG,
                    true
            )
        }

    }

    private fun replaceFragment(fragment: Fragment, fragmentTag: String, addBackStack: Boolean = false) {

        val fragmentPopped: Boolean = supportFragmentManager
                .popBackStackImmediate(fragmentTag, 0)


        if (addBackStack) {
            if (!fragmentPopped && supportFragmentManager.findFragmentByTag(ProfileFragment.TAG) == null) {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_nav_fragment, fragment, fragmentTag)
                        .addToBackStack(null)
                        .commit()
            }

        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_nav_fragment, fragment, fragmentTag)
                    .commit()
        }
    }


}
