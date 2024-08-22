# Variables
JFLAGS = -g
JC = javac
JVM = java
MAIN = SocialNetworkCLI
JAVADOC = javadoc
JAVADOCFLAGS = -d javadoc

# Source files
SOURCES = Person.java SocialNetwork.java SocialNetworkCLI.java

# Class files
CLASSES = $(SOURCES:.java=.class)

# Default rule
default: run

# Compile all .java files to .class files
%.class: %.java
	$(JC) $(JFLAGS) $<

# Compile all classes
classes: $(CLASSES)

# Clean up .class files
clean:
	rm -f *.class

# Generate Javadoc
javadoc:
	$(JAVADOC) $(JAVADOCFLAGS) $(SOURCES)

# Clean up Javadoc
cleandoc:
	rm -rf javadoc

# Run the main program
run: classes
	$(JVM) $(MAIN)

.PHONY: clean run javadoc cleandoc
