package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	private Date dataCompFamilia;

	private Familia familia;

	private Senha senha;

	private GrupoSenha grupoSenha;

}
