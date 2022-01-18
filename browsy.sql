-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2022 at 09:11 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `browsy`
--
CREATE DATABASE IF NOT EXISTS `browsy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `browsy`;

-- --------------------------------------------------------

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
CREATE TABLE `bookmark` (
  `id` int(11) NOT NULL,
  `pageId` int(11) DEFAULT NULL,
  `folderId` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `bookmark`
--

INSERT INTO `bookmark` (`id`, `pageId`, `folderId`, `createdAt`) VALUES
(1, 5, 1, NULL),
(2, 26, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `download`
--

DROP TABLE IF EXISTS `download`;
CREATE TABLE `download` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `downloadedAt` datetime DEFAULT NULL,
  `locationInSystem` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `size` decimal(10,0) DEFAULT NULL,
  `status` varchar(15) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `folder`
--

INSERT INTO `folder` (`id`, `name`) VALUES
(1, 'Bookmark');

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `pageId` int(11) DEFAULT NULL,
  `createdAt` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id`, `pageId`, `createdAt`) VALUES
(1, 4, '2022-01-17'),
(2, 5, '2022-01-17'),
(3, 6, '2022-01-17'),
(4, 7, '2022-01-17'),
(5, 8, '2022-01-18'),
(6, 9, '2022-01-18'),
(7, 10, '2022-01-18'),
(8, 11, '2022-01-18'),
(9, 12, '2022-01-18'),
(10, 13, '2022-01-18'),
(11, 14, '2022-01-18'),
(12, 15, '2022-01-18'),
(13, 16, '2022-01-18'),
(14, 17, '2022-01-18'),
(15, 18, '2022-01-18'),
(16, 19, '2022-01-18'),
(17, 20, '2022-01-18'),
(18, 21, '2022-01-18'),
(19, 22, '2022-01-18'),
(20, 23, '2022-01-18'),
(21, 24, '2022-01-18'),
(22, 25, '2022-01-18'),
(23, 29, '2022-01-18'),
(24, 30, '2022-01-18'),
(25, 31, '2022-01-18'),
(26, 32, '2022-01-18'),
(27, 33, '2022-01-18'),
(28, 34, '2022-01-18'),
(29, 35, '2022-01-18'),
(30, 36, '2022-01-18'),
(31, 37, '2022-01-18'),
(32, 38, '2022-01-18'),
(33, 39, '2022-01-18'),
(34, 40, '2022-01-18'),
(35, 41, '2022-01-18'),
(36, 42, '2022-01-18'),
(37, 43, '2022-01-18'),
(38, 44, '2022-01-18'),
(39, 45, '2022-01-18'),
(40, 46, '2022-01-18'),
(41, 47, '2022-01-18'),
(42, 48, '2022-01-18'),
(43, 49, '2022-01-18'),
(44, 50, '2022-01-18'),
(45, 51, '2022-01-18'),
(46, 52, '2022-01-18'),
(47, 53, '2022-01-18'),
(48, 54, '2022-01-18'),
(49, 55, '2022-01-18'),
(50, 56, '2022-01-18'),
(51, 57, '2022-01-18'),
(52, 58, '2022-01-18'),
(53, 59, '2022-01-18'),
(54, 60, '2022-01-18'),
(55, 61, '2022-01-18'),
(56, 62, '2022-01-18'),
(57, 63, '2022-01-18'),
(58, 64, '2022-01-18'),
(59, 65, '2022-01-18'),
(60, 66, '2022-01-18'),
(61, 67, '2022-01-18'),
(62, 68, '2022-01-18'),
(63, 69, '2022-01-18'),
(64, 70, '2022-01-18'),
(65, 71, '2022-01-18'),
(66, 72, '2022-01-18'),
(67, 73, '2022-01-18'),
(68, 74, '2022-01-18'),
(69, 75, '2022-01-18'),
(70, 76, '2022-01-18'),
(71, 77, '2022-01-18'),
(72, 78, '2022-01-18'),
(73, 79, '2022-01-18'),
(74, 80, '2022-01-18'),
(75, 81, '2022-01-18'),
(76, 82, '2022-01-18'),
(77, 83, '2022-01-18'),
(78, 84, '2022-01-18'),
(79, 85, '2022-01-18'),
(80, 86, '2022-01-18'),
(81, 87, '2022-01-18'),
(82, 88, '2022-01-18'),
(83, 89, '2022-01-18'),
(84, 90, '2022-01-18'),
(85, 91, '2022-01-18'),
(86, 92, '2022-01-18'),
(87, 93, '2022-01-18'),
(88, 94, '2022-01-18'),
(89, 95, '2022-01-18'),
(90, 96, '2022-01-18'),
(91, 97, '2022-01-18'),
(92, 98, '2022-01-18');

