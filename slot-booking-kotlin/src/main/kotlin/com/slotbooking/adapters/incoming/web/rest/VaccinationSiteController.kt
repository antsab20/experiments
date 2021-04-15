package com.slotbooking.adapters.incoming.web.rest

import com.slotbooking.domain.model.VaccinationSiteDto
import com.slotbooking.domain.model.VaccinationSitePageRequestDto
import com.slotbooking.domain.usecases.VaccinationSitesProviderUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class VaccinationSiteController(
        private val vaccinationSitesProviderUsecase: VaccinationSitesProviderUsecase
) {

    @PostMapping("/list")
    fun list(@RequestBody request: VaccinationSitePageRequestDto): List<VaccinationSiteDto> {
        return vaccinationSitesProviderUsecase.getAvailableVaccinationSites(request)
    }
}