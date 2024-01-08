#### 编码规范

| 目录 | 说明 | 详细说明 |
| ------ | ------ | ------ |
| java-project-specification | java web项目规范 [代码示例](java-project-specification/README.md) [oem相关记录规范](java-project-specification/src/main/java/plugin/datafort/afw/hs/README.md) |
| module-project-specification | 项目模块相关规范 [代码示例](module-project-specification/README.md)| [java模块帮助文档](module-project-specification/java-module-project-specification/README.md) [java模块规范文档](module-project-specification/java-module-project-specification/module-user/README.md) |
| project-specification | 项目脚本及其他配置规范 [代码实例](project-specification/README.md) | 无 |
| config-specification| 配置文件规范 [配置例子](config-specification/README.md) | 无 |

#### 分支管理规范
| 分支前缀/名称    | 分支来源      | 合并入分支                 | 分支说明                                                     |
| ---------------- | ------------- | -------------------------- | ------------------------------------------------------------ |
| debug/5.3        | develop/trunk | develop/trunk, release/5.3 | 版本开发分支，code review 合并到develop/trunk、release/对应的版本发布分支 |
| debug/5.4        | develop/trunk | develop/trunk, release/5.4 | 版本开发分支，code review 合并到develop/trunk、release/对应的版本发布分支 |
| develop/trunk    |               |                            | 代码主分支，包含主线和oem的所有功能                          |
| develop/single-1 | develop/trunk |                            | 有些oem功能或者主线功能暂时无法合并入主线分支，另开分支处理  |
| develop/single-2 | develop/trunk |                            | 有些oem功能或者主线功能暂时无法合并入主线分支，另开分支处理  |
| release/5.3      | debug/5.3     |                            | 发布分支                                                     |
| release/5.4      | debug/5.3     |                            | 发布分支                                                     |

###### 1、oem相关

尽量往develop/trunk合并, 合并不了从trunk开分支进行开发

###### 2、bug处理

比如在开发5.4版本 发现5.3版本的bug 在5.3版本上修改后合并到5.4

###### 3、客户现场发现问题

理论上不修改5.3（旧的发布分支），推荐客户升级5.4（新的发布分支）

#### oem相关问题
