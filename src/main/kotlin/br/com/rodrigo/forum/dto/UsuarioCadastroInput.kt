package br.com.rodrigo.forum.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UsuarioCadastroInput(
    @field:NotEmpty(message = "Campo obrigatório")
    @field:Size(min = 1, max = 50)
    val nome: String,

    @field:NotEmpty(message = "Campo obrigatório")
    @field:Email
    @field:Size(min = 1, max = 50)
    val email: String,

    @field:NotEmpty(message = "Campo obrigatório")
    @field:Size(min = 8, max = 50)
    val password: String
)
