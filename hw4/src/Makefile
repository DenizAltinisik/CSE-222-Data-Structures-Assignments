JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -private -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		FileSystemElement.java \
		Directory.java \
		File.java \
		FileSystem.java \
		Main.java

all : Main.class

run :
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) $(CLASSES)


clean:
	$(RM) *.class

cleandoc:
	$(RM) -r ./javadoc