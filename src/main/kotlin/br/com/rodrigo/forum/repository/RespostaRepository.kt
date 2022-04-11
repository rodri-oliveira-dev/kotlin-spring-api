package br.com.rodrigo.forum.repository

import br.com.rodrigo.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long>