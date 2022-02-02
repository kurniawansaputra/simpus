package id.go.kebumenkab.simpus.ui.activity

import android.os.Bundle
import id.go.kebumenkab.simpus.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableNightMode()
    }
}