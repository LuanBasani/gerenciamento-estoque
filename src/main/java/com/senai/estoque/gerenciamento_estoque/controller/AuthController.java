package com.senai.estoque.gerenciamento_estoque.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.senai.estoque.gerenciamento_estoque.service.FuncionarioService;

@Controller
public class AuthController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String nif,
                        @RequestParam String senha,
                        HttpSession session,
                        Model model) {
        boolean credenciaisOk = funcionarioService.validarLogin(nif, senha);
        if (!credenciaisOk) {
            model.addAttribute("erro", "NIF ou senha inválidos.");
            return "auth/login";
        }
        session.setAttribute("usuarioLogado", true);
        session.setAttribute("nif", nif);
        return "redirect:/app";
    }

    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "auth/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastro(@RequestParam String nome,
                           @RequestParam String nif,
                           @RequestParam String senha,
                           Model model) {
        String erro = funcionarioService.cadastrar(nome, nif, senha);
        if (erro != null) {
            model.addAttribute("erro", erro);
            return "auth/cadastro";
        }
        model.addAttribute("sucesso", "Conta criada com sucesso! Faça login.");
        return "auth/cadastro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}