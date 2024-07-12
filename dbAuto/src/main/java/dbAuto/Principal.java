package dbAuto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner entrada = new Scanner(System.in);

		List<Produtos> produtos = new ArrayList<Produtos>();
		System.out.println("entre com caminho: ");
		String sourceFileStr = entrada.nextLine();

		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {
			String itensCsv = br.readLine();

			while (itensCsv != null) {
				String[] fields = itensCsv.split(",");
				String nome = fields[0];
				double preco = Double.parseDouble(fields[1]);
				int quantidade = Integer.parseInt(fields[2]);

				produtos.add(new Produtos(nome, preco, quantidade));

				itensCsv = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("error ao ler" + e.getMessage());
		}

		for (Produtos produtos2 : produtos) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("produtos");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(produtos2);
			em.getTransaction().commit();

			em.close();
			emf.close();
		}
		entrada.close();
	}
}
