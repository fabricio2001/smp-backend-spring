import java.util.Collection;

public class Usuario {

	private String idUsuario;

	private String nomeUsuario;

	private String sobrenomeUsuario;

	private String cpfUsuario;

	private String emailUsuario;

	private String telefoneUsuario;

	private String senhaUsuario;

	private Date dataCadastroUsuario;

	private Integer confirmaUsuario;

	private Collection<PerguntaRecuperacaoCadastro> perguntaRecuperacaoCadastro;

	private Collection<PontoAcesso> pontoAcesso;

}