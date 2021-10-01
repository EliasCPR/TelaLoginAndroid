package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.utils.calcularImc
import br.senai.sp.jandira.imcapp20_a.utils.calcularNcd
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64ParaBitmap
import br.senai.sp.jandira.imcapp20_a.utils.converterByteArrayParaBitmap
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_novo_usuario.*

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        supportActionBar!!.hide()


        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        val peso = dados.getString("peso", "")
        if (peso.equals("0")) {
            createDialog()
        }
        preencherDashBoard()

        tv_logout.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putBoolean("lembrar", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        cl_pessarAgora.setOnClickListener{
            val intent = Intent(this, novo_peso_nivelAtividade_Activity::class.java)
            startActivity(intent)
        }

    }

    private fun createDialog() {
        val biulder = AlertDialog.Builder(this)

        biulder.setTitle("Cadastro incompleto")
        biulder.setMessage(
            "Para terminar-mos seu cadastro precisamos registrar um peso" +
                    "e um nivel de atividade. " +
                    "Gostaria de terminar agora?"
        )
        biulder.setPositiveButton("Sim", { dialog, id ->
            val intent = Intent(this, novo_peso_nivelAtividade_Activity::class.java)
            startActivity(intent)
        })

        val alert = biulder.create()
        alert.show()

    }


    private fun preencherDashBoard() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")
        tv_weight.text = dados.getString("peso", "0")
        tv_age.text = dados.getString("idade", "").toString()

        val imagem = dados.getString("foto", "")
        val imagemBitmap = converterBase64ParaBitmap(imagem)
        iv_profile.setImageBitmap(imagemBitmap)

        val peso = dados.getString("peso", "0.0")!!.toDouble()
        val altura = dados.getString("altura", "")!!.toDouble()
        val nivelAtividade = dados.getInt("nivelAtividade", 0)
        val sexo = dados.getString("sexo", "")!!

        val imc = calcularImc(peso, altura)

        tv_imc.text = String.format("%.1f", imc)

        val ncd = calcularNcd(peso, 1, nivelAtividade, sexo)

        tv_ncd.text = String.format("%.1f", ncd)

        // *** Colocar foto do Github no ImageView
//        val url = "https://avatars.githubusercontent.com/u/77053674?v=4"
//        Glide.with(this).load(url).into(iv_profile)
    }
}