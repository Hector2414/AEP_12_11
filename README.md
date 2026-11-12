
# 🔧 Metria — Sistema de Gestão de Manutenção Industrial

**Unicesumar — Engenharia de Software | 4º Semestre — 2026.2**

## 📋 Sobre o Projeto

O **Metria** é um sistema de gestão de manutenção industrial desenvolvido com o objetivo de centralizar e organizar informações relacionadas à manutenção de equipamentos.

A aplicação permite o gerenciamento de:

- 👷 Funcionários;
- 🏭 Equipamentos;
- 🔩 Peças;
- 📦 Estoque;
- 📝 Ordens de Serviço;
- 📚 Histórico de Manutenções;
- 💰 Custos e informações para indicadores de manutenção.

O sistema busca substituir controles fragmentados realizados em planilhas, documentos e registros manuais, proporcionando maior organização, rastreabilidade e apoio à tomada de decisões.

Além disso, os dados registrados no sistema servem como base para o acompanhamento de indicadores importantes da manutenção industrial, como:

- **MTBF — Mean Time Between Failures**;
- **MTTR — Mean Time To Repair**;
- custos das atividades de manutenção.

---

## 🌍 Alinhamento com os Objetivos de Desenvolvimento Sustentável

### 🏭 ODS 9 — Indústria, Inovação e Infraestrutura

O Metria contribui para a **ODS 9** ao utilizar tecnologia para apoiar a organização e a eficiência dos processos de manutenção industrial.

O sistema permite estruturar informações sobre:

- ativos industriais;
- falhas;
- intervenções;
- custos;
- desempenho dos equipamentos.

Dessa forma, os registros operacionais podem ser transformados em informações úteis para planejamento e tomada de decisões.

### ♻️ ODS 12 — Consumo e Produção Responsáveis

A relação com a **ODS 12** está diretamente ligada ao controle de peças e materiais utilizados nas manutenções.

O sistema permite:

- controlar o estoque de peças;
- definir estoque mínimo;
- registrar peças utilizadas em Ordens de Serviço;
- reduzir desperdícios;
- melhorar o planejamento de materiais.

---

# 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido utilizando:

| Tecnologia | Utilização |
|---|---|
| **Java** | Linguagem principal da aplicação |
| **Spring Boot** | Framework para desenvolvimento da aplicação |
| **Spring Web** | Desenvolvimento da API REST |
| **Spring Data JPA** | Persistência e acesso aos dados |
| **H2 Database** | Banco de dados em memória para o MVP |
| **Spring Validation** | Validação dos dados recebidos pela API |
| **Maven** | Gerenciamento de dependências e build |

---

# 🏗️ Arquitetura do Projeto

O sistema utiliza uma **Arquitetura em Camadas**, separando as responsabilidades da aplicação.

```text
CLIENTE / POSTMAN
        │
        ▼
   CONTROLLER
        │
        ▼
    SERVICE
        │
        ▼
   REPOSITORY
        │
        ▼
    DATABASE
```

A estrutura principal está organizada nos seguintes pacotes:

```text
model
repository
service
controller
exception
```

## 📦 Model

Contém as entidades e classes que representam o domínio da aplicação.

Exemplos:

- `Pessoa`
- `Funcionario`
- `Tecnico`
- `Equipamento`
- `Peca`
- `Estoque`
- `OrdemServico`
- `ItemOrdemServico`
- `Historico`

---

## 🗄️ Repository

Responsável pelo acesso e persistência dos dados utilizando **Spring Data JPA**.

Exemplos:

```text
FuncionarioRepository
EquipamentoRepository
PecaRepository
EstoqueRepository
OrdemServicoRepository
HistoricoRepository
```

---

## ⚙️ Service

Contém as regras de negócio da aplicação.

Exemplos de responsabilidades:

