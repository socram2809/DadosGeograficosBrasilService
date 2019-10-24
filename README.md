# DadosGeograficosBrasilService

## Descrição do projeto
O DadosGeograficosBrasilService é uma API que foi desenvolvida para manter os dados de cidades, estados e países. Além de possuir o CRUD para as três entidades citadas anteriormente, é possível buscar uma cidade por CEP (/cidade/buscaPorCep?numeroCEP=99999). Caso a cidade não seja encontrada no banco de dados, busca na API [Via CEP](https://viacep.com.br/) e persiste na base. Se a cidade já existe no banco, apenas vincula o CEP. Outra funcionalidade interessante (/cidade/retornaTrajeto?numeroCEPs=99999,88888,77777), é o retorno de uma lista de cidades ao receber uma relação de CEP's que indicam um trajeto.

Todos os serviços da API foram desenvolvidos no padrão REST, possuem implementação baseada em Spring Boot e testes automatizados.

A documentação de uso da API é definida no formato do [Swagger](https://swagger.io/solutions/api-documentation/). Caso acesse a aplicação localmente e na porta 8080, a documentação estará em [Swagger-UI](http://localhost:8080/swagger-ui.html).

## Tecnologias

- Java 8
- Banco de Dados H2
- Maven
- Spring Boot
- JPA

## Build

` mvn install `

Executado o comando acima na raiz do projeto, onde se encontra o arquivo "pom.xml", será construída e empacotada uma aplicação Spring Boot dentro de um único arquivo executável de extensão ".jar".

## Executar

` java -jar target/DadosGeograficosBrasilService-1.0.0.jar `

Para rodar a aplicação por linha de comando e acessá-la pelo navegador, deve-se usar o comando acima.

### Referências

- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)

## Autores

* **Marcos Rocha Júnior** - *Developer* - [marcosau_2809@hotmail.com](mailto:marcosau_2809@hotmail.com)
