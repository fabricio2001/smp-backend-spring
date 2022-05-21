package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Column(length = 50)
	@NotBlank(message = "Resposta da pergunta deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Resposta da pergunta deve ter no minimo 10 letras")
	private String respostaPerguntaRecuperacao;

	@Column(length = 50)
	@NotBlank(message = "Status da pergunta deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Status da pergunta deve ter no minimo 10 letras")
	private Integer statusPerguntaRecuperacao;
	
	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroPergunta;

	@NotNull(message = "O usuario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@NotNull(message = "A pergunta deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "pergunta_id")
	private Pergunta pergunta;

}