- validar funcionários ativos;
- controlar entrada e saída de estoque;
- impedir estoque negativo;
- abrir Ordens de Serviço;
- registrar peças utilizadas;
- realizar baixa automática no estoque;
- impedir alterações inválidas em OS encerradas;
- controlar encerramento das Ordens de Serviço.

---

## 🌐 Controller

Responsável pela comunicação entre o cliente e a aplicação por meio da API REST.

Os Controllers recebem as requisições HTTP, validam os dados e encaminham as operações para os Services.

---

# 🧠 Programação Orientada a Objetos

O projeto aplica os principais pilares da **Programação Orientada a Objetos (POO)**.

## 🔹 Classe Abstrata

A classe `Pessoa` representa características comuns entre pessoas do sistema.

```java
public abstract class Pessoa {

    private Long id;
    private String nome;
    private String telefone;

    public abstract String identificar();
}
```

---

## 🔹 Herança

As classes `Funcionario` e `Tecnico` herdam características da classe `Pessoa`.

```text
Pessoa
   │
   ├── Funcionario
   │
   └── Tecnico
```

---

## 🔹 Polimorfismo

O método abstrato:

```java
identificar()
```

é sobrescrito pelas classes filhas utilizando:

```java
@Override
```

Exemplo:

```java
@Override
public String identificar() {
    return "Funcionário: " + getNome();
}
```

---

## 🔹 Composição

A entidade `OrdemServico` possui uma composição com os itens de peças utilizadas.

```text
OrdemServico
       │
       └── ItemOrdemServico
                │
                └── Peca
```

Uma Ordem de Serviço pode possuir vários itens de peças.

---

## 🔹 Encapsulamento

Os atributos das classes são privados e acessados por meio de métodos públicos.

```java
private String nome;
```

O acesso é realizado por:

```java
getters
setters
```

As regras de negócio são concentradas principalmente na camada `Service`.

---

# 📁 Estrutura do Repositório

A estrutura do repositório segue o padrão definido para o projeto:

```text
Metria/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/metria/
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── FuncionarioController.java
│   │   │       │   ├── EquipamentoController.java
│   │   │       │   ├── PecaController.java
│   │   │       │   ├── EstoqueController.java
│   │   │       │   └── OrdemServicoController.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── FuncionarioService.java
│   │   │       │   ├── EquipamentoService.java
│   │   │       │   ├── PecaService.java
│   │   │       │   ├── EstoqueService.java
│   │   │       │   └── OrdemServicoService.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │
│   │   │       ├── model/
│   │   │       │
│   │   │       ├── exception/
│   │   │       │
│   │   │       └── MetriaApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── docs/
│   ├── AEP.pdf
│   ├── diagramas/
│   └── documentacao/
│
├── database/
│   └── scripts/
│
├── README.md
│
├── .gitignore
│
└── pom.xml
```

---

# 📌 Requisitos Funcionais

Os requisitos funcionais do projeto são:

| ID | Requisito |
|---|---|
| **RF01** | O sistema deve permitir o cadastro de funcionários, com nome, função, matrícula, telefone, e-mail, endereço e situação de ativo/inativo. |
| **RF02** | O sistema deve permitir o cadastro de equipamentos, com tag, nome, modelo, marca, número de série, setor, localização, data de aquisição e status. |
| **RF03** | O sistema deve permitir o cadastro de peças, com código, nome, descrição, unidade e estoque mínimo. |
| **RF04** | O sistema deve permitir controlar o estoque das peças, registrando a quantidade disponível e sinalizando situações abaixo do estoque mínimo. |
| **RF05** | O sistema deve permitir abrir, consultar, atualizar e encerrar ordens de serviço vinculadas a equipamentos e responsáveis, registrando data de abertura e data de conclusão. |
| **RF06** | O sistema deve permitir registrar o histórico das manutenções realizadas, incluindo descrição, data e custo da atividade. |
| **RF07** | O sistema deve permitir registrar as peças utilizadas em uma ordem de serviço e atualizar o controle de estoque correspondente. |
| **RF08** | O sistema deve permitir consultar informações de manutenção por equipamento, funcionário, período e situação da ordem de serviço. |
| **RF09** | O sistema deve disponibilizar dados necessários para acompanhar indicadores de manutenção, incluindo MTBF, MTTR e custos. |
| **RF10** | O sistema deve permitir excluir ou desativar registros conforme as regras de negócio, preservando a integridade dos relacionamentos do banco de dados. |

