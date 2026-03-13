-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 10 Μαρ 2026 στις 15:31:32
-- Έκδοση διακομιστή: 10.4.32-MariaDB
-- Έκδοση PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `eshop`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `categories`
--

INSERT INTO `categories` (`category_id`, `name`) VALUES
(1, 'Writing Instruments'),
(2, 'Paper Products'),
(3, 'Desk Accessories'),
(4, 'Office Technology');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `customers`
--

INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `email`, `phone`, `city`, `address`) VALUES
(1, 'Alexandros', 'Petrou', 'alex.petrou@email.com', '6900000001', 'Athens', '12 Solonos St'),
(2, 'Maria', 'Kara', 'maria.kara@email.com', '6900000002', 'Thessaloniki', '55 Egnatia St'),
(3, 'Dimitris', 'Lagos', 'd.lagos@email.com', '6900000003', 'Patras', '22 Korinthou St'),
(4, 'Eleni', 'Vlachou', 'eleni.v@email.com', '6900000004', 'Heraklion', '9 Knossou Ave'),
(5, 'Nikos', 'Tasos', 'nik.tasos@email.com', '6900000005', 'Athens', '88 Patision St');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `employees`
--

INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `position`, `store_id`) VALUES
(1, 'Nikos', 'Papadopoulos', 'Store Manager', 1),
(2, 'Maria', 'Ioannou', 'Sales Associate', 1),
(3, 'Giorgos', 'Kostas', 'Cashier', 1),
(4, 'Eleni', 'Dimitriou', 'Store Manager', 2),
(5, 'Kostas', 'Nikolaidis', 'Sales Associate', 2),
(6, 'Anna', 'Pavlou', 'Cashier', 2),
(7, 'Dimitris', 'Georgiou', 'Store Manager', 3),
(8, 'Sofia', 'Antonopoulou', 'Sales Associate', 3),
(9, 'Petros', 'Karalis', 'Cashier', 3),
(10, 'Manolis', 'Kritikos', 'Store Manager', 4),
(11, 'Irini', 'Markaki', 'Sales Associate', 4),
(12, 'Giorgos', 'Stavros', 'Cashier', 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `total_amount` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `order_date`, `status`, `total_amount`) VALUES
(1, 1, '2026-02-10', 'Completed', 18.50),
(2, 2, '2026-02-12', 'Completed', 12.00),
(3, 3, '2026-02-15', 'Pending', 35.00),
(4, 4, '2026-02-18', 'Completed', 65.00),
(5, 5, '2026-02-20', 'Shipped', 27.50);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `order_items`
--

