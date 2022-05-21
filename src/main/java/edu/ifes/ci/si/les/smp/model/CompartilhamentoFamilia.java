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
@EqualsAndHashCode(of = {"idCompFamilia"})
public class CompartilhamentoFamilia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idCompFamilia;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCompFamilia;

	@ManyToOne
	@JoinColumn(name = "familia_id")
	private Familia familia;

	@ManyToOne
	@JoinColumn(name = "grupoSenha_id")
	private GrupoSenha grupoSenha;

}
