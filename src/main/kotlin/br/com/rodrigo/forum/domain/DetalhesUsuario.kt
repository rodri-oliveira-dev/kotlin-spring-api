package br.com.rodrigo.forum.domain

import br.com.rodrigo.forum.model.Usuario
import org.springframework.security.core.userdetails.UserDetails

class DetalhesUsuario(private val usuario: Usuario) : UserDetails {
    override fun getAuthorities() = usuario.role

    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}