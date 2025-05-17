package li1.plp.imperative1.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;

public class Normalize implements Comando {
    private String nomeVariavel;
    private List<String> columnList; // Lista de colunas para normalizar (opcional)

    // Construtor com lista de colunas específicas
    public Normalize(String nomeVariavel, List columnList) {
        this.nomeVariavel = nomeVariavel;
        this.columnList = new ArrayList<String>();
        // Fazer cast para String para cada item
        for (Object col : columnList) {
            this.columnList.add((String) col);
        }
    }

    // Construtor sem lista específica (normaliza todas as colunas)
    public Normalize(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
        this.columnList = new ArrayList<>();
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    // Verifica se um valor é numérico
    private boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty() || 
            str.equals("NA") || str.equals("null") || 
            str.equals("NULL")) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isNumericColumn(String[] colValues) {
        for (String value : colValues) {
            if (value == null || value.trim().isEmpty() || 
                value.equalsIgnoreCase("NA") || 
                value.equalsIgnoreCase("null")) {
                continue; // Ignora valores ausentes
            }

            if (!isNumeric(value)) {
                return false; // Encontrou um valor não numérico válido
            }
        }

        // Se todos os valores válidos são numéricos, retorna true
        return true;
    }

    // Aplica standard scaling em valores numéricos (z-score normalization)
    private String[] applyStandardScaling(String[] values) {
        // Calcula média e desvio padrão
        double sum = 0;
        int count = 0;
        List<Double> numericValues = new ArrayList<>();
        
        // Primeira passagem: coletar valores válidos e calcular a soma
        for (String val : values) {
            if (val != null && !val.trim().isEmpty() && 
                !val.equals("NA") && !val.equals("null") && 
                !val.equals("NULL") && isNumeric(val)) {
                double num = Double.parseDouble(val);
                numericValues.add(num);
                sum += num;
                count++;
            }
        }
        
        if (count == 0) return values; // Retorna os valores originais se não houver valores válidos
        
        // Calcula a média
        double mean = sum / count;
        
        // Segunda passagem: calcula a variância
        double variance = 0;
        for (Double num : numericValues) {
            variance += Math.pow(num - mean, 2);
        }
        variance = variance / count;
        
        // Calcula o desvio padrão
        double stdDev = Math.sqrt(variance);
        
        // Se o desvio padrão for zero, retorna valores padronizados como zero
        if (stdDev == 0) {
            String[] result = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && !values[i].trim().isEmpty() && 
                    !values[i].equals("NA") && !values[i].equals("null") && 
                    !values[i].equals("NULL") && isNumeric(values[i])) {
                    result[i] = "0.0";
                } else {
                    result[i] = values[i]; // Mantém valores não numéricos como estão
                }
            }
            return result;
        }
        
        // Aplica standardization: (x - mean) / stdDev
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null && !values[i].trim().isEmpty() && 
                !values[i].equals("NA") && !values[i].equals("null") && 
                !values[i].equals("NULL") && isNumeric(values[i])) {
                double normalized = (Double.parseDouble(values[i]) - mean) / stdDev;
                result[i] = String.format("%.6f", normalized);
            } else {
                result[i] = values[i]; // Mantém valores não numéricos como estão
            }
        }
        return result;
    }

    // Aplica one-hot encoding em valores categóricos
    private Map<String, String[]> applyOneHotEncoding(String[] colNames, String[] values) {
        Map<String, String[]> result = new HashMap<>();
        
        // Identificar todas as categorias únicas (ignorando valores vazios/nulos)
        Set<String> categories = new HashSet<>();
        for (String value : values) {
            if (value != null && !value.trim().isEmpty() && 
                !value.equals("NA") && !value.equals("null") && 
                !value.equals("NULL")) {
                categories.add(value.trim());
            }
        }
        
        // Para cada categoria, criar uma nova coluna
        for (String category : categories) {
            String[] encodedValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && values[i].trim().equals(category)) {
                    encodedValues[i] = "1";
                } else {
                    encodedValues[i] = "0";
                }
            }
            // Nome da nova coluna: nome_original_categoria
            result.put(colNames[0] + "_" + category, encodedValues);
        }
        
        return result;
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
            String[] colunas = cabecalho.split(",", -1);
            
            // Extrair dados em formato tabular
            List<String[]> dados = new ArrayList<>();
            for (int i = 1; i < linhas.length; i++) {
                String[] valores = linhas[i].split(",", -1);
                // Garante que cada linha tenha o mesmo número de colunas que o cabeçalho
                if (valores.length == colunas.length) {
                    dados.add(valores);
                }
            }
            
            if (dados.isEmpty()) {
                throw new ErroTipoEntradaException("Dataset sem dados válidos");
            }
            
            // Determina as colunas a processar
            List<Integer> colunasParaProcessar = new ArrayList<>();
            if (columnList.isEmpty()) {
                // Se não houver colunas especificadas, considerar todas
                for (int i = 0; i < colunas.length; i++) {
                    colunasParaProcessar.add(i);
                }
            } else {
                // Caso contrário, considerar apenas as colunas especificadas
                for (String coluna : columnList) {
                    for (int i = 0; i < colunas.length; i++) {
                        if (colunas[i].trim().equals(coluna.trim())) {
                            colunasParaProcessar.add(i);
                            break;
                        }
                    }
                }
            }
            
            // Para cada coluna determinada, aplicar a normalização apropriada
            Map<Integer, String[]> colunasNormalizadas = new HashMap<>();
            Map<String, String[]> colunasOneHot = new HashMap<>();
            
            for (int colIdx : colunasParaProcessar) {
                // Extrair valores da coluna
                String[] colValues = new String[dados.size()];
                for (int i = 0; i < dados.size(); i++) {
                    String[] linha = dados.get(i);
                    if (colIdx < linha.length) {
                        colValues[i] = linha[colIdx];
                    }
                }
                
                // Determinar tipo de coluna e aplicar transformação apropriada
                if (isNumericColumn(colValues)) {
                    // Aplicar standard scaling para coluna numérica
                    String[] normValues = applyStandardScaling(colValues);
                    colunasNormalizadas.put(colIdx, normValues);
                    System.out.println("Aplicado standard scaling na coluna: " + colunas[colIdx].trim());
                } else {
                    // Aplicar one-hot encoding para coluna categórica
                    Map<String, String[]> oneHotColumns = applyOneHotEncoding(
                        new String[]{colunas[colIdx].trim()}, colValues);
                    colunasOneHot.putAll(oneHotColumns);
                    System.out.println("Aplicado one-hot encoding na coluna: " + colunas[colIdx].trim() + 
                                     " (" + oneHotColumns.size() + " categorias)");
                }
            }
            
            // Construir novo dataset com colunas normalizadas e one-hot encoded
            StringBuilder novoConteudo = new StringBuilder();
            
            // Construir novo cabeçalho
            StringBuilder novoCabecalho = new StringBuilder();
            for (int i = 0; i < colunas.length; i++) {
                if (colunasParaProcessar.contains(i) && !isNumericColumn(getDadosColuna(dados, i))) {
                    // Pular colunas categóricas originais que foram codificadas
                    continue;
                }
                if (novoCabecalho.length() > 0) {
                    novoCabecalho.append(",");
                }
                novoCabecalho.append(colunas[i]);
            }
            // Adicionar colunas one-hot
            for (String colName : colunasOneHot.keySet()) {
                novoCabecalho.append(",").append(colName);
            }
            novoConteudo.append(novoCabecalho.toString()).append("\n");
            
            // Construir linhas de dados
            for (int i = 0; i < dados.size(); i++) {
                StringBuilder novaLinha = new StringBuilder();
                String[] linha = dados.get(i);
                
                // Adicionar colunas originais (normalizadas ou não modificadas)
                for (int j = 0; j < linha.length; j++) {
                    // Pular colunas categóricas que foram one-hot encoded
                    if (colunasParaProcessar.contains(j) && !isNumericColumn(getDadosColuna(dados, j))) {
                        continue;
                    }
                    
                    if (novaLinha.length() > 0) {
                        novaLinha.append(",");
                    }
                    
                    // Usar valor normalizado se disponível
                    if (colunasNormalizadas.containsKey(j)) {
                        novaLinha.append(colunasNormalizadas.get(j)[i]);
                    } else {
                        novaLinha.append(linha[j]);
                    }
                }
                
                // Adicionar colunas one-hot
                for (String colName : colunasOneHot.keySet()) {
                    novaLinha.append(",").append(colunasOneHot.get(colName)[i]);
                }
                
                novoConteudo.append(novaLinha.toString()).append("\n");
            }
            
            // Remover última quebra de linha
            String resultado = novoConteudo.toString().trim();
            
            // Atualizar variável com dataset normalizado
            Valor novoValor = new ValorString(resultado);
            ambiente.changeValor(id, novoValor);
            
            System.out.println("Dataset normalizado com sucesso.");
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao limpar dataset: " + e.getMessage(), e);
        }
        
        return ambiente;
    }
    
    // Método auxiliar para obter os dados de uma coluna específica
    private String[] getDadosColuna(List<String[]> dados, int colIdx) {
        String[] result = new String[dados.size()];
        for (int i = 0; i < dados.size(); i++) {
            String[] linha = dados.get(i);
            if (colIdx < linha.length) {
                result[i] = linha[colIdx];
            }
        }
        return result;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {        
        return true;
}
}
