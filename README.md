# 国家法定节假日API
---

参考arccode的API设计规范，在此基础上做了相应修改。

## API 设计理念

1. 将涉及的实体抽象成资源，即按 __id__ 访问资源

2. 使用 HTTP 动词对资源进行 CRUD（增删改查）：

    GET -> 查, POST -> 增, PUT -> 改, DELETE -> 删

3. URL 命名规则，对于资源无法使用一个单数名词表示的情况，我使用下划线 ***_*** 连接

    资源采用名词命名，e.g：产品 -> product

    新增资源，e.g：新增产品 url -> /product, verb -> POST

    修改资源，e.g：修改产品 url -> /products/{id}, verb -> PUT

    资源详情，e.g：指定产品详情 url -> /products/{id}, verb -> GET

    删除资源，e.g：删除产品 url -> /products/{id}, verb -> DELETE

    资源列表，e.g：产品列表 url -> /products, verb -> GET

    资源关联关系，e.g：收藏产品 url -> /products/{id}/star, verb -> PUT

    资源关联关系，e.g：删除收藏产品 url -> /products/{id}/star, verb -> DELETE

4. API URL中增加版本号，如：

    GET https://url/api/v1/products
    

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
