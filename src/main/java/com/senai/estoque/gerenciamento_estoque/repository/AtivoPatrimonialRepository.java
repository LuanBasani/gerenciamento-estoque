package com.senai.estoque.gerenciamento_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.estoque.gerenciamento_estoque.model.AtivoPatrimonial;

public interface AtivoPatrimonialRepository extends JpaRepository<AtivoPatrimonial, Long> {
    boolean existsByNumeroPatrimonio(String numeroPatrimonio);
}