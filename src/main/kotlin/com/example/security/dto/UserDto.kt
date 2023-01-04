package com.example.security.dto

import com.example.security.model.Role
import com.example.security.model.User


data class UserDto(
    val id:String?,
    val firstname:String,
    val lastname: String,
    val email: String,
    val password: String,
    val userRole: Role,
    val locked: Boolean?,
    val enabled: Boolean?
)
{
    companion object{
        @JvmStatic
        fun convert(from: User): UserDto{
            return UserDto(
                from.id,
                from.firstname,
                from.lastname,
                from.email,
                from.password,
                from.userRole,
                from.locked,
                from.enabled)
        }
    }
}