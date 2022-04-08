package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.model.Topico
import br.com.rodrigo.forum.services.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<Topico> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Topico> {
        val topico = service.buscarPorId(id)

        return if (topico == null) {
            ResponseEntity<Topico>(null, HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity<Topico>(topico, HttpStatus.OK)
        }
    }
}