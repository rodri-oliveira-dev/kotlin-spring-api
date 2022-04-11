package br.com.rodrigo.forum.dto.response

import java.time.LocalDateTime

data class RespostaResponse(
    val id: Long?,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val autor: UsuarioResponse,
    val topico: TopicoResponse,
    val solucao: Boolean
)