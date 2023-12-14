#### 项目模块规范

##### 一、项目模块规范

```

调用模块install.sh -p=vm -o=zaxy
其中-p 为 机器型号 -o 为 oem厂商

├── install.sh
├── plugin 扩展支持
│   ├── default
│   │   ├── business.sh
│   │   ├── other.sh
│   │   ├── system.sh
│   │   └── web.sh
│   ├── hs
│   │   ├── business.sh
│   │   ├── other.sh
│   │   ├── system.sh
│   │   └── web.sh
│   └── trx
│       ├── business.sh
│       ├── other.sh
│       ├── system.sh
│       └── web.sh

```


##### 二、Java 模块引入

```
见java-module-project-specification/README.md
```