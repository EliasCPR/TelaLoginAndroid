package br.senai.sp.jandira.imcapp20

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var editUser : EditText
    lateinit var editPassword : EditText
    lateinit var btnLogin : Button
    lateinit var tvMensagemErro : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editUser = findViewById(R.id.ed_user)
        editPassword = findViewById(R.id.ed_password)
        btnLogin = findViewById(R.id.btnLogin)
        tvMensagemErro = findViewById(R.id.tv_mensagem_erro)

        btnLogin.setOnClickListener {
            Login()


        }
    }

    private fun Login() {
        //user@email.com
        //senha : 123
        val user = editUser.text.toString()
        val pass = editPassword.text.toString()

        if (user == "user@email.com" && pass == "123"){
            //armazenar os dados de usuarios sharedPreferences
            val preferences = getSharedPreferences("biometria", Context.MODE_PRIVATE)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            tvMensagemErro.text = "Usuário e/ou senha incorreta"
        }
    }
}