package com.notes.crud.zenolab.crud

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val LOG_TAG = RuntimeException().stackTrace[0].className
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_app_bar) //bottom
        replaceFragment(ScrollFragment())
        val fab: View = findViewById(R.id.fab_center)
        fab.setOnClickListener { view ->
            Log.d(LOG_TAG, "Press FAB");
            Toast.makeText(this@MainActivity, "Its toast!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        /**
         *
         * * popBackStack () будет выполнять команды выталкивания в течение
         * следующего цикла loop события (то есть следующей фазы отрисовки).
         * Так что это асинхронно с остальной частью кода. Это означает,
         * что FragmentTransaction не будет удалена из backstack после того,
         * как он будет выполнен.
         * В большинстве случаев вам не нужно немедленно извлекать FragmentTransaction,
         * поэтому он ждет, пока все остальное не будет завершено,
         * прежде чем это действительно произойдет.
         * Все это происходит так быстро, что пользователь этого не узнает.
         *
         *
         * popBackStackImmediate () будет выполнять команды выталкивания немедленно в вызове.
         * Результаты которого можно проверить сразу после вызова.
         * Это несколько медленнее, так как все действия появляются в вызове.
         *
         * Флаг в конце не имеет отношения.
         * В настоящее время он может быть установлен только на POP_BACK_STACK_INCLUSIVE.
         * FragmentManager позволяет вам установить идентификатор для backstack.
         * Если вы установите флаг, то он будет выдвигать FragmentTransaction,
         * который соответствует указанному идентификатору, пока не будет найден флаг,
         * который не соответствует идентификатору, или пока не будет достигнут нижний уровень.
         * Если флаг не установлен, то все FragmentTransactions,
         * которые не соответствуют идентификатору, выталкиваются,
         * пока не будет достигнут один,
         * который соответствует идентификатору или достигнут нижний уровень.
         */
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)

        if (!fragmentPopped) { //fragment not in back stack, create it.
            val ft = manager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.app_bar_fav -> toast(getString(R.string.fav_clicked))
            R.id.app_bar_search -> toast(getString(R.string.search_clicked))
            R.id.app_bar_settings -> toast(getString(R.string.settings_clicked))
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

    // This is an extension method for easy Toast call
    fun Context.toast(message: CharSequence) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    public override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}



