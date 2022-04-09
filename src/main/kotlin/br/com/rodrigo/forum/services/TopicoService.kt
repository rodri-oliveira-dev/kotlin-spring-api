package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.TopicoInput
import br.com.rodrigo.forum.dto.TopicoResponse
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

    fun buscarPorId(id: Long): TopicoResponse? = topicos.stream().filter { t -> t.id == id }.findFirst()
        .map { t -> topicoResponseMapper.map(t) }.orElse(null)

    fun cadastrar(dto: TopicoInput) {
        topicos.add(topicoInputMapper.map(dto))
    }
}