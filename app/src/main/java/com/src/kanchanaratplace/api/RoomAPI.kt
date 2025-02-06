package com.src.kanchanaratplace.api

import com.src.kanchanaratplace.data.Rooms
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomAPI {
    @GET("get-room/{floor}")
    fun getRoom(
        @Path("floor") floor : Int
    ) : Call<List<Rooms>>


    companion object{
        fun create() : RoomAPI{
            val roomClient : RoomAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RoomAPI::class.java)

            return roomClient
        }
    }
}