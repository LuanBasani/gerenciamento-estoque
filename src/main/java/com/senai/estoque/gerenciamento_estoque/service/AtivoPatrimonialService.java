package com.senai.estoque.gerenciamento_estoque.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.estoque.gerenciamento_estoque.model.AtivoPatrimonial;
import com.senai.estoque.gerenciamento_estoque.repository.AtivoPatrimonialRepository;

@Service
public class AtivoPatrimonialService {

    @Autowired
    private AtivoPatrimonialRepository ativoRepository;

    public List<AtivoPatrimonial> listar() {
        return ativoRepository.findAll();
    }

    public String salvar(String nome, String numeroPatrimonio, String descricao, String localizacao, String estado) {
        if (ativoRepository.existsByNumeroPatrimonio(numeroPatrimonio)) {
            return "Já existe um ativo com esse número de patrimônio.";
        }
        AtivoPatrimonial a = new AtivoPatrimonial();
        a.setNome(nome);
        a.setNumeroPatrimonio(numeroPatrimonio);
        a.setDescricao(descricao);
        a.setLocalizacao(localizacao);
        a.setEstado(estado);
        ativoRepository.save(a);
        return null;
    }

    public void deletar(Long id) {
        ativoRepository.deleteById(id);
    }

    public AtivoPatrimonial buscarPorId(Long id) {
        return ativoRepository.findById(id).orElse(null);
    }

    public void atualizar(Long id, String nome, String descricao, String localizacao, String estado) {
        AtivoPatrimonial a = ativoRepository.findById(id).orElse(null);
        if (a != null) {
            a.setNome(nome);
            a.setDescricao(descricao);
            a.setLocalizacao(localizacao);
            a.setEstado(estado);
            ativoRepository.save(a);
        }
    }
}