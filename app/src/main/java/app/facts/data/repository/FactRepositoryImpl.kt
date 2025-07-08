package app.facts.data.repository

import app.facts.data.api.FactApi
import app.facts.domain.mapper.tofact
import app.facts.domain.model.Fact
import app.facts.domain.repository.FactRepository

class FactRepositoryImpl(
    private val api: FactApi
) : FactRepository {
    override suspend fun getRandomFact(): Fact {
        return api.getRandomFact().tofact()
    }

}