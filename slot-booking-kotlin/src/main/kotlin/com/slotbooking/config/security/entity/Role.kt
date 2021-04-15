package com.slotbooking.config.security.entity

import javax.persistence.*

@Entity
@Table(name = "role")
open class Role(
        @Column(name ="role_name")
        open var rolename: String = ""
) {
        @Id
        @GeneratedValue
        open var id: Long = 0
        @ManyToMany(mappedBy = "roles")
        open var userinfos: MutableList<Userinfo> = mutableListOf()

        constructor(role: Role) : this(role.rolename) {
                id = role.id
                userinfos = role.userinfos
        }
}