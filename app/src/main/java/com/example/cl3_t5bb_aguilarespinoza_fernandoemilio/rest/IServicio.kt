package com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest

import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.pojo.ObjSERVICIO
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.tools.Constantes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IServicio {
    @GET(Constantes.GETLISTA_SERVICIO)
    @Headers( "Content-Type: application/json")
    fun getLista(
                 @Query(value = "NombreCliente") NombreCliente:String
    ): Call<List<ObjSERVICIO>>

    @GET(Constantes.GETLISTARKEY_SERVICIO)
    @Headers( "Content-Type: application/json")
    fun getListarKey(
        @Query(value = "pCodigoServicio") pCodigoServicio:Int
    ): Call<ObjSERVICIO>

    @GET(Constantes.GETRegistraModifica_SERVICIO)
    @Headers( "Content-Type: application/json")
    fun getRegistraModifica(
        @Query(value = "Accion") Accion:String,
        @Query(value = "CodigoServicio") CodigoServicio:Int,
        @Query(value = "NombreCliente") NombreCliente:String,
        @Query(value = "NumeroOrdenServicio") NumeroOrdenServicio:String,
        @Query(value = "FechaProgramada") FechaProgramada:String,
        @Query(value = "Linea") Linea:String,
        @Query(value = "Estado") Estado:String,
        @Query(value = "Observaciones") Observaciones:String
    ): Call<ObjSERVICIO>

    @GET(Constantes.GETElimina_SERVICIO)
    @Headers( "Content-Type: application/json")
    fun getElimina(
        @Query(value = "pCodigoServicio") pCodigoServicio:Int
    ): Call<ObjSERVICIO>
}