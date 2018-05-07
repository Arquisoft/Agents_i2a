# Agents_i2a

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c0c920d4630d42c3ac4e70dd6844715a)](https://www.codacy.com/app/jelabra/Agents_i2a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_i2a&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Arquisoft/Agents_i2a.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_i2a)
[![codecov](https://codecov.io/gh/Arquisoft/Agents_i2a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_i2a)
[![Codefresh build status]( https://g.codefresh.io/api/badges/build?repoOwner=Arquisoft&repoName=Agents_i2a&branch=master&pipelineName=Agents_i2a&accountName=javicodema&type=cf-1)]( https://g.codefresh.io/repositories/Arquisoft/Agents_i2a/builds?filter=trigger:build;branch:master;service:5ad475044c2ffe00018b55a1~Agents_i2a)

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


# Get started with the application

### **1. Software needed**
You will need to have installed in your computer the following software products:
	- [Maven](https://maven.apache.org/install.html)
	
	
### **2. Run the project**
	Download the project and go to its directory, open there a command prompt and execute:
		> mvn spring-boot:run
	Now the application is running if all it's correct.
	
	
### **3. Insert documents in MongoDB database**
	As this module is currently working along other modules, in order to have an agent to test the application, you should run the 
	[Loader](https://github.com/Arquisoft/Loader_i2a) module, which will add the agents you specify in a xls file to the database 		that this module uses.
	
### **4. Start using the application**
	Open http://localhost:8080/ on you navigator and login to use the application.

### **5. Test the application**
To run the tests just use `mvn test`. No data in the database is needed to run them. 


# More information

## MongoDB (with mLab)
This project uses MongoDB as the database. You can check how to use it on
 - [MongoDB install](https://github.com/Arquisoft/participants_i2b/wiki/MongoDB)

## Jasypt
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
 <kind>yourKind</kind>
</data>
```

