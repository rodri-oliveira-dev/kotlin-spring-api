package br.com.rodrigo.forum.dto.response

import br.com.rodrigo.forum.domain.IDomainError

class BaseResponse<T>(val data: T?, val errors: List<IDomainError> = ArrayList()) {
    val success: Boolean
        get() = errors.isEmpty()
}