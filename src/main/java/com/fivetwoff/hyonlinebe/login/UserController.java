package com.fivetwoff.hyonlinebe.login;

import cn.hutool.core.codec.Base64;
import com.fivetwoff.hyonlinebe.entity.Cart;
import com.fivetwoff.hyonlinebe.entity.Role;
import com.fivetwoff.hyonlinebe.entity.User;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndCart;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndRole;
import com.fivetwoff.hyonlinebe.service.CartService;
import com.fivetwoff.hyonlinebe.service.RoleService;
import com.fivetwoff.hyonlinebe.service.UserService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/27 - 08:19
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRoleService userRole;
    @Autowired
    private RoleService role;
    @Autowired
    private CartService cart;
    @Autowired
    private UserCartService userCart;

    @PostMapping("/register")
    @ApiOperation("注册")
    public Result<Object> register(@RequestBody RegisterDTO register, HttpServletResponse response) {
        try {
            User user = service.findByUsername(register.getUsername());
            // 返回信息
            if (user != null) {
                response.setStatus(403);
                return new Result<>(403, -1, user);
            } else {
                user = new User();
                user.setId(service.findAll().get(service.findAll().size() - 1).getId() + 1);
                user.setUsername(register.getUsername());
                user.setPassword_hash(Base64.encode(register.getPassword()));
                if (service.insert(user)) {
                    UserAndRole ur = new UserAndRole();
                    ur.setUser_key(user.getId());
                    ur.setRole_key(1);
                    Cart ca = new Cart();
                    ca.setId(user.getId());
                    ca.setTotal_price(0.0);
                    if (!cart.insert(ca)) {
                        throw new Exception("cart表插入异常");
                    }
                    UserAndCart uc = new UserAndCart();
                    uc.setCart_key(ca.getId());
                    uc.setUser_key(user.getId());
                    if (!userCart.insert(uc)) {
                        throw new Exception("user_cart表插入异常");
                    }
                    if (userRole.insert(ur)) {
                        response.setStatus(200);
                        String token = JwtUtils.genJsonWebToken(user, List.of(role.findById(1)));
                        return new Result<>(200, user.getId(), token);
                    } else {
                        throw new Exception("user_role表插入失败");
                    }
                } else {
                    throw new Exception("user表插入异常");
                }
            }
        } catch (Exception ex) {
            log.error(ex.toString());
            response.setStatus(500);
            return new Result<>(500, -2, ex.toString());
        }
    }

    @PostMapping("/login")
    @ApiOperation("登陆成功返回token")
    public Result<String> login(@RequestBody LoginDTO login) {
        Result<String> result = null;
        String token = null;
        boolean isAdmin = false;
        User u = service.findByUsername(login.getUsername());
        if (u != null) {
            List<UserAndRole> ur = userRole.findByUser(u.getId());
            List<Role> r = new ArrayList<>(ur.size());
            for (UserAndRole ru : ur) {
                r.add(role.findById(ru.getRole_key()));
                if (ru.getRole_key() == 3) {
                    isAdmin = true;
                }
            }
            //解码
            String passwordDB = Base64.decodeStr(u.getPassword_hash());
            String passwordDTO = login.getPassword();
            if (passwordDB.equals(passwordDTO)) {
                token = JwtUtils.genJsonWebToken(u, r);
            }
        }
        if (token != null) {
            result = new Result<>(200, u.getId(), isAdmin, token);
        } else {
            result = new Result<>(404, -2, "token未知");
        }
        return result;
    }

//    @GetMapping("/user-info")
//    @ApiOperation("获取用户信息")
//    Result<User> getUserInfo(HttpServletRequest request) {
//        String userId = request.getParameter("user_id").trim();
//        User userVo = service.findById(Integer.parseInt(userId));
//        return new Result<>(200, Integer.parseInt(userId), userVo);
//    }
}
