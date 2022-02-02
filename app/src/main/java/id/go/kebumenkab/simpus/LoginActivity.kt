package id.go.kebumenkab.simpus

import android.os.Bundle
import id.go.kebumenkab.simpus.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableNightMode()
    }
}