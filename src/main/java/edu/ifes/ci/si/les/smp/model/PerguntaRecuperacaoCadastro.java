package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idPerguntaRecuperacao"})
public class PerguntaRecuperacaoCadastro implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idPerguntaRecuperacao;

	private String respostaPerguntaRecuperacao;

	private Integer statusPerguntaRecuperacao;

	private Date dataCadastroPergunta;

	private Usuario usuario;

	private Pergunta pergunta;

}
