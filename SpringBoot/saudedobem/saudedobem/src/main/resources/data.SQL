INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Diabetes", "evitar açúcar", 0);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Asma", "Inflamação das vias aéreas", 0);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Cancer", "Aumento das células cancerigenas", 0);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Depressão", "Constante sensação de tristeza, pessimismo e baixa autoestima.", 0);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Hipertenção Arterial", "Pressão Alta", 0);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Sarampo", "Doença infecciosa grave, causada por um vírus", 1);
INSERT INTO tb_doenca (nome, descricao, transmissivel) VALUES ("Gripe", "A gripe é uma doença causada por um vírus do tipo Influenza,", 1); 

INSERT INTO tb_medicamento (nome, descricao, categoria, preco) 
VALUES ("Insulina", "Manten os níveis de glicose regulados após a alimentação.", "Genérico", 60.00);

INSERT INTO tb_medicamento (nome, descricao, categoria, preco) 
VALUES ("Atrovent", " É indicado para o tratamento de manutenção do broncoespasmo associado à Doença Pulmonar Obstrutiva Crônica, ", "Similar", 25.00);

INSERT INTO tb_medicamento (nome, descricao, categoria, preco) 
VALUES ("Escitalopram", "Indicado para pacientes depressivos e ansiosos, transtorno obsessivo compulsivo e transtorno de ansiedade social,", "Referência", 40.00);

INSERT INTO tb_medicamento (nome, descricao, categoria, preco) 
VALUES ("Losartana", "É indicado para reduzir o risco de morbidade e mortalidade cardiovascular em pacientes hipertensos.", "Genérico", 8.35);

INSERT INTO tb_medicamento (nome, descricao, categoria, preco) 
VALUES ("Benegrip", "É destinado para alívio dos sintomas decorrentes das gripes e resfriados, como dores de cabeça, febre e processos alérgicos.", "Similar", 24.49);

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Miguel", "migue", "miguel@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Gabriel", "gabi", "gabriel@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("João", "manguinha", "joao@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Tokumbo", "toks", "tokumbo@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Erik", "eri jonhson", "erik@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Thiago", "thigas", "thiago@gmail.com", "Administrador", "12345");

INSERT INTO tb_usuario (nome, apelido, email, tipo, senha) 
VALUES ("Yuri", "Yudi", "yuri@gmail.com", "Cliente", "54321"); 

INSERT INTO tb_venda (endereco, fk_medicamento, fk_usuario) 
VALUES ("Rua goiás nº59 - Parada Inglêsa", 1, 1);

INSERT INTO tb_venda (endereco, fk_medicamento, fk_usuario) 
VALUES ("Rua pernambuco nº105 - Santana", 2, 2);

INSERT INTO tb_venda (endereco, fk_medicamento, fk_usuario) 
VALUES ("Rua das flores nº59 - Chácara Inglêsa", 3, 3);

INSERT INTO tb_venda (endereco, fk_medicamento, fk_usuario) 
VALUES ("Rua do bosque nº71 - Vila Anastácio", 4, 4);

INSERT INTO tb_venda (endereco, fk_medicamento, fk_usuario) 
VALUES ("Rua das pedras nº68 - Vila Galvão", 5, 5);