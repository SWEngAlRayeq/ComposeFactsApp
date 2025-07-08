package app.facts.domain.usecase

import app.facts.domain.model.Fact
import app.facts.domain.repository.FactRepository
import javax.inject.Inject

class GetRandomFactUseCase @Inject constructor(
    private val repository: FactRepository
) {
    suspend operator fun invoke(): Fact = repository.getRandomFact()
}