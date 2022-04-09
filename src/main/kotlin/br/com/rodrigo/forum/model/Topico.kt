package br.com.rodrigo.forum.model

import java.time.LocalDateTime

data class Topico(
    val id: String,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    var respostas: List<Resposta> = ArrayList()
)