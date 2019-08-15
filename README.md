# spring-api-versioning
Simple development of multi-version api based on spring-mvc annotation via @ApiVersion, support for uri, header, param.

## Future
- URI: 请求/v1/user/list, /v2/user/list
- Header: 请求/user/list, 携带请求头: X-API-VERSION=1
- Param:  请求/user/list?api_version=1

## Quick
1. Add Dependency(Maven)
    ```
       <dependency>
           <groupId>com.github.lkqm</groupId>
           <artifactId>spring-api-versioning</artifactId>
           <version>1.0.0-SNAPSHOT</version>
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
    
## Config(application.properties)
```
    api.version.type=uri                # 多版本控制实现方式: uri(默认), header, param
    api.version.uri-prefix=             # URI前缀, 比如可添加/api, 请求地址: /api/v1/... /api/v2/...
    api.version.header=X-API-VERSION    # 版本请求头名称
    api.version.param=api_version       # 版本参数名
```

## 参考
- https://www.jianshu.com/p/2c43d15b1675