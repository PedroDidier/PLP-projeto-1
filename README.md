# Equipe
- Pedro Didier Maranhão - pdm@cin.upfe.br
- Nicole Charron - nc@cin.upfe.br

# Proposta/Objetivo do Projeto
Este projeto tem como objetivo desenvolver uma DSL (Domain-Specific Language) voltada para operações de carregamento, limpeza e normalização de dados.

 A DSL será traduzida para chamadas da biblioteca Pandas em Python, permitindo que pessoas não técnicas em dados escrevam scripts em uma linguagem de alto nível e expressiva, sem precisarem conhecer a fundo cada detalhe da API do Pandas.
 
 Quanto ao escopo, o propósito é oferecer uma DSL suficiente, capaz de lidar com múltiplas etapas do pipeline, como:
 - Normalização de valores
 - Salvamento e carregamento de datasets
 - Limpeza de dados (tratamento de valores ausentes, remoção de colunas e renames)
 - Transformação dos dados (PLUS)

 Para isso, será definida formalmente a gramática, criada uma arquitetura modular e implementado um parser que traduz as instruções da DSL para as operações correspondentes em Pandas.

# Visão Básica da BNF
Abaixo está um esboço simplificado (e não definitivo) de como a gramática da DSL poderia ser estruturada:

```
<program> ::= <statement_list>

<statement_list> ::= <statement> 
                   | <statement> <statement_list>

<statement> ::= <load_stmt> 
              | <clean_stmt>
              | <normalize_stmt>
              | <transform_stmt>

<load_stmt> ::= "LOAD" <string> ["AS" <identifier>]

<clean_stmt> ::= "CLEAN" <identifier> [ "DROP" <column_list> ]
                               [ "FILL" <column_value_pairs> ]
                               [ "RENAME" <old_new_pair_list> ]

<normalize_stmt> ::= "NORMALIZE" <identifier> [ "SCALER" <scaler_type> ]
                                         [ "COLUMNS" <column_list> ]

<transform_stmt> ::= "TRANSFORM" <identifier> "WITH" <function_call>

<column_list> ::= <string>
                | <string> "," <column_list>

<column_value_pairs> ::= <column_value_pair>
                       | <column_value_pair> "," <column_value_pairs>

<column_value_pair> ::= <string> "=" <string>

<old_new_pair_list> ::= <old_new_pair>
                      | <old_new_pair> "," <old_new_pair_list>

<old_new_pair> ::= <string> "->" <string>
```

Essa BNF ilustra apenas uma estrutura básica do que seria possível na linguagem. A ideia é permitir que o usuário escreva, por exemplo:

```
LOAD "dados.csv"
CLEAN DROP "coluna_x"
CLEAN FILL "idade" WITH 0
RENAME "nome" TO "nome_completo"
NORMALIZE
SAVE "saida.csv"
```
# Arquitetura de funcionamento 

![](imgs/image.png)

Passo a passo:

1. É dado um comando na DSL
2. Parser lê o texto DSL e gera uma árvore (parse tree)
3. Transformer traduz a parse tree para objetos úteis (AST)
4. Cada objeto da AST é uma classe que representa uma instrução da DSL em Python
5. Executor	executa os nós da AST usando pandas
6. Retorno do resultado para o usuário





