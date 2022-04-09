package br.com.rodrigo.forum.dto.extensions

import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.model.Usuario

fun TopicoCadastroInput.toTopico(idTopico: String, curso: Curso?, usuario: Usuario?): Topico {
    return Topico(
        id = idTopico,
        titulo = titulo,
        mensagem = mensagem,
        curso = curso!!,
        autor = usuario!!

    )
}