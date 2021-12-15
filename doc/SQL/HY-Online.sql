CREATE TABLE IF NOT EXISTS `User`
(
    `id`            int PRIMARY KEY,
    `username`      varchar(20),
    `password_hash` varchar(20)
);

CREATE TABLE IF NOT EXISTS `Goods`
(
    `id`          int PRIMARY KEY,
    `name`        varchar(40),
    `img`         varchar(100),
    `price`       double,
    `description` varchar(200)
);

CREATE TABLE IF NOT EXISTS `Store`
(
    `id`   int PRIMARY KEY,
    `name` varchar(40)
);

CREATE TABLE IF NOT EXISTS `Comment`
(
    `id`      int PRIMARY KEY,
    `content` varchar(500)
);

CREATE TABLE IF NOT EXISTS `Order`
(
    `id`      int PRIMARY KEY,
    `number`  int,
    `address` varchar(100)
);

CREATE TABLE IF NOT EXISTS `Role`
(
    `id`   int PRIMARY KEY,
    `rank` varchar(10)
);

CREATE TABLE IF NOT EXISTS `User_Store`
(
    `masterkey` int,
    `storekey`  int
);

CREATE TABLE IF NOT EXISTS `User_Order`
(
    `customerkey` int,
    `orderkey`    int
);

CREATE TABLE IF NOT EXISTS `Store_Order`
(
    `storekey` int,
    `orderkey` int
);

CREATE TABLE IF NOT EXISTS `Store_Goods`
(
    `storekey` int,
    `goodskey` int
);

CREATE TABLE IF NOT EXISTS `User_Comment`
(
    `userkey`    int,
    `commentkey` int
);

CREATE TABLE IF NOT EXISTS `User_Role`
(
    `userkey` int,
    `rolekey` int
);

ALTER TABLE `User_Store`
    ADD FOREIGN KEY (`masterkey`) REFERENCES `User` (`id`);

ALTER TABLE `User_Store`
    ADD FOREIGN KEY (`storekey`) REFERENCES `Store` (`id`);

ALTER TABLE `User_Order`
    ADD FOREIGN KEY (`customerkey`) REFERENCES `User` (`id`);

ALTER TABLE `User_Order`
    ADD FOREIGN KEY (`orderkey`) REFERENCES `Order` (`id`);

ALTER TABLE `Store_Order`
    ADD FOREIGN KEY (`storekey`) REFERENCES `Store` (`id`);

ALTER TABLE `Store_Order`
    ADD FOREIGN KEY (`orderkey`) REFERENCES `Order` (`id`);

ALTER TABLE `Store_Goods`
    ADD FOREIGN KEY (`storekey`) REFERENCES `Store` (`id`);

ALTER TABLE `Store_Goods`
    ADD FOREIGN KEY (`goodskey`) REFERENCES `Goods` (`id`);

ALTER TABLE `User_Comment`
    ADD FOREIGN KEY (`userkey`) REFERENCES `User` (`id`);

ALTER TABLE `User_Comment`
    ADD FOREIGN KEY (`commentkey`) REFERENCES `Comment` (`id`);

ALTER TABLE `User_Role`
    ADD FOREIGN KEY (`userkey`) REFERENCES `User` (`id`);

ALTER TABLE `User_Role`
    ADD FOREIGN KEY (`rolekey`) REFERENCES `Role` (`id`);
