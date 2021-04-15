package com.slotbooking.adapters.outgoing.database.entity

import com.slotbooking.adapters.outgoing.database.entity.BaseEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "vaccination_site")
class VaccinationSiteJpaEntity(
        id: UUID? = null,
        val name: String,
        val localityName: String,
        val address: String,
        val countyName: String
) : BaseEntity(id) {
    constructor() : this(null, "null", "null", "null", "null") {

    }
}