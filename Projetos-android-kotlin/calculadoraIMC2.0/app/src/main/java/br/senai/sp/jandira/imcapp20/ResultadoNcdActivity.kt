package br.senai.sp.jandira.imcapp20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ResultadoNcdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_ncd)

        val txtNcd: TextView = findViewById(R.id.text_view_ncd)
        val txtDica: TextView =findViewById(R.id.text_view_dica)


        val peso = intent.getDoubleExtra("peso", 0.0)
        val nivelAtividade = intent.getIntExtra("nivelAtividade", 0)
        val idade = intent.getIntExtra("faixaIdade", 0)
        val genero = intent.getCharExtra("genero", 'f')

        val ncd = calcularNdc(genero, peso, idade, nivelAtividade)

        txtNcd.text = String.format("%.1f", ncd)

        txtDica.text = getDicaDoDiaNcd()

//        Toast.makeText(this,"$ncd",Toast.LENGTH_LONG).show()
    }
}