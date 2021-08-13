package ru.fishteck.movies_time.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fishteck.movies_time.*
import ru.fishteck.movies_time.di.AppComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment : NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        setUpBottomNav(navController)

    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bottom_nav)
        bottomNavigationView?.setupWithNavController(navController)
    }

}
