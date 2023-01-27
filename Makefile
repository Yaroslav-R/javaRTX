all: 
	javac src/main/*.java
	java src/main/Rtx

clean:
	rm src/*/*.class