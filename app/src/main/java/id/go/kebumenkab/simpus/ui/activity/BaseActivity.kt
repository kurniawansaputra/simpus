package id.go.kebumenkab.simpus.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    protected fun disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}