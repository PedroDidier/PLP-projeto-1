package li1.plp.imperative1.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;

public class Clean implements Comando {
    private String nomeVariavel;
    private String strategy; // "drop" ou "fill"

    public Clean(String nomeVariavel, String strategy) {
        this.nomeVariavel = nomeVariavel;
        this.strategy = strategy;
    }

    public Clean(String nomeVariavel) {
        this(nomeVariavel, "drop");
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public String getStrategy() {
        return strategy;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String calculateMode(List<String> values) {
        Map<String, Integer> frequency = new HashMap<>();
        for (String value : values) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }
        return Collections.max(frequency.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private double calculateMean(List<String> values) {
        double sum = 0;
        int count = 0;
        for (String value : values) {
            if (!value.trim().isEmpty() && !value.equals("NA") && 
                !value.equals("null") && !value.equals("NULL")) {
                sum += Double.parseDouble(value);
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    @Override
    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
            EntradaVaziaException, ErroTipoEntradaException {

        try {
            Id id = new Id(nomeVariavel);
            Valor valor = ambiente.get(id);

            if (valor == null) {
                throw new VariavelNaoDeclaradaException(id);
            }

            if (!(valor instanceof ValorString)) {
                throw new ErroTipoEntradaException("A variável deve conter um dataset CSV");
            }

            String conteudo = ((ValorString) valor).valor();
            String[] linhas = conteudo.split("\n");

            if (linhas.length < 2) {
                throw new ErroTipoEntradaException("Dataset vazio ou inválido");
            }

            String cabecalho = linhas[0];
            String[] colunas = cabecalho.split(",");
            List<String> linhasLimpas = new ArrayList<>();
            linhasLimpas.add(cabecalho);

            if (strategy.equals("fill")) {
                List<List<String>> colunasValores = new ArrayList<>();
                for (int i = 0; i < colunas.length; i++) {
                    colunasValores.add(new ArrayList<>());
                }

                for (int i = 1; i < linhas.length; i++) {
                    String[] valores = linhas[i].split(",");
                    for (int j = 0; j < valores.length; j++) {
                        String valorColuna = valores[j].trim();
                        if (!valorColuna.isEmpty() && !valorColuna.equals("NA") &&
                            !valorColuna.equals("null") && !valorColuna.equals("NULL")) {
                            colunasValores.get(j).add(valorColuna);
                        }
                    }
                }

                String[] valoresPreenchimento = new String[colunas.length];
                for (int i = 0; i < colunas.length; i++) {
                    List<String> valoresColuna = colunasValores.get(i);
                    if (!valoresColuna.isEmpty()) {
                        if (isNumeric(valoresColuna.get(0))) {
                            double mean = calculateMean(valoresColuna);
                            valoresPreenchimento[i] = String.valueOf(mean);
                        } else {
                            valoresPreenchimento[i] = calculateMode(valoresColuna);
                        }
                    } else {
                        valoresPreenchimento[i] = "0";
                    }
                }

                for (int i = 1; i < linhas.length; i++) {
                    String[] valores = linhas[i].split(",");
                    String[] novaLinha = new String[colunas.length];

                    for (int j = 0; j < valores.length; j++) {
                        String valorColuna = valores[j].trim();
                        if (valorColuna.isEmpty() || valorColuna.equals("NA") ||
                            valorColuna.equals("null") || valorColuna.equals("NULL")) {
                            novaLinha[j] = valoresPreenchimento[j];
                        } else {
                            novaLinha[j] = valorColuna;
                        }
                    }

                    linhasLimpas.add(String.join(",", novaLinha));
                }

            } else if (strategy.equals("drop")) {
                for (int i = 1; i < linhas.length; i++) {
                    String linha = linhas[i];
                    String[] valores = linha.split(",");

                    boolean temValorFaltante = false;
                    for (String valorColuna : valores) {
                        if (valorColuna.trim().isEmpty() || valorColuna.trim().equals("NA") ||
                            valorColuna.trim().equals("null") || valorColuna.trim().equals("NULL")) {
                            temValorFaltante = true;
                            break;
                        }
                    }

                    if (!temValorFaltante) {
                        linhasLimpas.add(linha);
                    }
                }
            }

            String novoConteudo = String.join("\n", linhasLimpas);
            Valor novoValor = new ValorString(novoConteudo);
            ambiente.changeValor(id, novoValor);

            System.out.println("Dataset limpo com sucesso. " +
                    (strategy.equals("drop") ?
                            (linhas.length - linhasLimpas.size()) + " linhas removidas." :
                            "Valores faltantes preenchidos com média/moda."));

        } catch (Exception e) {
            throw new RuntimeException("Erro ao limpar dataset: " + e.getMessage(), e);
        }

        return ambiente;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) {
        return true;
    }
}
