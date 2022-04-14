package br.com.rodrigo.forum.dto.response

import br.com.rodrigo.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoResponse(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDateTime?
)