package com.slotbooking.domain.rules

import com.slotbooking.domain.model.VaccinationSiteDto
import com.slotbooking.domain.model.VaccinationSitePageRequestDto
import com.slotbooking.domain.ports.VaccinationSiteProviderPort
import com.slotbooking.domain.usecases.VaccinationSitesProviderUsecase
import org.springframework.stereotype.Service

@Service
class VaccinationSitesProviderService (
        private val vaccinationSiteProviderPort: VaccinationSiteProviderPort): VaccinationSitesProviderUsecase {

    override fun getAvailableVaccinationSites(request: VaccinationSitePageRequestDto): List<VaccinationSiteDto> {
        return vaccinationSiteProviderPort.listAvailableVaccinationSites(request)
    }
}