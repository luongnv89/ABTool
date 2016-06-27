#!/bin/sed -f

# This script adds generation rules to dependency rules

1 {
  s/^.*$/# &\n#\n# $Id$\n\n/
}

# Lines of the form ... : grammar.g supergrammar.g ...
/^[^:]\+: [^ ]\+\.g [^ ]\+\.g.*$/ {
  s/^\([^: ]\+\.html \)\+: \(\([^ ]\+\)\.g\) \([^ ]\+\.g\).*$/&\n	echo "HTML of \3"\n	cd ABTOOLS\/GRAMMAR \&\& $(ANTLR) -html -glib "\4" \2\n/
  t a
  s/^[^:]\+: \(\([^ ]\+\)\.g\) \([^ ]\+\.g\).*$/&\n	echo "Generation of \2"\n	cd ABTOOLS\/GRAMMAR \&\& $(ANTLR) -glib "\3" \1 2> \1.err\n/
  :a
}
# Lines of the form ... : grammar.g ...
/^[^:]\+: [^ ]\+\.g [^ ]\+\.g.*$/! {
  s/^\([^: ]\+\.html \)\+: \(\([^ ]\+\)\.g\).*$/&\n	echo "HTML of \3"\n	cd ABTOOLS\/GRAMMAR \&\& $(ANTLR) -html \2\n/
  t b
  s/^[^:]\+: \(\([^ ]\+\)\.g\).*$/&\n	echo "Generation of \2"\n	cd ABTOOLS\/GRAMMAR \&\& $(ANTLR) \1 2> \1.err\n/
  :b
}
