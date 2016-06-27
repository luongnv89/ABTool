#!/bin/sed -f

# This script cleans useless dependencies
# We exploit the fact that sed is greedy 
# so that [^ ]\+ matches a full filename

:a
s/^\([^ ]\+\)\([ ]\+\)\1\( \|$\)/\1\3/g
t a
s/ \([^ ]\+\)\([ ]\+\)\1\( \|$\)/ \1\3/g
t a
# Once there we know that duplicates are separated 
# by at least something
# 
s/^\([^ ]\+\)\( .\+\)\([ ]\+\)\1\( \|$\)/\1\2\4/g
t a
s/ \([^ ]\+\)\( .\+\)\([ ]\+\)\1\( \|$\)/ \1\2\4/g
t a
