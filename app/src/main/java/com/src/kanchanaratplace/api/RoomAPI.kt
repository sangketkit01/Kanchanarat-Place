package com.src.kanchanaratplace.api

import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.data.Rooms
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface RoomAPI {
    @GET("get-room/{floor}")
    fun getRoom(
        @Path("floor") floor : Int
    ) : Call<List<Rooms>>

    @GET("get-one-room/{room_id}")
    fun getOneRoom(
        @Path("room_id") roomId: Int
    ) : Call<DefaultRooms>

    @Multipart
    @PUT("reserving-room/{room_id}")
    fun reservingRoom(
        @Path("room_id") roomId: Int,
        @Part("status_id") statusId: RequestBody,
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("line") line: RequestBody,
        @Part slipPath: MultipartBody.Part
    ): Call<Reservation>

    @FormUrlEncoded
    @POST("check-reservation")
    fun checkReservation(
        @Field("name") name  : String,
        @Field("phone") phone : String
    ):Call<Reservation>

    @GET("getReservation/{reservation_id}")
    fun getReservation(
        @Path("reservation_id") reservationId : Int
    ):Call<Reservation>

    @GET("allReserved")
    fun getAllReserved() : Call<List<Reservation>>

    @POST("approve-reservation/{reservation_id}")
    fun approveReservation(
        @Path("reservation_id") reservationId : Int
    ) : Call<Reservation>

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