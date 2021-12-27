package com.fivetwoff.hyonlinebe;

import cn.hutool.core.codec.Base64;
import com.fivetwoff.hyonlinebe.cascade.*;
import com.fivetwoff.hyonlinebe.entity.*;
import com.fivetwoff.hyonlinebe.service.*;
import com.fivetwoff.hyonlinebe.service.cascade.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/24 - 09:37
 */


@SpringBootTest
public class ServiceTest {
    @Autowired
    private GoodsCartService goodsCart;
    @Autowired
    private StoreGoodsService storeGoods;
    @Autowired
    private StoreOrderService storeOrder;
    @Autowired
    private UserCartService userCart;
    @Autowired
    private UserCommentService userComment;
    @Autowired
    private UserOrderService userOrder;
    @Autowired
    private UserRoleService userRole;
    @Autowired
    private UserStoreService userStore;
    @Autowired
    private CartService cart;
    @Autowired
    private CommentService comment;
    @Autowired
    private GoodsService goods;
    @Autowired
    private OrderService order;
    @Autowired
    private RoleService role;
    @Autowired
    private StoreService store;
    @Autowired
    private UserService user;

    @Autowired
    private GoodsCommentService goodsAndComment;

    @Test
    public void CommentGoodsTest() {
        GoodsAndComment gc = new GoodsAndComment();
        gc.setComment_key(1);
        gc.setGoods_key(1);
        goodsAndComment.insert(gc);
        gc.setGoods_key(2);
        goodsAndComment.insert(gc);
        gc.setGoods_key(1);
        gc.setComment_key(2);
        goodsAndComment.insert(gc);
        System.out.println(goodsAndComment.findByComment(1));
        System.out.println(goodsAndComment.findByGoods(1));
        goodsAndComment.deleteByComment(2);
        goodsAndComment.deleteByGoods(2);
    }

    @Test
    public void SQLSelectTest() {
        List<Cart> carts = cart.findAll();
        for (Cart c : carts) {
            System.out.println(c);
        }
        System.out.println(cart.findById(1));

        List<Comment> comments = comment.findAll();
        for (Comment c : comments) {
            System.out.println(c);
        }
        System.out.println(comment.findById(1));

        List<Goods> goodsList = goods.findAll();
        for (Goods g : goodsList) {
            System.out.println(g);
        }
        System.out.println(goods.findById(1));

        List<Orders> orders = order.findAll();
        for (Orders o : orders) {
            System.out.println(o);
        }
        System.out.println(order.findById(1));

        List<Role> roles = role.findAll();
        for (Role r : roles) {
            System.out.println(r);
        }
        System.out.println(role.findById(1));

        List<Store> stores = store.findAll();
        for (Store s : stores) {
            System.out.println(s);
        }
        System.out.println(store.findById(1));

        List<User> users = user.findAll();
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println(user.findById(1));

        List<GoodsAndCart> goodsAndC = goodsCart.findByGoods(1);
        for (GoodsAndCart gc : goodsAndC) {
            System.out.println(gc);
        }
        List<GoodsAndCart> cartAndG = goodsCart.findByCart(1);
        for (GoodsAndCart gc : cartAndG) {
            System.out.println(gc);
        }

        List<StoreAndGoods> storeAndG = storeGoods.findByStore(1);
        for (StoreAndGoods sg : storeAndG) {
            System.out.println(sg);
        }
        List<StoreAndGoods> goodsAndS = storeGoods.findByGoods(1);
        for (StoreAndGoods sg : goodsAndS) {
            System.out.println(sg);
        }

        List<StoreAndOrder> storeAndO = storeOrder.findByStore(1);
        for (StoreAndOrder so : storeAndO) {
            System.out.println(so);
        }
        List<StoreAndOrder> orderAndS = storeOrder.findByOrder(1);
        for (StoreAndOrder so : orderAndS) {
            System.out.println(so);
        }

        UserAndCart userAndC = userCart.findByUser(1);
        System.out.println(userAndC);
        UserAndCart cartAndU = userCart.findByCart(1);
        System.out.println(cartAndU);

        List<UserAndComment> userAndComments = userComment.findByUser(1);
        for (UserAndComment uc : userAndComments) {
            System.out.println(uc);
        }
        List<UserAndComment> commentAndUsers = userComment.findByComment(1);
        for (UserAndComment uc : commentAndUsers) {
            System.out.println(uc);
        }

        List<UserAndOrder> userAndO = userOrder.findByUser(1);
        for (UserAndOrder uo : userAndO) {
            System.out.println(uo);
        }
        List<UserAndOrder> orderAndU = userOrder.findByOrder(1);
        for (UserAndOrder uo : orderAndU) {
            System.out.println(uo);
        }

        List<UserAndRole> userAndR = userRole.findByUser(1);
        for (UserAndRole ur : userAndR) {
            System.out.println(ur);
        }
        List<UserAndRole> roleAndU = userRole.findByRole(1);
        for (UserAndRole ur : roleAndU) {
            System.out.println(ur);
        }

        List<UserAndStore> userAndS = userStore.findByUser(1);
        for (UserAndStore us : userAndS) {
            System.out.println(us);
        }
        List<UserAndStore> storeAndU = userStore.findByStore(1);
        for (UserAndStore us : storeAndU) {
            System.out.println(us);
        }
    }

