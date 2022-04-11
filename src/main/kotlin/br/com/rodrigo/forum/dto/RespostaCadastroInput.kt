package br.com.rodrigo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RespostaCadastroInput(
    @field:NotEmpty(message = "Campo obrigatório")
    @field:Size(min = 1, max = 300)
    val mensagem: String,

    @field:NotNull
    val idTopico: Long,

    @field:NotNull
    val idAutor: Long
)
