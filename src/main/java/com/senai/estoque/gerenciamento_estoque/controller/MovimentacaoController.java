package com.senai.estoque.gerenciamento_estoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.senai.estoque.gerenciamento_estoque.service.MaterialService;
import com.senai.estoque.gerenciamento_estoque.service.MovimentacaoService;

@Controller
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        model.addAttribute("movimentacoes", movimentacaoService.listar());
        model.addAttribute("materiais", materialService.listar());
        return "movimentacoes/index";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long materialId,
                         @RequestParam String tipo,
                         @RequestParam Integer quantidade,
                         @RequestParam(required = false) String observacao,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogado") == null) return "redirect:/login";
        String erro = movimentacaoService.registrar(materialId, tipo, quantidade, observacao);
        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("movimentacoes", movimentacaoService.listar());
            model.addAttribute("materiais", materialService.listar());
            return "movimentacoes/index";
        }
        return "redirect:/movimentacoes";
    }
}