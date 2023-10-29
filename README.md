# Geckobug 2139

Java reproduction of Internationalization error for [#1239](https://github.com/mozilla/geckodriver/issues/2139)

### Set Version

Toggle this between `PASSING_VERSION` and `FAILING_VERSION`; Selenium manager should handle the versioning properly
```java
    options.setBrowserVersion(FAILING_VERSION);
```

### Execute Test
```shell
mvn test
```
