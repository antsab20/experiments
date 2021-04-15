package com.slotbooking.domain.ports

import com.slotbooking.domain.model.VaccinationSiteDto
import com.slotbooking.domain.model.VaccinationSitePageRequestDto

interface VaccinationSiteProviderPort {
    fun listAvailableVaccinationSites(request: VaccinationSitePageRequestDto): List<VaccinationSiteDto>
}