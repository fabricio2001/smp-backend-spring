package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Column(length = 50)
	@NotBlank(message = "Descrição do plano deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Descrição do plano deve ter no minimo 10 letras")
	private String descricaoPlanoAssinatura;

	@Digits(integer=4, fraction=0, message = "Duração do plano da assinatura deve ser preenchido com um valor inteiro")
	private Integer duracaoPlanoAssinatura;

	@Min(value = 1, message = "Valor do plano deve ser maior que zero")
    @NotNull(message = "Valor do plano deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor do plano deve ser preenchido com dígitos")
	private Float valorPlanoAssinatura;

}
