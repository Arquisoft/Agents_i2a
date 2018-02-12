# Agents_i2a

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c0c920d4630d42c3ac4e70dd6844715a)](https://www.codacy.com/app/jelabra/Agents_i2a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_i2a&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/Agents_i2a.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_i2a)
[![codecov](https://codecov.io/gh/Arquisoft/Agents_i2a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_i2a)

Agents module

# 17-18 Authors
- [Marcos Chacón Cadenas](https://github.com/chacon11)
- [Alba Cotarelo Tuñón](https://github.com/albacotarelo)
- [Javier Díez García](https://github.com/javicodema)

# Previous authors
- Herminio García González (@herminiogg)
- Jose Emilio Labra Gayo (@labra)
- Jorge Zapatero Sánchez (@JorgeZapa)
- Damián Rubio Cuervo (@DamianRubio)
- Antonio Nicolás Rivero Gómez (@Lan5432)

# MongoDB
This project uses MongoDB as the database. You can check how to use it on
 - [MongoDB install](https://github.com/Arquisoft/participants_i2b/wiki/MongoDB)

# Jasypt
This project uses Jasypt to encrypt the passwords. You don't need to download it, but you can check it at: http://www.jasypt.org/
 
## REST requests
In order to use the user credentials to obtain your data, you can send a POST request to http://localhost:8080/user. The
data in this request can come as:
 - JSON:
```json
{"login": user, "password": password, "kind": agent's kind}
```

- XML:
```xml
<data>
 <login>yourLogin</login>
 <password>yourPassword</password>
</data>
```

## Tests
To run the tests just use `mvn test`. No data in the database is needed to run them. But if you want to test the
user interface manually you'll have to introduce this document:

```json
{
    "_id" : ObjectId("58a8670df086e81dc034d7fc"),
    "firstName" : "Prueba01",
    "lastName" : "Apellido01",
    "email" : "prueba01@prueba.es",
    "address" : "c/Prueba n0 1a",
    "nationality" : "España",
    "userId" : "00000001J",
    "dateOfBirth" : ISODate("1981-12-27T23:00:00.000Z"),
    "password" : "khZZwjdhWwVbMdmOvz9eqBfKR1N6A+CdFBDM9c1dQduUnGewQyPRlBxB4Q6wT7Cq"
}
```

And as data use:
 - login: prueba01@prueba.es
 - password: 4[[j[frVCUMJ>hU

 If you have the data and the database running and the application still reports a 404 Not Found when it shouldn't
 try deleting the document and adding it again.
