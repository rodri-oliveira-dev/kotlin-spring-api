package br.com.rodrigo.forum.mappers

import br.com.rodrigo.forum.dto.CursoCadastroInput
import br.com.rodrigo.forum.dto.UsuarioCadastroInput
import br.com.rodrigo.forum.dto.response.CursoResponse
import br.com.rodrigo.forum.dto.response.RespostaResponse
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.dto.response.UsuarioResponse
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Resposta
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.model.Usuario

fun CursoCadastroInput.toCurso(): Curso {
    return Curso(null, nome, categoria)
}

fun Curso.toCursoResponse(): CursoResponse {
    return CursoResponse(id!!, nome, categoria)
}

fun Topico.toTopicoResponse(): TopicoResponse {
    return TopicoResponse(id, titulo, mensagem, status, dataCriacao, dataAlteracao)
}

fun Usuario.toUsuarioResponse(): UsuarioResponse {
    return UsuarioResponse(id, nome, email)
}

fun UsuarioCadastroInput.toUsuario(): Usuario {
    return Usuario(null, nome, email, password)
}

fun Resposta.toRespostaResponse(): RespostaResponse {
    return RespostaResponse(id, mensagem, dataCriacao, autor.toUsuarioResponse(), topico.toTopicoResponse(), solucao)
}
