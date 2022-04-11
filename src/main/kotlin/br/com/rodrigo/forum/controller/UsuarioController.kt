package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.domain.InternalServiceError
import br.com.rodrigo.forum.domain.NotFoundError
import br.com.rodrigo.forum.dto.UsuarioAtualizacaoInput
import br.com.rodrigo.forum.dto.UsuarioCadastroInput
import br.com.rodrigo.forum.dto.response.BaseResponse
import br.com.rodrigo.forum.dto.response.UsuarioResponse
import br.com.rodrigo.forum.services.UsuarioService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val service: UsuarioService) {

    @GetMapping
    fun listar(
        @PageableDefault(size = 5, sort = ["nome"], direction = Sort.Direction.ASC) paginacao: Pageable
    ): BaseResponse<Page<UsuarioResponse>> {
        return BaseResponse(service.listar(paginacao))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<BaseResponse<UsuarioResponse>> {
        val curso = service.buscarPorId(id)

        return if (curso == null) {
            ResponseEntity<BaseResponse<UsuarioResponse>>(
                BaseResponse<UsuarioResponse>(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        } else {
            ResponseEntity<BaseResponse<UsuarioResponse>>(
                BaseResponse(curso),
                HttpStatus.OK
            )
        }
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: UsuarioCadastroInput): ResponseEntity<BaseResponse<UsuarioResponse>> {
        val curso = service.cadastrar(dto)

        return if (curso != null) {
            ResponseEntity<BaseResponse<UsuarioResponse>>(BaseResponse(curso), HttpStatus.CREATED)
        } else {
            ResponseEntity<BaseResponse<UsuarioResponse>>(
                BaseResponse(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid dto: UsuarioAtualizacaoInput): ResponseEntity<BaseResponse<UsuarioResponse>> {
        val resultado = service.atualizar(dto)

        return if (resultado != null) {
            ResponseEntity<BaseResponse<UsuarioResponse>>(BaseResponse(resultado), HttpStatus.OK)
        } else {
            ResponseEntity<BaseResponse<UsuarioResponse>>(
                BaseResponse(null, listOf(InternalServiceError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): ResponseEntity<BaseResponse<UsuarioResponse>> {
        service.deletar(id)

        return ResponseEntity<BaseResponse<UsuarioResponse>>(BaseResponse(null), HttpStatus.NO_CONTENT)
    }
}