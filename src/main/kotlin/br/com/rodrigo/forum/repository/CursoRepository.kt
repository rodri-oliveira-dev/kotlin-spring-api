package br.com.rodrigo.forum.repository

import br.com.rodrigo.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long>