#!/bin/sed -f

# This script cleans makes makefile rules a bit more readable

/^[ ]*[^ ]\+[ ]*.*:.*$/ {
  s/^[ ]*\([^ ]\+\.[^ ]\+\)[ ]\+\([^:]*:[^:]*\)/\1 \\\n\2/;
  P
  D
}
