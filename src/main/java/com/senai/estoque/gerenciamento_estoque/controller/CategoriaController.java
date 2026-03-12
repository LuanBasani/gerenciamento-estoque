package com.senai.estoque.gerenciamento_estoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.senai.estoque.gerenciamento_estoque.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias/index";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                         @RequestParam String descricao,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        String erro = categoriaService.salvar(nome, descricao);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("categorias", categoriaService.listar());
            return "categorias/index";
        }
        return "redirect:/categorias";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, HttpSession session, Model model) {
    if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
    String erro = categoriaService.deletar(id);
    if (erro != null) {
        model.addAttribute("erro", erro);
        model.addAttribute("categorias", categoriaService.listar());
        return "categorias/index";
    }
    return "redirect:/categorias";
    }
}