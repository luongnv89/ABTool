#!/usr/bin/sed -f

# This script will take as a first argument a line containing an additional dependency (the .g file)

# Content of the hold space: 3 lines
# Name of currently considered class\n (called h1)
# files produced\n (called h2)
# files depended upon (called h3)

1 {
 s/^.*$/\n&.err\n&/
 h
 d
}

# We encounter a new class:
# class A extends B
# Normal case at the end for the hold space (minus what was considered so far)
# A\n
# h2 ATokenTypes.{java,txt} A.{java,smap,html}\n
# h3 B.mystery (because we don't know in what file B is, actually)
# If B is a base grammar, it is simpler:
# A\n
# h2 ATokenTypes.{java,txt} A.{java,smap,html}\n
# h3 (B is basic thus in antlr)

/^[ \t]*class[ \t]*\([^ \t]*\)[ \t]*extends[ \t]*\([^ \t]*\)[ \t]*;[^ \t]*$/ {
# class a extends b gives a.{java,smap} : b.mystery
 s/^[ \t]*class[ \t]*\([^ \t]*\)[ \t]*extends[ \t]*\([^ \t]*\)[ \t]*;[^ \t]*$/\2.mystery;\1.java \1.smap \1.html;\1/
# Add the contents of the hold space
 G
#Mysterious dependency on the right, produced files on the left
 s/^\(Parser\|TreeParser\|Lexer\)\.mystery;\(.*\);\(.*\)\n.*\n\(.*\)\n\([^ ]\+.g\)\(.*\)/\3\n\4 \3TokenTypes.java \3TokenTypes.txt \2\n\5\6/
 s/^\(.\+\);\(.*\);\(.*\)\n.*\n\(.*\)\n\([^ ]\+.g\)\(.*\)/\3\n\4 expanded\5 \3TokenTypes.java \3TokenTypes.txt \2\n\5\6 \1/
#Put back in the hold space
 h
 d
}

# A vocabulary is imported ! Thus there is a new dependency 
# on a file we don't know that produces the vocabulary
# importVocab X
# Result for the hold space
# h1\n
# h2 X.vocab\n
# h3

/^[ \t]*importVocab[ \t]*=[ \t]*\([^ \t]*\)[ \t]*;[ \t]*\(\/\/.*\)\?$/ {
# importVocab = v; becomes ... : ... v.vocab
 s/^[ \t]*importVocab[ \t]*=[ \t]*\([^ \t]*\)[ \t]*;[ \t]*\(\/\/.*\)\?$/\1.vocab/
# Add the hold space then put the vocab in the produced file line
 G
 s/\(.*\)\n\(.*\)\n\(.*\)\n\(.*\)/\2\n\3\n\4 \1/
# Put back in the hold space
 h
 d
}

# Damn, our current class exports a vocabulary of another name.
# Then we must get rid of ATokenTypes.{txt,java}
# And add the correct AnotherNameTokenTypes.{txt,java}
# Result for the hold space
# h1\n
# h2 \ {h1TokenTypes.java,h1TokenTypes.txt} AnotherNameTokenTypes.{txt,java}\n
# h3

/^[ \t]*exportVocab[ \t]*=[ \t]*\([^ \t]*\)[ \t]*;[ \t]*\(\/\/.*\)\?$/ {
# exportVocab = v; becomes vTokenTypes.{java,txt} : ...
 s/^[ \t]*exportVocab[ \t]*=[ \t]*\([^ \t]*\)[ \t]*;[ \t]*\(\/\/.*\)\?$/\1/
 s/^.*$/&/
# Add the hold space
 G
# Add the exported vocabs
 s/\(.*\)\n\(.*\)\n\(.*\)\n\(.*\)/\2\n\3 \1TokenTypes.java \1TokenTypes.txt\n\4/
# Then get rid of the wrong exported vocabs
  s/\(.*\)\n\(.*\) \1TokenTypes.java \1TokenTypes.txt\(.*\)\n\(.*\)/\1\n\2 \3\n\4/
# Put back in the hold space
 h
 d
}

# End of file... Then take the hold space, make it look like a 
# real Makefile dependency and print it
$ {
 g
 s/\(.*\)\n\(.*\)\n\(.*\)/\2 : \3/
 s/[ ]\+/ /g
 p
}

# We don't treat any other line of the .g file, thus getting rid of it
d
