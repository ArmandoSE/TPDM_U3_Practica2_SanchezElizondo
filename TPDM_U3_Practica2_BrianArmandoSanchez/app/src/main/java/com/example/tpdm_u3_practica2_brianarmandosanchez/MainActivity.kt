package com.example.tpdm_u3_practica2_brianarmandosanchez

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {
    var descripcion: EditText?=null
    var fecha      : EditText?=null
    var monto      : EditText?=null
    var pago       : EditText?=null
    var boton      : Button?=null
    var mostrar    :Button?=null
    var etiqueta   : TextView?=null
    var jsonRespuesta = ArrayList<org.json.JSONObject>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        descripcion    =findViewById(R.id.descripcion)
        monto          =findViewById(R.id.monto)
        fecha          =findViewById(R.id.fecha)
        pago           =findViewById(R.id.pago)
        boton          =findViewById(R.id.insertar)
        mostrar        =findViewById(R.id.mostrar)
        etiqueta       =findViewById(R.id.sinr)

        boton?.setOnClickListener {
            var conexionWeb = ConexionWeb(this)

            conexionWeb.agregarVariablesEnvio("descripcion",descripcion?.text.toString())
            conexionWeb.agregarVariablesEnvio("monto",monto?.text.toString())
            conexionWeb.agregarVariablesEnvio("fecha",fecha?.text.toString())
            conexionWeb.agregarVariablesEnvio("pago",pago?.text.toString())

            conexionWeb.execute(URL("https://desolate-springs-25140.herokuapp.com/insertar.php"))
        }
        mostrar?.setOnClickListener {
            var conexionWeb = ConexionWeb(this)

            conexionWeb.execute(URL("https://desolate-springs-25140.herokuapp.com/consultagenerica.php"))
            etiqueta?.setText(""+conexionWeb)
        }
    }
    fun mostrarResultado(result: String){
        /*var alerta = AlertDialog.Builder(this)
        alerta.setTitle("RESPUESTA DEL SERVIDOR")
            .setMessage(result)
            .setPositiveButton("Ok"){dialog, wich -> }
            .show()*/
        val jsonarray = org.json.JSONArray(result)
        var total = jsonarray.length()-1

        (0..total).forEach {
            jsonRespuesta.add(jsonarray.getJSONObject(it))
        }
        //etiqueta?.setText(result)
    }
}
