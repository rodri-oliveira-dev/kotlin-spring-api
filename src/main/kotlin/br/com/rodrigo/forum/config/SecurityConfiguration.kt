package br.com.rodrigo.forum.config

import br.com.rodrigo.forum.security.JwtAuthenticationFilter
import br.com.rodrigo.forum.security.JwtLoginFilter
import br.com.rodrigo.forum.services.UsuarioService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val usuarioService: UsuarioService,
    private val jwtUtil: JwtUtil
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        val rotas: Array<String> = arrayOf("/topicos", "/cursos", "/usuarios", "/respostas")

        http?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers(*rotas)?.hasAnyAuthority("LEITURA_ESCRITA")
            ?.antMatchers(HttpMethod.POST, "/login")?.permitAll()
            ?.anyRequest()
            ?.authenticated()
            ?.and()
        http?.addFilterBefore(
            JwtLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )
        http?.addFilterBefore(JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()
            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(usuarioService)?.passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()
}