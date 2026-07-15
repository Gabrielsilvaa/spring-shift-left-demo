package com.demo.spring_shift_left_demo.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    /**
     * VULNERABILIDADE 3: Senha utilizando MD5
     *
     * CWE: CWE-327 - Use of a Broken or Risky Cryptographic Algorithm
     * OWASP: A02:2021 - Cryptographic Failures
     *
     * Por que é perigoso:
     * O algoritmo MD5 é considerado quebrado e obsoleto. Ele é extremamente rápido e
     * vulnerável a ataques de força bruta, Rainbow Tables e colisões (collision attacks).
     *
     * Como corrigir:
     * Substituir por um algoritmo projetado especificamente para hashing de senhas, que seja
     * lento e utilize salt, como BCrypt, Argon2 ou PBKDF2. No Spring Security,
     * utilize o BCryptPasswordEncoder.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }
}
