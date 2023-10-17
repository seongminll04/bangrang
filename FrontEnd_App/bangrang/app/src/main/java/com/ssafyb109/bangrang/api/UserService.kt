package com.ssafyb109.bangrang.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    // 로그인
    @POST("api/user/Login")
    suspend fun userLogin(
        @Body request: LoginRequestDTO
    ): Response<LoginResponseDTO>

}

// 로그인 요청 DTO
data class LoginRequestDTO(
    val token: String
)

// 로그인 응답 DTO
data class LoginResponseDTO(
    val userIdx: Long,
    val userNickname: String,
    val accessToken: String,
    val refreshToken: String,
)

//