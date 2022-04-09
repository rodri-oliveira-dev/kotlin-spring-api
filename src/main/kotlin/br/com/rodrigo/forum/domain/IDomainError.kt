package br.com.rodrigo.forum.domain

interface IDomainError {
    val codigo: Int
    val mensagem: String
}