SRC := $(shell find src -name "*.java")
OUT := out

all: $(SRC)
	mkdir -p $(OUT)
	javac -d $(OUT) $(SRC)

run:
	java -cp out Main

clean:
	rm -rf out


