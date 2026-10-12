package cl.inacap.alarmamovimiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VerAcciones : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_ver_acciones.xml).
    private lateinit var tvusuacc: TextView
    private lateinit var btnvolacc: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_acciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        tvusuacc = findViewById(R.id.tvusuacc)
        btnvolacc = findViewById(R.id.btnvolacc)

        // Se recibe el objeto enviado desde la actividad anterior.
        @Suppress("DEPRECATION")
        usu = intent.getSerializableExtra("usu") as Usuario?

        tvusuacc.text = "Bienvenido(a) : " + usu?.nombre + " " + usu?.apellido

        // La imagen "listado_ejemplo" simula el listado de acciones.
        // Más adelante se obtendrán los registros reales desde Firebase.

        // Llamada de las funciones.
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".


    // Regresa al Menú enviando de vuelta el objeto "usu".
    fun volver() {
        btnvolacc.setOnClickListener {
            val intent = Intent(this@VerAcciones, Menu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
            finish()
        }
    } // Cierra la función "volver".


    private fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {
            // No hace nada.
        }
    } // Cierra la función "desactivarFlecha".

} // Cierra la clase "VerAcciones".
