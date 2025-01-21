# Descrição da Aplicação Fullstack de Gerenciamento de Veículos

## 1. Visão Geral

A aplicação fullstack de **Gerenciamento de Veículos** é um sistema completo para o cadastro, consulta, atualização e exclusão de veículos, abrangendo tanto **carros** quanto **motos**. A aplicação permite gerenciar atributos específicos de cada tipo de veículo, além de características comuns.  

A aplicação será desenvolvida utilizando tecnologias **Java (Spring Boot)** no backend e uma stack moderna no frontend, garantindo uma experiência de usuário responsiva e eficiente. A persistência de dados será realizada utilizando **JDBC** para manipulação direta de um banco de dados relacional, como **MySQL** ou **PostgreSQL**.

---

## 2. Funcionalidades da Aplicação

### 2.1. Funcionalidades Gerais
- Cadastro de veículos (carros e motos).
- Consulta de veículos por critérios como:
  - Tipo (Carro ou Moto).
  - Modelo, cor e ano de fabricação.
- Atualização das informações dos veículos cadastrados.
- Exclusão de veículos do sistema.
- Listagem paginada de todos os veículos.
- Validação de dados antes da persistência no banco.

### 2.2. Regras de Negócio
- Os veículos devem ser classificados em **Carros** e **Motos**, compartilhando atributos comuns, mas com especificidades distintas.
- Para **Carros**, devem ser armazenados atributos como:
  - Quantidade de portas.
  - Tipo de combustível (Gasolina, Etanol, Flex).
- Para **Motos**, deve ser registrada a **cilindrada**.
- O preço dos veículos deve ser registrado em formato numérico com precisão para duas casas decimais.

---

## 3. Tecnologias Utilizadas

### 3.1. Backend (API REST)
- **Linguagem:** Java (Spring Boot)
- **Frameworks e Bibliotecas:**
  - Spring Boot (versão 3.2.0)
  - Spring Data JDBC (para acesso direto ao banco de dados)
- **Banco de Dados:**
  - PostgreSQL
- **Ferramentas de Build e Dependências:**
  - Maven
- **Documentação da API:**
  - Springdoc OpenAPI (Swagger UI)

### 3.2. Frontend
- **Framework:** React.js (com Next.js para renderização otimizada)
- **Estilização:** Tailwind CSS
- **Consumo de API:** Axios 
- **Componentização:** Componentes reutilizáveis para formulários, listas e tabelas.

### 3.3. Infraestrutura e Deploy
- Docker (com Docker Compose para ambiente de desenvolvimento)

## 4. Arquitetura da Aplicação

A aplicação seguirá uma arquitetura **MVC (Model-View-Controller)** no backend, enquanto o frontend será baseado em **componentes reutilizáveis**. A comunicação entre frontend e backend ocorrerá via **API RESTful**, utilizando JSON como formato de dados.

### 4.1. Camadas do Backend
- **Controller Layer:** Recebe as requisições HTTP e repassa para o serviço correspondente.
- **Service Layer:** Contém as regras de negócio e lógica de processamento.
- **Repository Layer:** Responsável pela comunicação com o banco de dados via JDBC.

---

## 5. Fluxo de Funcionamento da Aplicação

1. O usuário acessa a interface web e visualiza uma lista de veículos cadastrados.
2. Ele pode buscar veículos filtrando por tipo, modelo, ano ou cor.
3. Para cadastrar um novo veículo, o usuário preenche um formulário e submete os dados.
4. O backend recebe a requisição, valida os dados e insere o novo registro no banco de dados.
5. O usuário pode editar informações de veículos já cadastrados ou removê-los do sistema.
6. Toda comunicação entre frontend e backend ocorre via chamadas REST API.

---

## 6. Deploy e Monitoramento

1. **Ambientes:** Desenvolvimento, homologação e produção.
2. **Docker:** Para conteinerização da aplicação.

## 7. Passo a Passo para Executar o Projeto com Docker Compose (Back-End)

A seguir, apresentamos um passo a passo detalhado para executar o projeto de **Gerenciamento de Veículos** utilizando **Docker Compose**. Este processo inclui a configuração do ambiente para o **backend** e **banco de dados**, bem como a inicialização do sistema.

### 7.1. Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas na sua máquina:
- **Docker**: Para conteinerização da aplicação.
- **Docker Compose**: Para orquestração dos contêineres.

### 7.2. Construção e Execução do Projeto
Agora, execute os seguintes passos para construir e iniciar os contêineres:

Iniciar o Docker Compose: No diretório raiz do projeto, execute o comando:

docker-compose up --build


#### Passos para acessar o frontend:

1. **Certifique-se de que o Docker Compose está rodando:**
   Se você ainda não tiver rodado o `docker-compose up --build`, execute-o novamente para garantir que os contêineres estejam em execução.

2. **Acessando a Interface do Frontend:**
   - Abra um navegador web e digite a URL: `http://localhost:3000`após executar o comando npm start.
   - Isso irá carregar a interface do frontend onde você pode interagir com a aplicação.

#### Observações:
- O frontend estará sendo servido pelo React, que é o que você verá no navegador.
- A comunicação entre o frontend e o backend (que roda no `localhost:8080`) será realizada automaticamente, conforme o que estiver configurado na aplicação React para consumir os dados da API.


