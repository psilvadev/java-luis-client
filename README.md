# java-luis-client
A Java client for Microsoft's [LUIS](https://www.luis.ai/), including an object model for the query result.

## Usage example:

```java
Client client = new Client(LUIS_APP_ID, LUIS_APP_KEY);
Result result = client.query("Book me a flight to Cairo");

System.out.println(result.getTopScoringIntent());
```


