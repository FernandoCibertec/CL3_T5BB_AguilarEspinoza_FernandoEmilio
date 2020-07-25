package com.example.cl3_t5bb_aguilarespinoza_fernandoemilio

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Activities.RegistroServicio
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Adaptador.AdaptadorServicio
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.pojo.ObjSERVICIO
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest.IServicio
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest.RestServicio
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Activities.RegistroServicio.Companion.TIPOACCIONR

class MainActivity : AppCompatActivity() {

    lateinit var ointento: Intent
    lateinit var oLista:List<ObjSERVICIO>
    lateinit var oAdaptadorSERVICIO: AdaptadorServicio
    lateinit var oObjSERVICIO: ObjSERVICIO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBuscar.setOnClickListener(){
            BuscarServicio()
        }
        btnNuevo.setOnClickListener(){
            cargarActivityRegistro()
        }
    }

    public fun BuscarServicio(){
        oLista = ArrayList<ObjSERVICIO>()
        val oIServicio: IServicio
        oIServicio= RestServicio().getServicio()!!.create(IServicio::class.java)
        val call: Call<List<ObjSERVICIO>> = oIServicio.getLista(edtNomCliente.text.toString())
        call.enqueue(object : Callback<List<ObjSERVICIO>> {
            override fun onResponse(call: Call<List<ObjSERVICIO>>?,
                                    response: retrofit2.Response<List<ObjSERVICIO>>
            ) {
                Log.d("body", response.body().toString())
                try {
                    oLista = response.body()!!
                    MostrarListado()
                }catch (e:java.lang.Exception){
                    Log.d("AppWs", e.toString())
                }
            }

            override fun onFailure(call: Call<List<ObjSERVICIO>>, t: Throwable) {
                Log.d("ERROR", t.toString())
            }
        })
    }

    fun MostrarListado(){
        oAdaptadorSERVICIO = AdaptadorServicio(oLista){
            oObjSERVICIO = it
            TIPOACCIONR = "A"
            AbrirActivityConDatos()
        }
        recyclerviewServicio.setAdapter(oAdaptadorSERVICIO)
        val ll= LinearLayoutManager(this)
        ll.orientation = LinearLayoutManager.VERTICAL
        recyclerviewServicio.setLayoutManager(ll)
        recyclerviewServicio.setHasFixedSize(true)
        recyclerviewServicio.setAdapter(oAdaptadorSERVICIO)
    }
    public fun cargarActivityRegistro(){
        ointento = Intent(applicationContext, RegistroServicio::class.java)
        TIPOACCIONR="N"
        startActivity(ointento)
        Log.d("My app", ""+TIPOACCIONR)
    }
    fun AbrirActivityConDatos(){
        ointento = Intent(applicationContext, RegistroServicio::class.java)
        val idServicio = oObjSERVICIO.codigoServicio.toString()
        ointento.putExtra("idservicio", idServicio)
        val Nombre = oObjSERVICIO.nombreCliente.toString()
        ointento.putExtra("nombre", Nombre)
        val NroOrden = oObjSERVICIO.numeroOrdenServicio.toString()
        ointento.putExtra("nroorden", NroOrden)
        val Fecha = oObjSERVICIO.fechaProgramada.toString()
        ointento.putExtra("fecha", Fecha)
        val Linea = oObjSERVICIO.linea.toString()
        ointento.putExtra("linea", Linea)
        val Estado = oObjSERVICIO.estado.toString()
        ointento.putExtra("estado", Estado)
        val Observa = oObjSERVICIO.observaciones.toString()
        ointento.putExtra("observa", Observa)
        TIPOACCIONR="A"
        startActivity(ointento)
        Log.d("My app", ""+TIPOACCIONR)
    }
}



