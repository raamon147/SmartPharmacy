INSERT INTO `farmacia`.`produto`
(`codigo`,
`produto`,
`preco`,
`status`,
`id_categoria`)
VALUES
(154,
"Monster Energy 473ml",
8.00,
"Disponivel",
2);

INSERT INTO `farmacia`.`produto`
(`codigo`,
`produto`,
`preco`,
`status`,
`id_categoria`)
VALUES
(4,
"Lexotan 75mg",
150.30,
"Disponivel",
1);

INSERT INTO `farmacia`.`produto`
(`codigo`,
`produto`,
`preco`,
`status`,
`id_categoria`)
VALUES
(1,
"Rivotril 50mg",
50.90,
"Disponivel",
1);

INSERT INTO `farmacia`.`produto`
(`codigo`,
`produto`,
`preco`,
`status`,
`id_categoria`)
VALUES
(344,
"Dipirona Gotas",
7.95,
"Em Falta",
1);


INSERT INTO `farmacia`.`gerente`
(`matricula`,
`senha`)
VALUES
(123,
123);

INSERT INTO `farmacia`.`categoria`
(`id_categoria`,
`descricao`)
VALUES
(1,
"Medicamento");

INSERT INTO `farmacia`.`categoria`
(`id_categoria`,
`descricao`)
VALUES
(2,
"Perfumaria");

INSERT INTO `farmacia`.`clientes`
(`cpf`,
`nome`,
`nascimento`,
`endereco`,
`pontos`)
VALUES
("43172098885",
"Ramon Sobreira",
"14/05/1995",
"Rua Victorio Santim",
200);

INSERT INTO `farmacia`.`convenios`
(`convenio`,
`desconto`)
VALUES
("Amil",
20);





