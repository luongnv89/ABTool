
TOP_PATH=..

#  Predicate_Substitution \

DIRECTORIES= \
  DEBUGGING \
  PO \
  CODE_GENERATOR \
  GRAMMAR \
  PRINTING \
  TYPING \
  INTERFACE_TEXTE \
  ANTLR_TOOLS

#CLASSES=

CLASSMAKES=$(patsubst %,ABTOOLS/%/Makefile.vars,$(strip $(DIRECTORIES)))
RULEMAKES=$(patsubst %,ABTOOLS/%/Makefile.classdeps,$(strip $(DIRECTORIES)))

include $(CLASSMAKES) 
include ABTOOLS/GRAMMAR/Makefile.gvars

include $(TOP_PATH)/Make/common.template

include $(RULEMAKES) 
include ABTOOLS/GRAMMAR/Makefile.grammars 

