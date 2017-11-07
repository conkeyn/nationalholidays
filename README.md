# 国家法定节假日API
---

参考[arccode](https://github.com/arccode)的API设计规范，在此基础上做了相应修改。

## API 设计理念

1. 将涉及的实体抽象成资源，即按 __id__ 访问资源

2. 使用 HTTP 动词对资源进行 CRUD（增删改查）：

    GET -> 查, POST -> 增, PUT -> 改, DELETE -> 删

3. URL 命名规则，对于资源无法使用一个单数名词表示的情况，我使用下划线 ***_*** 连接

    资源采用名词命名，e.g：节假日 -> holiday

    新增节假日 url -> /holiday, verb -> POST

    修改节假日 url -> /holidays/{id}, verb -> PUT

    节假日详情 url -> /holidays/{id}, verb -> GET

    删除节假日 url -> /holidays/{id}, verb -> DELETE

    节假日列表 url -> /holidays, verb -> GET


4. API URL中增加版本号，如：

    v1版本：
    
    ```GET https://url/api/v1/nationalholidays/cn/2017```
    
    v2版本：
    
    ```GET https://url/api/v2/nationalholidays/cn/2017```

## JSON 结构

requestParams：

    {
    
    }

responseBody：

    {
    "meta": {
        
        },
    "data": {
        
        }
    }

meta:

    {
        "code": 200,
        "msg":  "创建成功"
    }

***meta*** 中封装操作成功或失败的消息，***data*** 中封装返回的具体数据。
