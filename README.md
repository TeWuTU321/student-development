# 班级学生发展培养系统

本项目模拟班级中老师与学生的角色。

老师充当重要角色，老师拥有学生管理权限，可进行新增或编辑每位同学的信息，对于上传的新闻或图书可进行阅读审核决定是否保留

学生充当普通角色，每位同学可上传书籍或新闻，彼此之间可以相互阅读。

*🖖项目只作为学习练习使用，提供测试账号 : `tu`，密码 : 123456.*

## 部分项目展示

![img](https://rabbit-tewu.oss-cn-chengdu.aliyuncs.com/img/UM24Y$O%7BHANVB0VS0EL90$9.png)

![img.png](img.png)

![image-20220625140707090](https://rabbit-tewu.oss-cn-chengdu.aliyuncs.com/img/image-20220625140707090.png)
![img_1.png](img_1.png)
### 技术

**前端页面地址：https://github.com/TeWuTU321/esayvue**

- element-ui
- axios
- vue
- WangEdit富文本编辑器

**后端接口地址：https://github.com/TeWuTU321/student-development**



- mybatis plus
- springboot
- mysql
- redis
- mybatis
- 代码生成器

### 功能

-  登录验证
-  注册新用户
-  新闻增删改查、详情页的查看
- 图书增删改查、文件上传、文件下载
- 用户增删改查、关联相关用户所上传书籍列表

### 注意事项

#### 若需本地运行该项目时，请修改如下配置

```
1. 前端 : /public/static中config.js

2. 前端 : esayvue/vue.config.js

3. 后端 : application.yml 等
```