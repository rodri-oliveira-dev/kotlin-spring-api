package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.domain.DetalhesUsuario
import br.com.rodrigo.forum.dto.UsuarioAtualizacaoInput
import br.com.rodrigo.forum.dto.UsuarioCadastroInput
import br.com.rodrigo.forum.dto.response.UsuarioResponse
import br.com.rodrigo.forum.mappers.toUsuario
import br.com.rodrigo.forum.mappers.toUsuarioResponse
import br.com.rodrigo.forum.model.Usuario
import br.com.rodrigo.forum.repository.UsuarioRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) : UserDetailsService {

    fun buscarPorId(id: Long): UsuarioResponse? {
        val usuario = repository.findById(id).orElse(null)
        return usuario?.toUsuarioResponse()
    }

    fun buscarUsuarioPorId(id: Long): Usuario? {
        return repository.findById(id).orElse(null)
    }

    fun listar(paginacao: Pageable): Page<UsuarioResponse> {
        return repository.findAll(paginacao).map { t -> t.toUsuarioResponse() }
    }

    fun cadastrar(dto: UsuarioCadastroInput): UsuarioResponse? {
        val novoUsuario = dto.toUsuario()
        repository.save(novoUsuario)
        return novoUsuario.toUsuarioResponse()
    }

    fun atualizar(dto: UsuarioAtualizacaoInput): UsuarioResponse? {
        var usuario = repository.findById(dto.id).orElse(null)

        return if (usuario != null) {
            usuario.nome = dto.nome
            usuario.email = dto.email

            if (dto.password != "") {
                usuario.password = dto.password
            }

            usuario.toUsuarioResponse()
        } else {
            null
        }
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException()

        return DetalhesUsuario(usuario)
    }
}