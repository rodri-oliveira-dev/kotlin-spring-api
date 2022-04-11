package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.CursoAtualizacaoInput
import br.com.rodrigo.forum.dto.CursoCadastroInput
import br.com.rodrigo.forum.dto.response.CursoResponse
import br.com.rodrigo.forum.mappers.toCurso
import br.com.rodrigo.forum.mappers.toCursoResponse
import br.com.rodrigo.forum.model.Curso
import br.com.rodrigo.forum.repository.CursoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): CursoResponse? {
        val curso = repository.findById(id).orElse(null)
        return curso?.toCursoResponse()
    }

    fun buscarCursoPorId(id: Long): Curso? {
        return repository.findById(id).orElse(null)
    }

    fun listar(paginacao: Pageable): Page<CursoResponse> {
        return repository.findAll(paginacao).map { t -> t.toCursoResponse() }
    }

    fun cadastrar(dto: CursoCadastroInput): CursoResponse? {
        val novoCurso = dto.toCurso()
        repository.save(novoCurso)
        return novoCurso.toCursoResponse()
    }

    fun atualizar(dto: CursoAtualizacaoInput): CursoResponse? {
        var curso = repository.findById(dto.id).orElse(null)

        return if (curso != null) {
            curso.nome = dto.nome
            curso.categoria = dto.categoria

            curso.toCursoResponse()
        } else {
            null
        }
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}