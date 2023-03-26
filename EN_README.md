

# ⛱ Project Introduction:

The Electrical Mouse Target Range System is a web application with vulnerabilities designed to provide learning and practice opportunities for web security penetration testing learners. The target range system contains various common web security vulnerabilities such as SQL injection, cross-site scripting attacks, file inclusion vulnerabilities, code injection vulnerabilities, etc., to help learners become familiar with and master the principles and practical methods of these vulnerabilities.

By conducting actual vulnerability attacks and exploitation on the target range system, learners can better understand and master the techniques and methods of web security penetration testing, as well as how to protect web applications from attacks. The practical attack simulation of the target range system also helps learners to enhance their security awareness, understand common attack methods and vulnerability exploitation methods, and better protect themselves and the web applications they are responsible for.

The target range system is usually provided in the form of a virtual machine, and learners can install and configure the target range system in the virtual machine and then use various penetration testing tools and techniques to conduct vulnerability attacks and exploitation. The target range system also provides detailed descriptions and steps for vulnerability exploitation to help learners understand the process and methods of vulnerability exploitation. In addition, the target range system usually includes some practice exercises and challenges to test learners' abilities and skills.

Some of the vulnerability cases were written based on the Pikachu target range, which was developed using the PHP programming language.

# 🚀 Installation and Usage Instructions
The system does not contain any backdoor Trojan, but its actual functionality can allow hackers to easily attack your server. It is not recommended to install this system on a public cloud server. The best solution is to install Docker in a virtual machine and use Docker to install ElectricRat.

1. Click "Download" to download the latest release.

2. Unzip and enter the project directory, then open the terminal.

3. Enter the command to start the project using Docker Compose and import the database.
```
sudo docker-compose up -d
sudo docker exec electricrat-mysql /bin/bash -c 'cd /data && mysqladmin -u root -pAAsd123rdsgA create mycms && mysqladmin -u root -pAAsd123rdsgA create mycms_gbk && mysql -u root -pAAsd123rdsgA -Dmycms < dump-mycms.sql && mysql -u root -pAAsd123rdsgA -Dmycms_gbk < dump-mycms_gbk.sql'
```
4. Access http://127.0.0.1:12666/ElectricRat/index.html to access the homepage of ElectricRat vulnerability simulation system.

> Note that, in order to solve the runc version problem, I used privileged: true when creating electricrat-web. This may cause a Docker escape vulnerability, and considering security issues, the system should not be deployed on the public network or other potentially risky environments.

# 👩🏼‍💻 The list of vulnerability types on ElectricRat includes

- Burt Force
- XSS (Cross-Site Scripting)
- CSRF (Cross-Site Request Forgery)
- SQL-Inject (SQL Injection)
- RCE (Remote Code Execution)
- Arbitrary File Upload
- Arbitrary File Download
- Directory Traversal
- JAVA Deserialization
- XXE (XML External Entity Injection)
- Insecure URL Redirect
- SSRF (Server-Side Request Forgery)
- SPEL (Spring Expression Language Injection)
- SSTI (Server-Side Template Injection)
- File Inclusion
- Sensitive Information Disclosure
- Authorization Bypass

# ✨ Screenshot
![Home Page Display](https://user-images.githubusercontent.com/67619247/220506698-444237fb-0a1b-4b33-884b-5ed7c19754e1.png)
![functionality effects](https://user-images.githubusercontent.com/67619247/220506750-e377a7b4-a45b-4bc2-884e-91415d703310.png)

# ⚡️ Technology stack and dependencies

- Middleware: Apache-Tomcat-10.1.5
- Database: MySQL Server 8.0
- SDK version: OpenJDK 19.0.1
- Build: Maven
- Database connection pool: Druid-1.2.15

# 😘 Contribution Guidelines
We are very open to any good suggestions for the system and encourage everyone to submit pull requests.

# Star Growth Curve
![Star Growth Curve](https://api.star-history.com/svg?repos=linjiananallnt/ElectricRat&type=Date)
