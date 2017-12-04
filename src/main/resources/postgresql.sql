CREATE DATABASE multishop;

CREATE TABLE public.produkt
(
    numer_produktu SERIAL PRIMARY KEY NOT NULL,
    nazwa VARCHAR(30) NOT NULL,
    cena_jedn DOUBLE PRECISION NOT NULL,
    opis_produktu VARCHAR(50) NOT NULL,
    producent VARCHAR(20) NOT NULL,
    kategoria VARCHAR(20) NOT NULL,
    na_stanie INT NOT NULL,
    zamowione INT NOT NULL,
    aktywny BOOLEAN NOT NULL
);

CREATE TABLE public.zamowienie
(
    numer_zamowienia SERIAL PRIMARY KEY NOT NULL,
    numer_klienta VARCHAR(20) NOT NULL,
    numer_produktu INT NOT NULL,
    kwota DOUBLE PRECISION NOT NULL,
    data_zamowienia DATE NOT NULL,
    status_zamowienia VARCHAR(10) NOT NULL
);

CREATE TABLE public.klient
(
    numer_klienta SERIAL PRIMARY KEY NOT NULL,
    imie_klienta VARCHAR(20) NOT NULL,
    nazwisko_klienta VARCHAR(30) NOT NULL,
    adres_klienta VARCHAR(50) NOT NULL,
    miasto VARCHAR(20) NOT NULL,
    kraj VARCHAR(10) NOT NULL,
    email VARCHAR(30) NOT NULL,
    telefon VARCHAR(20) NOT NULL
);