-- --------------------------------------------------------

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `link` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `page`
--

INSERT INTO `page` (`id`, `name`, `link`) VALUES
(4, 'Google', 'https://www.google.com/'),
(5, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(6, 'Google', 'https://www.google.com/'),
(7, 'Google', 'https://www.google.com/'),
(8, 'Google', 'https://www.google.com/'),
(9, 'Google', 'https://www.google.com/'),
(10, 'Google', 'https://www.google.com/'),
(11, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(12, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(13, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(14, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(15, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(16, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(17, 'javafx – Recherche Qwant', 'https://www.qwant.com/?q=javafx&t=web'),
(18, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(19, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(20, 'Google', 'https://www.google.com/'),
(21, 'Tutorials List - Javatpoint', 'https://www.javatpoint.com/'),
(22, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(23, 'Tutorials List - Javatpoint', 'https://www.javatpoint.com/'),
(24, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(25, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(26, 'java – Recherche Qwant', 'https://www.qwant.com/?q=java&t=web'),
(27, 'java – Recherche Qwant', 'https://www.qwant.com/?q=java&t=web'),
(28, 'java – Recherche Qwant', 'https://www.qwant.com/?q=java&t=web'),
(29, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(30, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(31, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(32, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(33, 'PDF Drive - Search and download PDF files for free.', 'https://www.pdfdrive.com/'),
(34, 'Living in the Light: A guide to personal transformation by David Sargent - PDF Drive', 'https://www.pdfdrive.com/living-in-the-light-a-guide-to-personal-transformation-d10172273.html'),
(35, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(36, 'cour uml pdf – Recherche Qwant', 'https://www.qwant.com/?q=cour+uml+pdf&t=web'),
(37, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(38, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(39, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(40, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(41, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(42, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(43, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(44, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(45, 'Google', 'https://www.google.com/'),
(46, 'Google', 'https://www.google.com/'),
(47, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(48, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(49, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(50, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(51, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(52, 'Google', 'https://www.google.com/'),
(53, 'Google', 'https://www.google.com/'),
(54, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=java'),
(55, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=java&t=web'),
(56, 'java – Recherche Qwant', 'https://www.google.com/'),
(57, 'java - Recherche Google', 'https://www.google.com/search?q=java+fx&ei=aHDmYaSDO6WZjLsPk-C0kAc&ved=0ahUKEwik1_bF57r1AhWlDGMBHRMwDXIQ4dUDCA0&uact=5&oq=java+fx&gs_lcp=Cgdnd3Mtd2l6EAMyBwgAEIAEEAoyBwgAEIAEEAoyBQgAEIAEMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKOgcIABBHELADOgcIABCwAxBDOgoIABDkAhCwAxgAOgwILhDIAxCwAxBDGAFKBAhBGABKBAhGGAFQlAVY4QlgkQxoAXACeACAAe4BiAHbA5IBBTAuMi4xmAEAoAEByAERwAEB2gEGCAAQARgJ2gEGCAEQARgI&sclient=gws-wiz'),
(58, 'java fx - Recherche Google', 'http://www.qwant.com/'),
(59, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=javafx'),
(60, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=javafx&t=web'),
(61, 'javafx – Recherche Qwant', 'https://www.google.com/'),
(62, 'Google', 'https://www.google.com/search?q=java&source=hp&ei=83DmYZWzCaWkgwfT9pboCg&iflsig=ALs-wAMAAAAAYeZ_AyBHO_P3W5Ef6T62XYe8jWVreSvA&ved=0ahUKEwjV-OiH6Lr1AhUl0uAKHVO7Ba0Q4dUDCAo&uact=5&oq=java&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOggILhCABBCxAzoOCC4QgAQQsQMQxwEQrwE6DgguEIAEELEDEMcBEKMCOgUILhCABDoLCC4QgAQQsQMQgwFQQljdA2CbBmgBcAB4AIABd4gBtQOSAQMxLjOYAQCgAQGwAQA&sclient=gws-wiz'),
(63, 'java - Recherche Google', 'http://www.qwant.com/'),
(64, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=java'),
(65, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/?q=java&t=web'),
(66, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'http://www.qwant.com/'),
(67, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.google.com/'),
(68, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.google.com/'),
(69, 'Google', 'https://www.google.com/search?q=java&source=hp&ei=pnbmYYuxMYSGjLsP1L2ZkAc&iflsig=ALs-wAMAAAAAYeaEtnOn_S1stWBF64C097v_Mz1h49XZ&ved=0ahUKEwiLnOu_7br1AhUEA2MBHdReBnIQ4dUDCAo&uact=5&oq=java&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOggILhCABBCxAzoOCC4QgAQQsQMQxwEQrwE6DgguEIAEELEDEMcBEKMCOgUILhCABDoLCC4QgAQQsQMQgwFQhwFY2gRgqAZoAXAAeACAAY0BiAH7A5IBAzAuNJgBAKABAbABAA&sclient=gws-wiz'),
(70, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.google.com/'),
(71, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.google.com/'),
(72, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(73, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(74, 'Google', 'https://www.google.com/'),
(75, 'java - Recherche Google', 'https://www.google.com/search?q=java&source=hp&ei=mXjmYffUBruBjLsPzIOpsAo&iflsig=ALs-wAMAAAAAYeaGqbO9pXTc8zc8wiWL6mkG9l0ehVu3&ved=0ahUKEwi3hbmt77r1AhW7AGMBHcxBCqYQ4dUDCAo&uact=5&oq=java&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOggILhCABBCxAzoOCC4QgAQQsQMQxwEQrwE6DgguEIAEELEDEMcBEKMCOgUILhCABDoLCC4QgAQQsQMQgwFQAFj4AmC9BWgAcAB4AIABdogBtAOSAQMwLjSYAQCgAQE&sclient=gws-wiz'),
(76, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(77, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(78, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(79, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(80, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(81, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(82, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(83, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(84, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(85, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(86, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(87, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(88, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(89, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(90, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(91, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(92, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(93, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(94, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(95, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(96, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(97, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/'),
(98, 'Qwant - Le moteur de recherche qui respecte votre vie privée', 'https://www.qwant.com/');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookmark`
--
ALTER TABLE `bookmark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pageId` (`pageId`),
  ADD KEY `folderId` (`folderId`);

--
-- Indexes for table `download`
--
ALTER TABLE `download`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `folder`
--
ALTER TABLE `folder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pageId` (`pageId`);

--
-- Indexes for table `page`
--
ALTER TABLE `page`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookmark`
--
ALTER TABLE `bookmark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `download`
--
ALTER TABLE `download`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `folder`
--
ALTER TABLE `folder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `page`
--
ALTER TABLE `page`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookmark`
--
ALTER TABLE `bookmark`
  ADD CONSTRAINT `bookmark_ibfk_1` FOREIGN KEY (`pageId`) REFERENCES `page` (`id`),
  ADD CONSTRAINT `bookmark_ibfk_2` FOREIGN KEY (`folderId`) REFERENCES `folder` (`id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`pageId`) REFERENCES `page` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
