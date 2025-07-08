package app.facts.data.api

import app.facts.data.model.FactDto
import retrofit2.http.GET

interface FactApi {

    @GET("random.json?language=en")
    suspend fun getRandomFact(): FactDto

}