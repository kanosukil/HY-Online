# HY-Online-be

### 部署地址:

### 数据库模型地址: https://dbdiagram.io/d/61b8ac668c901501c0f0231b

### 数据库SQL文件: [HY-Online-be.sql](doc/SQL/HY-Online.sql)

### 项目地址: http://101.132.145.198:8080/

### 接口文档:

+ /user
    - /register: 注册
        - 方法: POST
        - 接收:
        ```
        { 
            “username”: “string”, 
            “password”: "string”
        }
        ```
        - 返回:
        ```
        {
            code”: 状态码,
            “userId”: number,
            “isAdmin”: boolean,
            "data": {
                "id": number,
                "username": "string",
                "password_hash": "string",
                "head_portrait": "default" // 该项(头像)可忽略,值默认为 default
            }

        }
        ```
    - /login: 登录
        - 方法: POST
        - 接收:
        ```
        {
            "username": "string",
            "password": "string"
        }
        ```
        - 返回:
        ```
        {
            "code": 状态码,
            "userId": number,
            "isAdmin": boolean,
            "data": "token"
        }
        ```
+ /cart
    - /get: 获取购物车的内容
        - 方法: GET
        - 接收:

      `?uId=number // 用户id`

        - 返回:
        ```
        {
            "code": number,
            "userId": number, 
            "info": "string",
            "list": [
                {
                    "storeName": "一间商店",
                    "goods": {
                        "id": 1,
                        "name": "商品",
                        "img": "default",
                        "price": 7.14,
                        "description": "一件商品",
                        "number": 1
                    }
                },
                {
                    "storeName": "一间商店",
                    "goods": {
                        "id": 2,
                        "name": "<<SpringBoot 框架入门>>",
                        "img": "default",
                        "price": 1000.0,
                        "description": "a book about Spring Boot",
                        "number": 1
                    }
                }
            ]
        }
        ```
    - /delete: 删除购物车内容
        - 方法:POST
        - 接收:
        ```
        {
            "uid": number,
            "gid": number
        }
        ```
        - 返回:
        ```
        {
            "code": number,
            "info": "string"
        }
        ```
    - /pay: 支付购物车的全部内容
        - 方法:POST
        - 接收:

      `?uId=number // 用户id`

        - 返回:
        ```
        {
            "code": number,
            "info": "string"
        }
        ```
    - /buy: 支付购物车内选择的内容
        - 方法:POST
        - 接收:
        ```
        {
            "uid": number,
            "gids": [numbers,]
        }
        ```
        - 返回:
        ```
        {
            "code": number,
            "info": "string"
        }
        ```
+ /comment
    - / :获得评论内容
        - 方法: GET
        - 接收:

      `?gId=number // 商品id`

        - 返回:
        ```
        {
            "code": number,
            "info": "string",
            "comments": [
                {
                    "id": 1,
                    "content": "很棒!"
                },
                {
                    "id": 1,
                    "content": "很棒!"
                },
                {
                    "id": 1,
                    "content": "很棒!"
                },
                {
                    "id": 3,
                    "content": "Well"
                }
            ]
        }
        ```
    - / : 添加评论
        - 方法: POST
        - 接收:
        ```
        {
            "gid": number,
            "comment": "string",
            "uid": number
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
+ /store
    - /show: 展示商店的商品
        - 方法: GET
        - 接收:

      `?uid=number // 用户id`

        - 返回:
        ```
        {
            "code": number,
            "info": "string",
            "userId": "1",
            "storeName": "一间商店",
            "list": [
                {
                    "id": 1,
                    "name": "商品",
                    "img": "default",
                    "price": 7.14,
                    "description": "一件商品",
                    "number": 1
                }
            ]
        }
        ```
    - /add: 添加商品
        - 方法: POST
        - 接收:
        ```
        {
            "goodsName": "string",
            "description": "string",
            "price": "string",
            "uid": number
        }

        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }

        ```
    - /delete: 删除商品
        - 方法: POST
        - 接收:
        ```
        {
            "gid": number,
            "uid": number
        }

        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
    - /update: 更新商品信息
        - 方法: POST
        - 接收:
        ```
        {
            "update": {
                "goodsTitle": "string",
                "goodsPrice": "string",
                "goodsSubtitle": "string"
            },
            "gid": number,
            "uid": number
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
+ /homepage
    - / :显示所有商品
        - 方法: GET
        - 接收: 无
        - 返回:
        ```
        {
            "code": number,
            "info": "string",
            "goodsList": [
                {
                    "id": number,
                    "storeName": "string",
                    "img": "string",
                    "context": "string",
                    "price": "string
                },
                ...
            ]
        }
        ```
    - / : 添加到购物车
        - 方法: POST
        - 接收:
        ```
        {
            "gid": number,
            "uid": number
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
+ /manage
    - /get_all_store: 返回所有商店
        - 方法: GET
        - 接收: 无
        - 返回:
        ```
        {
            "code": number,
            "info": "string",
            "list": [
                {
                    "id": 1,
                    "name": "一间商店"
                },
                {
                    "id": 2,
                    "name": "JavaLearn"
                },
                {
                    "id": 3,
                    "name": "xxxx"
                }
            ]
        }
        ```
    - /change_status: 改变系统维护状态
        - 方法: POST
        - 接收:
        ```
        {
            “status”: true // 意为可通过,不在维护 false:意为不可通过,在维护
        }

        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
    - /get_status: 获取当前系统维护状态
        - 方法: GET
        - 接收: 无
        - 返回:
        ```
        {
            "code": 200,
            "status": true
        }
        ```
    - /close_store: 关闭商店
        - 方法: POST
        - 接收:
        ```
        {
            “storeIds”: [numbers, ]
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```
    - /is_open_store: 用户是否开通了商店
        - 方法: POST
        - 接收:
        ```
        {
            “userId”: number
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "toOpenStore": true,
            "info": "string"
        }

        ```
    - /create_store: 创建商店
        - 方法: POST
        - 接收:
        ```
        {
            "storeNam": “string”,
            "userKey": number
        }
        ```
        - 返回:
        ```
        {
            "code": 200,
            "info": "string"
        }
        ```