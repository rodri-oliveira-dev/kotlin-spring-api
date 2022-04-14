package br.com.rodrigo.forum.config

import br.com.rodrigo.forum.services.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(private val usuarioService: UsuarioService) {
    private val expiration: Long = 60000 * 5
    private val secret: String = "mtN8Vj,7MGg&rj6L/D3=L#GDbA:D&28()^Gwdk*QH:dP:9(Y_BCx=9Z"

    fun generateToken(userName: String, authorities: MutableCollection<out GrantedAuthority>, host: String): String? {
        val currentDateTime = System.currentTimeMillis()
        val expirationDateTime = currentDateTime + expiration

        return Jwts.builder()
            .setIssuer(host)
            .setSubject(userName)
            .claim("role", authorities)
            .setIssuedAt(Date(currentDateTime))
            .setExpiration(Date(expirationDateTime))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}