package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso? {
        return repository.findById(id).orElse(null)
    }
}