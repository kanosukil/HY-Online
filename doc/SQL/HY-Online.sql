CREATE TABLE IF NOT EXISTS `User`
(
    `id`            int PRIMARY KEY UNIQUE,
    `username`      varchar(20),
    `password_hash` varchar(20),
    `head_portrait` varchar(100)
);

CREATE TABLE IF NOT EXISTS `Goods`
(
    `id`          int PRIMARY KEY UNIQUE,
    `name`        varchar(40),
    `img`         varchar(100),
    `price`       double,
    `description` varchar(200)
);

CREATE TABLE IF NOT EXISTS `Store`
(
    `id`   int PRIMARY KEY UNIQUE,
    `name` varchar(40)
);

CREATE TABLE IF NOT EXISTS `Comment`
(
    `id`      int PRIMARY KEY UNIQUE,
    `content` varchar(500)
);

CREATE TABLE IF NOT EXISTS `Order`
(
    `id`      int PRIMARY KEY UNIQUE,
    `number`  int,
    `address` varchar(100)
);

CREATE TABLE IF NOT EXISTS `Role`
(
    `id`   int PRIMARY KEY UNIQUE,
    `rank` varchar(10)
);

CREATE TABLE IF NOT EXISTS `User_Store`
(
    `master_key` int,
    `store_key`  int
);

CREATE TABLE IF NOT EXISTS `User_Order`
(
    `customer_key` int,
    `order_key`    int
);

CREATE TABLE IF NOT EXISTS `Store_Order`
(
    `store_key` int,
    `order_key` int
);

CREATE TABLE IF NOT EXISTS `Store_Goods`
(
    `store_key` int,
    `goods_key` int
);

CREATE TABLE IF NOT EXISTS `User_Comment`
(
    `user_key`    int,
    `comment_key` int
);

CREATE TABLE IF NOT EXISTS `User_Role`
(
    `user_key` int,
    `role_key` int
);

ALTER TABLE `User_Store`
    ADD FOREIGN KEY (`master_key`) REFERENCES `User` (`id`);

ALTER TABLE `User_Store`
    ADD FOREIGN KEY (`store_key`) REFERENCES `Store` (`id`);

ALTER TABLE `User_Order`
    ADD FOREIGN KEY (`customer_key`) REFERENCES `User` (`id`);

ALTER TABLE `User_Order`
    ADD FOREIGN KEY (`order_key`) REFERENCES `Order` (`id`);

ALTER TABLE `Store_Order`
    ADD FOREIGN KEY (`store_key`) REFERENCES `Store` (`id`);

ALTER TABLE `Store_Order`
    ADD FOREIGN KEY (`order_key`) REFERENCES `Order` (`id`);

ALTER TABLE `Store_Goods`
    ADD FOREIGN KEY (`store_key`) REFERENCES `Store` (`id`);

ALTER TABLE `Store_Goods`
    ADD FOREIGN KEY (`goods_key`) REFERENCES `Goods` (`id`);

ALTER TABLE `User_Comment`
    ADD FOREIGN KEY (`user_key`) REFERENCES `User` (`id`);

ALTER TABLE `User_Comment`
    ADD FOREIGN KEY (`comment_key`) REFERENCES `Comment` (`id`);

ALTER TABLE `User_Role`
    ADD FOREIGN KEY (`user_key`) REFERENCES `User` (`id`);

ALTER TABLE `User_Role`
    ADD FOREIGN KEY (`role_key`) REFERENCES `Role` (`id`);


CREATE TABLE IF NOT EXISTS `Cart`
(
    `id`          int PRIMARY KEY UNIQUE,
    `total_price` double
);

CREATE TABLE IF NOT EXISTS `User_Cart`
(
    `user_key` int,
    `cart_key` int
);

ALTER TABLE `User_Cart`
    ADD FOREIGN KEY (`user_key`) REFERENCES `User` (`id`);

ALTER TABLE `User_Cart`
    ADD FOREIGN KEY (`cart_key`) REFERENCES `Cart` (`id`);

CREATE TABLE IF NOT EXISTS `Goods_Cart`
(
    `goods_key` int,
    `cart_key`  int
);

ALTER TABLE `Goods_Cart`
    ADD FOREIGN KEY (`goods_key`) REFERENCES `Goods` (`id`);

ALTER TABLE `Goods_Cart`
    ADD FOREIGN KEY (`cart_key`) REFERENCES `Cart` (`id`);