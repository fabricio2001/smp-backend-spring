package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idAssinatura"})
public class Assinatura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idAssinatura;

	private String enderecoAssinatura;

	private Date dataAssinatura;

	private Date dataRenovacao;

	private Integer statusAssinatura;

	private Integer confirmaPagamentoAssinatura;

	private Usuario usuario;

	private PlanoAssinatura planoAssinatura;

}
