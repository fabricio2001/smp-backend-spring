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
@EqualsAndHashCode(of = {"idAssinatura"})
public class Assinatura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idAssinatura;

	private String enderecoAssinatura;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAssinatura;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataRenovacao;

	private Integer statusAssinatura;

	private Integer confirmaPagamentoAssinatura;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "planoAssinatura_id")
	private PlanoAssinatura planoAssinatura;

}
