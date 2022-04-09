package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.domain.InternalServiceError
import br.com.rodrigo.forum.domain.NotFoundError
import br.com.rodrigo.forum.dto.TopicoAtualizacaoInput
import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.dto.response.BaseResponse
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.services.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): BaseResponse<List<TopicoResponse>> {
        return BaseResponse(service.listar())
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: String): ResponseEntity<BaseResponse<TopicoResponse>> {
        val topico = service.buscarPorId(id)

        return if (topico == null) {
            ResponseEntity<BaseResponse<TopicoResponse>>(
                BaseResponse(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        } else {
            ResponseEntity<BaseResponse<TopicoResponse>>(BaseResponse(topico), HttpStatus.OK)
        }
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid dto: TopicoCadastroInput): ResponseEntity<BaseResponse<TopicoResponse>> {
        val topico = service.cadastrar(dto)

        return if (topico != null) {
            ResponseEntity<BaseResponse<TopicoResponse>>(BaseResponse(topico), HttpStatus.CREATED)
        } else {
            ResponseEntity<BaseResponse<TopicoResponse>>(
                BaseResponse(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid dto: TopicoAtualizacaoInput): ResponseEntity<BaseResponse<TopicoResponse>> {
        val resultado = service.atualizar(dto)

        return if (resultado != null) {
            ResponseEntity<BaseResponse<TopicoResponse>>(BaseResponse(resultado), HttpStatus.OK)
        } else {
            ResponseEntity<BaseResponse<TopicoResponse>>(
                BaseResponse(null, listOf(InternalServiceError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: String): ResponseEntity<BaseResponse<TopicoResponse>> {
        val resultado = service.deletar(id)

        return if (resultado) {
            ResponseEntity<BaseResponse<TopicoResponse>>(BaseResponse(null), HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity<BaseResponse<TopicoResponse>>(
                BaseResponse(null, listOf(InternalServiceError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }
}