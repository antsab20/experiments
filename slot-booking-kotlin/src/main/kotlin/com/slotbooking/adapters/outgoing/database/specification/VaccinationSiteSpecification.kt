package com.slotbooking.adapters.outgoing.database.specification

import com.slotbooking.adapters.outgoing.database.entity.VaccinationSiteJpaEntity
import com.slotbooking.domain.model.VaccinationSiteFilterCriteria
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class VaccinationSiteSpecification(
        private val vaccinationSiteFilterCriteria: VaccinationSiteFilterCriteria

): Specification<VaccinationSiteJpaEntity> {

    override fun toPredicate(
            root: Root<VaccinationSiteJpaEntity>,
            cq: CriteriaQuery<*>,
            cb: CriteriaBuilder): Predicate? {
        val filters = listOfNotNull(
            vaccinationSiteFilterCriteria.localityName?.let {
                cb.equal(root.get<UUID>("localityName"), it) },
            vaccinationSiteFilterCriteria.address?.let {
                cb.equal(root.get<UUID>("address"), it) },
            vaccinationSiteFilterCriteria.countyName?.let {
                cb.equal(root.get<UUID>("countyName"), it) }
        )
        return cb.and(*filters.toTypedArray())
    }
}