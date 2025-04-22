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
    private String action; // "DROP", "FILL", "DROPROWS", "RENAME"
    private List<String> columnList; // Lista de colunas para DROP, DROPROWS
    private Map<String, String> columnValuePairs; // Pares coluna-valor para FILL
    private Map<String, String> oldNewPairs; // Pares antigo-novo para RENAME

    // Construtor para DROP
    public Clean(String nomeVariavel, String action, List columnList) {
        this.nomeVariavel = nomeVariavel;
        this.action = action;
        this.columnList = new ArrayList<String>();
        // Fazer cast para String para cada item
        for (Object col : columnList) {
            this.columnList.add((String) col);
        }
        this.columnValuePairs = new HashMap<>();
        this.oldNewPairs = new HashMap<>();
    }

    // Construtor para FILL
    public Clean(String nomeVariavel, String action, Map columnValuePairs) {
        this.nomeVariavel = nomeVariavel;
        this.action = action;
        this.columnList = new ArrayList<>();
        this.columnValuePairs = new HashMap<String, String>();
        // Fazer cast para String para cada chave e valor
        for (Object key : columnValuePairs.keySet()) {
            this.columnValuePairs.put((String) key, (String) columnValuePairs.get(key));
        }
        this.oldNewPairs = new HashMap<>();
    }

    // Construtor para RENAME
    public Clean(String nomeVariavel, String action, Map oldNewPairs, boolean isRename) {
        this.nomeVariavel = nomeVariavel;
        this.action = action;
        this.columnList = new ArrayList<>();
        this.columnValuePairs = new HashMap<>();
        this.oldNewPairs = new HashMap<String, String>();
        // Fazer cast para String para cada chave e valor
        for (Object key : oldNewPairs.keySet()) {
            this.oldNewPairs.put((String) key, (String) oldNewPairs.get(key));
        }
    }

    // Para manter compatibilidade com o código anterior
    public Clean(String nomeVariavel, String strategy) {
        this.nomeVariavel = nomeVariavel;
        this.action = strategy.equals("drop") ? "DROPROWS" : "FILL";
        this.columnList = new ArrayList<>();
        this.columnValuePairs = new HashMap<>();
        this.oldNewPairs = new HashMap<>();
    }

    public Clean(String nomeVariavel) {
        this(nomeVariavel, "drop");
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public String getAction() {
        return action;
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
            String resultado = "";

            if (action.equals("DROP")) {
                // Excluir colunas especificadas
                List<Integer> colunasParaRemover = new ArrayList<>();
                for (String coluna : columnList) {
                    for (int i = 0; i < colunas.length; i++) {
                        if (colunas[i].trim().equals(coluna.trim())) {
                            colunasParaRemover.add(i);
                            break;
                        }
                    }
                }

                StringBuilder novoConteudo = new StringBuilder();
                for (String linha : linhas) {
                    String[] valores = linha.split(",");
                    StringBuilder novaLinha = new StringBuilder();
                    for (int i = 0; i < valores.length; i++) {
                        if (!colunasParaRemover.contains(i)) {
                            if (novaLinha.length() > 0) {
                                novaLinha.append(",");
                            }
                            novaLinha.append(valores[i]);
                        }
                    }
                    if (novoConteudo.length() > 0) {
                        novoConteudo.append("\n");
                    }
                    novoConteudo.append(novaLinha);
                }
                resultado = novoConteudo.toString();
                System.out.println("Dataset limpo com sucesso. Colunas removidas: " + String.join(", ", columnList));
            } 
            else if (action.equals("FILL")) {
                // Preencher valores faltantes nas colunas especificadas
                Map<Integer, String> indiceValorFill = new HashMap<>();
                for (Map.Entry<String, String> entry : columnValuePairs.entrySet()) {
                    String coluna = entry.getKey();
                    String valorSubstituicao = entry.getValue();
                    for (int i = 0; i < colunas.length; i++) {
                        if (colunas[i].trim().equals(coluna.trim())) {
                            indiceValorFill.put(i, valorSubstituicao);
                            break;
                        }
                    }
                }

                List<String> linhasPreenchidas = new ArrayList<>();
                linhasPreenchidas.add(cabecalho);

                for (int i = 1; i < linhas.length; i++) {
                    String[] valores = linhas[i].split(",");
                    for (int j = 0; j < valores.length; j++) {
                        if (indiceValorFill.containsKey(j) && 
                            (valores[j].trim().isEmpty() || valores[j].trim().equals("NA") ||
                             valores[j].trim().equals("null") || valores[j].trim().equals("NULL"))) {
                            valores[j] = indiceValorFill.get(j);
                        }
                    }
                    linhasPreenchidas.add(String.join(",", valores));
                }
                resultado = String.join("\n", linhasPreenchidas);
                System.out.println("Dataset limpo com sucesso. Valores faltantes preenchidos nas colunas especificadas.");
            } 
            else if (action.equals("DROPROWS")) {
                // Remover linhas com valores faltantes
                List<Integer> indicesColunas = new ArrayList<>();
                if (columnList.isEmpty()) {
                    // Se não houver colunas especificadas, considerar todas
                    for (int i = 0; i < colunas.length; i++) {
                        indicesColunas.add(i);
                    }
                } else {
                    // Caso contrário, considerar apenas as colunas especificadas
                    for (String coluna : columnList) {
                        for (int i = 0; i < colunas.length; i++) {
                            if (colunas[i].trim().equals(coluna.trim())) {
                                indicesColunas.add(i);
                                break;
                            }
                        }
                    }
                }

                List<String> linhasLimpas = new ArrayList<>();
                linhasLimpas.add(cabecalho);

                for (int i = 1; i < linhas.length; i++) {
                    String[] valores = linhas[i].split(",");
                    boolean temValorFaltante = false;
                    for (int indice : indicesColunas) {
                        if (indice < valores.length && 
                            (valores[indice].trim().isEmpty() || valores[indice].trim().equals("NA") ||
                             valores[indice].trim().equals("null") || valores[indice].trim().equals("NULL"))) {
                            temValorFaltante = true;
                            break;
                        }
                    }
                    if (!temValorFaltante) {
                        linhasLimpas.add(linhas[i]);
                    }
                }

                resultado = String.join("\n", linhasLimpas);
                System.out.println("Dataset limpo com sucesso. " + (linhas.length - linhasLimpas.size()) + " linhas removidas.");
            } 
            else if (action.equals("RENAME")) {
                // Renomear colunas
                String[] novasColunas = Arrays.copyOf(colunas, colunas.length);
                for (Map.Entry<String, String> entry : oldNewPairs.entrySet()) {
                    String colunaAntiga = entry.getKey();
                    String colunaNova = entry.getValue();
                    for (int i = 0; i < colunas.length; i++) {
                        if (colunas[i].trim().equals(colunaAntiga.trim())) {
                            novasColunas[i] = colunaNova;
                            break;
                        }
                    }
                }

                List<String> linhasRenomeadas = new ArrayList<>();
                linhasRenomeadas.add(String.join(",", novasColunas));
                for (int i = 1; i < linhas.length; i++) {
                    linhasRenomeadas.add(linhas[i]);
                }

                resultado = String.join("\n", linhasRenomeadas);
                System.out.println("Dataset limpo com sucesso. Colunas renomeadas: " + oldNewPairs.size());
            } 
            else {
                throw new RuntimeException("Ação de limpeza não reconhecida: " + action);
            }

            Valor novoValor = new ValorString(resultado);
            ambiente.changeValor(id, novoValor);

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
