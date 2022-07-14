package edu.ifes.ci.si.les.smp.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.Assinatura;
import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.model.Familia;
import edu.ifes.ci.si.les.smp.model.GrupoSenha;
import edu.ifes.ci.si.les.smp.model.Pergunta;
import edu.ifes.ci.si.les.smp.model.PerguntaRecuperacaoCadastro;
import edu.ifes.ci.si.les.smp.model.PlanoAssinatura;
import edu.ifes.ci.si.les.smp.model.PontoAcesso;
import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.model.Senha;
import edu.ifes.ci.si.les.smp.model.Usuario;
import edu.ifes.ci.si.les.smp.repositories.AssinaturaRepository;
import edu.ifes.ci.si.les.smp.repositories.CompartilhamentoFamiliaRepository;
import edu.ifes.ci.si.les.smp.repositories.ExportacaoRepository;
import edu.ifes.ci.si.les.smp.repositories.FamiliaRepository;
import edu.ifes.ci.si.les.smp.repositories.GrupoSenhaRepository;
import edu.ifes.ci.si.les.smp.repositories.PerguntaRecuperacaoCadastroRepository;
import edu.ifes.ci.si.les.smp.repositories.PerguntaRepository;
import edu.ifes.ci.si.les.smp.repositories.PlanoAssinaturaRepository;
import edu.ifes.ci.si.les.smp.repositories.PontoAcessoRepository;
import edu.ifes.ci.si.les.smp.repositories.RecuperacaoSenhaRepository;
import edu.ifes.ci.si.les.smp.repositories.SenhaRepository;
import edu.ifes.ci.si.les.smp.repositories.UsuarioRepository;

