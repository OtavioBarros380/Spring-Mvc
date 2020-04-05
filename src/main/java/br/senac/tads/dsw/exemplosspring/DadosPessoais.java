package br.senac.tads.dsw.exemplosspring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class DadosPessoais {
	
	private Integer id;
	
	@NotBlank(message="Preenchimento do nome é obrigatório")
	private String nome;
	
	private String descricao;
	
	@Email
	@NotBlank
	private String email;
	
	private String senha;
	
	private String repetirSenha;
	
	@Min(value = 1)
	@Max(value = 99)
	private int numeroSorte;
	
	@Digits(integer = 1, fraction = 2, message="Altura com mais de 2 casas decimais")
	private BigDecimal altura;
	
	@Digits(integer = 3, fraction = 1, message="Peso com mais de uma casa decimal")
	private BigDecimal peso;
	
	@Past
	//Converter para um formato válido
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dtNascimento;
	
	
	private int sexo;

	@Size(min = 1)
	@NotNull
	// No html os interesses são do tipo CheckBox, o usuário pode escolher
	// várias opções, por isso está sendo salvo em uma lista
	private List<String> interesses;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

	public int getNumeroSorte() {
		return numeroSorte;
	}

	public void setNumeroSorte(int numeroSorte) {
		this.numeroSorte = numeroSorte;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public List<String> getInteresses() {
		return interesses;
	}

	public void setInteresses(List<String> interesses) {
		this.interesses = interesses;
	}

}
