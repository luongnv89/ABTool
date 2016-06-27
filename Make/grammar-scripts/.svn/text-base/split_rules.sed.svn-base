#!/bin/sed -f

# We take a rule and separate the html productions from it so as to
# put them in another rule

/^.*:.*$/ {
# Save the rule
  h
# Remove the *.html from the productions
  s/\(^\| \)[^ ]\+\.html//g
  p
# Done and printed. Now get back the full rule again
  x
# We use the technique of keeping interesting stuff up to a newline
  s/^.*$/\n&/
# Now we move things.html in the first line and remove what is not
# until the :
  :a
  s/\(.*\)\n\([^ ]\+\.html \)\([^:]*\):\(.*\)$/\1\2\n\3:\4/
  ta
  s/\(.*\)\n\([^ ]\+ \)\([^:]*\):\(.*\)$/\1\n\3:\4/
  ta
# If we're here then the 2nd line is ": file file ...". We're done
  s/^\(.*\)\n:\(.*\)$/\1:\2/
  p
}

d
