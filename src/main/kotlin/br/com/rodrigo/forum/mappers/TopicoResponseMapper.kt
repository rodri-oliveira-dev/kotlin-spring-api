package br.com.rodrigo.forum.mappers

import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoResponseMapper : Mapper<Topico, TopicoResponse> {
    override fun map(t: Topico): TopicoResponse = TopicoResponse(t.id, t.titulo, t.mensagem, t.status, t.dataCriacao)
}