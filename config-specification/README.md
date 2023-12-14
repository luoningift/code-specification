#### 配置文件格式规范
##### 一、配置文件目录规范

配置文件目录为/home/用户/sys/.config/
目录结构为

```
├── config.json
├── hs
│   └── config.json
└── trx
└── └── config.json

原目录放到安装包和实际生产环境，禁止使用oem下的config.json覆盖上层目录的config.json
```
##### 二、配置文件格式规范

```json
{
  "common" : {
    "desc" : "通用配置",
    "mysql" : {
      "username" : "datafort",
      "password" : "encrypt_password",
    },
    "redis" : {
      "username" : "datafort",
      "password" : "encrypt_password",
    },
    "pika" : {
      "username" : "datafort",
      "password" : "encrypt_password",
    },
    "dbMap" : [
      {
        "identity" : "mysql",
        "code": 10,
        "name": "mysql数据库"
      }
    ]
  },
  "user_business": {
    "desc" : "用户相关配置",
    "sec_user_password" : "encrypt_password",
    "sys_user_password" : "encrypt_password",
    "aut_user_password" : "encrypt_password"
  },
  "message_business": {
    "desc" : "消息相关配置",
    "duration": 10
  }
}
```