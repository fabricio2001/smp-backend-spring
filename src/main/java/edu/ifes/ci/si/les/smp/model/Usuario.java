package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"idUsuario"})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idUsuario;

	private String nomeUsuario;

	private String sobrenomeUsuario;

	private String cpfUsuario;

	private String emailUsuario;

	private String telefoneUsuario;

	private String senhaUsuario;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroUsuario;

	private Integer confirmaUsuario;

	@OneToMany
	@JoinColumn(name = "perguntaRecuperacaoCadastro_id")
	private Collection<PerguntaRecuperacaoCadastro> perguntaRecuperacaoCadastros = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "pontoAcessos_id")
	private Collection<PontoAcesso> pontoAcessos = new ArrayList<>();

}
