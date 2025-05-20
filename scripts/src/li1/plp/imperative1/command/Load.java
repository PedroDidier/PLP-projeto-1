package li1.plp.imperative1.command;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;

public class Load implements Comando {
    private String caminho;
    private String nomeVariavel;

    /**
     * Construtor para carregar um dataset e associá-lo a uma variável
     * @param caminho O caminho do arquivo a ser carregado
     * @param nomeVariavel O nome da variável onde o dataset será armazenado
     */
    public Load(String caminho, String nomeVariavel) {
        this.caminho = caminho;
        this.nomeVariavel = nomeVariavel;
    }

    /**
     * Construtor para carregar um dataset sem associá-lo a uma variável
     * @param caminho O caminho do arquivo a ser carregado
     */
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
                ValorString valor = new ValorString(String.join("\n", linhas));
    
                // Se o comando LOAD especifica uma variável
                if (nomeVariavel != null) {
                    Id id = new Id(nomeVariavel);

                    try {
                        // Tenta alterar o valor da variável se ela já existir
                        ambiente.changeValor(id, valor);
                    } catch (VariavelNaoDeclaradaException e) {
                        // Se a variável não existir, cria um novo mapeamento
                        ambiente.map(id, valor);
                    }

                    // Verifica se a variável foi corretamente armazenada
                    Valor valorArmazenado = ambiente.get(id);
                    if (valorArmazenado != null) {
                        System.out.println("Dataset '" + nomeVariavel + "' carregado com sucesso.");
                    } else {
                        System.out.println("Erro ao carregar o dataset '" + nomeVariavel + "'.");
                    }
                }
                else {
                    // Se não houver nome de variável, apenas imprime o conteúdo
                    System.out.println("Conteúdo do arquivo:");
                    for (String linha : linhas) {
                        System.out.println(linha);
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
