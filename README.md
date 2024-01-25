# JDK Http Client CRUD Example

This is a simple application that will show you how to use the JDK Http Client. It talks to a simple REST API that is
available at https://jsonplaceholder.typicode.com/. 

## Tests

There are a number of tests that show how to use the JDK Http Client. They are all in the `src/test/java/TodoClientTest`.

## Logging 

If you want to enable logging, you can do so by setting the following system property:

```properties
-Djdk.httpclient.HttpClient.log=all
```