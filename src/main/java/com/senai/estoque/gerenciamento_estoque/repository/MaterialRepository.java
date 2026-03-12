package com.senai.estoque.gerenciamento_estoque.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.estoque.gerenciamento_estoque.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCategoria_Id(Long categoriaId);
}