package com.slotbooking.adapters.outgoing.database.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "available_slot")
internal class AvailableSlotJpaEntity(
        id: UUID? = null,
        val availableSlots: Int,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "vaccination_site_id")
        val vaccinationSite: VaccinationSiteJpaEntity? = null,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "time_slot_id")
        val timeSlot: TimeSlotJpaEntity? = null,
        @Version
        val optlockVersion: Int
) : BaseEntity(id)