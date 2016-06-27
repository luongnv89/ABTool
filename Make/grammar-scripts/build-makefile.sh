#!/bin/sh

# This script should be called in the directory where the grammars are
# located

to_here=`dirname $0`

build_deps="$to_here"/build_deps.sed
clean_deps="$to_here"/clean_deps.sed
split_rules="$to_here"/split_rules.sed
add_rules="$to_here"/add_rules.sed
pretty_rules="$to_here"/pretty_rules.sed

depfile=grammars.dep
tmpfile=tmp.txt
sedfile=autosed.sed

rm -f $depfile $tmpfile $sedfile
touch $depfile $tmpfile $sedfile

#Warning: we do not take into account spaces in filenames
allgrammars=`ls *.g`
basegrammars=

for i in $allgrammars
do
  gni=`echo $i | grep "expanded[^ ]\+\.g$"`
  if [ $? -eq 0 ]
  then is_exp=1
  else is_exp=0
  fi
  if [ $is_exp -eq 0 ]
  then
    echo $i | $build_deps - $i >>$depfile
    basegrammars="$i $basegrammars"
  fi
done

mysteries=`sed -e 's/ /\n/g' $depfile \
| grep '\.mystery$' \
| sort \
| uniq`

vocabs=`sed -e 's/ /\n/g' $depfile \
| grep '\.vocab$' \
| sort \
| uniq`

echo \#\!/bin/sed -f >$sedfile
chmod a+x $sedfile

for i in $vocabs
do
  barevocab=`echo $i | sed -e 's/\.vocab$//'`
  echo s! $i! "$barevocab"TokenTypes.txt!g | sed -e 's!\/!\\\/!g' -e 's!\.!\\.!g' >>$sedfile 
done

for i in $mysteries
do
  baremyst=`echo $i | sed -e 's/\.mystery$//'`
  file=`grep "^[[:space:]]*class[[:space:]]*\("$baremyst"\)[[:space:]]*extends[[:space:]]*\([^[:space:]]*\)[[:space:]]*;[^[:space:]]*$" $basegrammars | sed -e 's/^\([^:]*\):.*$/\1/' | head -n 1`
  if [ "x$file" != "x" ]
  then
    grammar=`basename $file`
# Check if the supergrammar extends a base grammar (Parser,...)
    dummy=`grep "^[[:space:]]*class[[:space:]]*\("$baremyst"\)[[:space:]]*extends[[:space:]]*\(TreeParser\|Parser\|Lexer\)[[:space:]]*;[^[:space:]]*$" $grammar`
    if [ $? -eq 0 ]
    then expandedgrammar=$grammar
    else expandedgrammar=expanded$grammar
    fi
    echo s! $i! $expandedgrammar!g | sed -e 's!\/!\\\/!g' -e 's!\.!\\.!g' >>$sedfile 
  fi
done
echo s![ \t]\+! !g >>$sedfile

./$sedfile $depfile >$tmpfile
mv $tmpfile $depfile

# Now we clean duplicate and circular dependencies
$clean_deps $depfile >$tmpfile
mv $tmpfile $depfile

# At this step dependencies are totally valid. But we still have html
# files in productions that shoud be separated from the process of
# producing java files

$split_rules $depfile >$tmpfile
mv $tmpfile $depfile

# Finally write the generation rules

echo Makefile for grammars | $add_rules - $depfile >$tmpfile
mv $tmpfile $depfile

sed -e '/^.*:.*$/ {s/[^ "]\+\.[^ "]\+/ABTOOLS\/GRAMMAR\/&/g}' $depfile >$tmpfile
mv $tmpfile $depfile

$pretty_rules $depfile >$tmpfile
mv $tmpfile $depfile

rm -f $tmpfile $sedfile

rm -f Makefile.gvars

echo \# Automatically generated, contains relevant variables >>Makefile.gvars
echo \# >>Makefile.gvars
echo \# \$Id\$ >>Makefile.gvars
echo >>Makefile.gvars
echo >>Makefile.gvars
echo GRAMMAR_FILES= \\ >>Makefile.gvars

grep '^.* \\$' $depfile | sed -e '$ {s/\\$//}' >>Makefile.gvars

mv $depfile Makefile.grammars

