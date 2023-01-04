package com.example.security.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Document
data class User @JvmOverloads constructor(
    @Id
    val id:String?=null,
    val firstname:String,
    val lastname: String,
    val email: String,
    @JvmField val password: String,
    val userRole: Role,
    val locked: Boolean?=false,
    val enabled: Boolean?=true
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authority =  SimpleGrantedAuthority(userRole.name)
        return Collections.singletonList(authority)
    }


    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled!!
    }
}

