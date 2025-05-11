package applet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;
import li1.plp.imperative1.parser.Imp1Parser;


public class MultiInterpretador {

	private static final int IMP1 = 0;

	private JTextArea messageBoard;

	private Imp1Parser imp1Parser = null;

	public MultiInterpretador(JTextArea textAreaMensagens) {
		super();
		messageBoard = textAreaMensagens;
	}

	public void interpretarCodigo(String sourceCode, String listaEntrada,
			int selectedIndex) {
		try {
			ByteArrayInputStream fis = new ByteArrayInputStream(sourceCode
					.getBytes());

			switch (selectedIndex) {
			case IMP1:
				interpretarImp1(fis, listaEntrada);
				break;
			}
		} catch (Exception e1) {
			messageBoard.setText(e1.getMessage());
			e1.printStackTrace();
		} catch (Throwable t) {
			messageBoard.setText(t.getMessage());
			t.printStackTrace();
		}

	}

	private void interpretarImp1(InputStream fis, String entradaStr)
			throws Exception {
		li1.plp.imperative1.Programa prog;
		if (imp1Parser == null) {
			imp1Parser = new Imp1Parser(fis);
		} else {
			Imp1Parser.ReInit(fis);
		}
		prog = Imp1Parser.Input();

		messageBoard.setText("sintaxe verificada com sucesso!\n");
		li1.plp.imperative1.memory.ListaValor entrada = obterListaEntradaImp1(entradaStr);
		if (prog.checaTipo(new ContextoCompilacaoImperativa(entrada))) {
			messageBoard.append("resultado = "
					+ prog.executar(new ContextoExecucaoImperativa(entrada))
							.toString());
		} else {
			messageBoard.append("erro de tipos!");
		}
	}


	@SuppressWarnings("unchecked")
	private li1.plp.imperative1.memory.ListaValor obterListaEntradaImp1(String texto) {
		@SuppressWarnings("rawtypes")
		List valores = new LinkedList<li1.plp.expressions2.expression.ValorConcreto>();
		li1.plp.imperative1.memory.ListaValor entrada = new li1.plp.imperative1.memory.ListaValor();
		StringTokenizer parser = new StringTokenizer(texto);

		while (parser.hasMoreTokens()) {
			String parametro = parser.nextToken();
			
			try {
				Integer inteiro = Integer.valueOf(parametro);
				valores.add(new li1.plp.expressions2.expression.ValorInteiro(inteiro.intValue()));
				continue;
			} catch (NumberFormatException e) {

			}

			if (parametro.equalsIgnoreCase("true")
					|| parametro.equalsIgnoreCase("false")) {
				Boolean booleano = Boolean.valueOf(parametro);
				valores.add(new li1.plp.expressions2.expression.ValorBooleano(booleano.booleanValue()));
			} else {
				valores.add(new li1.plp.expressions2.expression.ValorString(parametro));
			}
		}
		entrada = Imp1Parser.criaListaValor(valores);
		return entrada;
	}
}