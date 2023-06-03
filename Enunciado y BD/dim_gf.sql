-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 03-06-2023 a las 23:42:32
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dim_gf`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

DROP TABLE IF EXISTS `autores`;
CREATE TABLE IF NOT EXISTS `autores` (
  `id_autor` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `nombre_autor` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `id_pais` int NOT NULL COMMENT 'FK -> paises',
  PRIMARY KEY (`id_autor`),
  KEY `id_pais` (`id_pais`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`id_autor`, `nombre_autor`, `id_pais`) VALUES
(1, 'Botticelli', 1),
(2, 'Jan Matejko', 2),
(3, 'Katsushika Hokusai', 3),
(4, 'Velázquez', 4),
(5, 'Vermeer', 5),
(6, 'Rembrandt', 6),
(7, 'Gian Lorenzo Bernini', 7),
(8, 'Henri Matisse', 8),
(9, 'Hanna Pauli', 9),
(10, 'Rafael Sanzio', 10),
(11, 'Caravaggio', 11),
(12, 'Jan Matejko', 12),
(13, 'Fernando Botero', 13),
(14, 'Henri Matisse', 14),
(15, 'Tiziano', 15),
(16, 'René Magritte', 16),
(17, 'Théodore Géricault', 17),
(18, 'El Greco', 18),
(19, 'Van Gogh', 19),
(20, 'Edvard Munch', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `museos`
--

DROP TABLE IF EXISTS `museos`;
CREATE TABLE IF NOT EXISTS `museos` (
  `id_museo` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `nombre_museo` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `id_pais` int NOT NULL COMMENT 'FK -> paises',
  PRIMARY KEY (`id_museo`),
  KEY `id_pais` (`id_pais`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `museos`
--

INSERT INTO `museos` (`id_museo`, `nombre_museo`, `id_pais`) VALUES
(1, 'Galería de los Uffizi', 1),
(2, 'Museo Nacional de Polonia', 2),
(3, 'The Metropolitan Museum of Art. Nueva York, EE.UU.', 3),
(4, 'Museo del Hermitage', 4),
(5, 'Rijksmuseum, Amsterdam, Países Bajos', 5),
(6, 'Rijksmuseum, Amsterdam, Países Bajos', 6),
(7, 'Museo de Victoria y Alberto', 7),
(8, 'Museo de arte moderno Pompidou', 8),
(9, 'Museo nacional de Estocolmo', 9),
(10, 'Museo Borghese', 10),
(11, 'Galería Nacional de Arte Antiguo', 11),
(12, 'Museo Nacional de Polonia', 12),
(13, 'Museo Botero', 13),
(14, 'Museo del Hermitage', 14),
(15, 'Museo del Hermitage en San Petersburgo', 15),
(16, 'Museo de Magritte', 16),
(17, 'Museo del Louvre', 17),
(18, 'Museo del Prado', 18),
(19, 'Musée d Orsay', 19),
(20, 'Nacional de Noruega', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obras`
--

DROP TABLE IF EXISTS `obras`;
CREATE TABLE IF NOT EXISTS `obras` (
  `id_obra` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `nombre_obra` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descripción_obra` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `disciplina` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `id_museo` int NOT NULL COMMENT 'FK -> museos',
  `id_autor` int NOT NULL COMMENT 'FK -> autores',
  PRIMARY KEY (`id_obra`),
  KEY `id_museo` (`id_museo`),
  KEY `id_autor` (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paises`
--

DROP TABLE IF EXISTS `paises`;
CREATE TABLE IF NOT EXISTS `paises` (
  `id_pais` int NOT NULL AUTO_INCREMENT,
  `nombre_pais` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paises`
--

INSERT INTO `paises` (`id_pais`, `nombre_pais`) VALUES
(1, 'Italiana'),
(2, 'Polaca'),
(3, 'Japonesa'),
(4, 'Española'),
(5, 'Paises Bajos'),
(6, 'Paises Bajos'),
(7, 'Italia'),
(8, 'Frances'),
(9, 'Estocolmo'),
(10, 'Italiana'),
(11, 'Italiana'),
(12, 'Polaca'),
(13, 'Colombiano'),
(14, 'Frances'),
(15, 'Italiana'),
(16, 'Belga'),
(17, 'Frances'),
(18, 'Griego'),
(19, 'Paises Bajos'),
(20, 'Noruego');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

DROP TABLE IF EXISTS `ranking`;
CREATE TABLE IF NOT EXISTS `ranking` (
  `id_ranking` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `nombre_jugador` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `puntos_jugador` int NOT NULL,
  PRIMARY KEY (`id_ranking`),
  UNIQUE KEY `id_ranking` (`id_ranking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autores`
--
ALTER TABLE `autores`
  ADD CONSTRAINT `autores_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`);

--
-- Filtros para la tabla `museos`
--
ALTER TABLE `museos`
  ADD CONSTRAINT `museos_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`);

--
-- Filtros para la tabla `obras`
--
ALTER TABLE `obras`
  ADD CONSTRAINT `obras_ibfk_1` FOREIGN KEY (`id_museo`) REFERENCES `museos` (`id_museo`),
  ADD CONSTRAINT `obras_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
