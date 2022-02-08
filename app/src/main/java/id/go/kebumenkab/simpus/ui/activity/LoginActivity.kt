package id.go.kebumenkab.simpus.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import id.go.kebumenkab.simpus.databinding.ActivityLoginBinding
import id.go.kebumenkab.simpus.databinding.LayoutDialogLoginFailedBinding
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
                if (idNumber == "3306092908970001" && password == "1234") {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(layoutInflater)
                    val builder: AlertDialog.Builder = AlertDialog.Builder(layoutInflater.context)
                    builder.setView(binding.root)
                    val dialog: AlertDialog = builder.create()
                    binding.buttonOk.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.setCancelable(true)
                    dialog.show()
                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
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