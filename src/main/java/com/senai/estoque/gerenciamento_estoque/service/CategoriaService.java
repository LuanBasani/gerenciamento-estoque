package com.senai.estoque.gerenciamento_estoque.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.estoque.gerenciamento_estoque.model.Categoria;
import com.senai.estoque.gerenciamento_estoque.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public String salvar(String nome, String descricao) {
        if (categoriaRepository.existsByNome(nome)) {
            return "Já existe uma categoria com esse nome.";
        }
        Categoria c = new Categoria();
        c.setNome(nome);
        c.setDescricao(descricao);
        categoriaRepository.save(c);
        return null;
    }

    public String deletar(Long id) {
    try {
        categoriaRepository.deleteById(id);
        return null;
    } catch (Exception e) {
        return "Não é possível excluir uma categoria que possui materiais vinculados.";
    }
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void atualizar(Long id, String nome, String descricao) {
        Categoria c = categoriaRepository.findById(id).orElse(null);
        if (c != null) {
            c.setNome(nome);
            c.setDescricao(descricao);
            categoriaRepository.save(c);
        }
    }
}