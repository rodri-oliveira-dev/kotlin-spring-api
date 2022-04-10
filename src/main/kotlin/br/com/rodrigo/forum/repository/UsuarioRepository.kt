package br.com.rodrigo.forum.repository

import br.com.rodrigo.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long>