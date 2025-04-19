package li1.plp.imperative1.command;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.expressions2.expression.Id;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;


public class Save implements Comando {
    private final String caminho;
    private final String nomeVariavel;

    public Save(String caminho, String nomeVariavel) {
        this.caminho = caminho;
        this.nomeVariavel = nomeVariavel;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    @Override
    public String toString() {
        return "SAVE " + nomeVariavel + " AS \"" + caminho + "\"";
    }

    @Override
    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

        if (nomeVariavel != null) {
            Id id = new Id(nomeVariavel);
            Valor valor = ambiente.get(id);

            if (valor instanceof ValorString) {
                String conteudo = ((ValorString) valor).valor(); // ou getValor() dependendo da versão
                // Print Conteudo
                System.out.println("Conteudo: " + conteudo);
                try {
                    Files.write(Paths.get(caminho), conteudo.getBytes());
                    System.out.println("Arquivo salvo em: " + caminho);
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar arquivo: " + caminho, e);
                }
            } else {
                throw new RuntimeException("Tipo incompatível: esperado ValorString.");
            }
        }

        return ambiente;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return true;
    }
}