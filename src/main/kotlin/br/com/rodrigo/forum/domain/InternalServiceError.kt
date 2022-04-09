package br.com.rodrigo.forum.domain

class InternalServiceError(
    override val codigo: Int = 2,
    override val mensagem: String = "Erro ao executar a ação"
) : IDomainError