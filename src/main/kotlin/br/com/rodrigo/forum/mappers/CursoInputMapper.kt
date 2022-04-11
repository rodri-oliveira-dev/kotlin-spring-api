package br.com.rodrigo.forum.mappers

import br.com.rodrigo.forum.dto.CursoCadastroInput
import br.com.rodrigo.forum.dto.response.CursoResponse
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico

fun CursoCadastroInput.toCurso(): Curso {
    return Curso(null, nome, categoria)
}

fun Curso.toCursoResponse(): CursoResponse {
    return CursoResponse(id!!, nome, categoria)
}

fun Topico.toTopicoResponse(): TopicoResponse {
    return TopicoResponse(id, titulo, mensagem, status, dataCriacao)
}
