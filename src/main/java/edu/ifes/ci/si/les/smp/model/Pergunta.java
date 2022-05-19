package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;

import javax.persistence.*;

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

	private String descricaoPergunta;

}
