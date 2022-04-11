package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.domain.InternalServiceError
import br.com.rodrigo.forum.domain.NotFoundError
import br.com.rodrigo.forum.dto.CursoAtualizacaoInput
import br.com.rodrigo.forum.dto.CursoCadastroInput
import br.com.rodrigo.forum.dto.response.BaseResponse
import br.com.rodrigo.forum.dto.response.CursoResponse
import br.com.rodrigo.forum.services.CursoService
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
@RequestMapping("/cursos")
class CursoController(private val service: CursoService) {

    @GetMapping
    fun listar(
        @PageableDefault(size = 5, sort = ["nome"], direction = Sort.Direction.ASC) paginacao: Pageable
    ): BaseResponse<Page<CursoResponse>> {
        return BaseResponse(service.listar(paginacao))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<BaseResponse<CursoResponse>> {
        val curso = service.buscarPorId(id)

        return if (curso == null) {
            ResponseEntity<BaseResponse<CursoResponse>>(
                BaseResponse<CursoResponse>(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        } else {
            ResponseEntity<BaseResponse<CursoResponse>>(
                BaseResponse(curso),
                HttpStatus.OK
            )
        }
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: CursoCadastroInput): ResponseEntity<BaseResponse<CursoResponse>> {
        val curso = service.cadastrar(dto)

        return if (curso != null) {
            ResponseEntity<BaseResponse<CursoResponse>>(BaseResponse(curso), HttpStatus.CREATED)
        } else {
            ResponseEntity<BaseResponse<CursoResponse>>(
                BaseResponse(null, listOf(NotFoundError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid dto: CursoAtualizacaoInput): ResponseEntity<BaseResponse<CursoResponse>> {
        val resultado = service.atualizar(dto)

        return if (resultado != null) {
            ResponseEntity<BaseResponse<CursoResponse>>(BaseResponse(resultado), HttpStatus.OK)
        } else {
            ResponseEntity<BaseResponse<CursoResponse>>(
                BaseResponse(null, listOf(InternalServiceError())),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): ResponseEntity<BaseResponse<CursoResponse>> {
        service.deletar(id)

        return ResponseEntity<BaseResponse<CursoResponse>>(BaseResponse(null), HttpStatus.NO_CONTENT)
    }
}