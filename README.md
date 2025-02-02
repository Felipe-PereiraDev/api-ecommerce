# Documentação da API - E-commerce

## Visão Geral
Esta API permite gerenciar usuários, produtos, categorias e pedidos de um sistema de e-commerce.

## Tecnologias Utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Banco de dados MYSQL](https://www.mysql.com/)

---
## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
<!-- - Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Teste Unitários com o framework JUnit 5, com o suporte do Mockito para simulação de dependências -->

---
## Endpoints

### 1. Usuários (`/user`)

### Cadastrar Usuário
- **Método:** `POST /user`
- **Descrição:** Cria um novo usuário.
- **Request Body:**
  ```json
  {
    "username": "john_doe",
    "email": "john@example.com",
    "password": "senha123"
  }
- **Response**: `201 Created` (Com localização do recurso criado no header).

### Consultar Usuário
- **Método:** `GET /user/{id}`
- **Descrição:** Obtém os dados de um usuário pelo ID.
- **Response:**
  ```json
  {
    "id": "1",
    "username": "john_doe",
    "email": "john@example.com"
  }
### Adicionar Endereço
- **Método:** `POST /user/{username}/address`
- **Descrição:** Adiciona um endereço ao usuário.
- **Request Body:**
  ```json
  {
    "street": "Rua das Flores",
    "city": "São Paulo",
    "state": "SP",
    "zipcode": "12345-678"
  }
- **Status Http**: `200 ok`
---
### 2. Produtos (`/products`)

### Listar Produtos
- **Método:** `GET /products`
- **Descrição:** Retorna todos os produtos cadastrados.
- **Response:**
  ```json
  [
    {
        "id": 1,
        "name": "Produto A",
        "price": 99.99,
        "category": "Eletrônicos"
    }
  ]

- **Status Http**: `200 ok`

### Criar Produto
- **Método:** `POST /products`
- **Descrição:** Cria um novo produto.
- **Request Body:**
  ```json
  {
    "name": "Produto A",
    "price": 99.99,
    "categoryId": 1
  }
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Produto A",
    "price": 99.99,
    "category": "Eletrônicos"
  } 

### Atualizar Produto
- **Método:** `PUT /products/{id}`
- **Descrição:** Atualiza um produto existente.
- **Request Body:**
  ```json
  {
    "name": "Produto Atualizado",
    "price": 89.99
  }
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Produto Atualizado",
    "price": 89.99
  } 
### Excluir Produto
- **Método:** `DELETE /products/{id}`
- **Descrição:** Remove um produto pelo ID.
- **Response:** `204 No Content`

### Listar Categorias
- **Método:** `GET /products/category`
- **Descrição:** Retorna todas as categorias disponíveis.
- **Response:**
  ```json
  [
    {
      "id": 1,
      "name": "Eletrônicos"
    },
    {
      "id": 2,
      "name": "Esportes"
    },
    {
      "id": 3,
      "name": "Livros"
    }
  ]
---
### 3. Pedidos (`/order`)

### Criar Pedido
- **Método:** `POST /order/{userId}`
- **Descrição:** Retorna todos os produtos cadastrados.
- **Request Body:**
  ```json
    {
    "products": [
      {
        "productId": 6,
        "quantity": 10
      },
      {
        "productId": 3,
        "quantity": 5
      },
      {
        "productId": 1,
        "quantity": 5
      }
     ]
    }


- **Response**: 
    ```json
    {
	  "username": "felipe",
	  "orderId": "10c66e34-6b63-4fdd-9beb-fcf4d0c96aa9",
	  "orderDate": "2025-01-29T00:06:54.041811",
	  "productsProcessed": [
		{
		  "productId": 6,
		  "name": "Camisa do Flameng",
		  "quantity": 10,
		   "price": 600.00
		},
		{
		  "productId": 3,
	      "name": "Bola de Futebool",
		  "quantity": 5,
		  "price": 120.00
		},
		{
		  "productId": 1,
		  "name": "Carregador tipo C",
		  "quantity": 5,
		  "price": 99.99
		}
	  ],
	  "totalAmount": 7099.95,
	  "status": "PENDING"
    }


