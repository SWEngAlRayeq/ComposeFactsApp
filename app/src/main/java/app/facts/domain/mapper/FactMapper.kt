package app.facts.domain.mapper

import app.facts.data.model.FactDto
import app.facts.domain.model.Fact

fun FactDto.tofact(): Fact {
    return Fact(
        id = id,
        fact = text,
        source = source
    )
}