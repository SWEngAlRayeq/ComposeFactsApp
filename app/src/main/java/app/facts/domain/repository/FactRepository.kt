package app.facts.domain.repository

import app.facts.domain.model.Fact

interface FactRepository {
    suspend fun getRandomFact(): Fact
}