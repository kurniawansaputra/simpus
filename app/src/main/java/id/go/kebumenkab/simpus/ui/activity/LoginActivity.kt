package id.go.kebumenkab.simpus.ui.activity

import android.content.Intent
import android.os.Bundle
import id.go.kebumenkab.simpus.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableNightMode()
        setListener()
    }

    private fun setListener() {
        binding.apply {
            buttonLogin.setOnClickListener {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}