## AMQ shared consumers listening from different threads in the same topic

This is a small sample of how to consume, using QPID, from different threads in a multicast address on a Red AMQ 7 broker. Use it as reference for your own implementation.

### Configuration

1. Edit the `src/main/resources/jndi.properties` file and add your broker credentials to it.

2. Create a multicast address in your `broker.xml` file.

```xml
<address name="exampleTopic">
	<multicast/>
</address>
```

3. Run the `Consumer1.java` and `Consumer2.java` to create consumers that will listen for messages on the topic.

4. Run the `Producer.java`. A message must be sent to the topic and one of the consumers should consume it.

5. Alternatively, comment lines `32` and `35` and uncomment lines `33` and `36` in class `Producer.java`.

6. Run the `Producer.java` again. A message must be sent to the topic and the another consumer should consume it. 