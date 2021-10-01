package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.util.Base64
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.utils.converterBitmapParaBase64
import br.senai.sp.jandira.imcapp20_a.utils.converterBitmapParaBitArray
import br.senai.sp.jandira.imcapp20_a.utils.converterByteArrayParaBitmap
import br.senai.sp.jandira.imcapp20_a.utils.obterDiferencaEntredateEmAnos
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class UsuarioDao(val context: Context, val usuario: Usuario?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("nome", usuario!!.nome)
        dados.put("profissao", usuario.profissao)
        dados.put("email", usuario.email)
        dados.put("senha", usuario.senha)
        dados.put("altura", usuario.altura)
        dados.put("data_nascimento", usuario.dataNascimento.toString())
        dados.put("sexo", usuario.sexo.toString())
        dados.put("foto", converterBitmapParaBitArray(usuario.foto))

        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)

        db.close()
    }

    //    Fazer validação
    //    Comando sql SELECT email, senha, nome, profissao, data_nascimento
    //    FROM tb_usuario
    //    WHERE email = ? and WHERE senha = ?

    fun autenticar(email: String, senha: String): Boolean {
        // *** Obter uma instância de Leitura do banco

        val db = dbHelper.readableDatabase

        // *** Dterminar quais são as colunas do tabela
        // *** que nós queremos no resulto
        // *** Vamos criar uma projeção
        val campos = arrayOf(
            "id",
            "email",
            "senha",
            "nome",
            "profissao",
            "data_nascimento",
            "foto",
            "altura",
            "sexo"
        )
        // *** Vamos definir o filtro da consulta
        // *** O que estamos fazendo éconstruir o filtro
        // *** "WHERE email = ? AND senha = ?"
        val filtro = "email = ? AND senha = ?"

        // *** Vamos criar agora o argumento s do filtros
        // ** Vamos dizer ao Kotlin quais serão os vlores
        // *** que deverão ser substituídos pelas "?" no filtro
        val argumentos = arrayOf(email, senha)

        // *** Executar a consulta e obter o resultado
        // *** em um "cursor"
        val cursor = db.query(
            "tb_usuario",
            campos,
            filtro,
            argumentos,
            null,
            null,
            null
        )

//

        // *** Guardar a quantidade de linhas obtidas na consulta
        val linhas = cursor.count

        var autenticado = false

        if (linhas > 0) {
            autenticado = true
            cursor.moveToFirst()

            val idIndex = cursor.getColumnIndex("id")
            val emailIndex = cursor.getColumnIndex("email")
            val senhaIndex = cursor.getColumnIndex("senha")
            val nomeIndex = cursor.getColumnIndex("nome")
            val profissaoIndex = cursor.getColumnIndex("profissao")
            val dataNascimentoIndex = cursor.getColumnIndex("data_nascimento")
            val dataNascimento = cursor.getString(dataNascimentoIndex)
            val fotoIndex = cursor.getColumnIndex("foto")
            val alturaIndex = cursor.getColumnIndex("altura")
            val sexoIndex = cursor.getColumnIndex("sexo")

            // *** Criação/ atulização do sharedePreferences que será
            // utilizado no restante da aplicação
            val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putInt("id", cursor.getString(idIndex).toInt())
            editor.putString("nome", cursor.getString(nomeIndex))
            editor.putString("profissao", cursor.getString(profissaoIndex))
            editor.putString("idade", obterDiferencaEntredateEmAnos(dataNascimento))
            editor.putString("email", cursor.getString(emailIndex))
            editor.putString("senha", cursor.getString(senhaIndex))
            editor.putString("altura", cursor.getFloat(alturaIndex).toString())
            editor.putString("sexo", cursor.getString(sexoIndex))

            //converter o byteArray do banco em Bitmap
            var bitmap = converterByteArrayParaBitmap(cursor.getBlob(fotoIndex))
            Log.i("xxxxx", converterBitmapParaBase64(bitmap))
            editor.putString("foto", converterBitmapParaBase64(bitmap))

            editor.apply()

        }

        db.close()
        return autenticado
    }

}