---

# 🗓️ Cronograma de Execução

| Data / Período | Atividade | Responsável |
|---|---|---|
| **03/09/2026** | Definição do problema, partes interessadas e ODS | Equipe |
| **04/09/2026** | Levantamento e validação dos requisitos | Equipe / Requisitos |
| **05/09/2026** | Definição do escopo e regras de negócio | Equipe / Requisitos |
| **06/09/2026** | Modelagem inicial do domínio e classes | Responsável por POO |
| **07/09/2026** | Modelagem do banco e DER | Responsável por Banco de Dados |
| **08/09/2026** | Definição da arquitetura e estrutura do projeto | Equipe / Arquitetura |
| **09/09/2026** | Criação e organização do repositório GitHub | Responsável por Versionamento |
| **10/09/2026** | Revisão dos requisitos, diagramas e documentação | Equipe |
| **11/09/2026** | Consolidação e entrega da 1ª etapa | Equipe |
| **2º bimestre** | Implementação, CRUD, integração MySQL, testes e documentação final | Equipe |

> **Observação:** os responsáveis definidos por função devem ser substituídos pelos nomes dos integrantes da equipe antes da entrega final.

---

# 🚀 Como Executar o Projeto

## 📋 Pré-requisitos

Antes de executar o projeto, é necessário possuir:

- Java LTS instalado;
- Maven instalado ou configurado pela IDE;
- uma IDE compatível, como:
  - IntelliJ IDEA;
  - Eclipse;
  - VS Code com extensões Java.

---

## 1️⃣ Clonar o Repositório

No terminal, execute:

```bash
git clone https://github.com/SEU-USUARIO/Metria.git
```

Entre na pasta do projeto:

```bash
cd Metria
```

> Substitua `SEU-USUARIO` pelo usuário ou organização responsável pelo repositório.

---

## 2️⃣ Executar com Maven

### Linux/macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

Ou, caso o Maven esteja instalado globalmente:

```bash
mvn spring-boot:run
```

---

## 3️⃣ Executar pela IDE

Abra o projeto como um projeto Maven.

Localize a classe principal:

```text
MetriaApplication.java
```

Execute a aplicação utilizando a opção:

```text
Run / Executar
```

Quando a aplicação for iniciada corretamente, o Spring Boot disponibilizará a API REST localmente.

Por padrão:

```text
http://localhost:8080
```

---

# 🗄️ H2 Database

O MVP utiliza o **H2 Database em memória**.

A configuração esperada no arquivo:

```text
src/main/resources/application.properties
```

é semelhante a:

