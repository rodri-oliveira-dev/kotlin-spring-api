package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.TopicoAtualizacaoInput
import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.mappers.TopicoInputMapper
import br.com.rodrigo.forum.mappers.TopicoResponseMapper
import br.com.rodrigo.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = ArrayList(),
    private val topicoResponseMapper: TopicoResponseMapper,
    private val topicoInputMapper: TopicoInputMapper
) {


    fun listar(): List<TopicoResponse> = topicos.toList().stream()
        .map { t -> topicoResponseMapper.map(t) }
        .toList()

    fun buscarPorId(id: String): TopicoResponse? {
        val topico = recuperarTopico(id)

        return if (topico != null) {
            topicoResponseMapper.map(topico)
        } else {
            null
        }
    }

    fun cadastrar(dto: TopicoCadastroInput): TopicoResponse? {
        val novoTopico = topicoInputMapper.map(dto)
        topicos.add(novoTopico)
        return buscarPorId(novoTopico.id)
    }

    fun atualizar(dto: TopicoAtualizacaoInput): TopicoResponse? {
        var topico = recuperarTopico(dto.id)

        return if (topico != null) {
            deletar(dto.id)
            cadastrar(TopicoCadastroInput(topico.id, dto.titulo, dto.mensagem, topico.curso.id!!, topico.autor.id!!))
            buscarPorId(dto.id)
        } else {
            null
        }
    }

    fun deletar(id: String): Boolean {
        var topico = recuperarTopico(id)

        return if (topico != null) {
            topicos = topicos.filter { t -> t.id != id }.toMutableList()
            true
        } else {
            false
        }
    }

    private fun recuperarTopico(id: String) =
        topicos.stream().filter { t -> t.id == id }.findFirst().orElse(null)

}