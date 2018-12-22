-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 22 Ara 2018, 14:07:23
-- Sunucu sürümü: 5.7.23
-- PHP Sürümü: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `dbyolarkadas`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `konu`
--

DROP TABLE IF EXISTS `konu`;
CREATE TABLE IF NOT EXISTS `konu` (
  `konuID` int(11) NOT NULL AUTO_INCREMENT,
  `konuAdi` varchar(255) NOT NULL,
  `konuIcerik` text NOT NULL,
  `uyeID` int(11) NOT NULL,
  `konuTipi` int(11) NOT NULL,
  PRIMARY KEY (`konuID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `konu`
--

INSERT INTO `konu` (`konuID`, `konuAdi`, `konuIcerik`, `uyeID`, `konuTipi`) VALUES
(1, 'Film1', 'çok güzel bir film', 1, 2),
(2, 'film2', 'sadasdsad', 1, 2),
(3, 'gramer', 'asdasdasd', 1, 1),
(4, 'gramer2', 'asdsadad', 1, 1),
(5, 'kitap1', 'kitap1', 1, 3),
(6, 'kitap2', 'adasd', 1, 3),
(7, 'muzik1', 'adads', 1, 4),
(8, 'muzik2', 'asdadad', 1, 4),
(9, 'oneri1', 'asdasda', 1, 5),
(10, 'oneri2', 'asdad', 1, 5),
(11, 'film3', 'film cok iyi', 1, 2),
(12, 'film4', 'film efsaneydi Ã§ok yararl', 1, 2),
(13, 'gramer3', 'gramer 3 Ã§ok yararl', 1, 1),
(14, 'kitap5', 'asdasdasd', 1, 3),
(15, 'film 5', 'film efsaneydi', 1, 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `konular`
--

DROP TABLE IF EXISTS `konular`;
CREATE TABLE IF NOT EXISTS `konular` (
  `konuID` int(11) NOT NULL,
  `konuAdi` varchar(50) NOT NULL,
  PRIMARY KEY (`konuID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `konular`
--

INSERT INTO `konular` (`konuID`, `konuAdi`) VALUES
(1, 'Ingilizce kaynak '),
(2, 'Film'),
(3, 'Kitap'),
(4, 'Müzik'),
(5, 'Takip oneri siteleri');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `uye`
--

DROP TABLE IF EXISTS `uye`;
CREATE TABLE IF NOT EXISTS `uye` (
  `uyeID` int(11) NOT NULL AUTO_INCREMENT,
  `kullaniciAdi` varchar(50) NOT NULL,
  `sifre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `uyelikDerecesi` int(11) NOT NULL,
  PRIMARY KEY (`uyeID`),
  KEY `uyelikDerecesi` (`uyelikDerecesi`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `uye`
--

INSERT INTO `uye` (`uyeID`, `kullaniciAdi`, `sifre`, `email`, `uyelikDerecesi`) VALUES
(1, 'd', '1', 'cengiz', 1),
(2, 'asda', 'dsada', 'adads', 2),
(3, 'asda', 'ada', 'adad', 2),
(4, 'cengo', '1234', 'cengo', 2),
(5, 'cengo', '123', 'cengogmail.com', 2),
(6, 'dada', '123', 'dad@gmail.com', 2),
(7, 'ASD', '1', 'asd', 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `uyeturu`
--

DROP TABLE IF EXISTS `uyeturu`;
CREATE TABLE IF NOT EXISTS `uyeturu` (
  `ID` int(11) NOT NULL,
  `turAdi` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `uyeturu`
--

INSERT INTO `uyeturu` (`ID`, `turAdi`) VALUES
(1, 'Admin'),
(2, 'Normal');

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `uye`
--
ALTER TABLE `uye`
  ADD CONSTRAINT `uye_ibfk_1` FOREIGN KEY (`uyelikDerecesi`) REFERENCES `uyeturu` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
