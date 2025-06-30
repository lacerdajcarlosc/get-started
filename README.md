
# 📋 Projeto: Cadastro de Usuários em Java

Este é um projeto de linha de comando para cadastro de usuários, desenvolvido em Java, com funcionalidades de criação, atualização, exclusão, listagem e exportação para arquivo `.csv`.

---

## 📦 Funcionalidades

- [x] Cadastrar novo usuário
- [x] Atualizar dados de usuário
- [x] Remover usuário pelo ID
- [x] Buscar usuário por ID
- [x] Listar todos os usuários
- [x] Validação de entrada (nome, e-mail e data de nascimento)
- [x] Cálculo automático de idade
- [x] Exportação da tabela de usuários para `.csv` (abre no Excel)

---

## 🧪 Tecnologias utilizadas

- Java 17+
- JDK padrão (sem frameworks externos)
- Paradigma orientado a objetos
- Arquivos `.csv` com `FileWriter` e `BufferedWriter`

---

## ▶️ Como executar

### 1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-projeto.git
cd seu-projeto
```

### 2. Compile o projeto:

```bash
javac -d out src/**/*.java
```

### 3. Execute o programa:

```bash
java -cp out Main
```

---

## 🧾 Exemplo de uso no terminal:

```
Bem vindo ao cadastro de usuarios. Selecione a opcao desejada
1 - Cadastrar
2 - Atualizar
3 - Excluir
4 - Buscar por identificador
5 - Listar
6 - Sair
```

---

## 📤 Exportação para Excel

Após listar os usuários (`opção 5`), um arquivo `usuarios.csv` será criado automaticamente no diretório do projeto.  
Você pode abrir esse arquivo diretamente no **Excel** ou **Google Sheets**.

---

## 📂 Estrutura básica

```
src/
│
├── dao/                   # Simulação de persistência (DAO)
├── exception/             # Exceções customizadas
├── model/                 # Classe de domínio UserModel
├── validator/             # Validador de entrada (UserValidator)
├── Main.java              # Classe principal com menu
└── utils/
    └── UserTablePrinter.java   # Impressão em formato tabular
    └── UserExporter.java       # Exportação para CSV
```

---

## 🛠 Melhorias futuras

- [ ] Integração com banco de dados real (SQLite, PostgreSQL etc.)
- [ ] Interface gráfica (JavaFX ou Swing)
- [ ] Interface web (Spring Boot)
- [ ] Upload e leitura de usuários via arquivo CSV

---

## 🤝 Contribuição

Pull requests são bem-vindos! Para mudanças maiores, abra uma issue antes para discutir o que você gostaria de mudar.

---

## 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
