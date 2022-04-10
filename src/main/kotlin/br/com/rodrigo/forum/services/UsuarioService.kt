package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.model.Usuario
import br.com.rodrigo.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario? {
        return repository.findById(id).orElse(null)
    }
}