    @Test
    public void SQLInsertTest() {
        Cart cartTest = new Cart();
        cartTest.setId(2);
        cartTest.setTotal_price(29.9);
        if (cart.insert(cartTest)) {
            System.out.println("OK 1");
        }
        Comment commentTest = new Comment();
        commentTest.setId(2);
        commentTest.setContent("垃圾");
        if (comment.insert(commentTest)) {
            System.out.println("OK 2");
        }
        Goods goodsTest = new Goods();
        goodsTest.setId(2);
        goodsTest.setName("商品二");
        goodsTest.setPrice(12.2);
        goodsTest.setImg("default");
        goodsTest.setDescription("一件商品二");
        if (goods.insert(goodsTest)) {
            System.out.println("OK 3");
        }
        Orders ordersTest = new Orders();
        ordersTest.setId(2);
        ordersTest.setNumber(2);
        ordersTest.setAddress("南昌大学前湖校区修闲");
        if (order.insert(ordersTest)) {
            System.out.println("OK 4");
        }
        Store storeTest = new Store();
        storeTest.setId(2);
        storeTest.setName("商店二");
        if (store.insert(storeTest)) {
            System.out.println("OK 5");
        }
        User userTest = new User();
        userTest.setId(2);
        userTest.setUsername("VHBin");
        userTest.setPassword_hash("123123123");
        userTest.setHead_portrait("default");
        if (user.insert(userTest)) {
            System.out.println("OK 6");
        }
        GoodsAndCart goodsAndCartTest = new GoodsAndCart();
        goodsAndCartTest.setCart_key(2);
        goodsAndCartTest.setGoods_key(2);
        if (goodsCart.insert(goodsAndCartTest)) {
            System.out.println("OK 7");
        }
        StoreAndGoods storeAndGoodsTest = new StoreAndGoods();
        storeAndGoodsTest.setGoods_key(2);
        storeAndGoodsTest.setStore_key(2);
        if (storeGoods.insert(storeAndGoodsTest)) {
            System.out.println("OK 8");
        }
        StoreAndOrder storeAndOrderTest = new StoreAndOrder();
        storeAndOrderTest.setStore_key(2);
        storeAndOrderTest.setOrder_key(2);
        if (storeOrder.insert(storeAndOrderTest)) {
            System.out.println("OK 9");
        }
        UserAndCart userAndCartTest = new UserAndCart();
        userAndCartTest.setCart_key(2);
        userAndCartTest.setUser_key(2);
        if (userCart.insert(userAndCartTest)) {
            System.out.println("OK 10");
        }
        UserAndComment userAndCommentTest = new UserAndComment();
        userAndCommentTest.setUser_key(2);
        userAndCommentTest.setComment_key(2);
        if (userComment.insert(userAndCommentTest)) {
            System.out.println("OK 11");
        }
        UserAndOrder userAndOrderTest = new UserAndOrder();
        userAndOrderTest.setOrder_key(2);
        userAndOrderTest.setCustomer_key(2);
        if (userOrder.insert(userAndOrderTest)) {
            System.out.println("OK 12");
        }
        UserAndRole userAndRoleTest = new UserAndRole();
        userAndRoleTest.setUser_key(2);
        userAndRoleTest.setRole_key(2);
        if (userRole.insert(userAndRoleTest)) {
            System.out.println("OK 13");
        }
        UserAndStore userAndStoreTest = new UserAndStore();
        userAndStoreTest.setStore_key(2);
        userAndStoreTest.setMaster_key(2);
        if (userStore.insert(userAndStoreTest)) {
            System.out.println("OK 14");
        }
    }

