package com.example.studentoffice.ui.state.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.studentoffice.R
import com.example.studentoffice.model.App
import com.example.studentoffice.model.SharedPrefConst
import com.example.studentoffice.model.User
import com.example.studentoffice.ui.state.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.switch_item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var switchTheme: Switch
    private lateinit var headerLayout: ConstraintLayout
    private lateinit var imageViewLogout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme_Dark)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        hideElemsByUser(nav_view.menu, User.getUser().type)
        setControllers()
        setListeners()
    }

    private fun init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        navController = findNavController(R.id.nav_host_fragment)
        switchTheme = nav_view.menu.findItem(R.id.app_bar_switch).actionView.switch_theme
        headerLayout = nav_view.getHeaderView(0) as ConstraintLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_account,
                R.id.nav_news,
                R.id.nav_visit_requests,
                R.id.nav_requests_to_admin,
                R.id.nav_group_statistics
            ), drawer_layout
        )
        imageViewLogout = headerLayout.getViewById(R.id.imageViewLogout) as ImageView
    }

    private fun setControllers() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun changeTheme(isSwitchClicked: Boolean) {
        val sharedPreferences =
            App.getAppContext().getSharedPreferences(SharedPrefConst.KEY_NAME.string, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val themeId: Int = if (isSwitchClicked) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(themeId)
        editor.putInt(SharedPrefConst.KEY_THEME.string, themeId)
        editor.apply()
    }
    //лайвдата
    private fun logout() {
        val sPref = App.getAppContext()
            .getSharedPreferences(SharedPrefConst.KEY_NAME.string, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sPref.edit()
        editor.remove(SharedPrefConst.KEY_ACCESS_TOKEN.string)
        editor.remove(SharedPrefConst.KEY_REFRESH_TOKEN.string)
        editor.apply()
        val mIntent = Intent(this, LoginActivity::class.java)
        startActivity(mIntent)
        finish()
    }

    private fun requsetLocationPermision() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
    }

    private fun hasPermisionLocation(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setListeners() {
        mainViewModel.localResponse.observe(this, Observer {
            if (!it.loading) {
                fab.visibility = View.VISIBLE
                Toast.makeText(
                    this,
                    "Latitude: [" + it.location.latitude.toString() + "]"
                            + "\n"
                            + "Longitude: [" + it.location.longitude.toString() + "]",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        fab.setOnClickListener {
            if (hasPermisionLocation()) {
                mainViewModel.requestLocation()
            } else {
                requsetLocationPermision()
            }
            it.visibility = View.GONE
        }
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            changeTheme(isChecked)
        }
        imageViewLogout.setOnClickListener {
            logout()
        }
    }

    private fun hideElemsByUser(menu: Menu, typeOfUser: String) {
        when (typeOfUser) {
            "student" -> {
                menu.setGroupVisible(R.id.headmanGroup, false)
                menu.setGroupVisible(R.id.adminGroup, false)
            }
            "headman" -> {
                menu.setGroupVisible(R.id.adminGroup, false)
            }
            "admin" -> {
                menu.setGroupVisible(R.id.headmanGroup, false)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}