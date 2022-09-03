
CREATE DATABASE postgres;

CREATE TABLE IF NOT EXISTS public.nftusers
(
    nftuserid integer NOT NULL DEFAULT nextval('nftusers_nftuserid_seq'::regclass),
    nftpassword character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nftusername character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT nftusers_pkey PRIMARY KEY (nftuserid)
);

INSERT INTO public.nftusers (nftuserid, nftpassword, nftusername) VALUES (1, '$2a$10$8VKNS.m/6HpzNjl2XxKl4uuBBaa.Zg/Eu/CIwM6n331VRN/zIu3.e', 'appu');
