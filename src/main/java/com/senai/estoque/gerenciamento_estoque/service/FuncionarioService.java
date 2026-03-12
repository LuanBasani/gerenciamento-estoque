package com.senai.estoque.gerenciamento_estoque.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.estoque.gerenciamento_estoque.model.Funcionario;
import com.senai.estoque.gerenciamento_estoque.repository.FuncionarioAutenticadoRepository;
import com.senai.estoque.gerenciamento_estoque.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioAutenticadoRepository autenticadoRepository;

    // Valida login: busca pelo NIF e compara a senha
    public boolean validarLogin(String nif, String senha) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByNif(nif);
        return funcionario.isPresent() && funcionario.get().getSenha().equals(senha);
    }

    // Cadastro: só permite se o NIF+nome estiver na lista branca
    public String cadastrar(String nome, String nif, String senha) {
        boolean autorizado = autenticadoRepository.existsByNifAndNomeAndAtivoTrue(nif, nome);
        if (!autorizado) {
            return "NIF e nome não estão autorizados para cadastro.";
        }
        if (funcionarioRepository.existsByNif(nif)) {
            return "Já existe uma conta com esse NIF.";
        }
        Funcionario novo = new Funcionario();
        novo.setNome(nome);
        novo.setNif(nif);
        novo.setSenha(senha);
        funcionarioRepository.save(novo);
        return null; // null = sucesso
    }
}