package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idExportacao"})
public class Exportacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idExportacao;
	
	@NotNull(message = "Data de exportação deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataExportacao;

	@NotNull(message = "Assinatura deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "assinatura_id")
	private Assinatura assinatura;

	@NotNull(message = "O grupo de senha deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "grupoSenha_id")
	private GrupoSenha grupoSenha;

}
