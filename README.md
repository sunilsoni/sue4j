# sue4j
------

**&nbsp;&nbsp;Sue4j -基于Spring Boot的高性能JavaWeb项目快速构建**

<br>
Quick Start
-----------

> * 1、git clone [git@github.com:sue0917/sue4j.git](https://github.com/sue0917/sue4j)
* 2、在MySQL中导入 sue4j/src/test/resources/sue4j.sql 脚本
* 3、更新 quick4j/src/main/resources/application.yml 中spring.datasource.username和password:
* 4、cd sue4j
* <small>-----下面二选一</small>
* 5、mvn spring-boot:run 直接运行 
* 5、mvn package，然后 java -jar target/sue4j-1.1-SNAPSHOT.jar 运行(可使用nohup等后台,自行google)


<br>

技术选型
----

> * Spring Boot + Mybatis，简单的易于扩展的架构
* 使用Apache Shiro作为权限控制工具，使用JavaMelody作为性能监控工具
* 前端用的freemarker模板、Bootstrap、jQuery等常用框架
* 部署到Docker容器


<br>

版本说明(1.1-SNAPSHOT)
------------------

 - **版本新增**
 
 1. 增加Shiro作为权限控制工具，采用注解的方式控制权限，更灵活易懂
 2. 增加JavaMelody作为权限控制工具，访问{}/admin/monitoring即可查看（用户名sue，密码123456）
 3. 写了部分前台页面，直接用的bootstrap的模板修改而来
 4. 增加了缓存的支持（虽然大部分复制的，不过看了说明大概也知道什么意思)
 5. 对日志更好的处理和支持
<br>

 - **部分说明**
 
 1. shiro的缓存在修改用户后需要及时的清楚缓存,不然不会立即生效，方法已经实现，根据具体的场景修改
 2. 验证码已经实现，但是没有加入项目，也可以控制输错密码几次后出现验证码等等，根据场景修改
 3. 前台的页面设计有问题，导航头那些没有处理好，页面内容也没有加上，根据具体场景修改
<br>

 - **使用帮助**
 
 1. 使用sue(或vip或normal),密码123456即可登录
 2. 在sue4j/src/test/resources下有sql脚本和mybatis-generator的配置文件
 3. 代码有很全很详细的注释，大家应该都能看懂
 4. 有任何的意见或建议都可以与我联系，附上我的邮箱 swb0917@gmail.com

<br>
###关于我
> * **author：Mr.Su**
* **email：swb0917@gmail.com**
* **blog：http://blog.uuus.me**

<br>
### 特别鸣谢
> * quick4j，附上地址 [https://github.com/starzou/quick4j](https://github.com/starzou/quick4j)
* 众多为我提供过帮助的人

<br>
### License
>  本项目（sue4j）开源协议Apache 2.0 license.

<br>
###### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`If you have a better suggestion,Please share out,Let's do better.`
<br><br>
