package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idPlanoAssinatura"})
public class PlanoAssinatura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idPlanoAssinatura;

	private String descricaoPlanoAssinatura;

	private Integer duracaoPlanoAssinatura;

	private Float valorPlanoAssinatura;

}
