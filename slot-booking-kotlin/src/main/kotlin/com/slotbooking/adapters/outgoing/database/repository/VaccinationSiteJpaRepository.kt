package com.slotbooking.adapters.outgoing.database.repository

import com.slotbooking.adapters.outgoing.database.entity.VaccinationSiteJpaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VaccinationSiteJpaRepository : JpaRepository<VaccinationSiteJpaEntity, UUID> {
    fun findAll(specification: Specification<VaccinationSiteJpaEntity>, pageable: Pageable): Page<VaccinationSiteJpaEntity>
}
