package com.src.kanchanaratplace.api

import com.src.kanchanaratplace.data.Member
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MemberAPI {
    @FormUrlEncoded
    @POST("/login_verify")
    fun loginVerify(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<Member>

    companion object{
        fun create() : MemberAPI{
            val memberClient : MemberAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MemberAPI::class.java)

            return memberClient
        }
    }
}