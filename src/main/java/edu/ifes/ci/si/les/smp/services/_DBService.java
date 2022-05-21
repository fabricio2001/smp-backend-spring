package edu.ifes.ci.si.les.smp.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import edu.ifes.ci.si.les.smp.model.Assinatura;
import edu.ifes.ci.si.les.smp.model.PlanoAssinatura;
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
        
        PlanoAssinatura planoAssinatura1 = new PlanoAssinatura(null, "1111", 11, 11.1f);
        PlanoAssinatura planoAssinatura2 = new PlanoAssinatura(null, "2222", 22, 22.2f);
        
        planoAssinaturaRepository.saveAll(Arrays.asList(planoAssinatura1, planoAssinatura2));
    }
}
