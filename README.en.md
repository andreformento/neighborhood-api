[![Build Status](https://travis-ci.org/andreformento/neighborhood-api.svg?branch=master)](https://travis-ci.org/andreformento/neighborhood-api)

# neighborhood-api
An API to organize neighborhood

### How to
Test: `$ gradle integrationTest -i`

Build: `$ gradle build`

#### Run on Docker
Create image: `$ gradle buildDocker`

Run: `$ docker run -d -p 8080:8080 andreformento/neighborhood-api`

### APIs

#### Insert a property

Request `POST`
```
/properties
```
Example:
```
curl -X POST 'https://neighborhood-api-formento.herokuapp.com/properties' \
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

#### Find a property by id
Request `GET`
```
/properties/{id}
```
Example:
```
curl -X GET 'https://neighborhood-api-formento.herokuapp.com/properties/665'
```

#### Find a property by area
Request `GET`
```
curl -X GET /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
```
Example:
```
curl -X GET 'https://neighborhood-api-formento.herokuapp.com/properties?ax=70&ay=95&bx=70&by=0'
```

### References to resolve problem

- [3. Kd Trees](https://www.youtube.com/watch?v=W94M9D_yXKk)
- [K Dimensional Tree | Set 1 (Search and Insert)](http://www.geeksforgeeks.org/k-dimensional-tree)
