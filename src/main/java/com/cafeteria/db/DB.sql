CREATE TABLE Users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(20),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE MenuItems (
    foodItemId INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(5, 2) NOT NULL,
    availability BOOLEAN NOT NULL
);

CREATE TABLE Feedback (
    feedbackId INT AUTO_INCREMENT PRIMARY KEY,
    foodItemId INT NOT NULL,
    qualityRating INT,
    rating DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (foodItemId) REFERENCES MenuItems(foodItemId)
);

CREATE TABLE Notifications (
    notificationId INT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE RecommendedMenu (
    foodItemId INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rating DOUBLE,
);

CREATE TABLE RollOutItems (
    foodItemId INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(5, 2) NOT NULL,
    vote INT
);

CREATE TABLE Report (
    foodItemId INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rating DOUBLE,
    comments TEXT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Triggers and Stored Procedures as needed
