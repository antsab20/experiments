package com.slotbooking.domain.model

import org.springframework.data.domain.PageRequest
import kotlin.math.floor
import kotlin.math.min

private const val MAX_PAGE_SIZE = 50

data class VaccinationSitePageRequestDto(
        val startIndex: Int,
        val endIndex: Int,
        val filterCriteria: VaccinationSiteFilterCriteria
) {
    val pageSize = min(endIndex - startIndex, MAX_PAGE_SIZE)
    val page = floor(startIndex.toDouble() / pageSize.toDouble()).toInt()
}

data class VaccinationSiteFilterCriteria(
    val localityName: String?,
    val address: String?,
    val countyName: String?
)

fun VaccinationSitePageRequestDto.toPageable() = PageRequest.of(
        page,
        pageSize
)