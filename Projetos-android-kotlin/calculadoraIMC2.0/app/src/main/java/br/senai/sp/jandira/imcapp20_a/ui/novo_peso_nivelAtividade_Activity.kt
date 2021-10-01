package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.BiometriaDao
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_peso_nivel_atividade_.*
import java.time.LocalDate

class novo_peso_nivelAtividade_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_peso_nivel_atividade_)

        val btnSalvar: Button = findViewById(R.id.bt_salvar)

        btnSalvar.setOnClickListener {
            if (validar()) {

                val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
                val usuario = Usuario(
                    dados.getInt("id", 0)!!,
                    dados.getString("email", "")!!,
                    dados.getString("senha", "")!!,
                    dados.getString("nome", "")!!,
                    dados.getString("profissao", "")!!,
                    dados.getString("altura", "")!!.toDouble(),
                    dados.getString("data_nascimento", "")!!,
                    dados.getString("sexo", "")!!
                )

                val biometria = Biometria(
                    0,
                    et_peso.text.toString().toDouble(),
                    spin_nivel_atividade.selectedItemPosition,
                    LocalDate.now(),
                    usuario
                )
                val dao = BiometriaDao(this, biometria)
                dao.gravar()

                val validado = dao.setSharedPreferences()

                if (validado) {

                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }

    private fun validar(): Boolean {

        val etPeso: EditText = findViewById(R.id.et_peso)

        var validado = true

        if (etPeso.text.isEmpty()) {
            validado = false
            etPeso.error = "Você não me disse o seu peso!"
        }

        return validado
    }


}