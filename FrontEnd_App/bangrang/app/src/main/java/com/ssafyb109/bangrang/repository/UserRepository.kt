package com.ssafyb109.bangrang.repository

import com.ssafyb109.bangrang.api.LoginRequestDTO
import com.ssafyb109.bangrang.api.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    suspend fun verifyGoogleToken(token: String): ResultType {
        return try {
            val requestDTO = LoginRequestDTO(token)
            val response = userService.userLogin(requestDTO)

            if (response.isSuccessful) {
                ResultType.SUCCESS
            } else {
                ResultType.FAILURE
            }
        } catch (e: Exception) {
            ResultType.ERROR
        }
    }
}

enum class ResultType {
    SUCCESS, FAILURE, ERROR, LOADING
}