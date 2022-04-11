package br.com.rodrigo.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CursoCadastroInput(
    @field:NotEmpty
    @field:Size(min = 1, max = 50)
    val nome: String,

    @field:NotEmpty
    @field:Size(min = 1, max = 50)
    val categoria: String
)
