package com.src.kanchanaratplace.api

import com.src.kanchanaratplace.data.Bill
import com.src.kanchanaratplace.data.BillRequest
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface PaymentAPI {

    @POST("create-bill")
    fun createBill(
        @Body bill : BillRequest
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("create-meter-water")
    fun createWaterBill(
        @Field("room_id") roomId : Int,
        @Field("previous_water_used") previousWaterUsed : Int,
        @Field("current_water_used") currentWaterUsed : Int,
        @Field("water_used") waterUsed : Int,
        @Field("date") date : String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("create-meter-electricity")
    fun createElectricityBill(
        @Field("room_id") roomId : Int,
        @Field("previous_electricity_used") previousElectricityUsed : Int,
        @Field("current_electricity_used") currentElectricityUsed : Int,
        @Field("electricity_used") waterUsed : Int,
        @Field("date") date : String
    ) : Call<ResponseBody>

    @POST("release-bill/{month}/{year}")
    fun releaseBill(
        @Path("month") month : Int,
        @Path("year") year: Int
    ) : Call<ResponseBody>

    @GET("get-bill/{room_id}/{month}/{year}")
    fun getBill(
        @Path("room_id") roomId : Int,
        @Path("month") month : Int,
        @Path("year") year : Int
    ) : Call<Bill>

    @GET("get-latest-bill/{room_id}")
    fun getLatestBill(
        @Path("room_id") roomId : Int
    ) : Call<Bill>

    @GET("get-room-bills/{room_id}")
    fun getRoomBills(
        @Path("room_id") roomId : Int
    ) : Call<List<Bill>>


    @Multipart
    @POST("pay-bill/{bill_id}")
    fun payBill(
        @Path("bill_id") billId : Int,
        @Part slipPath: MultipartBody.Part
    ) : Call<ResponseBody>

    @POST("approve-bill/{bill_id}")
    fun approveBill(
        @Path("bill_id") billId: Int
    ) : Call<ResponseBody>


    companion object {
        fun create(): PaymentAPI {
            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            val paymentClient: PaymentAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PaymentAPI::class.java)

            return paymentClient
        }
    }
}