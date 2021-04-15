package com.slotbooking.config.security.entity

import javax.persistence.*

@Entity
@Table(name = "userinfo")
open class Userinfo(open var firstName: String = "",
                    open var lastName: String = "",
                    open var userName: String = "",
                    open var email: String = "",
                    open var passWord: String = "") {
        @Id
        @GeneratedValue
        open var id: Long = 0
        open var accountNonExpired: Boolean = true
        open var accountNonLocked: Boolean = true
        open var credentialsNonExpired: Boolean = true
        open var enabled: Boolean = true

        @ManyToMany(fetch=FetchType.EAGER)
        @JoinTable(name = "userinfo_role",
                joinColumns = arrayOf(JoinColumn(name = "userinfo_id", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
        open var roles: MutableList<Role> = mutableListOf()
        constructor(userinfo: Userinfo) : this(userinfo.firstName, userinfo.lastName, userinfo.userName, userinfo.email, userinfo.passWord) {
                id = userinfo.id
                firstName = userinfo.firstName
                lastName = userinfo.lastName
                userName = userinfo.userName
                email = userinfo.email
                passWord = userinfo.passWord
                accountNonExpired = userinfo.accountNonExpired
                accountNonLocked = userinfo.accountNonLocked
                credentialsNonExpired = userinfo.credentialsNonExpired
                enabled = userinfo.enabled
                roles = userinfo.roles
        }
}