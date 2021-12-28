package com.fivetwoff.hyonlinebe.login;

import com.fivetwoff.hyonlinebe.config.SystemStatus;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author VHBin
 * @date 2021/12/27 - 08:18
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final String USER = "USER";
    private final String MASTER = "MASTER";
    private final String ADMIN = "ADMIN";
    @Value("${interceptors.auth-ignore-uris}")
    private String authIgnoreUris;
    @Value("${interceptors.system-maintenance}")
    private String systemMaintenance;
    @Autowired
    private SystemStatus systemStatus;


    // 返回Json形式的数据
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        Gson g = new Gson();
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(g.toJson(obj));
        writer.close();
        response.flushBuffer();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("进入拦截器");
        TokenStatus tokenStatus = new TokenStatus();
        String uri = request.getRequestURI();
        String[] authIgnoreUriArr = authIgnoreUris.split(",");
        for (String authIgnoreUri : authIgnoreUriArr) {
            if (authIgnoreUri.equals(uri)) {
                System.out.println("登录/注册页面通过");
                return true;
            }
        }

        // 获取token
        String token = request.getHeader("Access-Token");
        if (token == null) {
            token = request.getHeader("token");
        }
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token == null) {
            response.setStatus(401);
            tokenStatus.setData(response);
            tokenStatus.setMsg("token为null,请先登录!");
            sendJsonMessage(response, tokenStatus);
            return false;
        }

        Claims claims = null;
        if (systemStatus.getStatus().equals(SystemStatus.ON)) {
            System.out.println("进入非维护状态的系统");
            claims = JwtUtils.checkJWT(token);
            if (claims == null) {
                tokenStatus.setMsg("token无效，请重新登录");
                response.setStatus(403);
                tokenStatus.setData(response);
                sendJsonMessage(response, tokenStatus);
                return false;
            } else {
                String id = (String) claims.get("id");
                String username = (String) claims.get("username");
                request.setAttribute("user_id", id);
                request.setAttribute("username", username);
                return true;
            }
        } else {
            System.out.println("进入维护状态的系统");
            claims = JwtUtils.checkJWT(token);
            if (claims == null) {
                tokenStatus.setMsg("token无效，请重新登录");
                response.setStatus(403);
                tokenStatus.setData(response);
                sendJsonMessage(response, tokenStatus);
                return false;
            } else {
                String id = (String) claims.get("id");
                String username = (String) claims.get("username");
                String roleRank = (String) claims.get("roleRank");
                request.setAttribute("user_id", id);
                request.setAttribute("username", username);
                request.setAttribute("role", roleRank);
                String[] roles = roleRank.split(",");
                // 管理员全域皆可通过
                for (String r : roles) {
                    if (this.ADMIN.equals(r.trim().toUpperCase())) {
                        response.setStatus(200);
                        return true;
                    }
                }
                // 其他用户只能通过维护页面
                if (systemMaintenance.equals(request.getRequestURI())) {
                    response.setStatus(205);
                    return true;
                } else {
                    response.setStatus(404);
                    return false;
                }
            }
        }
    }
}