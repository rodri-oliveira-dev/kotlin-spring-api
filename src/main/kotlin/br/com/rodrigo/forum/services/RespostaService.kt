package br.com.rodrigo.forum.services

import br.com.rodrigo.forum.dto.RespostaCadastroInput
import br.com.rodrigo.forum.dto.response.RespostaResponse
import br.com.rodrigo.forum.mappers.toRespostaResponse
import br.com.rodrigo.forum.model.Resposta
import br.com.rodrigo.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val repository: RespostaRepository,
    private val topicoService: TopicoService,
    private val usuarioService: UsuarioService,
) {

    fun buscarPorId(id: Long): RespostaResponse? {
        val resposta = repository.findById(id).orElse(null)
        return resposta?.toRespostaResponse()
    }

    fun cadastrar(dto: RespostaCadastroInput): RespostaResponse? {
        val novoResposta = toResposta(dto)
        repository.save(novoResposta)
        return novoResposta.toRespostaResponse()
    }

    private fun toResposta(t: RespostaCadastroInput): Resposta {
        val topico = topicoService.buscarEntidadePorId(t.idTopico)
        val usuario = usuarioService.buscarUsuarioPorId(t.idAutor)

        return Resposta(
            mensagem = t.mensagem,
            autor = usuario!!,
            topico = topico!!,
            solucao = false
        )
    }
}