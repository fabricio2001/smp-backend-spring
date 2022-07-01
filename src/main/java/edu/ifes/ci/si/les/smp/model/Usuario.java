package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idUsuario"})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idUsuario;

	@Column(length = 50)
	@NotBlank(message = "Nome do usuario deve ser preenchido")
	@Size(min = 1, max = 50 , message = "Nome do usuario deve ter entre 1 e 50 letras")
	private String nomeUsuario;

	@Column(length = 50)
	@NotBlank(message = "Sobrenome do usuario deve ser preenchido")
	@Size(min = 1, max = 50 , message = "Sobrenome do usuario deve ter entre 1 e 50 letras")
	private String sobrenomeUsuario;
	
	@Column(length = 50)
    @NotBlank(message = "CPF do usuario deve ser preenchido")
    @Size(min = 14, max = 14, message = "CPF da famila deve ter 14 caracteres")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF da familia deve seguir o padrão NNN.NNN.NNN-NN")
	private String cpfUsuario;

	@Column(length = 50)
	@NotBlank(message = "Email do usuario deve ser preenchido")
	@Size(min = 1, max = 50 , message = "Email do usuario deve ter entre 1 e 50 letras")
	private String emailUsuario;

	@Column(length = 50)
	@NotBlank(message = "Telefone do usuario deve ser preenchido")
	@Size(min = 9, max = 11 , message = "Telefone do usuario deve ter entre 9 e 11 letras")
	private String telefoneUsuario;

	@Column(length = 50)
	@NotBlank(message = "Senha do usuario deve ser preenchido")
	@Size(min = 8, max = 50 , message = "Senha do usuario deve ter entre 8 e 50 caracteres")
	private String senhaUsuario;

	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroUsuario;

	@Digits(integer=4, fraction=0, message = "Confirmação do usuario deve ser preenchido com um valor inteiro")
	private Integer confirmaUsuario;

	@OneToMany
	@JoinColumn(name = "idPerguntaRecuperacao")
	private Collection<PerguntaRecuperacaoCadastro> perguntaRecuperacaoCadastros = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "idPontoAcesso")
	private Collection<PontoAcesso> pontoAcessos = new ArrayList<>();

}
