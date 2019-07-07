# Projeto Persistence FIAP 2019

# Índice

* [Integrantes](#integrantes)
* [Modelo do Banco de Dados](#modelo-do-banco-de-dados)
* [Requisitos](#requisitos)
* [Bando de dados - MySQL Docker](#bando-de-dados---mysql-docker)

## Integrantes

|Nomes                       |Número de Matricula |
|----------------------------|--------------------|
|Jhonatan Oliveira de Guarda |RM333990            |
|Logan Mantovani             |RM334335            |
|Luiz Keese Tabacow          |RM334018            |

## Modelo do Banco de Dados

<img src="DiagramaRelacionamento.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />
<br>

## Requisitos

Trata-se de um sistema de cadastro de produtos e pedidos em um portal de e-commerce. Considere os requisitos: 
* O portal possui vários produtos em estoque e com uma determinada quantidade em estoque de cada um desses produtos.
*  Cada pedido possui um ou mais produtos e um cliente associado a esse pedido.
*  Um produto pode aparecer em um ou mais pedidos.
*  Cada produto possui um código, um nome, uma quantidade e um valor.
*  Cada cliente possui seus dados pessoais e dados de entrega.

## Banco de Dados - MySQL Docker

* Linux
``` sh
docker run -p 3306:3306 --name fiap-mysql -e MYSQL_ROOT_PASSWORD=fiap2019 -d mysql:8 mysqld --default-authentication-plugin=mysql_native_password
```
