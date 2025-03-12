package com.src.kanchanaratplace.api

import com.google.gson.JsonObject
import com.src.kanchanaratplace.data.Contract
import com.src.kanchanaratplace.data.DefaultRooms
import com.src.kanchanaratplace.data.Leaving
import com.src.kanchanaratplace.data.Member
import com.src.kanchanaratplace.data.Repair
import com.src.kanchanaratplace.data.RepairDetail
import com.src.kanchanaratplace.data.RepairDetailImage
import com.src.kanchanaratplace.data.RepairImages
import com.src.kanchanaratplace.data.Reservation
import com.src.kanchanaratplace.data.Rooms
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
import java.util.Date

interface RoomAPI {
    @GET("get-room/{floor}")
    fun getRoom(
        @Path("floor") floor : Int
    ) : Call<List<Rooms>>

    @GET("get-one-room/{room_id}")
    fun getOneRoom(
        @Path("room_id") roomId: Int
    ) : Call<DefaultRooms>

    @POST("make-room-unavailable/{room_id}")
    fun makeRoomUnavailable(
        @Path("room_id") roomId : Int
    ) : Call<ResponseBody>

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
    ): Call<ResponseBody>

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
    ) : Call<ResponseBody>

    @Multipart
    @POST("insert-contract")
    fun insertContract(
        @Part("room_id") roomId: RequestBody ,
        @Part("reservation_id") reservationId: RequestBody,
        @Part("contract_detail") contractDetail: RequestBody,
        @Part("contract_length_month") contractLength: RequestBody,
        @Part contractPath : MultipartBody.Part,
        @Part slipPath: MultipartBody.Part,
        @Part("expire_at") expireAt: RequestBody
    ) : Call<ResponseBody>

    @GET("get-new-contracts")
    fun getNewContracts() : Call<List<Contract>>

    @POST("approve-contract/{contract_id}")
    fun approveContract(
        @Path("contract_id") contractId : Int
    ) : Call<ResponseBody>

    @GET("get-room-contract/{room_id}")
    fun getRoomContract(
        @Path("room_id") roomId : Int
    ) : Call<Contract>

    @FormUrlEncoded
    @POST("insert-repair")
    fun insertRepair(
        @Field("room_id") roomId : Int,
        @Field("repair_title") repairTitle : String,
        @Field("repair_detail") repairDetail : String,
    ) : Call<JsonObject>

    @Multipart
    @POST("insert-repair-images/{repair_id}")
    fun insertRepairImages(
        @Path("repair_id") repairId : Int,
        @Part imagePath : MultipartBody.Part
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("insert-repair-details/{repair_id}")
    fun insertRepairDetail(
        @Path("repair_id") repairId: Int,
        @Field("repair_detail_detail") repairDetailDetail : String,
        @Field("repair_detail_cost_detail") repairDetailCost : String
    ) : Call<ResponseBody>

    @Multipart
    @POST("insert-repair-detail-images/{repair_id}")
    fun insertRepairDetailImage(
        @Path("repair_id") repairId: Int,
        @Part repairDetailImagePath : MultipartBody.Part
    ) : Call<ResponseBody>

    @GET("get-unapproved-repair")
    fun getUnapprovedRepair() : Call<List<Repair>>

    @GET("get-approved-repair")
    fun getApprovedRepair() : Call<List<Repair>>

    @GET("get-success-repair")
    fun getSuccessRepair() : Call<List<Repair>>

    @GET("get-repair-images/{repair_id}")
    fun getRepairImage(
        @Path("repair_id") repairId : Int
    ) : Call<List<RepairImages>>

    @PUT("approve-repair/{repair_id}")
    fun approveRepair(
        @Path("repair_id") repairId: Int
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @PUT("update-repair-cost-status/{repair_id}")
    fun updateRepairCostStatus(
        @Path("repair_id") repairId: Int,
        @Field("repair_cost") repairCost : Int,
        @Field("status_id") statusId : Int
    ) : Call<ResponseBody>

    @GET("get-repair-detail-image/{repair_id}")
    fun getRepairDetailImages(
        @Path("repair_id") repairId: Int
    ) : Call<List<RepairDetailImage>>

    @GET("get-repair-detail/{repair_id}")
    fun getRepairDetail(
        @Path("repair_id") repairId: Int
    ) : Call<RepairDetail>

    @FormUrlEncoded
    @POST("insert-leaving")
    fun insertLeaving(
        @Field("room_id") roomId : Int,
        @Field("report_date") reportDate : String,
        @Field("moveout_date") moveOutDate : String,
        @Field("reason") reason : String,
        @Field("other_detail") otherDetail : String
    ) : Call<ResponseBody>

    @GET("get-unapproved-leaving")
    fun getUnapprovedLeaving() : Call<List<Leaving>>

    @PUT("approve-leaving/{leaving_id}")
    fun approveLeaving(
        @Path("leaving_id") leavingId : Int
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("reject-leaving/{leaving_id}")
    fun rejectLeaving(
        @Path("leaving_id") leavingId: Int,
        @Field("reject_reason") rejectReason : String
    ) : Call<ResponseBody>

    @GET("get-member-by-room/{room_id}")
    fun getMemberByRoom(@Path("room_id") roomId: Int) : Call<Member>

    @Multipart
    @POST("update-profile/{member_id}")
    fun updateProfile(
        @Path("member_id") memberId: Int,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("birth_date") birthDate: RequestBody,
        @Part imagePath: MultipartBody.Part?
    ): Call<ResponseBody>


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