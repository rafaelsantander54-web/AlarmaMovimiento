package cl.inacap.alarmamovimiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_menu.xml).
    private lateinit var tvusumenu: TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button

    // Objeto donde se recibirá el objeto "usu" enviado desde el Login.
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        tvusumenu = findViewById(R.id.tvusumenu)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)

        // Se recibe el objeto enviado desde la actividad anterior.
        @Suppress("DEPRECATION")
        usu = intent.getSerializableExtra("usu") as Usuario?

        // Se muestra el nombre del usuario logueado.
        tvusumenu.text = "Bienvenido(a) : " + usu?.nombre + " " + usu?.apellido

        // Llamada de las funciones.
        boton1()
        boton2()
        boton3()
        boton4()
        boton5()
        desactivarFlecha()

    } // Cierra el "onCreate".


    // Manipular Circuito
    fun boton1() {
        btn1.setOnClickListener {
            val intent = Intent(this@Menu, ManipularCircuito::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "boton1".


    // Ver Acciones Realizadas
    fun boton2() {
        btn2.setOnClickListener {
            val intent = Intent(this@Menu, VerAcciones::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "boton2".


    // Ver Estadísticas
    fun boton3() {
        btn3.setOnClickListener {
            val intent = Intent(this@Menu, Estadisticas::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "boton3".


    // Ver Perfil
    fun boton4() {
        btn4.setOnClickListener {
            val intent = Intent(this@Menu, VerPerfil::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "boton4".


    // Cerrar Sesión (con AlertDialog de confirmación)
    fun boton5() {
        btn5.setOnClickListener {
            val a = AlertDialog.Builder(this@Menu)

            a.setTitle("Pregunta")
            a.setMessage("¿Desea Cerrar La Sesión?")

            a.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            a.setPositiveButton("Aceptar") { _, _ ->
                val intent = Intent(this@Menu, Login::class.java)
                startActivity(intent)
                finish()
            }

            a.setIcon(R.drawable.pregunta)
            a.setCancelable(false)
            a.show()
        }
    } // Cierra la función "boton5".


    private fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {
            // No hace nada: para salir se debe usar "Cerrar Sesión".
        }
    } // Cierra la función "desactivarFlecha".

} // Cierra la clase "Menu".
