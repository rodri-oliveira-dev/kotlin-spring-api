package br.com.rodrigo.forum.controller

import br.com.rodrigo.forum.domain.InternalServiceError
import br.com.rodrigo.forum.domain.NotFoundError
import br.com.rodrigo.forum.dto.TopicoAtualizacaoInput
import br.com.rodrigo.forum.dto.TopicoCadastroInput
import br.com.rodrigo.forum.dto.TopicoPorCategoriaDto
import br.com.rodrigo.forum.dto.response.BaseResponse
import br.com.rodrigo.forum.dto.response.TopicoResponse
import br.com.rodrigo.forum.services.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
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
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): BaseResponse<Page<TopicoResponse>> {
        return BaseResponse(service.listar(nomeCurso, paginacao))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<BaseResponse<TopicoResponse>> {
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
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
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
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
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
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deletar(@PathVariable id: Long): ResponseEntity<BaseResponse<TopicoResponse>> {
        service.deletar(id)

        return ResponseEntity<BaseResponse<TopicoResponse>>(BaseResponse(null), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return service.relatorio()
    }

}