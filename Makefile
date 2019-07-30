

JCC = javac
JFLAGS = -g

stickypolicy/ServiceProvider.class: stickypolicy/ServiceProvider.java
	$(JCC) $(JFLAGS) stickypolicy/ServiceProvider.java

stickypolicy/User.class: stickypolicy/User.java
	$(JCC) $(JFLAGS) stickypolicy/User.java

clean: 
	$(RM) stickypolicy/*.class
