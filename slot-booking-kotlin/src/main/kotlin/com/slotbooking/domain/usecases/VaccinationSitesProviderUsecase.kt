package com.slotbooking.domain.usecases

import com.slotbooking.domain.model.VaccinationSiteDto
import com.slotbooking.domain.model.VaccinationSitePageRequestDto

interface VaccinationSitesProviderUsecase {
    fun getAvailableVaccinationSites(request: VaccinationSitePageRequestDto): List<VaccinationSiteDto>
}