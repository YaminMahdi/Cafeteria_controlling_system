-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 03:18 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafeteria_controlling_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `homedeliverylist`
--

CREATE TABLE `homedeliverylist` (
  `date` date DEFAULT NULL,
  `OrderNo` int(11) NOT NULL,
  `CustomerName` varchar(30) DEFAULT NULL,
  `ItemNo_Quantity` varchar(50) DEFAULT NULL,
  `TotalPrice` int(11) DEFAULT NULL,
  `Location` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `homedeliverylist`
--

INSERT INTO `homedeliverylist` (`date`, `OrderNo`, `CustomerName`, `ItemNo_Quantity`, `TotalPrice`, `Location`) VALUES
('2020-12-08', 1, 'Fatema', '32-2,', 140, 'Doratana');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`Password`) VALUES
('yk00');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `ItemNo` int(11) NOT NULL,
  `Category` varchar(30) DEFAULT NULL,
  `FoodName` varchar(30) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`ItemNo`, `Category`, `FoodName`, `Price`) VALUES
(1, 'Burger', 'Chicken Burger', 80),
(2, 'Burger', 'Chicken Cheese Burger', 100),
(3, 'Burger', 'Beef Burger', 70),
(4, 'Burger', 'Beef Cheese Burger', 90),
(5, 'Burger', 'Vegetable Burger', 50),
(6, 'Pizza', 'Chicken Pizza', 170),
(7, 'Pizza', 'Chicken Cheese Pizza', 180),
(8, 'Pizza', 'Beef Pizza', 170),
(9, 'Pizza', 'Beef Cheese Pizza', 180),
(10, 'Noodles & Pasta', 'Egg Fried Noodles', 50),
(11, 'Noodles & Pasta', 'Chicken Noodles', 60),
(12, 'Noodles & Pasta', 'Chicken Pasta', 100),
(13, 'Noodles & Pasta', 'Beef Pasta', 100),
(14, 'Sandwich', 'Vegetable Sandwich', 30),
(15, 'Sandwich', 'Chicken Sandwich', 40),
(16, 'Sandwich', 'Grill Sandwich', 100),
(17, 'Fry Items', 'Fried Chicken (2pcs)', 80),
(18, 'Fry Items', 'Thai Fried Chicken', 100),
(19, 'Fry Items', 'Crispy Chicken (4pcs)', 150),
(20, 'Fry Items', 'French Fry', 70),
(21, 'Fry Items', 'Chicken Wings (2pcs)', 70),
(22, 'Fry Items', 'Chicken Drum Stick', 70),
(23, 'Cakes', 'Black Forest Cake', 50),
(24, 'Cakes', 'White Forest Cake', 50),
(25, 'Cakes', 'Rainbow Cake', 100),
(26, 'Refreshment', 'Cold Drinks', 20),
(27, 'Refreshment', 'Coffee', 40),
(28, 'Refreshment', 'Clod Coffee', 60),
(29, 'Refreshment', 'Lassi', 50),
(30, 'Refreshment', 'Faluda (Regular)', 80),
(31, 'Refreshment', 'Faluda (Special)', 100),
(32, 'Refreshment', 'Special Ice-Cream', 70);

-- --------------------------------------------------------

--
-- Table structure for table `orderlist`
--

CREATE TABLE `orderlist` (
  `date` date DEFAULT NULL,
  `TokenNo` int(11) NOT NULL,
  `CustomerName` varchar(30) DEFAULT NULL,
  `ItemNo_Quantity` varchar(50) DEFAULT NULL,
  `TotalPrice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderlist`
--

INSERT INTO `orderlist` (`date`, `TokenNo`, `CustomerName`, `ItemNo_Quantity`, `TotalPrice`) VALUES
('2020-12-06', 1, 'Muhib', '19-1,', 150),
('2020-12-06', 2, 'Muaz', '15-1,29-1,', 90),
('2020-12-06', 3, 'Sadia', '32-1,23-2,', 170),
('2020-12-07', 4, 'Tamim', '27-1,32-2,', 180),
('2020-12-08', 5, 'Hossain', '22-1,15-1,', 110),
('2020-12-08', 8, 'Imran', '20-2,', 140),
('2020-12-08', 9, 'Savu', '18-1,16-2,', 300);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `No` int(11) NOT NULL,
  `CustomerName` varchar(30) DEFAULT NULL,
  `Review` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`No`, `CustomerName`, `Review`) VALUES
(1, 'Imran', 'I just love the PIZZA.'),
(2, 'Hossain', 'Good pricing.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `homedeliverylist`
--
ALTER TABLE `homedeliverylist`
  ADD PRIMARY KEY (`OrderNo`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`Password`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`ItemNo`);

--
-- Indexes for table `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`TokenNo`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`No`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `homedeliverylist`
--
ALTER TABLE `homedeliverylist`
  MODIFY `OrderNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `ItemNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `TokenNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
