src=$(wildcard Lab*.java)
tgt=$(src:%.java=%.class)
bin=$(src:%.java=%)

all: $(tgt)

$(tgt): $(src)
	javac $(src)

run:
	@java $(bin)
