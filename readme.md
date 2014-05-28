Swap
====

Swap is a library that aims to make switching between applications simple.  This is a young project and there will be more external applications supported if there is enough interest.

There is currently support for
  - Twitter

Switching to an external application is as simple as one line:

```java
Twitter.getInstance(context).viewTweet(tweetID);
```

Of course, you would probably want to check if the user actually has the external applicaiton installed before you go and do this - but don't worry, Swap has got you covered with another simple line of code.

```java
boolean isInstalled = Twitter.getInstance(context).isInstalled();
```
