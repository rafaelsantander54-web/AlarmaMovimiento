package cl.inacap.alarmamovimiento

import java.security.MessageDigest

// Funciones de seguridad de la aplicación (ISO 27400: protección de credenciales).
// Las contraseñas NUNCA se guardan ni se comparan en texto plano: se transforman
// con el algoritmo SHA-256 en un "hash" (cadena de 64 caracteres) que no se puede
// revertir. Al iniciar sesión se calcula el hash de lo que escribe el usuario y se
// compara con el hash guardado en Firebase.
object Seguridad {

    fun hash(texto: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(texto.toByteArray(Charsets.UTF_8))

        // Se convierte cada byte a su representación hexadecimal (2 caracteres).
        var resultado = ""
        for (b in bytes) {
            resultado += String.format("%02x", b)
        }
        return resultado
    } // Cierra la función "hash".

} // Cierra el objeto "Seguridad".
