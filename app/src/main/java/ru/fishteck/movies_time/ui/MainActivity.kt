package ru.fishteck.movies_time.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.remote.MovieWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        setUpBottomNav(navController)
        setUpWorker()

    }

    private fun setUpWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val worker =
            PeriodicWorkRequestBuilder<MovieWorker>(1, TimeUnit.HOURS).setConstraints(constraints)
                .build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            "MovieWork",
            ExistingPeriodicWorkPolicy.KEEP,
            worker
        )
    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bottom_nav)
        bottomNavigationView?.setupWithNavController(navController)
    }


}
