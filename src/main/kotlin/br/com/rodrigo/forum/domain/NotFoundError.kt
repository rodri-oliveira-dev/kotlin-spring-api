package br.com.rodrigo.forum.domain

class NotFoundError(
    override val codigo: Int = 1,
    override val mensagem: String = "Recurso não encontrado"
) : IDomainError