package com.slotbooking.config.security.repository

import com.slotbooking.config.security.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByRolename(rolename: String): Role
}