package br.com.rodrigo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoCadastroInput(
    val id: String?,

    @field:NotEmpty(message = "Campo obrigatório")
    @field:Size(min = 1, max = 255)
    val titulo: String,

    @field:NotEmpty
    @field:Size(min = 1, max = 5000)
    val mensagem: String,

    @field:NotNull
    val idCurso: Long,

    @field:NotNull
    val idAutor: Long
)
