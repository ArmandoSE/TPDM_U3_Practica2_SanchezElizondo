package com.example.tpdm_u3_practica2_brianarmandosanchez


import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.AsyncTask
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class ConexionWeb(p:MainActivity): AsyncTask<URL,Void,String>() {
    var puntero = p
    var variablesEnvio = ArrayList<String>()
    var dialogo = ProgressDialog(puntero)

    override fun onPreExecute() {
        super.onPreExecute()
        dialogo.setTitle("ATENCION")
        dialogo.setMessage("Conectando con servidor")
        dialogo.show()
    }

    fun agregarVariablesEnvio(clave: String, valor: String) {
        var cad = clave + "&" + valor
        variablesEnvio.add(cad)
    }


    override fun doInBackground(vararg p0: URL?): String {
        var respuesta = ""
        var CadenaENvioPOST = ""
        var total = variablesEnvio.size - 1

        //creando cadena de clave valor para post
        (0..total).forEach {
            try {
                var data = variablesEnvio.get(it).split("&")
                CadenaENvioPOST += data[0] + "=" + URLEncoder.encode(data[1], "utf-8") + " "
            } catch (err: UnsupportedEncodingException) {
                respuesta = "No se pudo codificar URL"
            }//fin catch
        }//fin for

        CadenaENvioPOST = CadenaENvioPOST.trim()
        CadenaENvioPOST = CadenaENvioPOST.replace("", "&")


        var conexion: HttpURLConnection? = null

        try {
            //Amarro conexion con servidor web/lenguaje web
            //"as" forza el cambio de valor
            conexion = p0[0]?.openConnection() as HttpURLConnection

            conexion?.doOutput = true
            conexion?.setFixedLengthStreamingMode(CadenaENvioPOST.length)
            conexion?.requestMethod = "POST"
            conexion?.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")


            //envio variables ya codificadas
            var salida = BufferedOutputStream(conexion?.outputStream)

            salida.write(CadenaENvioPOST.toByteArray())
            salida.flush()
            salida.close()

            if (conexion?.responseCode == 200) {
                var flujoEntrada = InputStreamReader(conexion?.inputStream, "UTF-8")
                var entrada = BufferedReader(flujoEntrada)

                respuesta = """${entrada.readLine()}"""
                entrada.close()

            } else {
                respuesta = "Chales" + conexion?.responseCode
            }

        } catch (err: IOException) {
            respuesta = "ERROR IOEXCEPTION"
        } finally {
            if (conexion != null) {
                conexion?.disconnect()
            }
        }

        return respuesta
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        dialogo.dismiss()
        puntero.mostrarResultado(result!!)
    }
}

