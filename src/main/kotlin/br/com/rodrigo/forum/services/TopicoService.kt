package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.TopicoDto
import br.com.rodrigo.forum.dto.extensions.toTopico
import br.com.rodrigo.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: MutableList<Topico> = ArrayList()) {


    fun listar(): List<Topico> {
        return topicos.toList()
    }

    fun buscarPorId(id: Long): Topico? {
        return topicos.stream().filter { t -> t.id == id }.findFirst().orElse(null)
    }

    fun cadastrar(dto: TopicoDto) {

        topicos.add(dto.toTopico((topicos.count() + 1).toLong()))
    }
}