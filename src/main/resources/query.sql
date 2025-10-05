-- Create the database
CREATE DATABASE IF NOT EXISTS `bangladesh_tour_guide`;
USE `bangladesh_tour_guide`;

-- Table for Districts
CREATE TABLE `districts` (
                             `district_id` INT AUTO_INCREMENT PRIMARY KEY,
                             `district_name` VARCHAR(255) NOT NULL UNIQUE
);

-- Table for Spots
CREATE TABLE `spots` (
                         `spot_id` INT AUTO_INCREMENT PRIMARY KEY,
                         `spot_name` VARCHAR(255) NOT NULL,
                         `district_id` INT NOT NULL,
                         `photo_url` VARCHAR(500), -- Store path or URL to an image
                         `ticket_price` DECIMAL(10, 2),
                         `facilities` TEXT,
                         FOREIGN KEY (`district_id`) REFERENCES `districts`(`district_id`) ON DELETE CASCADE
);

-- Table for Transports
CREATE TABLE `transports` (
                              `transport_id` INT AUTO_INCREMENT PRIMARY KEY,
                              `district_id` INT NOT NULL,
                              `transport_medium` VARCHAR(100) NOT NULL,
                              `cost` DECIMAL(10, 2) NOT NULL,
                              FOREIGN KEY (`district_id`) REFERENCES `districts`(`district_id`) ON DELETE CASCADE
);

-- Table for Foods
CREATE TABLE `foods` (
                         `food_id` INT AUTO_INCREMENT PRIMARY KEY,
                         `food_name` VARCHAR(255) NOT NULL,
                         `price` DECIMAL(10, 2) NOT NULL,
                         `rating` DECIMAL(2, 1), -- e.g., 4.5
                         `district_id` INT, -- Assuming food can be associated with a district
                         FOREIGN KEY (`district_id`) REFERENCES `districts`(`district_id`) ON DELETE SET NULL
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(128) NOT NULL,
    role VARCHAR(50) DEFAULT 'user'
    );


-- Sample user: username = admin, password = password
-- SHA-256("password") = 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8
INSERT INTO users (username, password_hash, role) VALUES ('admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'admin');

-- Sample Data (Optional, but good for testing)
INSERT INTO `districts` (`district_name`) VALUES
                                              ('Dhaka'), ('Chittagong'), ('Cox''s Bazar'), ('Sylhet');

INSERT INTO `spots` (`spot_name`, `district_id`, `photo_url`, `ticket_price`, `facilities`) VALUES
                                                                                                ('Ahsan Manzil', 1, 'path/to/ahsan_manzil.jpg', 100.00, 'Museum, river view'),
                                                                                                ('Lalbagh Fort', 1, 'path/to/lalbagh_fort.jpg', 50.00, 'Historical site'),
                                                                                                ('Cox''s Bazar Beach', 3, 'path/to/cox_bazar_beach.jpg', 0.00, 'Longest sea beach, surfing, paragliding');

INSERT INTO `transports` (`district_id`, `transport_medium`, `cost`) VALUES
                                                                         (1, 'Bus', 500.00), (1, 'Train', 600.00), (3, 'Bus', 1200.00), (3, 'Plane', 4000.00);

INSERT INTO `foods` (`food_name`, `price`, `rating`, `district_id`) VALUES
                                                                        ('Biryani', 250.00, 4.5, 1),
                                                                        ('Fuchka', 80.00, 4.0, 1),
                                                                        ('Sea Fish Fry', 400.00, 4.8, 3);