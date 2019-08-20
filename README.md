# spring-api-versioning
Simple development of multi-version api based on spring-mvc via @ApiVersion annotation, support for uri, header, param.

Supports: JDK 1.7, spring-boot 1.5.x, spring-boot 2.x, spring-mvc(untested)

## Future
- URI:  /v1/user/list, /v2/user/list
- Header: /user/list, header: X-API-VERSION=1
- Param:  /user/list?api_version=1

## Quick
1. Add Dependency(Maven)
    ```
       <dependency>
           <groupId>com.github.lkqm</groupId>
           <artifactId>spring-api-versioning</artifactId>
           <version>1.0.1-SNAPSHOT</version>
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
        @ApiVersion
        public class UserController {
        
            @GetMapping("/list")
            public String list() {
                return "list";
            }
        
            @GetMapping("/list")
            @ApiVersion(2)
            public String list2() {
                return "list2";
            }
        }
    ```
4. Test
    ```
        curl http://127.0.0.1:8080/v1/user/list
        curl http://127.0.0.1:8080/v2/user/list
    ```


## Config properties
```
    api.version.type=uri                # 多版本控制实现方式: uri(default), header, param
    api.version.uri-prefix=             # URI前缀, 比如可添加/api, 请求地址: /api/v1/... /api/v2/...
    api.version.header=X-API-VERSION    # 版本请求头名称
    api.version.param=api_version       # 版本参数名
```

## Reference
- [如何优雅的设计 Spring Boot API 接口版本号](https://www.jianshu.com/p/2c43d15b1675)
- [springmvc实现restful api版本控制并兼容swagger](https://luoluonuoya.github.io/2017/11/10/springmvc实现restful%20api版本控制并兼容swagger/)