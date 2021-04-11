# spring-api-versioning
![Maven Central](https://img.shields.io/maven-central/v/com.github.lkqm/spring-api-versioning)
![Travis (.org) branch](https://img.shields.io/travis/lkqm/spring-api-versioning/master)

Simple development of multi-version api based on spring-mvc via @ApiVersion annotation, support for uri, header, param.

Supports: JDK 1.7, spring-boot 1.5.x, spring-boot 2.x

## Features
- URI:  /v1/user/list, /v2/user/list
- Header: /user/list, header: X-API-VERSION=1
- Param:  /user/list?api_version=1

Important: version number use precise matching with String equals method.

## Quick
1. Add Dependency(Maven)
    ```
   <dependency>
       <groupId>com.github.lkqm</groupId>
       <artifactId>spring-api-versioning</artifactId>
       <version>${version}</version>
   </dependency>
    ```
    
2. @EnableApiVersioning with Application class
    ```
    @SpringBootApplication
    @EnableApiVersioning
    public class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }
    ```

3. Controller
    ```
    @RestController
    @RequestMapping("/user")
    @ApiVersion("1")
    public class UserController {
    
        @GetMapping("/list")
        public String list1() {
            return "list1";
        }
    
        @GetMapping("/list")
        @ApiVersion("1.1)
        public String list2() {
            return "list2";
        }
    }
    ```
4. Test
    ```
    curl http://127.0.0.1:8080/v1/user/list
    curl http://127.0.0.1:8080/v1.1/user/list
    ```


## Config properties
```
api.version.type=uri                # versioning implement way: uri(default), header, param
api.version.uri-prefix=             # uri prefix, if set /api, request uri like: /api/v1/... /api/v2/...
api.version.header=X-API-VERSION    # version control http header name
api.version.param=api_version       # version control http query string name
```