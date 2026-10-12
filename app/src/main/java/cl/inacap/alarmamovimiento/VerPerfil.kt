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

class VerPerfil : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_ver_perfil.xml).
    private lateinit var tvusuperfil: TextView
    private lateinit var tvrutper: TextView
    private lateinit var tvnomper: TextView
    private lateinit var tvapeper: TextView
    private lateinit var tvcorper: TextView
    private lateinit var tvcarper: TextView
    private lateinit var tvconper: TextView
    private lateinit var btnvolper: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        tvusuperfil = findViewById(R.id.tvusuperfil)
        tvrutper = findViewById(R.id.tvrutper)
        tvnomper = findViewById(R.id.tvnomper)
        tvapeper = findViewById(R.id.tvapeper)
        tvcorper = findViewById(R.id.tvcorper)
        tvcarper = findViewById(R.id.tvcarper)
        tvconper = findViewById(R.id.tvconper)
        btnvolper = findViewById(R.id.btnvolper)

        // Se recibe el objeto enviado desde la actividad anterior.
        @Suppress("DEPRECATION")
        usu = intent.getSerializableExtra("usu") as Usuario?

        tvusuperfil.text = "Bienvenido(a) : " + usu?.nombre + " " + usu?.apellido

        // Se presentan todos los datos del usuario que inició la sesión.
        tvrutper.text = usu?.rut
        tvnomper.text = usu?.nombre
        tvapeper.text = usu?.apellido
        tvcorper.text = usu?.correo
        tvcarper.text = usu?.cargo
        tvconper.text = usu?.contraseña

        // Llamada de las funciones.
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".


    // Regresa al Menú enviando de vuelta el objeto "usu".
    fun volver() {
        btnvolper.setOnClickListener {
            val intent = Intent(this@VerPerfil, Menu::class.java)
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

} // Cierra la clase "VerPerfil".
