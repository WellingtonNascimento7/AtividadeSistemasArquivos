package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("C:\\TEMP");
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("Diretorio existe");
			
		} else {
			dir.mkdir();
			System.out.println("Diretorio criado, Agora crie o arquivo Cadastro.csv para prosseguir");
		}
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File("C:\\TEMP", arquivo);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) { // procurando EOF
				linha = buffer.readLine();
				if(linha!=null) {
				String[] p = linha.split(";");
				if (Integer.parseInt(p[0]) == codigo) {
					buffer.close();
					leitor.close();
					fluxo.close();
					return true;
				}
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Inválido");
		}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		File arq = new File ("C:\\TEMP", arquivo);
		boolean aux = false;
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) { // procurando EOF
				linha = buffer.readLine();
				if(linha!=null) {
				String[] p = linha.split(";");
				if (Integer.parseInt(p[0]) == codigo) {
					System.out.println("Codigo: " + p[0]+ "\nNome: " + p[1] + "\nEmail: " + p[2]+ "\n");
					aux = true;
				}
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			if(!aux) {
				System.out.println("Nenhum registro encontrado");
			}
		} else {
			throw new IOException("Arquivo Inválido");
		}
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File dir = new File("C:\\TEMP");
		File arq = new File("C:\\TEMP", arquivo);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(arq.exists()) {
				existe = true;
			}
			if(verificaRegistro(arquivo, codigo)) {
				System.out.println("Já existe um registro com este codigo.");				
			}else {
			String conteudo = codigo + ";" + nome + ";" + email + "\r\n";
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();	
			}
		}else {
			throw new IOException("Diretorio inválido");
		}
	}

}
