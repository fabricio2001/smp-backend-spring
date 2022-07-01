package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idPergunta"})
public class Pergunta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idPergunta;

	@Column(length = 50)
	@NotBlank(message = "Descrição da pergunta deve ser preenchido")
	@Size(min = 1, max = 50 , message = "Descrição da pergunta deve ter entre 1 e 50 letras")
	private String descricaoPergunta;

}
