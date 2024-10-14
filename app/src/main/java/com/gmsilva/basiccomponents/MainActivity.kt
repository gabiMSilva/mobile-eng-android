package com.gmsilva.basiccomponents

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.gmsilva.basiccomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val THEME_PREFERENCES_LABEL = "THEME_PREFERENCES"
    private val THEME_MODE_LABEL = "THEME_MODE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(THEME_PREFERENCES_LABEL, Context.MODE_PRIVATE)

        recoveryAndApplyTheme();
        setContentView(binding.root)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun applyTheme(theme: Int) {
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    private fun recoveryAndApplyTheme() {
        applyTheme(getThemePreference());
    }

    private fun getThemePreference(): Int {
        return sharedPreferences.getInt(THEME_MODE_LABEL, MODE_NIGHT_NO)
    }

    private fun saveThemePreference(theme: Int) {
        sharedPreferences.edit().putInt(THEME_MODE_LABEL, theme).apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_theme, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_theme_light -> {
                applyTheme(MODE_NIGHT_NO)
                saveThemePreference(MODE_NIGHT_NO)
                true
            }

            R.id.menu_theme_dark -> {
                applyTheme(MODE_NIGHT_YES)
                saveThemePreference(MODE_NIGHT_YES)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}