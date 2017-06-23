[![Build Status](https://travis-ci.org/andreformento/neighborhood-api.svg?branch=master)](https://travis-ci.org/andreformento/neighborhood-api)

# neighborhood-api
Esta aplicação é uma API construída para para organizar propriedades. É possível cadastrar um nova propriedade, buscar por ID e por uma determinada área. As propriedades sempre estão vínculadas aos Spotippos, podendo inclusive pertencer a mais de um.

Foi utilizado o algoritmo "kdtree dimensional" para otimizar a busca.

### Tecnologias

- Java 8
- Gradle
- Docker (opcional)

### Como fazer
Teste: `$ gradle integrationTest -i`

Build: `$ gradle build`

#### Rodando com Docker
Criar imagem: `$ gradle buildDocker`

Rodar: `$ docker run -d -p 8080:8080 formento/neighborhood-api`

### APIs

#### Criar imóveis em Spotippos :)

```
POST /properties
```
Exemplo:
```
curl -X POST 'http://localhost:8080/properties' \
-H 'Content-Type: application/json' \
-d '
{
    "x":222,
    "y":444,
    "title":"Imóvel código 1, com 5 quartos e 4 banheiros",
    "price":1250000,
    "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    "beds":4,
    "baths":3,
    "squareMeters":210
}
'
```

#### Mostrar um imóvel específico em Spotippos =]
```
GET /properties/{id}
```
Exemplo:
```
curl -X GET 'http://localhost:8080/properties/665'
```

#### Buscar imóveis em Spotippos :D
```
GET /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
```
Exemplo:
```
curl -X GET 'http://localhost:8080/properties?ax=70&ay=95&bx=70&by=0'
```

### Referências para resolver o algoritmo

- [3. Kd Trees](https://www.youtube.com/watch?v=W94M9D_yXKk)
- [K Dimensional Tree | Set 1 (Search and Insert)](http://www.geeksforgeeks.org/k-dimensional-tree)
