SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `cart`
VALUES (1, 20);
INSERT INTO `cart`
VALUES (2, 100);


INSERT INTO `comment`
VALUES (1, '很棒!');
INSERT INTO `comment`
VALUES (2, 'suki');
INSERT INTO `comment`
VALUES (3, 'Well');
INSERT INTO `comment`
VALUES (4, 'xxxx');


INSERT INTO `goods`
VALUES (1, '商品', 'default', 7.14, '一件商品', NULL);
INSERT INTO `goods`
VALUES (2, 'xxx', 'default', 2.2, 'xxx', 1);
INSERT INTO `goods`
VALUES (4, 'xxx', 'default', 2.2, 'xxxxx', 1);


INSERT INTO `goods_cart`
VALUES (2, 2);
INSERT INTO `goods_cart`
VALUES (1, 2);

INSERT INTO `goods_comment`
VALUES (1, 1);
INSERT INTO `goods_comment`
VALUES (1, 1);
INSERT INTO `goods_comment`
VALUES (1, 1);
INSERT INTO `goods_comment`
VALUES (1, 3);
INSERT INTO `goods_comment`
VALUES (1, 4);


INSERT INTO `goods_order`
VALUES (1, 1);
INSERT INTO `goods_order`
VALUES (2, 2);
INSERT INTO `goods_order`
VALUES (1, 3);
INSERT INTO `goods_order`
VALUES (2, 4);
INSERT INTO `goods_order`
VALUES (2, 5);


INSERT INTO `orders`
VALUES (1, 1, '南昌大学前湖校区天健园');
INSERT INTO `orders`
VALUES (2, 10, '南昌大学青山湖校区');
INSERT INTO `orders`
VALUES (3, 1, 'default');
INSERT INTO `orders`
VALUES (4, 1, 'default');
INSERT INTO `orders`
VALUES (5, 1, 'default');

INSERT INTO `role`
VALUES (1, 'USER');
INSERT INTO `role`
VALUES (2, 'MASTER');
INSERT INTO `role`
VALUES (3, 'ADMIN');

INSERT INTO `store`
VALUES (1, '一间商店');
INSERT INTO `store`
VALUES (2, 'JavaLearn');
INSERT INTO `store`
VALUES (3, NULL);


INSERT INTO `store_goods`
VALUES (1, 1);
INSERT INTO `store_goods`
VALUES (2, 2);
INSERT INTO `store_goods`
VALUES (2, 4);


INSERT INTO `user`
VALUES (1, 'VHBin', 'eHh4eA==', 'default');
INSERT INTO `user`
VALUES (2, '丿丶Sana', 'OTE3MDkyMDA3', 'default');
INSERT INTO `user`
VALUES (3, 'admin', 'MTIzNDU2', 'default');
INSERT INTO `user`
VALUES (4, 'lll', 'MTAyMDlzZGFk', 'default');


INSERT INTO `user_cart`
VALUES (1, 1);
INSERT INTO `user_cart`
VALUES (2, 2);


INSERT INTO `user_comment`
VALUES (1, 1);
INSERT INTO `user_comment`
VALUES (2, 2);
INSERT INTO `user_comment`
VALUES (1, 3);
INSERT INTO `user_comment`
VALUES (1, 4);

INSERT INTO `user_order`
VALUES (1, 1);
INSERT INTO `user_order`
VALUES (2, 2);
INSERT INTO `user_order`
VALUES (1, 3);
INSERT INTO `user_order`
VALUES (1, 4);
INSERT INTO `user_order`
VALUES (1, 5);


INSERT INTO `user_role`
VALUES (1, 1);
INSERT INTO `user_role`
VALUES (2, 2);
INSERT INTO `user_role`
VALUES (3, 3);
INSERT INTO `user_role`
VALUES (1, 2);
INSERT INTO `user_role`
VALUES (1, 2);
INSERT INTO `user_role`
VALUES (4, 1);
INSERT INTO `user_role`
VALUES (2, 2);


INSERT INTO `user_store`
VALUES (1, 1);
INSERT INTO `user_store`
VALUES (2, 2);
INSERT INTO `user_store`
VALUES (2, 3);

SET FOREIGN_KEY_CHECKS = 1;
