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
@EqualsAndHashCode(of = {"idGrupo"})
public class GrupoSenha implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idGrupo;

	@Column(length = 50)
	@NotBlank(message = "Nome do grupo deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Nome do grupo deve ter no minimo 10 letras")
	private String nomeGrupo;

	@Column(length = 50)
	@NotBlank(message = "Descrição do grupo deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Descrição do grupo deve ter no minimo 10 letras")
	private String descricaoGrupo;

	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroGrupo;

	@NotNull(message = "O usuario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
