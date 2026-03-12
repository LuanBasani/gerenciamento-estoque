# Sistema de Gerenciamento de Estoque e Ativos Patrimoniais

Sistema desenvolvido para a unidade escolar do SENAI-SP com o objetivo de controlar materiais de laboratórios, salas de aula e demais áreas, além de gerenciar os ativos patrimoniais da instituição.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Thymeleaf**
- **PostgreSQL**
- **Maven**

---

## ▶️ Como Rodar o Projeto

### Pré-requisitos
- Java JDK 17 ou 21
- PostgreSQL instalado e rodando
- VS Code com Extension Pack for Java e Spring Boot Extension Pack

### Configuração do banco
Crie o banco de dados:
```sql
CREATE DATABASE estoque_senai;
```

Configure o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_senai
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Rodando
```bash
./mvnw spring-boot:run
```

Acesse: `http://localhost:8080`

### Primeiro acesso
Insira um registro na tabela de funcionários autorizados para conseguir se cadastrar:
```sql
INSERT INTO funcionarios_autenticados (nome, nif, ativo) VALUES ('Seu Nome', '12345', true);
```

---

## 📋 Requisitos Funcionais (RF)

| ID | Descrição |
|----|-----------|
| RF01 | O sistema deve permitir o cadastro de funcionários mediante validação por NIF e nome na lista de autorizados |
| RF02 | O sistema deve permitir login de funcionários cadastrados mediante NIF e senha |
| RF03 | O sistema deve permitir o logout do funcionário |
| RF04 | O sistema deve permitir cadastrar, listar e excluir categorias de materiais |
| RF05 | O sistema deve permitir cadastrar, listar e excluir materiais vinculados a uma categoria |
| RF06 | O sistema deve controlar a quantidade em estoque de cada material automaticamente |
| RF07 | O sistema deve permitir registrar movimentações de entrada e saída de estoque |
| RF08 | O sistema deve impedir saída de estoque quando a quantidade for insuficiente |
| RF09 | O sistema deve exibir o histórico de movimentações |
| RF10 | O sistema deve permitir cadastrar, listar e excluir ativos patrimoniais |
| RF11 | O sistema deve registrar número de patrimônio, localização e estado de cada ativo |
| RF12 | O sistema deve proteger todas as rotas internas, redirecionando para login caso não autenticado |

---

## 📋 Requisitos Não Funcionais (RNF)

| ID | Descrição |
|----|-----------|
| RNF01 | O sistema deve ser desenvolvido em Java com Spring Boot |
| RNF02 | O sistema deve utilizar banco de dados relacional PostgreSQL |
| RNF03 | O sistema deve seguir a identidade visual do SENAI-SP (cores, tipografia e logo) |
| RNF04 | A interface deve ser responsiva e compatível com navegadores modernos |
| RNF05 | O sistema deve utilizar sessão HTTP para controle de autenticação |
| RNF06 | O sistema deve utilizar Thymeleaf como template engine |
| RNF07 | O código deve seguir o padrão de camadas: Model, Repository, Service e Controller |
| RNF08 | O sistema deve validar duplicidade de NIF no cadastro de funcionários |
| RNF09 | O sistema deve validar duplicidade de número de patrimônio no cadastro de ativos |
| RNF10 | O sistema deve exibir mensagens de erro amigáveis ao usuário |

---

## 🗄️ Schema do Banco de Dados

```
funcionarios_autenticados     funcionarios
├── id (PK)                   ├── id (PK)
├── nome                      ├── nome
├── nif                       ├── nif (unique)
└── ativo                     ├── senha
                              └── ativo

categorias                    materiais
├── id (PK)                   ├── id (PK)
├── nome (unique)             ├── nome
└── descricao                 ├── descricao
                              ├── quantidade_estoque
                              └── categoria_id (FK → categorias)

movimentacoes                 ativos_patrimoniais
├── id (PK)                   ├── id (PK)
├── material_id (FK)          ├── nome
├── tipo (ENTRADA/SAIDA)      ├── numero_patrimonio (unique)
├── quantidade                ├── descricao
├── observacao                ├── localizacao
└── data_hora                 └── estado
```

---

## 🔗 Rotas da Aplicação

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | / | Página inicial |
| GET | /login | Tela de login |
| POST | /login | Processar login |
| GET | /cadastro | Tela de cadastro |
| POST | /cadastro | Processar cadastro |
| GET | /logout | Encerrar sessão |
| GET | /app | Área interna |
| GET | /categorias | Listar categorias |
| POST | /categorias/salvar | Salvar categoria |
| GET | /categorias/deletar/{id} | Excluir categoria |
| GET | /materiais | Listar materiais |
| POST | /materiais/salvar | Salvar material |
| GET | /materiais/deletar/{id} | Excluir material |
| GET | /movimentacoes | Listar movimentações |
| POST | /movimentacoes/salvar | Registrar movimentação |
| GET | /ativos | Listar ativos patrimoniais |
| POST | /ativos/salvar | Salvar ativo |
| GET | /ativos/deletar/{id} | Excluir ativo |

---

## 👨‍💻 Desenvolvido por Enzo Basani

Projeto desenvolvido como Situação de Aprendizagem — Back-End  
**SENAI-SP**
