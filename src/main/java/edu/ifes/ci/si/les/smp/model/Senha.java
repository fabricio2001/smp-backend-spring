package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	private String nomeSenha;

	private String urlSenha;

	private String emailSenha;

	private String contaSenha;

	private String senhaSenha;

	private String adicionalSenha;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroSenha;

	@ManyToOne
	@JoinColumn(name = "grupoSenha_id")
	private GrupoSenha grupoSenha;

}
