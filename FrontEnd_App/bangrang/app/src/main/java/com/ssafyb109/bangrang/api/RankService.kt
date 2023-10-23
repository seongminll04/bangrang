package com.ssafyb109.bangrang.api

import retrofit2.http.GET

interface RankService {

    // 지역별 랭킹
    @GET("api/rank/{location}")
    suspend fun rankByLocation(
    ): Void

    // 유저의 전체 랭킹
    @GET("api/rank/myRank")
    suspend fun userRank(
    ): RegionDTO

}


data class RegionDTO(
    val Seoul: Long,
    val Busan: Long,
    val Incheon: Long,
    val Gwangju: Long,
    val Daejeon: Long,
    val Daegu: Long,
    val Ulsan: Long,
    val Sejong: Long,
    val Jeju: Long,
    val Dokdo: Long,

    val Gangwon: Long,
    val Gyeonggi: Long,
    val Gyeongnam: Long,
    val Gyeongbuk: Long,
    val Jeollanam: Long,
    val Jeollabuk: Long,
    val Chungnam: Long,
    val Chungbuk: Long,
)

