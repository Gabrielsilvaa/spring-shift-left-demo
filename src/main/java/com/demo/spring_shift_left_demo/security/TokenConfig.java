package com.demo.spring_shift_left_demo.security;

import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

    /**
     * VULNERABILIDADE 2: Hardcoded Secret
     *
     * CWE: CWE-798 - Use of Hard-coded Credentials
     * OWASP: A07:2021 - Identification and Authentication Failures
     *
     * Por que é perigoso:
     * Chaves fixas no código-fonte podem ser extraídas através de engenharia reversa
     * ou vazadas caso o repositório seja exposto. Qualquer pessoa com essa chave pode
     * forjar tokens ou descriptografar dados sensíveis.
     *
     * Como corrigir:
     * Remover do código e injetar via variável de ambiente (@Value("${jwt.secret}"))
     * ou utilizar um cofre de chaves (Secret Manager, HashiCorp Vault, AWS Secrets Manager).
     */
    public static final String SECRET_KEY = "super-secret-bank-key-123456!";

    public String getSecretKey() {
        return SECRET_KEY;
    }
}
