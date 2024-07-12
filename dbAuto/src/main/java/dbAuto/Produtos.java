package dbAuto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produtos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	String nome;
	double preco;
	int quantidade;

	public Produtos(String nome, double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Produtos() {
		super();
	}

}
