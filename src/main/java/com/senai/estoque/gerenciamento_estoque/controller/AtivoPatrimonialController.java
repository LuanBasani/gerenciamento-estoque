package com.senai.estoque.gerenciamento_estoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.senai.estoque.gerenciamento_estoque.service.AtivoPatrimonialService;

@Controller
@RequestMapping("/ativos")
public class AtivoPatrimonialController {

    @Autowired
    private AtivoPatrimonialService ativoService;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("ativos", ativoService.listar());
        return "ativos/index";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String nome,
                         @RequestParam String numeroPatrimonio,
                         @RequestParam String descricao,
                         @RequestParam String localizacao,
                         @RequestParam String estado,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        String erro = ativoService.salvar(nome, numeroPatrimonio, descricao, localizacao, estado);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("ativos", ativoService.listar());
            return "ativos/index";
        }
        return "redirect:/ativos";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        ativoService.deletar(id);
        return "redirect:/ativos";
    }
}