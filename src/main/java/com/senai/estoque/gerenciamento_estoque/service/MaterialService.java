package com.senai.estoque.gerenciamento_estoque.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.estoque.gerenciamento_estoque.model.Material;
import com.senai.estoque.gerenciamento_estoque.repository.CategoriaRepository;
import com.senai.estoque.gerenciamento_estoque.repository.MaterialRepository;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Material> listar() {
        return materialRepository.findAll();
    }

    public String salvar(String nome, String descricao, Integer quantidade, Long categoriaId) {
        var categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (categoria == null) return "Categoria não encontrada.";
        Material m = new Material();
        m.setNome(nome);
        m.setDescricao(descricao);
        m.setQuantidadeEstoque(quantidade);
        m.setCategoria(categoria);
        materialRepository.save(m);
        return null;
    }

    public String deletar(Long id) {
        try {
            materialRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            return "Não é possível excluir um material que possui movimentações registradas.";
        }
    }

    public Material buscarPorId(Long id) {
        return materialRepository.findById(id).orElse(null);
    }

    public void atualizar(Long id, String nome, String descricao, Long categoriaId) {
        Material m = materialRepository.findById(id).orElse(null);
        var categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (m != null && categoria != null) {
            m.setNome(nome);
            m.setDescricao(descricao);
            m.setCategoria(categoria);
            materialRepository.save(m);
        }
    }
}