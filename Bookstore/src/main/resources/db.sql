CREATE TABLE public.author (
	id bigserial NOT NULL,
	middle_name varchar(255) NULL,
	"name" varchar(255) NULL,
	surname varchar(255) NOT NULL,
	CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE public.book (
	id bigserial NOT NULL,
	pages int4 NULL,
	price int4 NOT NULL,
	title varchar(255) NOT NULL,
	year_of_publication int4 NULL,
	author_id int8 NOT NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id),
	CONSTRAINT uk_nhwq1sppxx33alljcnw3qi40t UNIQUE (id, price)
);

CREATE TABLE public.book_warehouse (
	id bigserial NOT NULL,
	balance int4 NULL,
	book_id int8 NOT NULL,
	CONSTRAINT book_warehouse_pkey PRIMARY KEY (id)
);

CREATE TABLE public.buyer (
	id bigserial NOT NULL,
	birth_date date NULL,
	middle_name varchar(255) NULL,
	"name" varchar(255) NULL,
	surname varchar(255) NOT NULL,
	CONSTRAINT buyer_pkey PRIMARY KEY (id)
);

CREATE TABLE public."ordering" (
	id bigserial NOT NULL,
	buyer_id int8 NOT NULL,
	CONSTRAINT ordering_pkey PRIMARY KEY (id)
);

CREATE TABLE public.ordering_details (
	id bigserial NOT NULL,
	quantity int4 NULL,
	book_id int8 NULL,
	price int4 NULL,
	ordering_id int8 NULL,
	CONSTRAINT ordering_details_pkey PRIMARY KEY (id)
);

INSERT INTO public.author
(id, middle_name, "name", surname)
VALUES(1, 'Ivanovich', 'Ivan', 'Petrov');
INSERT INTO public.author
(id, middle_name, "name", surname)
VALUES(2, 'Petrovich', 'Ivan', 'Ivanov');


INSERT INTO public.book
(id, pages, price, title, year_of_publication, author_id)
VALUES(1, 220, 600, 'Summer', 2002, 1);
INSERT INTO public.book
(id, pages, price, title, year_of_publication, author_id)
VALUES(2, 200, 800, 'Spring', 2004, 1);
INSERT INTO public.book
(id, pages, price, title, year_of_publication, author_id)
VALUES(3, 180, 800, 'Winter', 2006, 2);
INSERT INTO public.book
(id, pages, price, title, year_of_publication, author_id)
VALUES(4, 270, 1000, 'Autumn', 2005, 2);

INSERT INTO public.book_warehouse
(id, balance, book_id)
VALUES(1, 4, 1);
INSERT INTO public.book_warehouse
(id, balance, book_id)
VALUES(2, 3, 2);
INSERT INTO public.book_warehouse
(id, balance, book_id)
VALUES(3, 5, 3);
INSERT INTO public.book_warehouse
(id, balance, book_id)
VALUES(4, 3, 4);


INSERT INTO public.buyer
(id, birth_date, middle_name, "name", surname)
VALUES(1, '1999-01-01', 'Ivanovich', 'Vlad', 'Kozlov');
INSERT INTO public.buyer
(id, birth_date, middle_name, "name", surname)
VALUES(2, '1994-05-04', 'Petrovich', 'Vova', 'Maslov');


INSERT INTO public."ordering"
(id, buyer_id)
VALUES(1, 1);

INSERT INTO public.ordering_details
(id, quantity, book_id, price, ordering_id)
VALUES(1, 2, 1, 600, 1);
INSERT INTO public.ordering_details
(id, quantity, book_id, price, ordering_id)
VALUES(2, 1, 2, 800, 1);