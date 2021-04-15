package com.slotbooking.adapters.outgoing.database.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "time_slot")
internal class TimeSlotJpaEntity(
        id: UUID? = null,
        date: Date
) : BaseEntity(id)