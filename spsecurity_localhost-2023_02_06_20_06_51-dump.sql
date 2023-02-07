--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: spsecurity; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE spsecurity2 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Peru.1252';


ALTER DATABASE spsecurity OWNER TO postgres;

\connect spsecurity

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: rol; Type: TABLE; Schema: public; Owner: userSpring
--

CREATE TABLE public.role (
    id_role integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.rol OWNER TO "userSpring";

--
-- Name: rol_id_role_seq; Type: SEQUENCE; Schema: public; Owner: userSpring
--

CREATE SEQUENCE public.rol_id_role_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rol_id_role_seq OWNER TO "userSpring";

--
-- Name: rol_id_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: userSpring
--

ALTER SEQUENCE public.rol_id_role_seq OWNED BY public.rol.id_role;


--
-- Name: sequence_id; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_id OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: userSpring
--

CREATE TABLE public.usuario (
    id_user integer NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.usuario OWNER TO "userSpring";

--
-- Name: usuario_id_user_seq; Type: SEQUENCE; Schema: public; Owner: userSpring
--

CREATE SEQUENCE public.usuario_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_user_seq OWNER TO "userSpring";

--
-- Name: usuario_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: userSpring
--

ALTER SEQUENCE public.usuario_id_user_seq OWNED BY public.usuario.id_user;


--
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario_rol (
    id_user integer NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE public.usuario_rol OWNER TO postgres;

--
-- Name: rol id_role; Type: DEFAULT; Schema: public; Owner: userSpring
--

ALTER TABLE ONLY public.rol ALTER COLUMN id_role SET DEFAULT nextval('public.rol_id_role_seq'::regclass);


--
-- Name: usuario id_user; Type: DEFAULT; Schema: public; Owner: userSpring
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_user SET DEFAULT nextval('public.usuario_id_user_seq'::regclass);


--
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: userSpring
--

COPY public.rol (id_role, name) FROM stdin;
2	ROLE_OPERADOR
1	ROLE_ADMIN
3	ROLE_CLIENTE
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: userSpring
--

COPY public.usuario (id_user, password, username) FROM stdin;
2	123	123
1	$2a$10$mbdJ0iK527O2wvYgCUJAw.rdyNK8Kji97hetFhgmJv5uvUJB2JrsO	ADMIN
\.


--
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario_rol (id_user, id_role) FROM stdin;
1	1
2	2
\.


--
-- Name: rol_id_role_seq; Type: SEQUENCE SET; Schema: public; Owner: userSpring
--

SELECT pg_catalog.setval('public.rol_id_role_seq', 2, true);


--
-- Name: sequence_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_id', 1, false);


--
-- Name: usuario_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: userSpring
--

SELECT pg_catalog.setval('public.usuario_id_user_seq', 2, true);


--
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: userSpring
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_role);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: userSpring
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_user);


--
-- Name: usuario_rol usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (id_user, id_role);


--
-- Name: usuario_rol usuario_rol_id_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_id_role_fkey FOREIGN KEY (id_role) REFERENCES public.rol(id_role) ON DELETE CASCADE;


--
-- Name: usuario_rol usuario_rol_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.usuario(id_user) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

