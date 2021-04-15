package com.slotbooking.adapters.outgoing.database.entity

import org.springframework.data.domain.Persistable
import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(givenId: UUID? = null) : Persistable<UUID> {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private val id: UUID = givenId ?: UUID.randomUUID()


    override fun getId(): UUID = id

    override fun isNew(): Boolean = true

    override fun equals(other: Any?): Boolean {
        return when {
            this === other           -> true
            other == null            -> false
            other !is BaseEntity -> false
            else                     -> getId() == other.getId()
        }
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}