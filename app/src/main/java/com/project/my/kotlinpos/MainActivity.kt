package com.project.my.kotlinpos

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.project.my.kotlinpos.Fragment.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var doubleBackToExitPressedOnce = false
    private var use=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref=getSharedPreferences("Login", Context.MODE_PRIVATE)
        use=pref.getBoolean("session",false)

        Toast.makeText(applicationContext,use.toString(),Toast.LENGTH_SHORT).show()
        if (use){
            viewin()
        }else{
            viewout()
        }

    }

    private fun viewin(){
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun viewout(){
        setContentView(R.layout.main_loading)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportFragmentManager.beginTransaction().add(R.id.frame,LoginFragment()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) run {
            supportFragmentManager.popBackStack()
        }else if (!doubleBackToExitPressedOnce){

            this.doubleBackToExitPressedOnce=true
            Toast.makeText(applicationContext,"Please click BACK again to exit!",Toast.LENGTH_SHORT).show()

            Handler().postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }else{
            super.onBackPressed()
            return
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
//                supportFragmentManager.beginTransaction().add(R.id.frame,LoginFragment()).commit()
            }
            R.id.nav_gallery -> {
                Toast.makeText(applicationContext,"Gallery",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_slideshow -> {
                Toast.makeText(applicationContext,"Slideshow",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_manage -> {
                Toast.makeText(applicationContext,"Manage",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(applicationContext,"Share",Toast.LENGTH_SHORT).show()
            }
            R.id.nav_send -> {
                Toast.makeText(applicationContext,"Send",Toast.LENGTH_SHORT).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
