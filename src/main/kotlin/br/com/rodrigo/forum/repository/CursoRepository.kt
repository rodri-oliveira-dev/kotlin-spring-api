package br.com.rodrigo.forum.repository

import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
    fun findByNome(nome: String, paginacao: Pageable): Page<Topico>
}