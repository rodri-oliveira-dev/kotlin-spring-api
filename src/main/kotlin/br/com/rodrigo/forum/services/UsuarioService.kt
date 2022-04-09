package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private var usuarios: MutableList<Usuario>) {

    init {
        val usuario = Usuario(1, "Rodrigo", "rodrigo@mail.com")

        usuarios = arrayListOf(usuario)
    }

    fun listar(): List<Usuario> {
        return usuarios.toList()
    }

    fun buscarPorId(id: Long): Usuario? {
        return usuarios.stream().filter { t -> t.id == id }.findFirst().orElse(null)
    }
}