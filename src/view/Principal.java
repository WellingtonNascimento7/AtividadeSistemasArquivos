package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
	public static void main(String[] args) {

		IArquivosController arqCont = new ArquivosController();
		String dirWin = "C:\\Windows";
		String path = "C:\\TEMP\\aula";
		String arquivo = "Cadastros.csv";
		try {
			arqCont.verificaDirTemp();
			if(arqCont.verificaRegistro(arquivo, 1)) {
				System.out.println("Tem");
			}else {
				System.out.println("Não tem");
			}
			arqCont.imprimeCadastro(arquivo, 1);
			arqCont.insereCadastro(arquivo, 1, "teste1", "teste1@teste.com");
			arqCont.insereCadastro(arquivo, 2, "teste2", "teste2@teste.com");
			arqCont.insereCadastro(arquivo, 3, "teste3", "teste3@teste.com");

			arqCont.imprimeCadastro(arquivo, 1);
			arqCont.imprimeCadastro(arquivo, 2);
			arqCont.imprimeCadastro(arquivo, 3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
