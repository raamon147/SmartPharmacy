use farmacia;
CREATE TABLE `aplicacao` (
  `cpf` varchar(12) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `nascimento` varchar(12) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `medicamento` varchar(45) DEFAULT NULL,
  `data` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `carrinho` (
  `codigo` int(11) NOT NULL,
  `produto` varchar(45) NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `descricao` varchar(30) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `clientes` (
  `cpf` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `nascimento` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `pontos` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `convenios` (
  `convenio` varchar(23) NOT NULL,
  `desconto` int(11) NOT NULL,
  PRIMARY KEY (`convenio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `fidelidade` (
  `cpf` int(11) NOT NULL,
  `pontos` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `gerente` (
  `matricula` int(11) NOT NULL,
  `senha` int(11) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL,
  `produto` varchar(44) NOT NULL,
  `preco` double NOT NULL,
  `status` varchar(44) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `vendedor` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
gerente