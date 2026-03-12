package com.senai.estoque.gerenciamento_estoque.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.estoque.gerenciamento_estoque.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByMaterial_IdOrderByDataHoraDesc(Long materialId);
}