--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Debian 14.3-1.pgdg110+1)
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-08 23:15:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16385)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    codigo_cliente character varying(255) NOT NULL,
    contrasena character varying(255) NOT NULL,
    estado boolean NOT NULL,
    id uuid NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16392)
-- Name: cuenta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta (
    id uuid NOT NULL,
    estado boolean NOT NULL,
    numero_cuenta character varying(255) NOT NULL,
    saldo_inicial numeric(38,2) NOT NULL,
    tipo_cuenta character varying(255) NOT NULL,
    cliente_id uuid NOT NULL,
    CONSTRAINT cuenta_tipo_cuenta_check CHECK (((tipo_cuenta)::text = ANY ((ARRAY['AHORROS'::character varying, 'CORRIENTE'::character varying])::text[])))
);


ALTER TABLE public.cuenta OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16400)
-- Name: movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento (
    id uuid NOT NULL,
    fecha date NOT NULL,
    saldo numeric(38,2),
    tipo_movimiento character varying(255) NOT NULL,
    valor numeric(38,2) NOT NULL,
    cuenta_id uuid,
    CONSTRAINT movimiento_tipo_movimiento_check CHECK (((tipo_movimiento)::text = ANY ((ARRAY['DEPOSITO'::character varying, 'RETIRO'::character varying])::text[])))
);


ALTER TABLE public.movimiento OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16406)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona (
    id uuid NOT NULL,
    direccion character varying(255),
    dni character varying(255) NOT NULL,
    edad integer NOT NULL,
    genero character varying(255),
    nombre character varying(255) NOT NULL,
    telefono character varying(255),
    CONSTRAINT persona_genero_check CHECK (((genero)::text = ANY ((ARRAY['MASCULINO'::character varying, 'FEMENINO'::character varying, 'NO_BINARIO'::character varying, 'OTRO'::character varying, 'NO_ESPECIFICADO'::character varying])::text[])))
);


ALTER TABLE public.persona OWNER TO postgres;
ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 3186 (class 2606 OID 16399)
-- Name: cuenta cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id);


--
-- TOC entry 3190 (class 2606 OID 16405)
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (id);


--
-- TOC entry 3192 (class 2606 OID 16413)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);


--
-- TOC entry 3194 (class 2606 OID 16419)
-- Name: persona ukhlwyecu2r9wagqayhej1kt5wy; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT ukhlwyecu2r9wagqayhej1kt5wy UNIQUE (dni);


--
-- TOC entry 3188 (class 2606 OID 16417)
-- Name: cuenta ukpj7ncg765kt4klndu25bwbwe4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT ukpj7ncg765kt4klndu25bwbwe4 UNIQUE (numero_cuenta);


--
-- TOC entry 3184 (class 2606 OID 16415)
-- Name: cliente ukvian24qonqik61ocu33tsbft; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT ukvian24qonqik61ocu33tsbft UNIQUE (codigo_cliente);


--
-- TOC entry 3197 (class 2606 OID 16430)
-- Name: movimiento fk4ea11fe7p3xa1kwwmdgi9f2fi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk4ea11fe7p3xa1kwwmdgi9f2fi FOREIGN KEY (cuenta_id) REFERENCES public.cuenta(id);


--
-- TOC entry 3196 (class 2606 OID 16425)
-- Name: cuenta fk4p224uogyy5hmxvn8fwa2jlug; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk4p224uogyy5hmxvn8fwa2jlug FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- TOC entry 3195 (class 2606 OID 16420)
-- Name: cliente fkkpvkbjg32bso6riqge70hwcel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fkkpvkbjg32bso6riqge70hwcel FOREIGN KEY (id) REFERENCES public.persona(id);


--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-05-08 23:15:56

--
-- PostgreSQL database dump complete
--

