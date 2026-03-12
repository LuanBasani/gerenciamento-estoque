package com.senai.estoque.gerenciamento_estoque.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.estoque.gerenciamento_estoque.model.Movimentacao;
import com.senai.estoque.gerenciamento_estoque.model.Movimentacao.Tipo;
import com.senai.estoque.gerenciamento_estoque.repository.MaterialRepository;
import com.senai.estoque.gerenciamento_estoque.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public List<Movimentacao> listar() {
        return movimentacaoRepository.findAll();
    }

    public String registrar(Long materialId, String tipo, Integer quantidade, String observacao) {
        var material = materialRepository.findById(materialId).orElse(null);
        if (material == null) return "Material não encontrado.";
        if (tipo.equals("SAIDA") && material.getQuantidadeEstoque() < quantidade) {
            return "Estoque insuficiente para saída.";
        }
        Movimentacao mov = new Movimentacao();
        mov.setMaterial(material);
        mov.setTipo(Tipo.valueOf(tipo));
        mov.setQuantidade(quantidade);
        mov.setObservacao(observacao);
        movimentacaoRepository.save(mov);

        if (tipo.equals("ENTRADA")) {
            material.setQuantidadeEstoque(material.getQuantidadeEstoque() + quantidade);
        } else {
            material.setQuantidadeEstoque(material.getQuantidadeEstoque() - quantidade);
        }
        materialRepository.save(material);
        return null;
    }
}