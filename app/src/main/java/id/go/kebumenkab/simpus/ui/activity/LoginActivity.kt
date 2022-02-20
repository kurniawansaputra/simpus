package id.go.kebumenkab.simpus.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AlertDialog
import id.go.kebumenkab.simpus.databinding.ActivityLoginBinding
import id.go.kebumenkab.simpus.databinding.LayoutDialogLoginFailedBinding
import id.go.kebumenkab.simpus.hawkstorage.HawkStorage
import id.go.kebumenkab.simpus.model.patient.dummy.UserResponse
import id.go.kebumenkab.simpus.network.ApiConfig
import id.go.kebumenkab.simpus.utils.Dialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity() {
//    private lateinit var idNumber: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var dialog: Dialog

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disableNightMode()
        dialog = Dialog()
        setListener()
    }

    private fun setListener() {
        binding.apply {
            editEmail.addTextChangedListener(textWatcherIdNumber)
            editPassword.addTextChangedListener(textWatcherPassword)
            buttonLogin.setOnClickListener {
                loginToServer(email, password)
//                if (idNumber == "3306092908970001" && password == "1234") {
//                    dialog.showProgressDialog(this@LoginActivity)
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                    toast("Masuk")
//                } else {
//                    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(layoutInflater)
//                    val builder: AlertDialog.Builder = AlertDialog.Builder(layoutInflater.context)
//                    builder.setView(binding.root)
//                    val dialog: AlertDialog = builder.create()
//                    binding.buttonOk.setOnClickListener {
//                        dialog.dismiss()
//                    }
//                    dialog.setCancelable(true)
//                    dialog.show()
//                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                }
            }
        }
    }

    private fun loginToServer(email: String, password: String) {
        dialog.showProgressDialog(this)
        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                dialog.hideDialog()
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null){
                        HawkStorage.instance(this@LoginActivity).setUser(user)
                        goToHome()
                    }
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

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dialog.hideDialog()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    private fun goToHome() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private val textWatcherIdNumber: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.apply {
//                if (editIdNumber.text!!.length < 16) {
//                    containerIdNumber.error = "NIK tidak valid"
//                } else if (editIdNumber.text!!.length == 16) {
//                    containerIdNumber.error = null
//                }
                if (editEmail.text!!.isEmpty()) {
                    containerEmail.error = "Data harus diisi"
                } else {
                    containerEmail.error = null
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
            email = editEmail.text.toString()
            password = editPassword.text.toString()
//            idNumber = editIdNumber.text.toString()
//            buttonLogin.isEnabled = idNumber.isNotEmpty() && idNumber.length == 16 && password.isNotEmpty()
            buttonLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    companion object{
        private const val TAG = "LoginActivity"
    }
}