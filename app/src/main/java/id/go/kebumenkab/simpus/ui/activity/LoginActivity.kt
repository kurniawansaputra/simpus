package id.go.kebumenkab.simpus.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import id.go.kebumenkab.simpus.databinding.ActivityLoginBinding
import id.go.kebumenkab.simpus.utils.Loading

class LoginActivity : BaseActivity() {
    private lateinit var idNumber: String
    private lateinit var password: String
    private lateinit var loading: Loading

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableNightMode()
        loading = Loading(this)
        setListener()
    }

    private fun setListener() {
        binding.apply {
            editIdNumber.addTextChangedListener(textWatcherIdNumber)
            editPassword.addTextChangedListener(textWatcherPassword)
            buttonLogin.setOnClickListener {
                loading.startLoading()
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    private val textWatcherIdNumber: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.apply {
                if (editIdNumber.text!!.length < 16) {
                    containerIdNumber.error = "NIK tidak valid"
                } else if (editIdNumber.text!!.length == 16) {
                    containerIdNumber.error = null
                }
                validateInput()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val textWatcherPassword: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.apply {
                if (editPassword.text!!.isEmpty()) {
                    containerPassword.error = "Data harus diisi"
                } else {
                    containerPassword.error = null
                }
                validateInput()
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private fun validateInput() {
        binding.apply {
            password = editPassword.text.toString()
            idNumber = editIdNumber.text.toString()
            buttonLogin.isEnabled = idNumber.isNotEmpty() && idNumber.length == 16 && password.isNotEmpty()
        }
    }
}