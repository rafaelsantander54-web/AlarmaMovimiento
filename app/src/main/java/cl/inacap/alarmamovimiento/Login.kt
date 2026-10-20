package cl.inacap.alarmamovimiento

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_login.xml).
    private lateinit var txtrut: EditText
    private lateinit var txtcon: EditText
    private lateinit var btnini: Button
    private lateinit var btncrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos (si no se hace, no se pueden usar).
        txtrut = findViewById(R.id.txtrut)
        txtcon = findViewById(R.id.txtcon)
        btnini = findViewById(R.id.btnini)
        btncrear = findViewById(R.id.btncrear)

        // Llamada de las funciones.
        botonLogin()
        botonCrearCuenta()
        desactivarFlecha()

    } // Cierra el "onCreate".


    fun botonLogin() {
        btnini.setOnClickListener {

            if (txtrut.text.toString().trim().isEmpty()
                || txtcon.text.toString().trim().isEmpty()) {

                Toast.makeText(this@Login, "¡ Complete Los Campos Faltantes !", Toast.LENGTH_LONG).show()

            } else {

                // Se capturan los datos escritos por el usuario.
                // La contraseña se compara en forma de hash SHA-256 (ver clase Seguridad).
                val rut = txtrut.text.toString().trim()
                val con = txtcon.text.toString().trim()
                var estado = false

                // Conexión al servicio de Firebase, apuntando a la colección "Usuario".
                val dbref = FirebaseDatabase.getInstance().getReference("Usuario")

                // Lectura de los datos de la colección (una sola vez).
                dbref.addListenerForSingleValueEvent(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        // "snapshot" representa todos los registros de la colección "Usuario".
                        for (x in snapshot.children) {

                            // Si el rut y la contraseña coinciden con algún registro, se inicia la sesión.
                            if (rut.equals(x.child("rut").getValue().toString(), ignoreCase = true)
                                && Seguridad.hash(con) == x.child("contraseña").getValue().toString()) {

                                estado = true

                                val usu = Usuario(
                                    x.child("rut").getValue().toString(),
                                    x.child("nombre").getValue().toString(),
                                    x.child("apellido").getValue().toString(),
                                    x.child("correo").getValue().toString(),
                                    x.child("cargo").getValue().toString(),
                                    x.child("contraseña").getValue().toString()
                                )

                                val intent = Intent(this@Login, Menu::class.java)
                                intent.putExtra("usu", usu)
                                startActivity(intent)
                                txtrut.setText("")
                                txtcon.setText("")
                            }
                        }

                        // Si "estado" sigue en false, las credenciales no existen en la BD.
                        if (estado == false) {
                            txtrut.setText("")
                            txtcon.setText("")
                            ocultarTeclado()
                            Toast.makeText(this@Login, "¡ Error de Rut y/o Contraseña !", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@Login, "Error de conexión: " + error.message, Toast.LENGTH_LONG).show()
                    }
                })

            } // Cierra el if/else.
        }
    } // Cierra la función "botonLogin".


    fun botonCrearCuenta() {
        btncrear.setOnClickListener {
            val intent = Intent(this@Login, CrearCuenta::class.java)
            startActivity(intent)
        }
    } // Cierra la función "botonCrearCuenta".


    // Inhabilita la flecha de retroceso del sistema.
    private fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {
            // No hace nada: el usuario no puede retroceder con la flecha.
        }
    } // Cierra la función "desactivarFlecha".


    // Esconde el teclado en pantalla.
    private fun ocultarTeclado() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } // Cierra la función "ocultarTeclado".

} // Cierra la clase "Login".
