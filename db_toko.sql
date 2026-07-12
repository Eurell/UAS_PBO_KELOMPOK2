-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 12, 2026 at 07:23 PM
-- Server version: 8.0.45
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_toko`
--

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kode` int NOT NULL,
  `kategori` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `brand` varchar(100) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kode`, `kategori`, `brand`) VALUES
(2, 'Action Figure', 'Bandai Namco'),
(9, 'Action Figure', 'Bandai Spirits'),
(1, 'Action Figure', 'Good Smile Company'),
(8, 'Action Figure', 'Max Factory'),
(12, 'Aksesoris', 'COSPA'),
(11, 'Apparel/Cosplay', 'Dokidoki Cosplay'),
(10, 'Apparel/Cosplay', 'Uwowo Cosplay'),
(6, 'Manga/Komik', 'Elex Media Komputindo'),
(7, 'Manga/Komik', 'Level Comic'),
(5, 'Manga/Komik', 'M&C! Comics'),
(4, 'Manga/Komik', 'Shounen Jump'),
(3, 'Manga/Komik', 'Shueisha');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode` int NOT NULL,
  `kode_produk` int NOT NULL,
  `nama` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `harga` int NOT NULL,
  `stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kode`, `kode_produk`, `nama`, `harga`, `stock`) VALUES
(1, 1, 'Nendoroid Gojo Satoru', 850000, 5),
(2, 2, 'Gundam Aerial HG 1/144', 350000, 12),
(3, 3, 'Komik One Piece Vol 100', 45000, 20),
(4, 8, 'Figma Tanjiro Kamado DX edition', 1600000, 12),
(5, 7, 'Attack on Titan', 82000, 30),
(6, 5, 'Hai, Miiko!', 33000, 40),
(7, 10, 'Kostum Violet Evergarden', 8000000, 12),
(8, 6, 'One Piece Vol. 111', 42000, 33),
(9, 6, 'Jujuutsu Kaisen Edisi Reguler', 50000, 23),
(10, 6, 'Spy x Family Vol. 13', 45000, 34),
(11, 6, 'Detektif Conan Vol.105', 50000, 32),
(12, 12, 'Hoodie Onne Piece', 1200000, 24),
(13, 4, 'Naruto Shipuden Vol.123', 99000, 23),
(14, 8, 'Figma Eren Yeager', 1300000, 21),
(15, 9, 'S>H Figuarts Son Goku', 7000000, 12),
(16, 11, 'Kostum Gojo Satoru', 600000, 23),
(17, 12, 'T-Shirt Gundam', 1200000, 11),
(18, 12, 'Tas Selempang/Totebag Anime', 400000, 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kode`),
  ADD UNIQUE KEY `kategori` (`kategori`,`brand`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode`),
  ADD UNIQUE KEY `nama` (`nama`),
  ADD KEY `kode_produk` (`kode_produk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kode` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `kode` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kode_produk`) REFERENCES `kategori` (`kode`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
