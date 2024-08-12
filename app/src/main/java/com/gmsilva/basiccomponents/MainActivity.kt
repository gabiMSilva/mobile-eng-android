package com.gmsilva.basiccomponents

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.transition.Visibility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Adicionando referências
        val textField = findViewById<EditText>(R.id.campo_texto)
        val searchBtn = findViewById<Button>(R.id.btn_buscar)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        val resultContainer = findViewById<ScrollView>(R.id.pesquisa_container)
        val resultImage = findViewById<ImageView>(R.id.imagem_resultado)
        val resultTitle = findViewById<TextView>(R.id.titulo_resultado)
        val resultDescription = findViewById<TextView>(R.id.descricao_resultado)


        searchBtn.setOnClickListener {
            CoroutineScope(Main).launch {
                val text = textField.text.toString()
                Toast.makeText(
                    this@MainActivity,
                    "Valor do campo de texto: ${text}",
                    Toast.LENGTH_SHORT
                ).show()

                textField.isEnabled = false
                progressBar.isVisible = true
                searchBtn.isVisible = false

                resultContainer.isVisible = false
                resultTitle.text = ""
                resultDescription.text = ""
                resultImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.mipmap.ic_launcher
                    )
                )

                val inseto = buscarResult(text)

                resultContainer.isVisible = true
                resultTitle.text = inseto.title
                resultDescription.text = inseto.description

                inseto.image?.let {
                    resultImage.setImageDrawable(inseto.image)
                }


                textField.isEnabled = true
                progressBar.isVisible = false
                searchBtn.isVisible = true

            }
        }


    }

    suspend fun buscarResult(text: String): InsetoModel {
        delay(5000)

        if (text == "lagarto") {
            return InsetoModel(
                "Inseto Lagarto",
                "Os lagartos, como os demais répteis, são animais que apresentam corpo coberto por escamas, 4 membros e cauda. Eles fazem parte da ordem dos Escamados juntamente com as serpentes. São ovíparos e alguns são onívoros, como o Teiú. Apresentam grande variação de tamanho desde poucos centímetros até mais de 1 metro da cabeça à ponta da cauda. Existem mais de 3 mil espécies de lagartos, distribuídos em 45 famílias. Dentre os lagartos mais conhecidos, podemos destacar as iguanas, camaleões e lagartixas.",
                ContextCompat.getDrawable(this, R.mipmap.lagarto)
            )
        }

        return InsetoModel()
    }
}