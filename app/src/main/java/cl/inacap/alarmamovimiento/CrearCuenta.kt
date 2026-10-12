package cl.inacap.alarmamovimiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearCuenta : AppCompatActivity() {

    // Objetos que representan los controles del diseño (activity_crear_cuenta.xml).
    private lateinit var txtrut: EditText
    private lateinit var txtnom: EditText
    private lateinit var txtape: EditText
    private lateinit var txtcorreo: EditText
    private lateinit var spcargo: Spinner
    private lateinit var txtcon: EditText
    private lateinit var txtcon2: EditText
    private lateinit var btnreg: Button
    private lateinit var btnvolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de los objetos.
        txtrut = findViewById(R.id.txtrut)
        txtnom = findViewById(R.id.txtnom)
        txtape = findViewById(R.id.txtape)
        txtcorreo = findViewById(R.id.txtcorreo)
        spcargo = findViewById(R.id.spcargo)
        txtcon = findViewById(R.id.txtcon)
        txtcon2 = findViewById(R.id.txtcon2)
        btnreg = findViewById(R.id.btnreg)
        btnvolver = findViewById(R.id.btnvolver)

        // Llamada de las funciones.
        registrar()
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".


    fun registrar() {
        btnreg.setOnClickListener {
            val rut = txtrut.text.toString().trim()
            val nom = txtnom.text.toString().trim()
            val ape = txtape.text.toString().trim()
            val correo = txtcorreo.text.toString().trim()
            val cargo = spcargo.selectedItem.toString()
            val con = txtcon.text.toString().trim()
            val con2 = txtcon2.text.toString().trim()

            if (rut.isEmpty() || nom.isEmpty() || ape.isEmpty()
                || correo.isEmpty() || con.isEmpty() || con2.isEmpty()) {

                Toast.makeText(this@CrearCuenta, "¡ Complete Todos Los Campos !", Toast.LENGTH_LONG).show()

            } else if (spcargo.selectedItemPosition == 0) {

                // La posición 0 del Spinner es "Seleccione Cargo".
                Toast.makeText(this@CrearCuenta, "¡ Debe Seleccionar Un Cargo !", Toast.LENGTH_LONG).show()

            } else if (!correo.contains("@")) {

                Toast.makeText(this@CrearCuenta, "¡ El Correo No Es Válido !", Toast.LENGTH_LONG).show()

            } else if (con != con2) {

                Toast.makeText(this@CrearCuenta, "¡ Las Contraseñas No Coinciden !", Toast.LENGTH_LONG).show()
                txtcon.setText("")
                txtcon2.setText("")

            } else if (Datos.existeRut(rut)) {

                Toast.makeText(this@CrearCuenta, "¡ El Rut Ya Está Registrado !", Toast.LENGTH_LONG).show()

            } else {

                // Se crea el objeto Usuario y se guarda en la lista en memoria.
                // A futuro: aquí se guardará en la colección de Firebase Realtime Database.
                val usu = Usuario(rut, nom, ape, correo, cargo, con)
                Datos.usuarios.add(usu)

                Toast.makeText(this@CrearCuenta, "¡ Usuario Registrado Correctamente !", Toast.LENGTH_LONG).show()

                // Se regresa al Login para que inicie sesión con la cuenta nueva.
                val intent = Intent(this@CrearCuenta, Login::class.java)
                startActivity(intent)
                finish()

            } // Cierra el if/else.
        }
    } // Cierra la función "registrar".


    fun volver() {
        btnvolver.setOnClickListener {
            val intent = Intent(this@CrearCuenta, Login::class.java)
            startActivity(intent)
            finish()
        }
    } // Cierra la función "volver".


    private fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {
            // No hace nada: el usuario debe usar el botón "Volver".
        }
    } // Cierra la función "desactivarFlecha".

} // Cierra la clase "CrearCuenta".
