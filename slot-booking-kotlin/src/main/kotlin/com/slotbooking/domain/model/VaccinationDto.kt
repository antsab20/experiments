package com.slotbooking.domain.model

import java.util.*

data class VaccinationSiteDto(
        val id: UUID? = null,
        val name: String,
        val localityName: String,
        val address: String,
        val countyName: String
)