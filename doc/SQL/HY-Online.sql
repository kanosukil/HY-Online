create table cart
(
    id          int    not null
        primary key,
    total_price double null,
    constraint cart_id_uindex
        unique (id)
);

create table comment
(
    id      int          not null
        primary key,
    content varchar(500) null,
    constraint comment_id_uindex
        unique (id)
);

create table goods
(
    id          int          not null
        primary key,
    name        varchar(40)  null,
    img         varchar(100) null,
    price       double       null,
    description varchar(200) null,
    number      int          null,
    constraint goods_id_uindex
        unique (id)
);

create table goods_cart
(
    goods_key int null,
    cart_key  int null,
    constraint goods_cart_ibfk_1
        foreign key (goods_key) references goods (id),
    constraint goods_cart_ibfk_2
        foreign key (cart_key) references cart (id)
);

create index goods_key
    on goods_cart (goods_key);

create table goods_comment
(
    goods_key   int null,
    comment_key int null,
    constraint goods_comment_comment_id_fk
        foreign key (goods_key) references comment (id),
    constraint goods_comment_goods_id_fk
        foreign key (goods_key) references goods (id)
);

create table orders
(
    id      int          not null
        primary key,
    number  int          null,
    address varchar(100) null,
    constraint order_id_uindex
        unique (id)
);

create table goods_order
(
    goods_key int null,
    order_key int null,
    constraint goods_order_ibfk_1
        foreign key (goods_key) references goods (id),
    constraint goods_order_ibfk_2
        foreign key (order_key) references orders (id)
);

create table role
(
    id     int         not null
        primary key,
    `rank` varchar(10) null,
    constraint role_id_uindex
        unique (id)
);

create table store
(
    id   int         not null
        primary key,
    name varchar(40) null,
    constraint store_id_uindex
        unique (id)
);

create table store_goods
(
    store_key int null,
    goods_key int null,
    constraint store_goods_ibfk_1
        foreign key (store_key) references store (id),
    constraint store_goods_ibfk_2
        foreign key (goods_key) references goods (id)
);

create index goods_key
    on store_goods (goods_key);

create index store_key
    on store_goods (store_key);

create table user
(
    id            int          not null
        primary key,
    username      varchar(20)  null,
    password_hash varchar(100) null,
    head_portrait varchar(100) null,
    constraint user_id_uindex
        unique (id)
);

create table user_cart
(
    user_key int null,
    cart_key int null,
    constraint user_cart_ibfk_1
        foreign key (user_key) references user (id),
    constraint user_cart_ibfk_2
        foreign key (cart_key) references cart (id)
);

create index user_key
    on user_cart (user_key);

create table user_comment
(
    user_key    int null,
    comment_key int null,
    constraint user_comment_ibfk_1
        foreign key (user_key) references user (id),
    constraint user_comment_ibfk_2
        foreign key (comment_key) references comment (id)
);

create index comment_key
    on user_comment (comment_key);

create index user_key
    on user_comment (user_key);

create table user_order
(
    customer_key int null,
    order_key    int null,
    constraint user_order_ibfk_1
        foreign key (customer_key) references user (id),
    constraint user_order_ibfk_2
        foreign key (order_key) references orders (id)
);

create index customer_key
    on user_order (customer_key);

create index order_key
    on user_order (order_key);

create table user_role
(
    user_key int null,
    role_key int null,
    constraint user_role_ibfk_1
        foreign key (user_key) references user (id),
    constraint user_role_ibfk_2
        foreign key (role_key) references role (id)
);

create index role_key
    on user_role (role_key);

create index user_key
    on user_role (user_key);

create table user_store
(
    master_key int null,
    store_key  int null,
    constraint user_store_ibfk_1
        foreign key (master_key) references user (id),
    constraint user_store_ibfk_2
        foreign key (store_key) references store (id)
);

create index master_key
    on user_store (master_key);

create index store_key
    on user_store (store_key);