```properties
spring.datasource.url=jdbc:h2:mem:metriadb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## 🖥️ Acessando o Console Web do H2

Com a aplicação em execução, acesse:

```text
http://localhost:8080/h2-console
```

Utilize as seguintes informações:

| Campo | Valor |
|---|---|
| **JDBC URL** | `jdbc:h2:mem:metriadb` |
| **User Name** | `sa` |
| **Password** | *(deixe em branco)* |

Após conectar, será possível visualizar as tabelas e dados gerados pela aplicação.

> Como o banco está configurado **em memória**, os dados podem ser perdidos quando a aplicação for encerrada.

---

# 🌐 API REST

A API pode ser testada utilizando ferramentas como:

- Postman;
- Insomnia;
- Thunder Client;
- outros clientes HTTP.

A URL base da API é:

```text
http://localhost:8080/api
```

---

# 👷 Funcionários

### Cadastrar funcionário

```http
POST /api/funcionarios
```

Exemplo:

```json
{
  "nome": "João da Silva",
  "telefone": "44999999999",
  "funcao": "Técnico de Manutenção",
  "matricula": "FUNC001",
  "email": "joao@metria.com",
  "endereco": "Rua A, 100",
  "ativo": true
}
```

### Listar funcionários

```http
GET /api/funcionarios
```

### Buscar funcionário por ID

```http
GET /api/funcionarios/{id}
```

### Desativar funcionário

```http
PUT /api/funcionarios/{id}/desativar
```

---

# 🏭 Equipamentos

### Cadastrar equipamento

```http
POST /api/equipamentos
```

Exemplo:

```json
{
  "tag": "EQ-001",
  "nome": "Torno CNC",
  "modelo": "CNC-500",
  "marca": "Industrial",
  "numeroSerie": "SN-001",
  "setor": "Produção",
  "localizacao": "Galpão A",
  "dataAquisicao": "2024-01-15",
  "status": "OPERACIONAL"
}
```

### Listar equipamentos

```http
GET /api/equipamentos
```

### Buscar equipamento por ID

```http
GET /api/equipamentos/{id}
```

### Atualizar status

```http
PUT /api/equipamentos/{id}/status?status=EM_MANUTENCAO
```

---

# 🔩 Peças

### Cadastrar peça

```http
POST /api/pecas
```

Exemplo:

```json
{
  "codigo": "PC-001",
  "nome": "Rolamento",
  "descricao": "Rolamento industrial",
  "unidade": "UN",
  "estoqueMinimo": 10
}
```

### Listar peças

```http
GET /api/pecas
```

### Buscar peça por ID

```http
GET /api/pecas/{id}
```

---

# 📦 Estoque

### Listar estoques

```http
GET /api/estoques
```

### Consultar estoque de uma peça

```http
GET /api/estoques/peca/{pecaId}
```

### Adicionar estoque

```http
POST /api/estoques/peca/{pecaId}/entrada?quantidade=50
```

### Consultar situação do estoque

```http
GET /api/estoques/peca/{pecaId}/situacao
```

A aplicação verifica se a quantidade disponível está abaixo do estoque mínimo.

---

# 📝 Ordens de Serviço

### Abrir Ordem de Serviço

```http
POST /api/ordens-servico?equipamentoId=1&responsavelId=1&tipo=CORRETIVA
```

### Listar Ordens de Serviço

```http
GET /api/ordens-servico
```

### Buscar Ordem de Serviço por ID

```http
GET /api/ordens-servico/{id}
```

### Adicionar peça à Ordem de Serviço

```http
POST /api/ordens-servico/{id}/pecas?pecaId=1&quantidade=5
```

Ao adicionar uma peça à Ordem de Serviço:

1. o sistema verifica se a OS está aberta;
2. verifica se a peça existe;
3. verifica se existe quantidade suficiente no estoque;
4. registra a peça utilizada;
5. realiza a baixa automática do estoque.

### Remover peça da Ordem de Serviço

```http
DELETE /api/ordens-servico/{id}/pecas/{itemId}
```

A quantidade removida é devolvida ao estoque.

### Encerrar Ordem de Serviço

```http
PUT /api/ordens-servico/{id}/encerrar
```

Após o encerramento, a Ordem de Serviço não deve receber novas alterações relacionadas às peças.

---

# 🔄 Fluxo de Teste Recomendado

Para testar o sistema, siga a sequência:

```text
1. Cadastrar Funcionário
        ↓
2. Cadastrar Equipamento
        ↓
3. Cadastrar Peça
        ↓
4. Adicionar quantidade ao Estoque
        ↓
5. Abrir Ordem de Serviço
        ↓
6. Adicionar peça à OS
        ↓
7. Verificar baixa automática no Estoque
        ↓
