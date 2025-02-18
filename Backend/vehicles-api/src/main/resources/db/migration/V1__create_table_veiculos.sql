CREATE TABLE public.veiculos (
	tipo_veiculo varchar(31) NOT NULL,
	id serial4 NOT NULL,
	ano int4 NOT NULL,
	cor varchar(255) NULL,
	fabricante varchar(255) NOT NULL,
	modelo varchar(255) NOT NULL,
	preco float8 NOT NULL,
	quantidade_portas int4 NULL,
	tipo_combustivel varchar(255) NULL,
	cilindrada int4 NULL,
	CONSTRAINT veiculos_pkey PRIMARY KEY (id)
);