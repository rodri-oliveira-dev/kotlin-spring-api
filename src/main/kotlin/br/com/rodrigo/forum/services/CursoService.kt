package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: MutableList<Curso>) {

    init {
        val curso = Curso(1, "Kotlin", "Programação")
        val curso2 = Curso(2, "SQL", "Programação")

        cursos = arrayListOf(curso, curso2)
    }

    fun listar(): List<Curso> {
        return cursos.toList()
    }

    fun buscarPorId(id: Long): Curso? {
        return cursos.stream().filter { t -> t.id == id }.findFirst().orElse(null)
    }
}