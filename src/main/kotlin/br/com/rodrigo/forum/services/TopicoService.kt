package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.TopicoAtualizacaoInput
import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.dto.TopicoPorCategoriaDto
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.mappers.ExtensionMapper
import br.com.rodrigo.forum.mappers.toTopicoResponse
import br.com.rodrigo.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoInputMapper: ExtensionMapper
) {


    fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoResponse> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map { t -> t.toTopicoResponse() }
    }

    fun buscarPorId(id: Long): TopicoResponse? {
        val topico = recuperarTopico(id)

        return if (topico != null) {
            topico.toTopicoResponse()
        } else {
            null
        }
    }

    fun cadastrar(dto: TopicoCadastroInput): TopicoResponse? {
        val novoTopico = topicoInputMapper.map(dto)
        repository.save(novoTopico)
        return novoTopico.toTopicoResponse()
    }

    fun atualizar(dto: TopicoAtualizacaoInput): TopicoResponse? {
        var topico = recuperarTopico(dto.id)

        return if (topico != null) {
            topico.titulo = dto.titulo
            topico.mensagem = dto.mensagem

            topico.toTopicoResponse()
        } else {
            null
        }
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }

    private fun recuperarTopico(id: Long) =
        repository.findById(id).orElse(null)
}