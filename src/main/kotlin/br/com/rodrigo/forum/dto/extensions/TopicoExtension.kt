package br.com.rodrigo.forum.dto.extensions

import br.com.rodrigo.forum.dto.TopicoDto
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.model.Usuario

fun TopicoDto.toTopico(idTopico: Long): Topico {
    return Topico(
        id = idTopico,
        titulo = titulo,
        mensagem = mensagem,
        curso = Curso(idCurso, "Curso$idCurso", "Programação"),
        autor = Usuario(idAutor, "Rodrigo", "rodrigo@mail.com")

    )
}