package com.scallop.marveldex.data.api

import com.scallop.marveldex.data.entitites.MarvelApiRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MarvelApiRequest

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): MarvelApiRequest
}