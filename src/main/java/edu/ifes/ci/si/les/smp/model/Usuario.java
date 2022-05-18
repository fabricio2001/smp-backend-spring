package edu.ifes.ci.si.les.smp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	private Date dataCadastroUsuario;

	private Integer confirmaUsuario;

}