    @Test
    public void SQLDeleteTest() {
        if (cart.deleteById(2)) {
            System.out.println("OK 1");
        }
        if (comment.deleteById(2)) {
            System.out.println("OK 2");
        }
        if (goods.deleteById(2)) {
            System.out.println("OK 3");
        }
        if (order.deleteById(2)) {
            System.out.println("OK 4");
        }
        if (store.deleteById(2)) {
            System.out.println("OK 5");
        }
        if (user.deleteById(2)) {
            System.out.println("OK 6");
        }

//        if (goodsCart.deleteByCart(2)) {
//            System.out.println("OK 11");
//        }
//        if (storeGoods.deleteByGoods(2)) {
//            System.out.println("OK 12");
//        }
//        if (storeOrder.deleteByOrder(2)) {
//            System.out.println("OK 13");
//        }
//        if (userCart.deleteByCart(2)) {
//            System.out.println("OK 14");
//        }
//        if (userComment.deleteByComment(2)) {
//            System.out.println("OK 15");
//        }
//        if (userOrder.deleteByOrder(2)) {
//            System.out.println("OK 16");
//        }
//        if (userRole.deleteByRole(2)) {
//            System.out.println("OK 17");
//        }
//        if (userStore.deleteByStore(2)) {
//            System.out.println("OK 18");
//        }

//        if (goodsCart.deleteByGoods(2)) {
//            System.out.println("OK 21");
//        }
//        if (storeGoods.deleteByStore(2)) {
//            System.out.println("OK 22");
//        }
//        if (storeOrder.deleteByStore(2)) {
//            System.out.println("OK 23");
//        }
//        if (userCart.deleteByUser(2)) {
//            System.out.println("OK 24");
//        }
//        if (userComment.deleteByUser(2)) {
//            System.out.println("OK 25");
//        }
//        if (userOrder.deleteByUser(2)) {
//            System.out.println("OK 26");
//        }
//        if (userRole.deleteByUser(2)) {
//            System.out.println("OK 27");
//        }
//        if (userStore.deleteByUser(2)) {
//            System.out.println("Ok 28");
//        }
    }

    @Test
    public void SQLUpdateTest() {
        Cart cartUpdate = cart.findById(2);
        cartUpdate.setTotal_price(100.0);
        if (cart.update(cartUpdate)) {
            System.out.println("OK 1");
        }
        Comment commentUpdate = comment.findById(2);
        commentUpdate.setContent("suki");
        if (comment.update(commentUpdate)) {
            System.out.println("OK 2");
        }
        Goods goodsUpdate = goods.findById(2);
        goodsUpdate.setName("<<SpringBoot 框架入门>>");
        goodsUpdate.setPrice(1000.0);
        goodsUpdate.setDescription("a book about Spring Boot");
        if (goods.update(goodsUpdate)) {
            System.out.println("OK 3");
        }
        Orders ordersUpdate = order.findById(2);
        ordersUpdate.setNumber(10);
        ordersUpdate.setAddress("南昌大学青山湖校区");
        if (order.update(ordersUpdate)) {
            System.out.println("OK 4");
        }
        Store storeUpdate = store.findById(2);
        storeUpdate.setName("JavaLearn");
        if (store.update(storeUpdate)) {
            System.out.println("OK 5");
        }
        User userUpdate = user.findById(2);
        userUpdate.setUsername("丿丶Sana");
        userUpdate.setPassword_hash("917092007");
        if (user.update(userUpdate)) {
            System.out.println("OK 6");
        }
    }

    @Test
    public void updateUserPassword() {
        List<User> users = user.findAll();
        users.get(0).setPassword_hash(Base64.encode("xxxx"));
        users.get(1).setPassword_hash(Base64.encode("917092007"));
        users.get(2).setPassword_hash(Base64.encode("123456"));
        for (User u : users) {
            user.update(u);
        }

    }
}
