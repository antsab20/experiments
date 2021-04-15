package com.slotbooking.adapters.outgoing.database.service

import com.slotbooking.adapters.outgoing.database.converter.VaccinationSiteToDtoConverter
import com.slotbooking.adapters.outgoing.database.entity.VaccinationSiteJpaEntity
import com.slotbooking.adapters.outgoing.database.repository.VaccinationSiteJpaRepository
import com.slotbooking.adapters.outgoing.database.specification.VaccinationSiteSpecification
import com.slotbooking.domain.model.VaccinationSiteDto
import com.slotbooking.domain.model.VaccinationSitePageRequestDto
import com.slotbooking.domain.model.toPageable
import com.slotbooking.domain.ports.VaccinationSiteProviderPort
import org.springframework.stereotype.Service

@Service
class DatabaseVaccinationSiteProviderService  (
        private val vaccinationSiteJpaRepository: VaccinationSiteJpaRepository,
        private val vaccinationSiteToDtoConverter: VaccinationSiteToDtoConverter
) : VaccinationSiteProviderPort {

    private fun VaccinationSiteJpaEntity.toDto() = vaccinationSiteToDtoConverter.convert(this)

    override fun listAvailableVaccinationSites(request: VaccinationSitePageRequestDto): List<VaccinationSiteDto> {
        return vaccinationSiteJpaRepository
                .findAll(
                        VaccinationSiteSpecification(request.filterCriteria),
                        request.toPageable()
                )
                .content.map { it.toDto() }
    }
}