# Tlias School Management System - Backend

This is the backend service for the Tlias School Management System, a robust and scalable application built with Java and the Spring Boot framework. The project is structured as a Maven multi-module project to ensure a clear separation of concerns, high maintainability, and reusability.

## Technology Stack

- **Framework**: Spring Boot 3
- **Data Access**: MyBatis, MyBatis-PageHelper
- **Database**: MySQL 8
- **Authentication**: JSON Web Tokens (JWT)
- **API Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Build Tool**: Maven
- **File Storage**: Aliyun OSS (primary) and Local Storage, managed by a custom Spring Boot Starter.

## Project Structure

The project follows a Maven multi-module architecture. This design pattern is crucial for building scalable and maintainable applications. Here's why:

- **Separation of Concerns**: Each module has a single, well-defined responsibility. This makes the codebase easier to understand, develop, and test.
- **Reusability**: Common functionalities like utility classes, data models, and custom starters are encapsulated in their own modules, allowing them to be easily reused across different parts of the application or even in other projects.
- **Clear Dependency Management**: The modular structure enforces a clear and unidirectional dependency flow, preventing circular dependencies and making the project architecture clean.

### Module Breakdown

```
Tlias-School-Management-System-Backend/
├── pom.xml                                 # Parent POM: Manages all sub-module versions and common dependencies.
├── tlias-pojo/                             # Defines the data model of the application.
│   ├── entity/                             # - Java objects mapping to database tables (e.g., Student, Employee).
│   └── dto/                                # - Data Transfer Objects for API requests/responses.
├── tlias-common/                           # Contains common code shared across the application.
│   ├── util/                               # - Utility classes (e.g., JWTUtil, LocalDriveUtil).
│   ├── config/                             # - Common configurations.
│   ├── exception/                          # - Custom exception classes.
│   └── common/                             # - Standardized response classes (Result, PageResult).
├── tlias-backend/                          # The core application module.
│   ├── controller/                         # - RESTful API controllers.
│   ├── service/                            # - Business logic layer.
│   ├── mapper/                             # - MyBatis mapper interfaces for data access.
│   ├── resources/
│   │   ├── application.yml                 # - Main application configuration.
│   │   └── mapper/                         # - MyBatis XML files with SQL queries.
│   └── TliasBackendApplication.java        # - Spring Boot main entry point.
└── aliyun-oss-operator-spring-boot-parent/ # A custom, reusable Spring Boot starter for Aliyun OSS.
    ├── aliyun-oss-operator-spring-boot-autoconfigure/ # - Auto-configuration logic.
    └── aliyun-oss-operator-spring-boot-starter/       # - Starter POM that pulls in the autoconfigure module.
```

## Getting Started

### Prerequisites

- Java 21 or later
- Maven 3.6+
- MySQL 8.0+

### Configuration

1.  Create a database named `tlias` in your MySQL server.
2.  Navigate to `tlias-backend/src/main/resources/application.yml`.
3.  Update the `spring.datasource` properties with your MySQL username and password.
4.  Configure the JWT secret key under `jwt.secret-key`.
5.  Configure file storage. For integration with the frontend, this project uses Aliyun OSS for file uploads and deletions. You must configure your Aliyun OSS credentials under the `aliyun.oss` section. The local storage implementation (`file.upload.path`) is provided as an alternative example.

### Build & Run

1.  **Build the project**:
    Open a terminal in the project root directory and run the following Maven command. This will compile all modules and install them into your local Maven repository.
    ```bash
    mvn clean package
    ```

2.  **Run the application**:
    You can run the main application class `TliasBackendApplication.java` from your IDE, or run the packaged JAR file:
    ```bash
    java -jar tlias-backend/target/tlias-backend-0.0.1-SNAPSHOT.jar
    ```

3.  **Access the application**:
    The application will be running at `http://localhost:8080`.

## API Documentation

The project uses SpringDoc to automatically generate API documentation. Once the application is running, you can access the Swagger UI at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

# Tlias 学籍管理系统 - 后端

这是一个为 Tlias 学籍管理系统提供的后端服务，它是一个使用 Java 和 Spring Boot 框架构建的、健壮且可扩展的应用程序。该项目被构建为一个 Maven 多模块项目，以确保清晰的关注点分离、高可维护性和代码复用性。

