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
@EqualsAndHashCode(of = {"idPontoAcesso"})
public class PontoAcesso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idPontoAcesso;

	@Column(length = 50)
	@NotBlank(message = "Novo dispositivo de acesso deve ser preenchido")
	@Size(min = 10, max = 50 , message = "Novo dispositivo de acesso deve ter no minimo 10 letras")
	private String novoDispositivoPontoAcesso;

	@NotNull(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCadastroPontoAcesso;

	@NotNull(message = "O usuario deve ser preenchido")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
