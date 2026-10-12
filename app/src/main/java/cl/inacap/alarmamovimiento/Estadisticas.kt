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

class Estadisticas : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_estadisticas.xml).
    private lateinit var tvusuest: TextView
    private lateinit var tvactivaciones: TextView
    private lateinit var tvdesactivaciones: TextView
    private lateinit var tvdetecciones: TextView
    private lateinit var tvtiempo: TextView
    private lateinit var btnvolest: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estadisticas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        tvusuest = findViewById(R.id.tvusuest)
        tvactivaciones = findViewById(R.id.tvactivaciones)
        tvdesactivaciones = findViewById(R.id.tvdesactivaciones)
        tvdetecciones = findViewById(R.id.tvdetecciones)
        tvtiempo = findViewById(R.id.tvtiempo)
        btnvolest = findViewById(R.id.btnvolest)

        // Se recibe el objeto enviado desde la actividad anterior.
        @Suppress("DEPRECATION")
        usu = intent.getSerializableExtra("usu") as Usuario?

        tvusuest.text = "Bienvenido(a) : " + usu?.nombre + " " + usu?.apellido

        // Llamada de las funciones.
        mostrarEstadisticas()
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".


    // Muestra las cantidades. Activaciones y desactivaciones son reales
    // (se cuentan en "Manipular Circuito"); detecciones y tiempo son de ejemplo
    // hasta que el ESP32 envíe los datos reales a Firebase.
    fun mostrarEstadisticas() {
        tvactivaciones.text = "" + Datos.activaciones
        tvdesactivaciones.text = "" + Datos.desactivaciones
        tvdetecciones.text = "17"
        tvtiempo.text = "46 min"
    } // Cierra la función "mostrarEstadisticas".


    // Regresa al Menú enviando de vuelta el objeto "usu".
    fun volver() {
        btnvolest.setOnClickListener {
            val intent = Intent(this@Estadisticas, Menu::class.java)
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

} // Cierra la clase "Estadisticas".
