# Equipe
- Nicole Charron - nc@cin.upfe.br
- Pedro Didier Maranhão - pdm@cin.upfe.br

# Proposta/Objetivo do Projeto
Este projeto tem como objetivo desenvolver uma DSL (Domain-Specific Language) voltada para operações de carregamento, limpeza e normalização de dados.

A DSL será uma extensão da linguagem imperativa 1 do JavaCC, de forma que sua utilização seja intuitiva, permitindo que pessoas não técnicas em dados escrevam scripts em uma linguagem de alto nível e expressiva, sem precisarem conhecer a fundo cada detalhe da API do Pandas.
 
Quanto ao escopo, o propósito é oferecer uma DSL suficiente, capaz de lidar com múltiplas etapas do pipeline, como:
- Salvar e carregar datasets
- Limpeza de dados (tratamento de valores ausentes, remoção de colunas e renames)
- Normalizar valores (a nível de coluna)
- Transformação básica dos dados (a nível de coluna)
  

# Visão da BNF
Aqui está o diagrama de nossa DSL, com ênfase no que foi alterado pela equipe:

```
Programa ::= Comando

Comando ::= Atribuicao
          | ComandoDeclaracao
          | While
          | IfThenElse
          | IO
          | Skip
          | Comando ";" Comando
          | LoadStmt 
          | CleanStmt
          | NormalizeStmt
          | TransformStmt
          | SaveStmt

Skip ::= "skip"

Atribuicao ::= Id ":=" Expressao

Expressao ::= Valor 
            | ExpUnaria 
            | ExpBinaria 
            | Id

Valor ::= ValorConcreto

ValorConcreto ::= ValorInteiro 
                | ValorBooleano 
                | ValorString

ExpUnaria ::= "-" Expressao 
            | "not" Expressao 
            | "length" Expressao

ExpBinaria ::= Expressao "+" Expressao
             | Expressao "-" Expressao
             | Expressao "and" Expressao
             | Expressao "or" Expressao
             | Expressao "==" Expressao
             | Expressao "++" Expressao

ComandoDeclaracao ::= "{" Declaracao ";" Comando "}"

Declaracao ::= DeclaracaoVariavel 
             | DeclaracaoComposta

DeclaracaoVariavel ::= "var" Id "=" Expressao

DeclaracaoComposta ::= Declaracao "," Declaracao

While ::= "while" Expressao "do" Comando

IfThenElse ::= "if" Expressao "then" Comando "else" Comando

IO ::= "write" "(" Expressao ")" 
     | "read" "(" Id ")"

;-------------------------------------------------------
; Comandos novos da DSL
;-------------------------------------------------------

LoadStmt ::= "LOAD" StringLiteral ["AS" Id]

CleanStmt ::= "CLEAN" Id CleanAction

CleanAction ::= "DROP" ColumnList
              | "FILL" ColumnValuePairs
              | "DROPROWS" [ ColumnList ]
              | "RENAME" RenamePairs
              | "REPLACE" Id ReplacePairs

NormalizeStmt ::= "NORMALIZE" Id [ ColumnList ]

TransformStmt ::= "TRANSFORM" Id TransformAction

TransformAction ::= "ADD" NumericPairs
                  | "MULT" NumericPairs

SaveStmt ::= "SAVE" Id "AS" StringLiteral

;-------------------------------------------------------
; Definições auxiliares
;-------------------------------------------------------

ColumnList ::= Id | Id "," ColumnList

ColumnValuePairs ::= ColumnValuePair 
                   | ColumnValuePair "," ColumnValuePairs

ColumnValuePair ::= Id "=" StringLiteral 
                  | Id "=" Numero

RenamePairs ::= RenamePair 
              | RenamePair "," RenamePairs

RenamePair ::= Id "=" Id

ReplacePairs ::= ReplacePair 
               | ReplacePair "," ReplacePairs

ReplacePair ::= StringLiteral "=" StringLiteral

NumericPairs ::= NumericPair 
               | NumericPair "," NumericPairs

NumericPair ::= Id "=" Numero

Numero ::= [0-9]+ ("." [0-9]+)?

Id ::= [a-zA-Z_][a-zA-Z0-9_]*

StringLiteral ::= "\"" [^\"]* "\"" 
                | "'" [^']* "'"
```

Essa BNF ilustra apenas uma estrutura básica do que seria possível na linguagem. A ideia é permitir que o usuário escreva, por exemplo:

```
LOAD "dataset.csv" AS dados;
CLEAN dados REPLACE cidade "rio"="rio de janeiro", ""="null";
CLEAN dados FILL idade = "0", salario = "0";
TRANSFORM dados ADD idade=1;
TRANSFORM dados MULT salario=1.2;
SAVE dados AS "resultado.csv"
```
# Arquitetura de funcionamento 

![](imgs/image.png)

Passo a passo:

1. É dado um comando na DSL;
2. Lexer converte o comando em tokens;
3. Parser interpreta os tokens e transforma em objetos;
4. A sequência de objetos forma uma lista de comandos para serem executadas (AST);
5. Executor	executa os nós da AST;
6. Retorno do resultado para o usuário.







