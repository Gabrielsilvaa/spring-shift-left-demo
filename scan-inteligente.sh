#!/bin/bash

BLUE='\033[0;34m'
GREEN='\033[0;32m'
NC='\033[0m'

echo -e "${BLUE}[*] Fase 1: Iniciando varredura SAST local com Semgrep...${NC}"

# Adicionamos um filtro "sed" no final para expurgar códigos ANSI de terminal
semgrep scan --config semgrep.yml --json 2>/dev/null | \
jq -r '.results[] | "File: \(.path) | Line: \(.start.line) | Issue: \(.extra.message)"' | \
ollama run gemma2 "Voce e um Arquiteto de Seguranca Senior. O texto a seguir e um relatorio cru de SAST. Escreva uma 'Ordem de Servico' detalhada, rica e profissional em Markdown (.md) para um Agente de IA na IDE ler e consertar o codigo.

REGRAS ABSOLUTAS:
1. ZERO acentos graficos (nao use til, cedilha, acentos agudos).
2. NENHUM codigo ANSI. Apenas texto e sintaxe Markdown limpa.
3. Seja descritivo, tecnico e mandatorio.
4. Va direto ao ponto, NAO crie textos de introducao ou conclusao fora do escopo.

ESTRUTURA EXIGIDA:
# ORDEM DE SERVICO: REMEDIACAO SAST

Para cada falha encontrada, crie um bloco estruturado exatamente assim:

## Arquivo: [Nome do arquivo]
- **Linha:** [Linha]
- **Problema Detectado:** [Descreva a vulnerabilidade baseado no Issue recebido]
- **Instrucao Tecnica para a IA:** [Instrucao direta usando o framework Spring]

Aqui esta o log SAST bruto para voce analisar e transformar: " | \
sed $'s/\033\\[[0-9;]*[a-zA-Z]//g' > INSTRUCOES_IDE.md

echo -e "${GREEN}[+] Sucesso! Arquivo gerado limpo: INSTRUCOES_IDE.md${NC}"
echo -e "${BLUE}[*] O artefato esta pronto para ser consumido pela IA da sua IDE.${NC}"