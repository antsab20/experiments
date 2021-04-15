package com.slotbooking.config.security.repository

import com.slotbooking.config.security.entity.Userinfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserinfoRepository : JpaRepository<Userinfo, Long> {
    fun findOneByUserName(userName: String): Userinfo?
}