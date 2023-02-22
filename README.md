![](https://img.shields.io/badge/web安全-靶场-PTEST)
![](https://img.shields.io/badge/version-1.0-success)
![](https://img.shields.io/github/stars/linjiananallnt/ElectricRat.svg)
![](https://img.shields.io/github/forks/linjiananallnt/ElectricRat.svg)
![](https://img.shields.io/github/license/linjiananallnt/ElectricRat.svg)

# 项目介绍
电气鼠靶场系统是一种带有漏洞的Web应用程序，旨在为Web安全渗透测试学习者提供学习和实践的机会。靶场系统包含了各种常见的Web安全漏洞，例如SQL注入、跨站脚本攻击、文件包含漏洞、代码注入漏洞等等，以帮助学习者熟悉和掌握这些漏洞的原理和实际利用方法。

通过在靶场系统上进行实际漏洞攻击和利用，学习者可以更好地理解和掌握Web安全渗透测试的技术和方法，以及如何保护Web应用程序免受攻击。靶场系统的实际攻击模拟也有助于学习者提高他们的安全意识，了解常见的攻击手段和漏洞利用方式，从而更好地保护他们自己和他们所负责的Web应用程序。

靶场系统通常是以虚拟机的形式提供，学习者可以在虚拟机中安装和配置靶场系统，然后使用各种渗透测试工具和技术进行漏洞攻击和利用。靶场系统还提供了漏洞利用的详细说明和步骤，以帮助学习者了解漏洞利用的过程和方法。此外，靶场系统通常还包括一些练习题和挑战，以测试学习者的能力和技能。

一些漏洞案例的编写上借鉴了使用PHP语言编写的 Pikachu 靶场。

# 安装和使用说明
此系统没有后门木马，但是实际的功能上可以让黑客轻松的攻击您的服务器。不建议将此系统安装到公网的云服务器最好的方案是在虚拟机中安装docker并使用docker安装ElectricRat。
1. 点击下载[下载最新发布](https://github.com/linjiananallnt/ElectricRat/releases)。
2. 解压并进入到项目目录下打开操控终端。
3. 输入命令，使用`docker-compose`启动项目，并且导入数据库。
```
sudo docker-compose up -d
sudo docker exec -it electricrat-mysql /bin/bash -c 'cd /data && mysqladmin -u root -pAAsd123rdsgA create mycms && mysqladmin -u root -pAAsd123rdsgA create mycms_gbk && mysql -u root -pAAsd123rdsgA -Dmycms < dump-mycms-202302201704.sql && mysql -u root -pAAsd123rdsgA -Dmycms_gbk < dump-mycms_gbk-202302201704.sql'
```

# 电气鼠上的漏洞类型列表如下：
- Burt Force(暴力破解漏洞)
- XSS(跨站脚本漏洞)
- CSRF(跨站请求伪造)
- SQL-Inject(SQL注入漏洞)
- RCE(远程命令执行)
- 任意文件上传
- 任意文件下载
- 目录遍历漏洞
- JAVA反序列化漏洞
- XXE(XML实体注入漏洞)
- 不安全的URL重定向
- SSRF(服务器端请求伪造)
- SPEL(表达式注入)
- SSTI(模板注入)

# 效果图
![首页效果](https://user-images.githubusercontent.com/67619247/220506698-444237fb-0a1b-4b33-884b-5ed7c19754e1.png)
![功能效果](https://user-images.githubusercontent.com/67619247/220506750-e377a7b4-a45b-4bc2-884e-91415d703310.png)

# 技术栈和依赖项
- 中间件：Apache-Tomcat-10.1.5
- 数据库：MySQL Server 8.0
- SDK版本：OpenJDK 19.0.1
- 构建：Maven
- 数据库连接池：Druid-1.2.15

# 贡献指南
非常渴望对该系统的各种良好的建议，鼓励大家提交PR。
