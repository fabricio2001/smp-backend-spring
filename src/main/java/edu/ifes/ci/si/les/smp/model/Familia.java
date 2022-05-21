package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
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
@EqualsAndHashCode(of = {"idFamilia"})
public class Familia implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idFamilia;

	@Column(length = 50)
	@NotBlank(message = "Nome da familia deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Nome da familia deve ter no minimo 10 letras")
	private String nomeFamilia;

	@Column(length = 50)
	@NotBlank(message = "Sobrenome da familia deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Sobrenome da familia deve ter no minimo 10 letras")
	private String sobrenomeFamilia;

	@Column(length = 50)
    @NotBlank(message = "CPF da familia deve ser preenchido")
    @Size(min = 2, max = 50, message = "CPF da famila deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF da familia deve seguir o padrão NNN.NNN.NNN-NN")
	private String cpfFamilia;

	@Column(length = 50)
    @NotBlank(message = "Email da familia deve ser preenchido")
    @Size(min = 2, max = 50, message = "Email da familia  deve ter entre 2 e 50 letras")
	private String emailFamilia;

	@Column(length = 50)
    @NotBlank(message = "Telefone da familia deve ser preenchido")
    @Size(min = 2, max = 50, message = "Telefone da familia  deve ter entre 2 e 50 letras")
	private String telefoneFamilia;
	
	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroFamilia;

	@Digits(integer=4, fraction=0, message = "Status do pagamento da assinatura deve ser preenchido com um valor inteiro")
	private Integer confirmaFamilia;

	@NotNull(message = "O usuario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
