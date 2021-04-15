package com.slotbooking.config.security.service

import com.slotbooking.config.security.repository.UserinfoRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class CustomUserDetailsService (private val userinfoRepository: UserinfoRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUsersDetails(userinfoRepository.findOneByUserName(username)!!)
    }

}