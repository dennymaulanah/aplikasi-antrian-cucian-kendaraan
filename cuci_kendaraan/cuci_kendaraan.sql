-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2019 at 12:23 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuci_kendaraan`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_admin`
--

CREATE TABLE `tb_admin` (
  `id` int(4) NOT NULL,
  `username` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_admin`
--

INSERT INTO `tb_admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_antrian`
--

CREATE TABLE `tb_antrian` (
  `id` int(11) NOT NULL,
  `pemilik` varchar(20) NOT NULL,
  `no_kendaraan` varchar(20) NOT NULL,
  `kendaraan` int(1) NOT NULL COMMENT '1. Motor, 2. Mobil',
  `tipe_kendaraan` varchar(64) NOT NULL,
  `token` varchar(12) NOT NULL,
  `waktu_antri` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `waktu_keluar` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL COMMENT '1. Menunggu 2. Proses 3.Selesai 4.Diambil'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_antrian`
--

INSERT INTO `tb_antrian` (`id`, `pemilik`, `no_kendaraan`, `kendaraan`, `tipe_kendaraan`, `token`, `waktu_antri`, `waktu_keluar`, `status`) VALUES
(7, 'Diroh', 'E 7772 EQ', 1, 'Mio', '+CbEDLjdTI', '2019-05-30 07:37:37', '0000-00-00 00:00:00', 2),
(9, 'Agus', 'E 3121 FF', 2, 'Jazz', 'E_eD=7S[uv', '2019-05-30 07:36:27', '2019-05-30 08:55:32', 3),
(10, 'Alghifari', 'E 9920 Z', 1, 'Brioz', 'Vr9TY=u[Q+', '2019-05-30 07:38:31', '2019-05-30 08:38:05', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_antrian`
--
ALTER TABLE `tb_antrian`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_admin`
--
ALTER TABLE `tb_admin`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_antrian`
--
ALTER TABLE `tb_antrian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
