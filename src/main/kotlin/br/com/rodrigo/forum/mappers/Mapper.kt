package br.com.rodrigo.forum.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}
