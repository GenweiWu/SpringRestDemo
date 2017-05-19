[![Build Status](https://travis-ci.org/travis-ci/travis-web.svg?branch=master)](https://travis-ci.org/travis-ci/travis-web)
# SpringRestDemo
使用Spring的rest能力，达成后台rest请求

#### 1. 传递参数、接受参数：String/List/ObjectNode

#### 2. get、post方法如何传递参数

#### 3. 中文参数的乱码问题
配置下编码为utf-8即可
```
produces = MediaType.APPLICATION_JSON_UTF8_VALUE
```

#### 4. 设置请求头的方法
```java
MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<>();
        dataMap.add("paramData", paramStr);

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");

        HttpEntity<Object> httpEntity = new HttpEntity<>(dataMap, header);
```

#### 5. 打印请求内容，便于问题定位
> according to [this solution](http://stackoverflow.com/a/41983744/6182927)

The solution given by xenoterracide to use
```properties
logging.level.org.apache.http=DEBUG
```
is good but the problem is that by default Apache HttpComponents is not used.

To use Apache HttpComponents add to your pom.xml
```xml
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpasyncclient</artifactId>
</dependency>
```
and configure RestTemplate with :
```java
RestTemplate restTemplate = new RestTemplate();
restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
```
