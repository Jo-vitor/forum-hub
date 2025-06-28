package forum.hub.api.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    public DadosDetalhamentoTopico validarESalvar(DadosNovoTopico dados){
        var duplicado = repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if(duplicado){
            throw new RuntimeException("Titulo e mensagem do tópico já existem");
        }

        Topico topico = new Topico(dados);
        repository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico validarEAtualizar(DadosAtualizarTopico dados, Long id){
        var topicoExiste = repository.findById(id);

        if(topicoExiste.isEmpty()){
            throw new RuntimeException("Tópico inexistente");
        }

        var topico = topicoExiste.get();

        topico.atualizarInformacoes(dados);

        return new DadosDetalhamentoTopico(topico);
    }

    public void validarEDeletar(Long id){
        var topico = repository.existsById(id);

        if(!topico){
            throw new RuntimeException("Tópico inexistente");
        }

        repository.deleteById(id);
    }
}
