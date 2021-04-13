package com.scallop.marveldex.data.fakes

import com.scallop.randomweather.data.utils.getJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

/**
 * Handles Requests from mock web server
 */
internal class MarvelApiDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/v1/public/characters?limit=20&offset=0" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/characters.json"))
            }
            else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }
}