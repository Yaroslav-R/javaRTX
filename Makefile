all: 
	javac src/geometry/*.java src/main/*.java
	java src/main/Rtx
clearn:
	rm *.class