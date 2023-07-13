# Projeto de Criação de Ordem com GCP Pub-Sub
Este projeto tem como objetivo implementar um fluxo assíncrono para a criação de pedidos, 
utilizando o serviço de mensageria Pub-Sub do Google Cloud Platform (GCP). O projeto consiste em três subprojetos 
principais: Listener, Publisher e Frontend.

## Listener
O subprojeto Listener é responsável por receber as mensagens enviadas pelo Publisher, 
processá-las e persistir os pedidos no banco de dados (MongoDB). Ele utiliza o GCP Pub-Sub como serviço de mensageria para 
receber as mensagens assincronamente. O projeto foi desenvolvido em Java 11, com Spring Boot 2.7.14

## Publisher
O subprojeto Publisher é responsável por receber as requisições para criação e listagem de pedidos. 
Ele utiliza o GraphQL como interface para receber essas requisições. O Publisher, então, publica as mensagens no tópico 
do Pub-Sub correspondente à criação de pedidos, para serem processadas pelo Listener. 
O projeto foi desenvolvido em Java 11, com Spring Boot 2.7.14

## Frontend
O subprojeto Frontend é desenvolvido em React com Next.js e é responsável por fornecer uma interface para o usuário 
interagir com o sistema de listagem de pedidos. Ele se comunica com o Publisher por meio de requisições GraphQL. <br>
Obs.: Atualmente, esse serviço interage apenas com a listagem de ordem, para a criação da ordem, devesse utilizar
algum sistema auxiliar, iremos falar melhor no tópico de API-REQUESTS

## API-REQUESTS
Dentro do repositório, tem uma pasta chamada api-requests, dentro dela, temos 2 arquivos, onde neles estão presentes 2 curl's:
<ul>
  <li>create-order.txt -> Nesse possui o curl para a criação de ordem</li>
  <li>list-orders.txt -> Nesse possui o curl para a obtenção da listagem de ordens</li>
</ul>
Na versão que o docker-compose sobe das imagens, não se tem acesso ao editor graphiql, então, a chamada deve ser realizada
por alguma ferramenta auxiliar, eu executei pelo postman e obtivo o curl por ele, é possível importar o curl para o postman novamente.<br>
O list-orders é chamado pelo frontend, porém, o create-order precisará ser chamado especificamente, para conseguir
fazer a criação de um pedido.

# Iniciliazação
Todos os módulos foram dockerizados e reunidos dentro do docker-compose, sendo assim, tudo o que precisa ser feito, é executar
o comando `docker-compose up` dentro da pasta do projeto, esse comando irá subir os containers:
<ol>
  <li>Pub-Sub Emulator -> irá subir um container do emulador do Pub-Sub, com o tópico e a subscrição criada</li>
  <li>MongoDB -> Irá subir um container para o banco de dados da aplicação, já irá ser criado o database e as collections necessárias</li>
  <li>Publisher -> Irá subir uma container do serviço publisher</li>
  <li>Listener -> Irá subir uma container do serviço lister</li>
  <li>Frontend -> Irá subir uma container da aplicação web</li>
</ol>
Obs.: Ao subir o container do publisher e do listener, um log de erro será exibido, pois as credencias do GCP não foram 
settadas, porém, está sendo utilizado um emulador e o projeto está configurado para utilizar o emulador, ou seja, o log
de erro não afeta no funcionamento dos serviços.<br>

Após todos os serviços estarem up, temos 2 IP's importantes: o primeiro, o IP da aplicação web: 174.101.101.105, que estará
rodando na porta 3000, ou seja, para acessar a aplicação web, basta acessar no navegador http://174.101.101.105:3000. 
O segundo IP, é do publisher, pois ele é a API exposta para as comunicações externas (desta forma, só precisamos garantir
a alta disponibilidade de um serviço, pois o cliente só consome ele), esse serviço está alocado no ip 174.101.101.103 rodando
na porta 8080.<br>
Obs.: Os serviços do publisher e do listener, estão com a pasta do target dentro do repositório, pois o docker necessita
do jar da aplicação e esse normalmente é gerado pela pipeline CI da aplicação, então para não precisar buildar a aplicação
na maquina, o jar foi publicado no repo

## Schemas do GraphQL

### Input Types

#### ItemDTO
```graphql
input ItemDTO {
    name: String!
    image: String!
    qty: Int!
    cost: Float!
    currency: String!
}
```

#### OrderDTO
```graphql
input OrderDTO {
    order: String!
    origin: String!
    items: [ItemDTO!]!
}
```

### Object Types

#### Item
```graphql
type Item {
    name: String!
    image: String!
    qty: Int!
    cost: Float!
    currency: String!
}
```

#### Item
```graphql
type Item {
    name: String!
    image: String!
    qty: Int!
    cost: Float!
    currency: String!
}
```

#### Order
```graphql
type Order {
    order: String!
    origin: String!
    items: [Item!]!
}
```

#### OrderResponse
```graphql
type OrderResponse {
    order: String!
    origin: String!
    items: [Item!]!
    total: Float!
    createdAt: String!
}
```

### Mutations

#### publishPlacedOrderMessage
```graphql
type Mutation {
    publishPlacedOrderMessage(order: OrderDTO!): Order
}
```

### Queries

#### placedOrders
```graphql
type Query {
    placedOrders: [OrderResponse]
}
```
