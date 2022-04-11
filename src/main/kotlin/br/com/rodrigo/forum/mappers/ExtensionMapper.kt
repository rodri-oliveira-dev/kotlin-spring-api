package br.com.rodrigo.forum.mappers

import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.services.CursoService
import br.com.rodrigo.forum.services.UsuarioService
import org.springframework.stereotype.Component

@Component
class ExtensionMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) : Mapper<TopicoCadastroInput, Topico> {
    override fun map(t: TopicoCadastroInput): Topico {
        val curso = cursoService.buscarCursoPorId(t.idCurso)
        val usuario = usuarioService.buscarUsuarioPorId(t.idAutor)

        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = curso!!,
            autor = usuario!!
        )
    }
}