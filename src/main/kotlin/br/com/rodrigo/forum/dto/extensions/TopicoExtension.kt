package br.com.rodrigo.forum.dto.extensions

import br.com.rodrigo.forum.dto.TopicoInput
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.model.Usuario

fun TopicoInput.toTopico(idTopico: Long, curso: Curso?, usuario: Usuario?): Topico {
    return Topico(
        id = idTopico,
        titulo = titulo,
        mensagem = mensagem,
        curso = curso!!,
        autor = usuario!!

    )
}