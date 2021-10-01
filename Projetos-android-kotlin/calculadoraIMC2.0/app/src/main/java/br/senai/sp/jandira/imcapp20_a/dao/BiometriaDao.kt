package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.model.Biometria

class BiometriaDao(val context: Context, val biometria: Biometria?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    fun gravar() {
        val db = dbHelper.writableDatabase

        val dados = ContentValues()
        dados.put("peso", biometria!!.peso)
        dados.put("nivel_atividade", biometria!!.nivelAtiviade)
        dados.put("data_pesagem", biometria!!.dataPesagem.toString())
        dados.put("id_usario", biometria!!.usuario.id)

        db.insert("tb_biometria", null, dados)

        db.close()

    }

    fun setSharedPreferences(): Boolean {
        val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        val db = dbHelper.readableDatabase

        val campos = arrayOf(
            "peso",
            "nivel_atividade"
        )

        val idUsuario = dados.getInt("id", 0)


        val filtro = "id_usario = ${idUsuario}"

        val ordem = "id DESC"

        val cursor = db.query(
            "tb_biometria",
            campos,
            filtro,
            null,
            null,
            null,
            ordem
        )

        val linhas = cursor.count

        Log.i("XPTO", "${linhas} qtd lnhas")
        var validado = false

        if (linhas > 0) {
            validado = true
            cursor.moveToFirst()


            val pesoIndex = cursor.getColumnIndex("peso")
            val nivelAtividadeIndex = cursor.getColumnIndex("nivel_atividade")


            val editor = dados.edit()
            editor.putString("peso", cursor.getFloat(pesoIndex).toString())
            editor.putInt("nivelAtividade", cursor.getInt(nivelAtividadeIndex))

            editor.apply()
        }

        db.close()
        return validado
    }


}