@Service
public class _DBService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;
    @Autowired
    private CompartilhamentoFamiliaRepository compartilhamentoFamiliaRepository;
    @Autowired
    private ExportacaoRepository exportacaoRepository;
    @Autowired
    private FamiliaRepository familiaRepository;
    @Autowired
    private GrupoSenhaRepository grupoSenhaRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private PerguntaRecuperacaoCadastroRepository perguntaRecuperacaoCadastroRepository;
    @Autowired
    private PlanoAssinaturaRepository planoAssinaturaRepository;
    @Autowired
    private PontoAcessoRepository pontoAcessoRepository;
    @Autowired
    private RecuperacaoSenhaRepository recuperacaoSenhaRepository;
    @Autowired
    private SenhaRepository senhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public void instantiateTestDatabase() throws ParseException, IOException {

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        PlanoAssinatura planoAssinatura1 = new PlanoAssinatura(null, "free", 0, 0f);
        PlanoAssinatura planoAssinatura2 = new PlanoAssinatura(null, "premium", 12, 300f);
        planoAssinaturaRepository.saveAll(Arrays.asList(planoAssinatura1, planoAssinatura2));

        Pergunta pergunta1 = new Pergunta(null, "Nome do cachorro?");
        Pergunta pergunta2 = new Pergunta(null, "Nome do gato?");
        perguntaRepository.saveAll(Arrays.asList(pergunta1, pergunta2));

        Usuario usuario1 = new Usuario(null, "Fabricio", "Louzada", "123.456.789-10", "fabricio@gmail.com", "123456789", "123456789", sdf.parse("2020-04-12"), 1, null, null);
        Usuario usuario2 = new Usuario(null, "Fabio", "Colodette", "123.456.789-10", "fabio@gmail.com", "123456789", "123456789", sdf.parse("2020-04-12"), 1, null, null);
        Usuario usuario3 = new Usuario(null, "Eduardo", "Vascocelo", "123.456.789-10", "eduardo@gmail.com", "123456789", "123456789", sdf.parse("2020-04-12"), 1, null, null);
        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));

        PerguntaRecuperacaoCadastro perguntaRecuperacaoCadastro1 = new PerguntaRecuperacaoCadastro(null, "Pitoco", 1, sdf.parse("2020-04-12"), usuario1, pergunta1);
        PerguntaRecuperacaoCadastro perguntaRecuperacaoCadastro2 = new PerguntaRecuperacaoCadastro(null, "Julia", 1, sdf.parse("2020-04-12"), usuario1, pergunta2);
        perguntaRecuperacaoCadastroRepository.saveAll(Arrays.asList(perguntaRecuperacaoCadastro1, perguntaRecuperacaoCadastro2));

        RecuperacaoSenha recuperacaoSenha1 = new RecuperacaoSenha(null, sdf.parse("2020-04-12"), true, usuario1);
        RecuperacaoSenha recuperacaoSenha2 = new RecuperacaoSenha(null, sdf.parse("2020-04-12"), true, usuario1);
        recuperacaoSenhaRepository.saveAll(Arrays.asList(recuperacaoSenha1, recuperacaoSenha2));

        PontoAcesso pontoAcesso1 = new PontoAcesso(null, "MAC-020503", sdf.parse("2020-04-12"), usuario1);
        PontoAcesso pontoAcesso2 = new PontoAcesso(null, "MAC-412593", sdf.parse("2020-04-12"), usuario1);
        pontoAcessoRepository.saveAll(Arrays.asList(pontoAcesso1, pontoAcesso2));

        GrupoSenha grupoSenha1 = new GrupoSenha(null, "Redes Sociais", "Redes Sociais", sdf.parse("2020-04-12"), usuario1);
        GrupoSenha grupoSenha2 = new GrupoSenha(null, "Jogos", "Jogos", sdf.parse("2020-04-12"), usuario1);
        GrupoSenha grupoSenha3 = new GrupoSenha(null, "Redes Sociais", "Redes Sociais", sdf.parse("2020-04-12"), usuario3);
        GrupoSenha grupoSenha4 = new GrupoSenha(null, "Jogos", "Jogos", sdf.parse("2020-04-12"), usuario3);
        GrupoSenha grupoSenha5 = new GrupoSenha(null, "Email", "Email", sdf.parse("2020-04-12"), usuario3);
        grupoSenhaRepository.saveAll(Arrays.asList(grupoSenha1, grupoSenha2, grupoSenha3, grupoSenha4, grupoSenha5));

        Senha senha1 = new Senha(null, "facebook", "facebook.com", "fabricio@gmail.com", "fabricio", "159753", null, sdf.parse("2020-04-12"), grupoSenha1);
        Senha senha2 = new Senha(null, "LoL", "lol.com", "fabricio@gmail.com", "fabricio", "159753", null, sdf.parse("2020-04-12"), grupoSenha2);
        senhaRepository.saveAll(Arrays.asList(senha1, senha2));
    
        Assinatura assinatura1 = new Assinatura(null, "usuario 1", sdf.parse("2020-04-12"), sdf.parse("2021-04-12"), 1, 0, usuario1, planoAssinatura1);
        Assinatura assinatura2 = new Assinatura(null, "usuario 2", sdf.parse("2020-04-12"), sdf.parse("2021-04-12"), 1, 1, usuario2, planoAssinatura2);
        Assinatura assinatura3 = new Assinatura(null, "usuario 3", sdf.parse("2020-04-12"), sdf.parse("2021-04-12"), 1, 1, usuario3, planoAssinatura2);
        assinaturaRepository.saveAll(Arrays.asList(assinatura1, assinatura2, assinatura3));

        Exportacao exportacao1 = new Exportacao(null, sdf.parse("2020-04-12"), assinatura2, grupoSenha1);
        Exportacao exportacao2 = new Exportacao(null, sdf.parse("2020-05-12"), assinatura2, grupoSenha2);
        exportacaoRepository.saveAll(Arrays.asList(exportacao1, exportacao2));

        Familia familia1 = new Familia(null, "fabricio", "louzada", "123.654.789-10", "fabricio@gmail.com", "987654321", sdf.parse("2020-04-12"), 1, usuario2);
        Familia familia2 = new Familia(null, "andre", "louzada", "123.654.789-11", "andre@gmail.com", "987654321", sdf.parse("2020-05-12"), 1, usuario2);
        familiaRepository.saveAll(Arrays.asList(familia1, familia2));

        CompartilhamentoFamilia compartilhamentoFamilia1 = new CompartilhamentoFamilia(null, sdf.parse("2020-04-12"), familia1, grupoSenha1);
        CompartilhamentoFamilia compartilhamentoFamilia2 = new CompartilhamentoFamilia(null, sdf.parse("2020-05-12"), familia2, grupoSenha2);
        compartilhamentoFamiliaRepository.saveAll(Arrays.asList(compartilhamentoFamilia1, compartilhamentoFamilia2));

    }
}
