# Equipe
- Pedro Didier Maranhão - pdm@cin.upfe.br
- Nicole Charron - nc@cin.upfe.br

# Proposta do Projeto
Este projeto tem como objetivo desenvolver uma DSL (Domain-Specific Language) voltada para operações de carregamento, limpeza e normalização de dados. A DSL será traduzida para chamadas da biblioteca Pandas em Python, permitindo que pessoas não técnicas em dados escrevam scripts em uma linguagem de alto nível e expressiva, sem precisarem conhecer a fundo cada detalhe da API do Pandas. Quanto ao escopo, o propósito é oferecer uma DSL suficiente, capaz de lidar com múltiplas etapas do pipeline, como a verificação de formatos, padronização de colunas, tratamento de valores ausentes e normalizações. Para isso, será definida formalmente a gramática, criada uma arquitetura modular e implementado um parser que traduz as instruções da DSL para as operações correspondentes em Pandas.

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
LOAD "data/clientes.csv" AS clientes
CLEAN clientes DROP "col_que_nao_preciso" RENAME "nome->nome_cliente"
NORMALIZE clientes SCALER Standard COLUMNS "idade","renda"
TRANSFORM clientes WITH funcao_externa(param=42)
```
