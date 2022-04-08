package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
        val topico = Topico(
            1,
            "Duvida",
            "Variaveis",
            curso = Curso(1, "Kotlin", "Programação"),
            autor = Usuario(1, "Rodrigo", "rodrigo@gmail.com")
        )

        val topico2 = Topico(
            2,
            "Duvida",
            "Sql",
            curso = Curso(2, "SQL", "Banco de Dados"),
            autor = Usuario(1, "Rodrigo", "rodrigo@gmail.com")
        )

        val topico3 = Topico(
            3,
            "Duvida",
            "Spring",
            curso = Curso(3, "Kotlin Spring", "Programação"),
            autor = Usuario(1, "Rodrigo", "rodrigo@gmail.com")
        )

        topicos = listOf(topico, topico2, topico3)
    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico? {
        return topicos.stream().filter { t -> t.id == id }.findFirst().orElse(null)
    }
}