package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	private String nomeFamilia;

	private String sobrenomeFamilia;

	private String cpfFamilia;

	private String emailFamilia;

	private String telefoneFamilia;

	private Date dataCadastroFamilia;

	private Integer confirmaFamilia;

	private Usuario usuario;

}
