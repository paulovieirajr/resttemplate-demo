# Consumo de API com RestTemplate

Este projeto consiste em uma API que consome uma API externa (https://dummyjson.com/products) e fornece três endpoints
que permitem:

1. Buscar todos os produtos.
2. Buscar um produto por ID.
3. Buscar produtos filtrados por nome.

Todas as funcionalidades do projeto são cobertas por testes, tanto nos controllers quanto na camada de cliente que se
conecta à API externa.

## Endpoints

### Buscar Todos os Produtos

Este endpoint permite buscar todos os produtos disponíveis.

- **URL:** `/api/products`
- **Método:** GET
- **Exemplo de Requisição:** `http://localhost:8080/api/products`

### Buscar Produto por ID

Este endpoint permite buscar um produto específico por ID.

- **URL:** `/api/products/{id}`
- **Método:** GET
- **Exemplo de Requisição:** `http://localhost:8080/api/products/1`

### Buscar Produtos por Nome

Este endpoint permite buscar produtos filtrados por nome.

- **URL:** `/api/products/search`
- **Método:** GET
- **Parâmetros de Consulta:** `q` (nome do produto)
- **Exemplo de Requisição:** `http://localhost:8080/api/products/search?q=produto`

## Testes

Todas as funcionalidades deste projeto são testadas para garantir seu correto funcionamento. Os testes abrangem tanto os
controllers quanto a camada de cliente que se comunica com a API externa.

## Documentação no Swagger

Este projeto possui documentação no Swagger, que fornece informações detalhadas sobre os endpoints e permite testá-los
interativamente.

Para acessar a documentação no Swagger, basta abrir o seguinte URL em seu navegador:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Certifique-se de que o aplicativo esteja em execução para acessar a documentação.

## Como Executar o Projeto

Para executar o projeto, siga estas etapas:

1. Clone o repositório para o seu ambiente local.
2. Certifique-se de ter o Java e o Maven instalados.
3. Navegue até o diretório do projeto e execute o seguinte comando:

```agsl
mvn spring-boot:run
```

4. O aplicativo estará disponível em [http://localhost:8080](http://localhost:8080).

Agora você pode usar os endpoints e acessar a documentação no Swagger para explorar o projeto.
