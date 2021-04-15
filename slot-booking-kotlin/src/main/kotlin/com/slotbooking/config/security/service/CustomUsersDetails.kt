package com.slotbooking.config.security.service

import com.slotbooking.config.security.entity.Userinfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

open class CustomUsersDetails : Userinfo, UserDetails {

    constructor(userinfo: Userinfo) : super(userinfo)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles
                .stream()
                .map { role ->
                    SimpleGrantedAuthority(role.toString())
                }
                .collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return super.passWord!!
    }

    override fun getUsername(): String {
        return super.userName!!
    }

    override fun isEnabled(): Boolean {
        return super.enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return super.credentialsNonExpired
    }

    override fun isAccountNonExpired(): Boolean {
        return super.accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return super.accountNonLocked
    }
}