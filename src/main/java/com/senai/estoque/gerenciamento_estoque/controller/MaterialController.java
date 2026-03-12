package com.senai.estoque.gerenciamento_estoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.senai.estoque.gerenciamento_estoque.service.CategoriaService;
import com.senai.estoque.gerenciamento_estoque.service.MaterialService;

@Controller
@RequestMapping("/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("materiais", materialService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "materiais/index";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                         @RequestParam String descricao,
                         @RequestParam Integer quantidade,
                         @RequestParam Long categoriaId,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        String erro = materialService.salvar(nome, descricao, quantidade, categoriaId);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("materiais", materialService.listar());
            model.addAttribute("categorias", categoriaService.listar());
            return "materiais/index";
        }
        return "redirect:/materiais";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, HttpSession session, Model model) {
    if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
    String erro = materialService.deletar(id);
    if (erro != null) {
        model.addAttribute("erro", erro);
        model.addAttribute("materiais", materialService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "materiais/index";
    }
    return "redirect:/materiais";
}
}