package com.slotbooking.adapters.outgoing.database.converter

import com.slotbooking.adapters.outgoing.database.entity.VaccinationSiteJpaEntity
import com.slotbooking.domain.model.VaccinationSiteDto
import org.springframework.stereotype.Component

@Component
class VaccinationSiteToDtoConverter {
    fun convert(entity: VaccinationSiteJpaEntity) = VaccinationSiteDto(
            id = entity.id,
            name = entity.name,
            localityName = entity.localityName,
            address = entity.address,
            countyName = entity.countyName
    )
}