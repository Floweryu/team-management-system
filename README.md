# team-management-system

Beijing University of Chemical Technology Graduation Project.

## 开发环境

- `SpringBoot 2.4.2`

- `Maven 3.6.2`

- `MySQL8.0`
- `MyBatis 3.5.7`
- `Java 1.8.2.251`
- `Git 2.31.1`
- `IDEA 2020.2.3`
- `Vue.js 2.x`
- `Element-UI 2.15.1`
- `Axios 0.21.1`
- `Node.js 12.18.1`
- `VS Code 1.56.2`

## 数据库

在`team-manage-server`文件的`sql`目录中。

## 文件功能

`team-manage-server`——项目的后端

`team-manage-web`——项目的前端

## 项目简介及启动

先导入数据库，在后端修改数据库连接。

#### 前端：

采用`vue`全家桶，使用`nginx`部署到服务器上

进入前端目录，先`npm run install`安装依赖

执行`npm run dev`启动开发环境

执行`npm run prod`进行打包，打包目录是`dist`

#### 后端：

默认启动的是本地环境，也可指定启动环境文件.使用IDEA加载依赖直接启动即可。

****

采用以下命令可部署到服务器上，需要指定线上环境：

```bash
nohup java -jar manage-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > temp.txt 2>&1 &
```

由于项目涉及到文件上传功能，下面说说解决方法：

改变`springboot`静态资源映射路径，不把图片等资源存放到`resources`下，而是存储到外挂目录——在`src`同级目录下的`resource`中。这样做的好处是：打包可以不用将静态资源打包，静态资源在项目运行中是会动态增加的，这样做方便管理。但带来的问题是：后端部署到服务器上回造成资源路径找不到，这时需要配置`nginx`静态资源服务器：

![image-20210605134600042](https://i.loli.net/2021/06/05/UrHzb1Yua9knGqN.png)

然后，在服务器中将资源文件放到`jar`包的同级目录即可访问资源。