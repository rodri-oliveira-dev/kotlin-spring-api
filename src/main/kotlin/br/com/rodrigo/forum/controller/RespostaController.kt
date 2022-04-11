package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.domain.NotFoundError
import br.com.rodrigo.forum.dto.RespostaCadastroInput
import br.com.rodrigo.forum.dto.response.BaseResponse
import br.com.rodrigo.forum.dto.response.RespostaResponse
import br.com.rodrigo.forum.services.RespostaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/respostas")
class RespostaController(private val service: RespostaService) {

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<BaseResponse<RespostaResponse>> {
        val resposta = service.buscarPorId(id)

        return if (resposta == null) {
            ResponseEntity<BaseResponse<RespostaResponse>>(
                BaseResponse<RespostaResponse>(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        } else {
            ResponseEntity<BaseResponse<RespostaResponse>>(
                BaseResponse(resposta),
                HttpStatus.OK
            )
        }
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: RespostaCadastroInput): ResponseEntity<BaseResponse<RespostaResponse>> {
        val resposta = service.cadastrar(dto)

        return if (resposta != null) {
            ResponseEntity<BaseResponse<RespostaResponse>>(BaseResponse(resposta), HttpStatus.CREATED)
        } else {
            ResponseEntity<BaseResponse<RespostaResponse>>(
                BaseResponse(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

}