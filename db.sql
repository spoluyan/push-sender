--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-05-21 16:28:58 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE task1;
--
-- TOC entry 2066 (class 1262 OID 16384)
-- Name: task1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE task1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ru_RU.UTF-8' LC_CTYPE = 'ru_RU.UTF-8';


ALTER DATABASE task1 OWNER TO postgres;

\connect task1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 180 (class 3079 OID 11899)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 180
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 16395)
-- Name: auth_tokens; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE auth_tokens (
    id bigint NOT NULL,
    userid bigint,
    deviceid character varying(255),
    pushtoken character varying(255),
    device_platform smallint
);


ALTER TABLE auth_tokens OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16417)
-- Name: auth_tokens_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_tokens_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000000
    CACHE 1;


ALTER TABLE auth_tokens_seq OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16390)
-- Name: device_platforms; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE device_platforms (
    id smallint NOT NULL,
    name character varying(100)
);


ALTER TABLE device_platforms OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16415)
-- Name: device_platforms_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE device_platforms_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100
    CACHE 1;


ALTER TABLE device_platforms_seq OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16421)
-- Name: push_tasks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE push_tasks (
    id smallint NOT NULL,
    text character varying(255),
    day_of_week smallint,
    "time" smallint,
    device_platforms character varying(100),
    is_scheduled boolean
);


ALTER TABLE push_tasks OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16429)
-- Name: push_tasks_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE push_tasks_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 100
    CACHE 1;


ALTER TABLE push_tasks_seq OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16385)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    firstname character varying(100)
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16413)
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000000
    CACHE 1;


ALTER TABLE users_seq OWNER TO postgres;

--
-- TOC entry 1948 (class 2606 OID 16402)
-- Name: auth_tokens_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY auth_tokens
    ADD CONSTRAINT auth_tokens_id PRIMARY KEY (id);


--
-- TOC entry 1946 (class 2606 OID 16394)
-- Name: device_platforms_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY device_platforms
    ADD CONSTRAINT device_platforms_id PRIMARY KEY (id);


--
-- TOC entry 1950 (class 2606 OID 16428)
-- Name: push_tasks_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY push_tasks
    ADD CONSTRAINT push_tasks_id PRIMARY KEY (id);


--
-- TOC entry 1944 (class 2606 OID 16389)
-- Name: users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- TOC entry 1952 (class 2606 OID 16408)
-- Name: device_platforms_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_tokens
    ADD CONSTRAINT device_platforms_fk FOREIGN KEY (device_platform) REFERENCES device_platforms(id);


--
-- TOC entry 1951 (class 2606 OID 16403)
-- Name: users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_tokens
    ADD CONSTRAINT users_fk FOREIGN KEY (userid) REFERENCES users(id);


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-05-21 16:28:59 MSK

--
-- PostgreSQL database dump complete
--