8. Encerrar Ordem de Serviço
```

---

# ⚠️ Regras de Negócio Implementadas

Entre as principais regras de negócio do MVP estão:

- Funcionários podem estar ativos ou inativos;
- funcionários inativos não podem ser responsáveis por novas Ordens de Serviço;
- cada equipamento possui uma identificação por TAG;
- uma Ordem de Serviço deve possuir equipamento e responsável;
- o estoque não pode ficar negativo;
- a quantidade adicionada ou retirada deve ser maior que zero;
- peças utilizadas em uma OS realizam baixa automática no estoque;
- a remoção de uma peça da OS devolve a quantidade ao estoque;
- uma OS encerrada não pode receber novas peças;
- o estoque pode sinalizar alerta quando a quantidade estiver abaixo do mínimo;
- as operações relacionadas são executadas utilizando transações para preservar a consistência dos dados.

---

# 🔐 Tratamento de Erros

A aplicação utiliza exceções personalizadas para representar situações específicas.

Exemplos:

```text
RecursoNaoEncontradoException
RegraDeNegocioException
```

Um tratamento global de exceções é realizado para retornar respostas HTTP adequadas.

| Situação | Código HTTP |
|---|---|
| Cadastro realizado | `201 Created` |
| Consulta realizada | `200 OK` |
| Atualização realizada | `200 OK` |
| Operação sem conteúdo | `204 No Content` |
| Dados inválidos | `400 Bad Request` |
| Recurso não encontrado | `404 Not Found` |
| Erro interno | `500 Internal Server Error` |

---

# 👥 Integrantes da Equipe

Preencha as informações abaixo com os integrantes do grupo:

| Integrante | RA |
|---|---|
| [Nome do Integrante] | [RA] |
| [Nome do Integrante] | [RA] |
| [Nome do Integrante] | [RA] |
| [Nome do Integrante] | [RA] |
| [Nome do Integrante] | [RA] |

---

# 📚 Contexto Acadêmico

**Instituição:** Unicesumar  
**Curso:** Engenharia de Software  
**Semestre:** 4º Semestre  
**Período:** 2026.2  
**Projeto:** Atividade de Estudo Programada — AEP  
**Tema:** Gestão de Manutenção Industrial  

---

# 📄 Documentação

A documentação complementar do projeto deve estar disponível na pasta:

```text
/docs
```

Incluindo, quando aplicável:

- documento da AEP;
- diagramas;
- DER;
- documentação técnica;
- demais artefatos acadêmicos do projeto.

---

# 🚧 Status do Projeto

🟡 **MVP em desenvolvimento**

Funcionalidades principais já estruturadas:

- [x] Modelagem do domínio;
- [x] Aplicação de conceitos de POO;
- [x] Persistência com Spring Data JPA;
- [x] H2 Database;
- [x] Repositórios;
- [x] Services e regras de negócio;
- [x] Controle de estoque;
- [x] Ordens de Serviço;
- [x] Controllers REST;
- [x] Validação de dados;
- [x] Tratamento global de exceções;
- [ ] Histórico completo de manutenções;
- [ ] Consultas avançadas;
- [ ] Indicadores MTBF e MTTR;
- [ ] Testes automatizados;
- [ ] Persistência final conforme os requisitos de entrega acadêmica.

---

## 📌 Observação Final

O Metria foi desenvolvido como um projeto acadêmico voltado à aplicação prática de conceitos de:

- Engenharia de Software;
- Programação Orientada a Objetos;
- Arquitetura em Camadas;
- APIs REST;
- Persistência de Dados;
- Regras de Negócio;
- Gestão de Manutenção Industrial.

O projeto busca demonstrar como a organização de informações sobre equipamentos, funcionários, peças, estoque e Ordens de Serviço pode contribuir para uma gestão de manutenção mais estruturada, rastreável e orientada por dados.

---

**Metria — Sistema de Gestão de Manutenção Industrial**  
*Unicesumar — Engenharia de Software — 4º Semestre — 2026.2*