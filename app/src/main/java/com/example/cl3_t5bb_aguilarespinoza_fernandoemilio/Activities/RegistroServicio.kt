package com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Adaptador.AdaptadorServicio
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.MainActivity
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.R
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.pojo.ObjSERVICIO
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest.IServicio
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest.RestServicio
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro_servicio.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import java.lang.Integer.parseInt

class RegistroServicio : AppCompatActivity() {
    lateinit var ointento: Intent
    lateinit var oLista:List<ObjSERVICIO>
    lateinit var oAdaptadorSERVICIO: AdaptadorServicio
    lateinit var oObjSERVICIO: ObjSERVICIO


    companion object {
        var TIPOACCIONR:String = "N"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_servicio)

        var intent = intent

        val idservi = intent.getStringExtra("idservicio")
        val ids = findViewById<TextView>(R.id.idservici)
        ids.setText(idservi)

        val nom = intent.getStringExtra("nombre")
        val num = this.findViewById(R.id.edttxtCNomCliente) as EditText
        num.setText(nom)

        val nrordn = intent.getStringExtra("nroorden")
        val ordn = this.findViewById(R.id.edttxtNroOrden) as EditText
        ordn.setText(nrordn)

        val fec = intent.getStringExtra("fecha")
        val fe = this.findViewById(R.id.edttxtFecha) as EditText
        fe.setText(fec)

        val lin = intent.getStringExtra("linea")
        val li = this.findViewById(R.id.edtLinea) as EditText
        li.setText(lin)

        val st = intent.getStringExtra("estado")
        val s = this.findViewById(R.id.edttxtEstado) as EditText
        s.setText(st)

        val obs = intent.getStringExtra("observa")
        val ob = this.findViewById(R.id.edttxtObservaciones) as EditText
        ob.setText(obs)


        btnAprobar.setOnClickListener(){
            GrabarServicio()
        }
        btnCancelar.setOnClickListener(){
            ointento = Intent(applicationContext, MainActivity::class.java)
            startActivity(ointento)
        }
        btnEliminar.setOnClickListener(){
            EliminarServicio()
        }
    }
    fun GrabarServicio(){
        val onuevoNomCliente = this.findViewById(R.id.edttxtCNomCliente) as EditText
        val onuevoNroOrden = this.findViewById(R.id.edttxtNroOrden) as EditText
        val onuevoFecha = this.findViewById(R.id.edttxtFecha) as EditText
        val onuevoLinea = this.findViewById(R.id.edtLinea) as EditText
        val onuevoEstado = this.findViewById(R.id.edttxtEstado) as EditText
        val onuevoObservaciones = this.findViewById(R.id.edttxtObservaciones) as EditText



        if (TIPOACCIONR=="N"){
            Log.d("My app", ""+ TIPOACCIONR)
            var num=0;

            val oIServicio: IServicio
            oIServicio= RestServicio().getServicio()!!.create(IServicio::class.java)
            val call: Call<ObjSERVICIO> = oIServicio.getRegistraModifica(TIPOACCIONR, num,
                onuevoNomCliente.text.toString(), onuevoNroOrden.text.toString(), onuevoFecha.text.toString(),
                onuevoLinea.text.toString(), onuevoEstado.text.toString(), onuevoObservaciones.text.toString())
            call.enqueue(object : Callback<ObjSERVICIO> {
                override fun onResponse(call: Call<ObjSERVICIO>,
                                        response: retrofit2.Response<ObjSERVICIO>
                ) {
                    Log.d("body", response.body().toString())
                    try {
                        var ointento: Intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(ointento)
                    }catch (e:java.lang.Exception){
                        Log.d("AppWs", e.toString())
                    }
                }

                override fun onFailure(call: Call<ObjSERVICIO>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }
            })

        }else{
            Log.d("MyApp",""+ TIPOACCIONR);

            val iddelServicio = findViewById<TextView>(R.id.idservici)
            var servicioid = parseInt(iddelServicio.text.toString())

            val oIServicio: IServicio
            oIServicio= RestServicio().getServicio()!!.create(IServicio::class.java)
            val call: Call<ObjSERVICIO> = oIServicio.getRegistraModifica(TIPOACCIONR, servicioid,
                onuevoNomCliente.text.toString(), onuevoNroOrden.text.toString(), onuevoFecha.text.toString(),
                onuevoLinea.text.toString(), onuevoEstado.text.toString(), onuevoObservaciones.text.toString())
            call.enqueue(object : Callback<ObjSERVICIO> {
                override fun onResponse(call: Call<ObjSERVICIO>,
                                        response: retrofit2.Response<ObjSERVICIO>
                ) {
                    Log.d("body", response.body().toString())
                    try {
                        var ointento: Intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(ointento)
                    }catch (e:java.lang.Exception){
                        Log.d("AppWs", e.toString())
                    }
                }

                override fun onFailure(call: Call<ObjSERVICIO>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }
            })
        }



    }

    fun EliminarServicio(){

        val iddelServicio = findViewById<TextView>(R.id.idservici)
        var servicioid = parseInt(iddelServicio.text.toString())

        val oIServicio: IServicio
        oIServicio= RestServicio().getServicio()!!.create(IServicio::class.java)
        val call: Call<ObjSERVICIO> = oIServicio.getElimina(servicioid)
        call.enqueue(object : Callback<ObjSERVICIO> {
            override fun onResponse(call: Call<ObjSERVICIO>,
                                    response: retrofit2.Response<ObjSERVICIO>
            ) {
                Log.d("body", response.body().toString())
                try {
                    var ointento: Intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(ointento)
                }catch (e:java.lang.Exception){
                    Log.d("AppWs", e.toString())
                }
            }

            override fun onFailure(call: Call<ObjSERVICIO>, t: Throwable) {
                Log.d("ERROR", t.toString())
            }
        })
    }
}