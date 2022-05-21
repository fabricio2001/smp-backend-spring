package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idSenha"})
public class Senha implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idSenha;

	@Column(length = 50)
	@NotBlank(message = "Nome da senha deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Nome da senha deve ter no minimo 10 letras")
	private String nomeSenha;

	@Column(length = 50)
	@NotBlank(message = "Url da senha deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Url da senha deve ter no minimo 10 letras")
	private String urlSenha;

	@Column(length = 50)
	@NotBlank(message = "Email da senha deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Email da senha deve ter no minimo 10 letras")
	private String emailSenha;

	@Column(length = 50)
	@NotBlank(message = "Conta da senha deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Conta da senha deve ter no minimo 10 letras")
	private String contaSenha;
	
	@Column(length = 50)
	@NotBlank(message = "A senha deve ser preenchido")
	@Size(min = 10, max = 50 , message = "A senha deve ter no minimo 10 letras")
	private String senhaSenha;

	private String adicionalSenha;

	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroSenha;

	@NotNull(message = "O Grupo da senha deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "grupoSenha_id")
	private GrupoSenha grupoSenha;

}
