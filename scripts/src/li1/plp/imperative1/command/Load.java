package li1.plp.imperative1.command;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.memory.Contexto;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;



public class Load implements Comando {
    private String caminho;
    private String nomeVariavel;

    public Load(String caminho, String nomeVariavel) {
        this.caminho = caminho;
        this.nomeVariavel = nomeVariavel;
    }

    public Load(String caminho) {
        this.caminho = caminho;
        this.nomeVariavel = null;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
    throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
    EntradaVaziaException, ErroTipoEntradaException {

        try {
            // Ler as linhas do arquivo
            List<String> linhas = Files.readAllLines(Paths.get(caminho.replace("\"", "")));
            
            // Se houver pelo menos uma linha, a primeira será o cabeçalho (nomes das colunas)
            if (!linhas.isEmpty()) {
                String cabecalho = linhas.get(0); // Primeira linha (cabeçalho)
                String[] colunas = cabecalho.split(","); // Separar por vírgula (CSV)
    
                // Imprimir os nomes das colunas
                System.out.println("Nomes das colunas:");
                for (String coluna : colunas) {
                    System.out.println(coluna.trim()); // Imprimir cada nome de coluna
                }
    
                // Criação do valor a ser armazenado (todo o conteúdo do arquivo)
                Valor valor = new li1.plp.expressions2.expression.ValorString(String.join("\n", linhas));
    
                // Se o comando LOAD especifica um alias (ex: AS table)
                if (nomeVariavel != null) {
                    Id id = new Id(nomeVariavel); // Nome da variável (ex: table)

                    try {
                        ambiente.changeValor(id, valor); // Tenta alterar valor da variável
                    } catch (VariavelNaoDeclaradaException e) {
                        // Garante que há um escopo ativo antes de mapear
                        ambiente.incrementa();
                        ambiente.map(id, valor); // Mapeia a variável se ainda não declarada
                    }

                    // Verifica se a variável foi corretamente armazenada
                    Valor valorTable = ambiente.get(id); // Obter o valor da variável
                    if (valorTable != null) {
                        System.out.println("Valor de '" + nomeVariavel + "': " + valorTable);
                    } else {
                        System.out.println("A variável '" + nomeVariavel + "' não foi encontrada no ambiente.");
                    }
                }
                else {
                    // Se não houver alias, apenas imprime o conteúdo
                    System.out.println("Conteúdo do arquivo:");
                    for (String linha : linhas) {
                        System.out.println(linha); // Imprimir cada linha do arquivo
                    }
                }
            }
    
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo: " + caminho, e);
        }

    return ambiente;
    }

    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return true;
    }
    
}
