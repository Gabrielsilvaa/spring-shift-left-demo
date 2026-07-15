package com.demo.spring_shift_left_demo.controller;


import com.demo.spring_shift_left_demo.model.User;
import com.demo.spring_shift_left_demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;

    public UserController(UserService userService, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * VULNERABILIDADE 1: SQL Injection
     *
     * CWE: CWE-89 - Improper Neutralization of Special Elements used in an SQL Command
     * OWASP: A03:2021 - Injection
     *
     * Por que é perigoso:
     * A entrada do usuário ('name') é concatenada diretamente na query SQL.
     * Um atacante pode enviar o valor "' OR '1'='1" e extrair todos os usuários
     * do banco de dados, ou até mesmo executar comandos de DROP TABLE (se o banco permitir).
     *
     * Como corrigir:
     * Utilizar Prepared Statements (variáveis de bind).
     * Exemplo de correção:
     * String safeQuery = "SELECT * FROM users WHERE name = ?";
     * return jdbcTemplate.query(safeQuery, new Object[]{name}, new BeanPropertyRowMapper<>(User.class));
     */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) {

        // Query montada com concatenação de string - Altamente vulnerável
        String query = "SELECT * FROM users WHERE name = '" + name + "'";

        try {
            List<User> users = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}