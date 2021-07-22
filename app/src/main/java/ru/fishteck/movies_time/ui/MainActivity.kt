package ru.fishteck.movies_time.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.fishteck.movies_time.*
import ru.fishteck.movies_time.adapters.GenresListAdapter
import ru.fishteck.movies_time.data.local.GenresDTO
import ru.fishteck.movies_time.data.local.GenresDataSourceImpl
import ru.fishteck.movies_time.data.local.MoviesDTO
import ru.fishteck.movies_time.data.local.MoviesDataSourceImpl
import ru.fishteck.movies_time.utils.DiffUtilMovies
import ru.fishteck.movies_time.utils.GridItemDecoration

class MainActivity : AppCompatActivity() {

    private var popularMoviesFragment: PopularMoviesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            popularMoviesFragment = PopularMoviesFragment()
            popularMoviesFragment?.apply {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_nav_fragment, this, PopularMoviesFragment.TAG)
                        .commit()
            }
        } else {
            popularMoviesFragment =
                    supportFragmentManager
                            .findFragmentByTag(PopularMoviesFragment.TAG) as? PopularMoviesFragment
        }

        findViewById<ImageButton>(R.id.main_bottom_nav_btn_home).setOnClickListener {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_nav_fragment, PopularMoviesFragment(), PopularMoviesFragment.TAG)
                    .commit()
        }

        findViewById<ImageButton>(R.id.main_bottom_nav_btn_profile).setOnClickListener {

            if (supportFragmentManager.findFragmentByTag(ProfileFragment.TAG) == null) {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_nav_fragment, ProfileFragment(), ProfileFragment.TAG)
                        .addToBackStack(null)
                        .commit()
            }
        }

    }


}