CREATE TABLE `order_items` (
  `order_item_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `order_items`
--

INSERT INTO `order_items` (`order_item_id`, `order_id`, `product_id`, `quantity`, `price`) VALUES
(1, 1, 1, 10, 0.50),
(2, 1, 18, 2, 1.50),
(3, 2, 34, 2, 3.50),
(4, 3, 41, 1, 12.00),
(5, 3, 47, 1, 20.00),
(6, 4, 45, 1, 65.00),
(7, 5, 27, 1, 6.00),
(8, 5, 33, 5, 1.30);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `products`
--

INSERT INTO `products` (`product_id`, `name`, `price`, `stock`, `category_id`) VALUES
(1, 'Ballpoint Pen Blue', 0.50, 500, 1),
(2, 'Ballpoint Pen Black', 0.50, 500, 1),
(3, 'Gel Pen', 1.20, 300, 1),
(4, 'Mechanical Pencil', 1.50, 200, 1),
(5, 'Wooden Pencil HB', 0.30, 600, 1),
(6, 'Permanent Marker', 1.80, 150, 1),
(7, 'Whiteboard Marker', 2.00, 120, 1),
(8, 'Highlighter Yellow', 1.10, 200, 1),
(9, 'Highlighter Pink', 1.10, 200, 1),
(10, 'Fountain Pen', 8.50, 50, 1),
(11, 'Refill Ink Cartridge', 2.50, 120, 1),
(12, 'Calligraphy Pen', 7.00, 40, 1),
(13, 'Colored Pencil Set', 5.00, 80, 1),
(14, 'A4 Printer Paper 500 sheets', 5.50, 200, 2),
(15, 'A3 Printer Paper 500 sheets', 8.50, 100, 2),
(16, 'Notebook A4', 3.00, 150, 2),
(17, 'Notebook A5', 2.00, 200, 2),
(18, 'Sticky Notes Small', 1.50, 300, 2),
(19, 'Sticky Notes Large', 2.50, 250, 2),
(20, 'Envelope Pack C4', 4.00, 100, 2),
(21, 'Envelope Pack C5', 3.50, 120, 2),
(22, 'Graph Paper Pad', 2.20, 80, 2),
(23, 'Legal Pad', 2.80, 70, 2),
(24, 'Index Cards', 1.90, 90, 2),
(25, 'Colored Paper Pack', 6.50, 60, 2),
(26, 'Presentation Paper Premium', 9.00, 40, 2),
(27, 'Stapler', 6.00, 70, 3),
(28, 'Staples Pack', 1.50, 200, 3),
(29, 'Paper Clips Box', 1.20, 300, 3),
(30, 'Binder Clips Pack', 2.00, 180, 3),
(31, 'Desk Organizer', 9.50, 60, 3),
(32, 'Tape Dispenser', 4.00, 90, 3),
(33, 'Adhesive Tape', 1.30, 150, 3),
(34, 'Scissors', 3.50, 100, 3),
(35, 'Ruler 30cm', 1.00, 200, 3),
(36, 'Calculator Basic', 7.50, 80, 3),
(37, 'File Folder', 1.80, 150, 3),
(38, 'Ring Binder', 3.20, 120, 3),
(39, 'USB Flash Drive 16GB', 7.00, 90, 4),
(40, 'USB Flash Drive 32GB', 10.00, 80, 4),
(41, 'Wireless Mouse', 12.00, 60, 4),
(42, 'Keyboard USB', 15.00, 50, 4),
(43, 'Printer Ink Black', 18.00, 40, 4),
(44, 'Printer Ink Color', 22.00, 35, 4),
(45, 'External Hard Drive 1TB', 65.00, 20, 4),
(46, 'Laptop Stand', 25.00, 30, 4),
(47, 'Desk Lamp LED', 20.00, 40, 4),
(48, 'Label Maker', 30.00, 15, 4),
(49, 'Paper Shredder', 55.00, 10, 4),
(50, 'Webcam HD', 35.00, 25, 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `stores`
--

CREATE TABLE `stores` (
  `store_id` int(11) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `stores`
--

INSERT INTO `stores` (`store_id`, `city`, `address`) VALUES
(1, 'Athens', '25 Ermou Street'),
(2, 'Thessaloniki', '12 Tsimiski Street'),
(3, 'Patras', '45 Agiou Andreou Street'),
(4, 'Heraklion', '18 Dedalou Street');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `store_inventory`
--

CREATE TABLE `store_inventory` (
  `inventory_id` int(11) NOT NULL,
  `store_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `store_inventory`
--

INSERT INTO `store_inventory` (`inventory_id`, `store_id`, `product_id`, `quantity`) VALUES
(1, 1, 1, 200),
(2, 1, 14, 100),
(3, 1, 27, 40),
(4, 2, 1, 150),
(5, 2, 18, 120),
(6, 2, 41, 25),
(7, 3, 33, 90),
(8, 3, 34, 60),
(9, 3, 47, 30),
(10, 4, 45, 10),
(11, 4, 48, 12),
(12, 4, 50, 15);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Ευρετήρια για πίνακα `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Ευρετήρια για πίνακα `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `store_id` (`store_id`);

--
-- Ευρετήρια για πίνακα `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Ευρετήρια για πίνακα `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`order_item_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Ευρετήρια για πίνακα `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Ευρετήρια για πίνακα `stores`
--
ALTER TABLE `stores`
  ADD PRIMARY KEY (`store_id`);

--
-- Ευρετήρια για πίνακα `store_inventory`
--
ALTER TABLE `store_inventory`
  ADD PRIMARY KEY (`inventory_id`),
  ADD KEY `store_id` (`store_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`);

--
-- Περιορισμοί για πίνακα `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Περιορισμοί για πίνακα `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Περιορισμοί για πίνακα `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Περιορισμοί για πίνακα `store_inventory`
--
ALTER TABLE `store_inventory`
  ADD CONSTRAINT `store_inventory_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`),
  ADD CONSTRAINT `store_inventory_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
