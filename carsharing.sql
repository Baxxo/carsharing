-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Dic 17, 2016 alle 10:59
-- Versione del server: 10.1.13-MariaDB
-- Versione PHP: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsharing`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `auto`
--

CREATE TABLE `auto` (
  `targa` varchar(10) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modello` varchar(50) NOT NULL,
  `costo_giornaliero` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `auto`
--

INSERT INTO `auto` (`targa`, `marca`, `modello`, `costo_giornaliero`) VALUES
('AA222DS', 'FIAT', '500', '27.00'),
('AB009FG', 'SEAT', 'IBIZA', '25.00'),
('BB333EE', 'FORD', 'ESPACE', '50.00'),
('BC111KL', 'SEAT', 'LEON', '30.00');

-- --------------------------------------------------------

--
-- Struttura della tabella `noleggi`
--

CREATE TABLE `noleggi` (
  `codice_noleggio` int(11) NOT NULL,
  `auto` varchar(10) NOT NULL,
  `socio` varchar(16) NOT NULL,
  `inizio` date NOT NULL,
  `fine` date NOT NULL,
  `auto_restituita` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `noleggi`
--

INSERT INTO `noleggi` (`codice_noleggio`, `auto`, `socio`, `inizio`, `fine`, `auto_restituita`) VALUES
(1, 'AB009FG', 'RSSMRA19T54A000Z', '2015-12-23', '2016-01-03', 1),
(2, 'AB009FG', 'RSSMRA19T54A000Z', '2016-04-15', '2016-04-30', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `soci`
--

CREATE TABLE `soci` (
  `cf` varchar(16) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `soci`
--

INSERT INTO `soci` (`cf`, `cognome`, `nome`, `indirizzo`, `telefono`) VALUES
('BNCLGO68B80E111T', 'BIANCHI', 'OLGA', 'VIA XXIV GIUGNO, 100/A ROMA', ''),
('DMALDA18D91A000A', 'SMILZO', 'ALDO', 'VICOLO ITALIA, 120 TREVISO', '333889900112'),
('RSSLCA21A78A000Q', 'ROSSI', 'LUCA', 'VIALE ROMANO, 17 MILANO', '34789891234'),
('RSSMRA19T54A000Z', 'ROSSI', 'MARIO', 'VIA DEL SOLE, 41 TREVISO', '34511223344'),
('VRDNNA41C66S456W', 'VERDI', 'ANNA', 'VIA PIAVE, 18 TREVISO', '34511223344');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`targa`);

--
-- Indici per le tabelle `noleggi`
--
ALTER TABLE `noleggi`
  ADD PRIMARY KEY (`codice_noleggio`);

--
-- Indici per le tabelle `soci`
--
ALTER TABLE `soci`
  ADD PRIMARY KEY (`cf`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `noleggi`
--
ALTER TABLE `noleggi`
  MODIFY `codice_noleggio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