## 技术栈

- **框架**: Spring Boot 3
- **数据访问**: MyBatis, MyBatis-PageHelper
- **数据库**: MySQL 8
- **认证**: JSON Web Tokens (JWT)
- **API 文档**: SpringDoc OpenAPI (Swagger UI)
- **构建工具**: Maven
- **文件存储**: 阿里云 OSS (主要) 和本地存储，通过自定义 Spring Boot Starter 进行管理。

## 项目结构

该项目遵循 Maven 多模块架构。这种设计模式对于构建可扩展和可维护的应用程序至关重要。其优势如下：

- **关注点分离**: 每个模块都有一个单一且明确的职责。这使得代码库更容易理解、开发和测试。
- **可复用性**: 像工具类、数据模型和自定义 Starter 这样的通用功能被封装在它们自己的模块中，使它们可以轻松地在应用程序的不同部分甚至在其他项目中被复用。
- **清晰的依赖管理**: 模块化结构强制执行清晰的单向依赖流程，防止循环依赖，并使项目架构保持整洁。

### 模块详解

```
Tlias-School-Management-System-Backend/
├── pom.xml                                 # 父POM: 管理所有子模块的版本和公共依赖。
├── tlias-pojo/                             # 定义应用程序的数据模型。
│   ├── entity/                             # - 映射到数据库表的 Java 对象 (例如 Student, Employee)。
│   └── dto/                                # - 用于 API 请求/响应的数据传输对象。
├── tlias-common/                           # 包含在整个应用程序中共享的通用代码。
│   ├── util/                               # - 工具类 (例如 JWTUtil, LocalDriveUtil)。
│   ├── config/                             # - 通用配置。
│   ├── exception/                          # - 自定义异常类。
│   └── common/                             # - 标准化响应类 (Result, PageResult)。
├── tlias-backend/                          # 核心应用程序模块。
│   ├── controller/                         # - RESTful API 控制器。
│   ├── service/                            # - 业务逻辑层。
│   ├── mapper/                             # - 用于数据访问的 MyBatis Mapper 接口。
│   ├── resources/
│   │   ├── application.yml                 # - 主应用程序配置。
│   │   └── mapper/                         # - 包含 SQL 查询的 MyBatis XML 文件。
│   └── TliasBackendApplication.java        # - Spring Boot 主入口点。
└── aliyun-oss-operator-spring-boot-parent/ # 一个可复用的、自定义的阿里云 OSS Spring Boot Starter。
    ├── aliyun-oss-operator-spring-boot-autoconfigure/ # - 自动配置逻辑。
    └── aliyun-oss-operator-spring-boot-starter/       # - 引入自动配置模块的 Starter POM。
```

## 快速开始

### 环境要求

- Java 21 或更高版本
- Maven 3.6+
- MySQL 8.0+

### 配置

1.  在您的 MySQL 服务器中创建一个名为 `tlias` 的数据库。
2.  打开 `tlias-backend/src/main/resources/application.yml` 文件。
3.  使用您的 MySQL 用户名和密码更新 `spring.datasource` 属性。
4.  在 `jwt.secret-key` 下配置 JWT 密钥。
5.  配置文件存储。在与前端集成时，本项目使用阿里云OSS进行文件上传和删除。您必须在 `aliyun.oss` 部分配置您的阿里云OSS凭证。本地存储的实现 (`file.upload.path`) 仅作为备用示例提供。

### 构建与运行

1.  **构建项目**:
    在项目根目录打开一个终端，并运行以下 Maven 命令。这将编译所有模块并将它们安装到您的本地 Maven 仓库中。
    ```bash
    mvn clean package
    ```

2.  **运行应用程序**:
    您可以从您的 IDE 中运行主应用程序类 `TliasBackendApplication.java`，或者运行打包后的 JAR 文件：
    ```bash
    java -jar tlias-backend/target/tlias-backend-0.0.1-SNAPSHOT.jar
    ```

3.  **访问应用程序**:
    应用程序将在 `http://localhost:8080` 上运行。

## API 文档

项目使用 SpringDoc 自动生成 API 文档。应用程序运行后，您可以通过以下地址访问 Swagger UI：

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
