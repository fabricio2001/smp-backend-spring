package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

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

	@Column(length = 50)
	@NotBlank(message = "Endereço deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Endereço deve ter no minimo 10 letras")
	private String enderecoAssinatura;

	@NotNull(message = "Data de assinatura deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAssinatura;

	@NotNull(message = "Data de renovação deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataRenovacao;

	@Digits(integer=4, fraction=0, message = "Status da assinatura deve ser preenchido com um valor inteiro")
	private Integer statusAssinatura;

	@Digits(integer=4, fraction=0, message = "Status do pagamento da assinatura deve ser preenchido com um valor inteiro")
	private Integer confirmaPagamentoAssinatura;

	@NotNull(message = "O Usuario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@NotNull(message = "O Plano deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "planoAssinatura_id")
	private PlanoAssinatura planoAssinatura;

}
