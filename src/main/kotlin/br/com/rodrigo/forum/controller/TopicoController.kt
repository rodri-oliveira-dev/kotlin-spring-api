package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.dto.TopicoInput
import br.com.rodrigo.forum.dto.TopicoResponse
import br.com.rodrigo.forum.services.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoResponse> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TopicoResponse> {
        val topico = service.buscarPorId(id)

        return if (topico == null) {
            ResponseEntity<TopicoResponse>(null, HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity<TopicoResponse>(topico, HttpStatus.OK)
        }
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: TopicoInput): ResponseEntity<Void> {
        service.cadastrar(dto)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}