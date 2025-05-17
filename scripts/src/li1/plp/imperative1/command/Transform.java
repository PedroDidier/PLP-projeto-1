package li1.plp.imperative1.command;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.Valor;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;

public class Transform implements Comando {
    private String nomeVariavel;
    private String operation; // "ADD" ou "MULT"
    private Map<String, Double> columnValuePairs; // Pares coluna-valor para ADD ou MULT

    // Construtor para ADD ou MULT
    public Transform(String nomeVariavel, String operation, Map columnValuePairs) {
        this.nomeVariavel = nomeVariavel;
        this.operation = operation;
        this.columnValuePairs = new HashMap<String, Double>();
        
        // Fazer cast para String para cada chave e valor para Double
        for (Object key : columnValuePairs.keySet()) {
            try {
                Double valor = Double.parseDouble((String) columnValuePairs.get(key));
                this.columnValuePairs.put((String) key, valor);
            } catch (NumberFormatException e) {
                // Se o valor não for numérico, armazenamos para validação posterior
                this.columnValuePairs.put((String) key, null);
            }
        }
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public String getOperation() {
        return operation;
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

    // Detecta automaticamente se uma coluna é numérica
    private boolean isNumericColumn(String[] colValues) {
        int numericCount = 0;
        for (String value : colValues) {
            if (value != null && !value.trim().isEmpty() && 
                !value.equals("NA") && !value.equals("null") && 
                !value.equals("NULL") && isNumeric(value)) {
                numericCount++;
            }
        }
        // Se mais de 70% dos valores (não nulos) são numéricos, consideramos a coluna como numérica
        return numericCount > 0 && numericCount >= (colValues.length * 0.7);
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
            
            // Mapear índices das colunas que serão transformadas
            Map<Integer, Double> indicesValores = new HashMap<>();
            for (Map.Entry<String, Double> entry : columnValuePairs.entrySet()) {
                String coluna = entry.getKey();
                Double valorOperacao = entry.getValue();
                boolean colunaEncontrada = false;
                
                for (int i = 0; i < colunas.length; i++) {
                    if (colunas[i].trim().equals(coluna.trim())) {
                        colunaEncontrada = true;
                        
                        // Verificar se a coluna é numérica
                        String[] valoresColuna = new String[linhas.length - 1];
                        for (int j = 1; j < linhas.length; j++) {
                            String[] valores = linhas[j].split(",", -1);
                            if (i < valores.length) {
                                valoresColuna[j-1] = valores[i];
                            }
                        }
                        
                        if (!isNumericColumn(valoresColuna)) {
                            throw new ErroTipoEntradaException("A coluna '" + coluna + "' não é numérica e não pode ser transformada");
                        }
                        
                        if (valorOperacao == null) {
                            throw new ErroTipoEntradaException("O valor para a operação na coluna '" + coluna + "' deve ser numérico");
                        }
                        
                        indicesValores.put(i, valorOperacao);
                        break;
                    }
                }
                
                if (!colunaEncontrada) {
                    throw new ErroTipoEntradaException("Coluna '" + coluna + "' não encontrada no dataset");
                }
            }
            
            // Aplicar transformação nas colunas especificadas
            List<String> linhasTransformadas = new ArrayList<>();
            linhasTransformadas.add(cabecalho);  // Adiciona o cabeçalho sem alterações
            
            for (int i = 1; i < linhas.length; i++) {
                String[] valores = linhas[i].split(",", -1);
                
                for (Map.Entry<Integer, Double> entry : indicesValores.entrySet()) {
                    int indiceColuna = entry.getKey();
                    double valorOperacao = entry.getValue();
                    
                    if (indiceColuna < valores.length && isNumeric(valores[indiceColuna])) {
                        double valorAtual = Double.parseDouble(valores[indiceColuna]);
                        double novoValor;
                        
                        if (operation.equals("ADD")) {
                            novoValor = valorAtual + valorOperacao;
                        } else if (operation.equals("MULT")) {
                            novoValor = valorAtual * valorOperacao;
                        } else {
                            throw new ErroTipoEntradaException("Operação não suportada: " + operation);
                        }
                        
                        // Formatar o resultado com até 6 casas decimais
                        String valorFormatado = String.format("%.6f", novoValor)
                                .replaceAll("0*$", "0") // remove zeros à direita
                                .replaceAll("\\.$", ""); // remove ponto decimal se for um número inteiro
                        
                        if (valorFormatado.endsWith(".0")) {
                            valorFormatado = valorFormatado.substring(0, valorFormatado.length() - 2);
                        }
                        
                        valores[indiceColuna] = valorFormatado;
                    }
                }
                
                linhasTransformadas.add(String.join(",", valores));
            }
            
            String resultado = String.join("\n", linhasTransformadas);
            
            // Atualizar a variável com o dataset transformado
            ambiente.changeValor(id, new ValorString(resultado));
            
            System.out.println("Dataset transformado com sucesso. Operação " + 
                              (operation.equals("ADD") ? "de adição" : "de multiplicação") + 
                              " aplicada nas colunas especificadas.");
            
            return ambiente;
            
        } catch (VariavelNaoDeclaradaException | ErroTipoEntradaException e) {
            throw e;
        } catch (Exception e) {
            throw new ErroTipoEntradaException("Erro ao transformar o dataset: " + e.getMessage());
        }
    }

    @Override
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {        
        return true;
    }
} 