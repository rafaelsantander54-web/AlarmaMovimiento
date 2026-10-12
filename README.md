# AlarmaMovimiento

Aplicación móvil Android (Kotlin) para el control y monitoreo de una **alarma con sensor de movimiento PIR basada en ESP32**.

- **Asignatura:** Aplicaciones Móviles para IoT (TI3042) – INACAP
- **Unidad 2:** Aplicaciones Móviles Android (preentrega)
- **Autor:** Rafael Santander
- **Docente:** Miguel Ángel López Jerez

## Pantallas

| # | Activity | Descripción |
|---|----------|-------------|
| 1 | `Login` | Inicio de sesión con Rut y contraseña. |
| 2 | `CrearCuenta` | Registro de usuario (Rut, nombre, apellido, correo, cargo, contraseña). |
| 3 | `Menu` | Menú principal con el nombre del usuario logueado y cierre de sesión. |
| 4 | `ManipularCircuito` | Botones **Activar** / **Desactivar** la alarma. |
| 5 | `VerAcciones` | Listado de acciones registradas (imagen de ejemplo). |
| 6 | `Estadisticas` | Activaciones, desactivaciones, movimientos detectados y tiempo activa. |
| 7 | `VerPerfil` | Datos completos del usuario logueado. |

## Estado actual

- Sin base de datos: los usuarios y contadores viven en memoria (`Datos.kt`) mientras la app está abierta.
- Usuario de prueba: **Rut** `11111111-1` – **Contraseña** `1234`.
- El objeto `Usuario` (`Serializable`) viaja entre pantallas con `intent.putExtra("usu", usu)`.

## Próximos pasos

- Conectar a **Firebase Realtime Database** (login, registro, acciones y estadísticas).
- Comunicación con el **ESP32** para activar/desactivar la alarma y recibir las detecciones del sensor PIR.

## Requisitos

- Android Studio (Gradle 9.4.1, AGP 9.2.1, compileSdk 36, minSdk 24).
- Emulador o dispositivo Android.
