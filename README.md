# 基于Java_Android和Java-IDEA的项目

## 更新时间：2026/7/10
* 更新在2025-2026上学期的Java代码
* 包括Java实训、云大数据作业和安卓应用开发
## 创建时间于2023-2024

本项目包含多个Java和Android开发项目，涵盖基础Java编程、Android应用开发、数据库操作等多个方面。

## 目录 (Table of Contents)

### Android应用项目
1. [App1](#app1) - 基础Android应用模板
2. [DaibanActivity5](#daibanactivity5) - 待办事项应用（基础版）
3. [JavaTextview](#javatextview) - TextView控件学习
4. [Mine-master](#mine-master) - 记账本应用
5. [MyApplication3](#myapplication3) - Activity生命周期学习
6. [loginApp](#loginapp) - 登录界面应用
7. [newempth](#newempth) - 空白应用模板
8. [noactivity](#noactivity) - 简单登录应用
9. [photoskip](#photoskip) - 照片跳转应用
10. [ResignerLogin](#resignerlogin) - 注册登录系统
11. [Smartfactory](#smartfactory) - 智慧工厂设备管理系统
12. [TextLogin](#textlogin) - 文本登录学习
13. [TextViews2](#textviews2) - 多按钮界面学习
14. [todo_step1](#todo_step1) - 待办事项应用（步骤1）
15. [todo_step3](#todo_step3) - 待办事项应用（步骤3，含数据库）

### Java IDEA项目
16. [CarsEngine](#carsengine) - 汽车引擎类继承（已删除）
17. [Java实训](#java实训) - 学生成绩管理系统
18. [StudentScoreSystem](#studentscoresystem) - 学生成绩系统
19. [classTansfrom](#classtansfrom) - 类转换与继承
20. [kaoshitest](#kaoshitest) - 考试测试项目
21. [jicheng](#jicheng) - 继承示例（已删除）
22. [多态](#多态) - 多态性示例（已删除）
23. [接口](#接口) - 接口实现示例（已删除）
24. [类](#类) - 类定义示例（已删除）
25. [重写](#重写) - 方法重写示例（已删除）

### 其他项目
26. [JavaTest](#javatest) - Java测试项目
27. [photoship](#photoship) - 空项目

### 其他文件
28. [Mine-master.zip](#mine-masterzip) - 记账本应用压缩包
29. [23物联网3班-2025303030312-肖楷煜大作业.zip](#大作业) - 课程大作业

--------

## Android应用项目详细说明

### App1
**简介**：基础Android应用模板，使用Navigation组件实现Fragment导航，包含两个Fragment页面和浮动操作按钮。

**技术栈**：Android SDK, Navigation Component, Material Design

**主要文件**：
- `MainActivity.java` - 主活动，设置导航和工具栏
- `FirstFragment.java` - 第一个Fragment页面
- `SecondFragment.java` - 第二个Fragment页面

**功能特点**：
- 使用Navigation组件管理Fragment导航
- 实现浮动操作按钮（FAB）
- 支持ActionBar导航

---

### DaibanActivity5
**简介**：待办事项应用的基础版本，实现基本的界面跳转和日期时间选择功能。

**技术栈**：Android SDK, Material Design, DatePicker, TimePicker

**主要文件**：
- `MainActivity.java` - 主活动，包含添加按钮
- `MainActivity2.java` - 添加待办事项页面，包含日期时间选择

**功能特点**：
- 浮动操作按钮跳转到添加页面
- 日期选择器（DatePickerDialog）
- 时间选择器（TimePickerDialog）
- 取消和保存功能

---

### JavaTextview
**简介**：TextView控件学习项目，用于学习Android基本UI控件的使用。

**技术栈**：Android SDK, TextView控件

**主要文件**：
- 测试文件（ExampleInstrumentedTest.java, ExampleUnitTest.java）

**学习内容**：
- TextView的基本使用
- Android项目结构理解

---

### Mine-master
**简介**：自己开发的记账本应用，界面简洁干净，支持基本的记账功能。

**技术栈**：Android SDK, MVP架构, 自定义框架

**主要文件**：
- `app/` - 主应用模块
- `libbase/` - 基础库模块
- `libupdate/` - 更新库模块
- `source/` - 资源文件

**功能特点**：
- 记账功能
- MVP架构设计
- 模块化开发
- 应用宝可下载

**项目地址**：[应用宝下载](http://sj.qq.com/myapp/detail.htm?apkName=com.coderpage.mine)

---

### MyApplication3
**简介**：Activity生命周期学习项目，演示Android Activity的完整生命周期回调。

**技术栈**：Android SDK, Activity生命周期

**主要文件**：
- `MainActivity.java` - 主活动，重写所有生命周期方法

**学习内容**：
- onCreate, onStart, onResume, onPause, onStop, onDestroy等生命周期方法
- Activity状态管理

---

### loginApp
**简介**：登录界面应用，使用Navigation组件实现登录后的页面导航。

**技术栈**：Android SDK, Navigation Component, Material Design

**主要文件**：
- `MainActivity.java` - 主活动，设置导航
- `FirstFragment.java` - 第一个Fragment
- `SecondFragment.java` - 第二个Fragment

**功能特点**：
- 登录界面设计
- Fragment导航管理
- 工具栏和浮动按钮

---

### newempth
**简介**：空白应用模板，用于快速创建新的Android项目。

**技术栈**：Android SDK

**主要文件**：
- `MainActivity.java` - 基本主活动

**用途**：
- 新项目开发起点
- Android项目结构学习

---

### noactivity
**简介**：简单登录应用，实现基本的账号密码验证和页面跳转。

**技术栈**：Android SDK, Intent, Toast

**主要文件**：
- `MainActivity.java` - 登录主活动，验证账号密码（admin/1234）
- `MainActivity2.java` - 登录成功后的页面

**功能特点**：
- 硬编码账号密码验证
- 登录成功跳转
- 错误提示（Toast）

---

### photoskip
**简介**：照片跳转应用，实现启动页面的倒计时跳转功能，类似广告页。

**技术栈**：Android SDK, 多线程, Intent

**主要文件**：
- `MainActivity1.java` - 启动页面，5秒倒计时
- `MainActivity2.java` - 主内容页面

**功能特点**：
- 5秒倒计时显示
- 跳过按钮
- 自动跳转
- 多线程实现倒计时

---

### ResignerLogin
**简介**：完整的注册登录系统，包含用户注册、登录、修改密码和个人主页功能。

**技术栈**：Android SDK, SQLite数据库, SharedPreferences

**主要文件**：
- `MainActivity.java` - 登录页面
- `RegisterActivity.java` - 注册页面
- `HomeActivity.java` - 个人主页
- `ChangePasswordActivity.java` - 修改密码页面
- `DatabaseHelper.java` - 数据库帮助类

**功能特点**：
- 用户注册（包含密码确认）
- 用户登录验证
- 修改密码功能
- 个人主页展示
- SQLite本地数据存储
- 表单验证（密码安全性、邮箱格式、姓名长度）

---

### Smartfactory
**简介**：智慧工厂设备管理系统，实现设备的分类管理、增删改查功能。

**技术栈**：Android SDK, SQLite数据库, Fragment, RecyclerView, Material Design

**主要文件**：
- `MainActivity.java` - 登录页面
- `RegisterActivity.java` - 注册页面
- `HomeActivity.java` - 主页面，包含底部导航
- `EquipmentFragment.java` - 设备列表Fragment
- `EquipmentDialogFragment.java` - 设备编辑对话框
- `EquipmentAdapter.java` - 设备列表适配器
- `EquipmentDatabaseHelper.java` - 设备数据库帮助类
- `UserPreferences.java` - 用户偏好存储

**功能特点**：
- 用户注册登录
- 设备分类管理（生产、测试、包装、仓储）
- 设备增删改查
- 底部导航切换
- Material Design界面
- RecyclerView列表展示

---

### TextLogin
**简介**：文本登录学习项目，包含期中考核大作业和登录功能学习材料。

**技术栈**：Android SDK

**项目内容**：
- `Login/` - 登录功能相关文件
- `25-26-02-23物联网工程本科《物联网移动应用开发》期中考核-大作业-贾小硕.doc` - 期中考核大作业文档
- `大作业说明文档.docx` - 大作业说明文档

**学习内容**：
- Android登录功能开发
- 期中考核大作业参考

---

### TextViews2
**简介**：多按钮界面学习项目，演示多个Button控件的使用。

**技术栈**：Android SDK, Button控件

**主要文件**：
- `MainActivity.java` - 主活动，包含三个按钮

**学习内容**：
- 多个Button控件的初始化
- 基本UI布局

---

### todo_step1
**简介**：待办事项应用的第一步，实现基本的界面跳转功能。

**技术栈**：Android SDK, Intent

**主要文件**：
- `MainActivity.java` - 主活动，包含添加按钮
- `AddActivity.java` - 添加待办事项页面

**功能特点**：
- 简单的页面跳转
- 添加待办事项入口

---

### todo_step3
**简介**：待办事项应用的完整版本，使用Room数据库实现数据持久化。

**技术栈**：Android SDK, Room数据库, RecyclerView, LiveData

**主要文件**：
- `MainActivity.java` - 主活动，显示待办列表
- `AddActivity.java` - 添加待办事项页面
- `TodoAdapter.java` - 列表适配器
- `ToDoDao.java` - 数据访问对象
- `ToDoDb.java` - 数据库定义
- `ToDoItem.java` - 数据实体

**功能特点**：
- Room数据库存储
- RecyclerView列表展示
- 异步数据加载
- 数据增删改查

---

## Java IDEA项目详细说明

### CarsEngine
**简介**：Java面向对象编程示例，演示类的继承关系，以汽车和发动机为例。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Car.java` - 汽车类（父类）
- `Engine.java` - 发动机类（子类）
- `Test.java` - 测试类

**学习内容**：
- 类的继承
- 父类和子类关系
- 对象创建和使用

---

### Java实训
**简介**：学生成绩管理系统，实现学生信息的增删改查和成绩管理功能。

**技术栈**：Java SE, 面向对象编程, 集合框架

**主要文件**：
- `Main.java` - 主程序，包含菜单和用户交互
- `ScoreManager.java` - 成绩管理类，处理业务逻辑
- `Student.java` - 学生类，定义学生属性

**功能特点**：
- 学生信息管理（添加、删除、修改、查询）
- 成绩录入和管理
- 学生平均分计算
- 课程平均分计算
- 控制台菜单交互
- 初始化示例数据

---

### StudentScoreSystem
**简介**：学生成绩系统，与Java实训项目类似，可能是不同版本或重复项目。

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Main.java` - 主程序
- `ScoreManager.java` - 成绩管理类
- `Student.java` - 学生类
- `untitled1.iml` - IntelliJ IDEA模块文件

**功能特点**：
- 学生成绩管理
- 与Java实训项目功能相似
- 项目名称拼写为"StudentScoreSytsem"（注意拼写错误）

---

### classTansfrom
**简介**：类转换与继承示例，演示Java中父类和子类的转换、内部类和外部类的使用。

**技术栈**：Java SE, 面向对象编程, 内部类

**主要文件**：
- `Demo/Animal.java` - 动物基类
- `Demo/Dog.java` - 狗类（继承Animal）
- `Demo/Test.java` - 测试类
- `Jieko/Animal.java` - 接口示例
- `Jieko/test.java` - 接口测试
- `NeibuClass/Outer.java` - 外部类
- `NeibuClass/Test.java` - 内部类测试

**学习内容**：
- 类的继承和转换
- 内部类和外部类
- 接口实现
- 多态性

---

### kaoshitest
**简介**：考试测试项目，包含Maven插件开发示例和Spark环境下的WordCount算法。

**技术栈**：Java SE, Maven, Apache Spark

**主要文件**：
- `MyMojo.java` - Maven插件示例，实现touch功能
- `test.java` - Spark WordCount算法框架

**学习内容**：
- Maven插件开发
- Apache Spark基础
- 大数据处理

---

### jicheng
**简介**：Java继承示例项目，演示动物类的继承关系，包括猫类和狗类的继承实现。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Animal.java` - 动物基类
- `Animal2.java` - 另一个动物类
- `Cat.java` - 猫类（继承Animal）
- `Dog.java` - 狗类（继承Animal）
- `Test.java` - 测试类

**学习内容**：
- 类的继承
- 方法重写
- 对象创建和使用

---

### 多态
**简介**：Java多态性示例，演示通过继承实现的多态特性。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Cat.java` - 猫类
- `Dog.java` - 狗类
- `Test.java` - 测试类

**学习内容**：
- 多态的概念
- 方法重写
- 向上转型
- 动态绑定

---

### 接口
**简介**：Java接口实现示例，演示接口的定义和实现。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 接口

**主要文件**：
- `Cat.java` - 猫类（实现接口）
- `Dog.java` - 狗类（实现接口）
- `Test.java` - 测试类

**学习内容**：
- 接口定义
- 接口实现
- 接口与抽象类的区别

---

### 类
**简介**：Java类定义示例，演示基本的类创建和使用。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Animal.java` - 动物类
- `Animal2.java` - 另一个动物类

**学习内容**：
- 类的定义
- 成员变量和方法
- 构造函数

---

### 重写
**简介**：Java方法重写示例，演示子类重写父类方法的特性。

**状态**：已从项目中删除（可在git历史中查看）

**技术栈**：Java SE, 面向对象编程

**主要文件**：
- `Animal.java` - 动物基类
- `Animal2.java` - 另一个动物类
- `Cat.java` - 猫类（重写方法）
- `Dog.java` - 狗类（重写方法）
- `Test.java` - 测试类
- `Java.iml` - IntelliJ IDEA模块文件

**学习内容**：
- 方法重写（Override）
- @Override注解
- 父类引用指向子类对象

---

## 其他文件

### Mine-master.zip
**简介**：Mine-master记账本应用的压缩包版本，包含完整的项目文件。

**用途**：
- 项目备份
- 离线使用
- 分发分享

### JavaTest
**简介**：Java测试项目，使用Kotlin DSL构建脚本，可能是Android项目模板或测试环境。

**技术栈**：Android SDK, Kotlin DSL, Gradle

**项目结构**：
- `app/` - 应用模块
- `gradle/` - Gradle包装器
- `build.gradle.kts` - Kotlin DSL构建脚本

**用途**：
- Android项目测试
- Kotlin DSL学习
- 构建脚本测试

---

### photoship
**简介**：空项目目录，可能是预留的项目空间或未完成的项目。

**状态**：空目录

**用途**：
- 项目预留空间
- 未来开发使用

---

### 大作业
**简介**：23物联网3班-2025303030312-肖楷煜大作业.zip，包含课程大作业的完整项目。

**用途**：
- 课程作业提交
- 项目备份

---

## 项目统计

- **Android应用项目**：15个
- **Java IDEA项目**：10个（其中5个已删除）
- **其他项目**：2个
- **其他文件**：2个
- **总计**：29个项目/文件

## 技术栈总结

### Android开发
- Android SDK
- Material Design
- Navigation Component
- Room数据库
- RecyclerView
- SQLite
- SharedPreferences
- Fragment
- Intent
- 多线程

### Java开发
- Java SE
- 面向对象编程
- 继承、封装、多态
- 接口和抽象类
- 集合框架
- Maven
- Apache Spark

## 学习路径建议

### 初学者
1. Java基础：类 → 接口 → 多态 → 重写
2. Android基础：TextViews2 → noactivity → photoskip
3. 项目实践：todo_step1 → todo_step3

### 进阶者
1. 完整应用：ResignerLogin → Smartfactory
2. 架构学习：Mine-master（MVP架构）
3. 数据库应用：todo_step3（Room数据库）

### 高级学习
1. 大数据：kaoshitest（Spark）
2. 模块化开发：Mine-master
3. 插件开发：kaoshitest（Maven插件）


