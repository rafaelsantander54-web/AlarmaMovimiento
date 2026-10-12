package cl.inacap.alarmamovimiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ManipularCircuito : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_manipular_circuito.xml).
    private lateinit var tvusuman: TextView
    private lateinit var tvestado: TextView
    private lateinit var btnactivar: Button
    private lateinit var btndesactivar: Button
    private lateinit var btnvolman: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manipular_circuito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        tvusuman = findViewById(R.id.tvusuman)
        tvestado = findViewById(R.id.tvestado)
        btnactivar = findViewById(R.id.btnactivar)
        btndesactivar = findViewById(R.id.btndesactivar)
        btnvolman = findViewById(R.id.btnvolman)

        // Se recibe el objeto enviado desde la actividad anterior.
        @Suppress("DEPRECATION")
        usu = intent.getSerializableExtra("usu") as Usuario?

        tvusuman.text = "Bienvenido(a) : " + usu?.nombre + " " + usu?.apellido

        // Llamada de las funciones.
        mostrarEstado()
        activar()
        desactivar()
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".


    // Muestra en pantalla si la alarma está activada o desactivada.
    fun mostrarEstado() {
        if (Datos.alarmaActiva) {
            tvestado.text = "Estado de la alarma: ACTIVADA"
            tvestado.setTextColor(ContextCompat.getColor(this, R.color.verde_activar))
        } else {
            tvestado.text = "Estado de la alarma: DESACTIVADA"
            tvestado.setTextColor(ContextCompat.getColor(this, R.color.rojo_alarma))
        }
    } // Cierra la función "mostrarEstado".


    // Botón ACTIVAR: por ahora solo cambia el estado en memoria.
    // A futuro: enviará la orden al ESP32 (vía Firebase) para activar el sensor PIR.
    fun activar() {
        btnactivar.setOnClickListener {
            if (Datos.alarmaActiva) {
                Toast.makeText(this@ManipularCircuito, "¡ La Alarma Ya Está Activada !", Toast.LENGTH_SHORT).show()
            } else {
                Datos.alarmaActiva = true
                Datos.activaciones = Datos.activaciones + 1
                mostrarEstado()
                Toast.makeText(this@ManipularCircuito, "Alarma ACTIVADA", Toast.LENGTH_SHORT).show()
            }
        }
    } // Cierra la función "activar".


    // Botón DESACTIVAR.
    fun desactivar() {
        btndesactivar.setOnClickListener {
            if (!Datos.alarmaActiva) {
                Toast.makeText(this@ManipularCircuito, "¡ La Alarma Ya Está Desactivada !", Toast.LENGTH_SHORT).show()
            } else {
                Datos.alarmaActiva = false
                Datos.desactivaciones = Datos.desactivaciones + 1
                mostrarEstado()
                Toast.makeText(this@ManipularCircuito, "Alarma DESACTIVADA", Toast.LENGTH_SHORT).show()
            }
        }
    } // Cierra la función "desactivar".


    // Regresa al Menú enviando de vuelta el objeto "usu".
    fun volver() {
        btnvolman.setOnClickListener {
            val intent = Intent(this@ManipularCircuito, Menu::class.java)
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

} // Cierra la clase "ManipularCircuito".
