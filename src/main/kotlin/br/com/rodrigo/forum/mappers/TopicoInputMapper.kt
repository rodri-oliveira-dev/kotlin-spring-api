package br.com.rodrigo.forum.mappers

import br.com.rodrigo.forum.dto.TopicoInput
import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.services.CursoService
import br.com.rodrigo.forum.services.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoInputMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
) : Mapper<TopicoInput, Topico> {
    override fun map(t: TopicoInput): Topico {
        val curso = cursoService.buscarPorId(t.idCurso)
        val usuario = usuarioService.buscarPorId(t.idAutor)
        val idGerada = (0..Long.MAX_VALUE).random()

        return Topico(
            id = idGerada,
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = curso!!,
            autor = usuario!!

        )
    }
}