import org.jmlspecs.models.*;
public abstract class SOCIAL_NETWORK {
  //@ public final ghost JMLEqualsSet<Integer> PERSON;
  //@ public static final ghost JMLEqualsSet<String> OPS= JMLEqualsSet.convertFrom(new String[] {"view", "edit"});
  //@ public final ghost JMLEqualsSet<Integer> RAWCONTENT;
  //@ public model JMLEqualsSet<Integer> person;
  //@ public model JMLEqualsSet<Integer> rawcontent;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> content;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> owner;
  //@ public model JMLEqualsToEqualsRelation<JMLEqualsEqualsPair<Integer, String>,Integer> act;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> visible;


/*@ public invariant
person.isSubset(PERSON)
 && rawcontent.isSubset(RAWCONTENT)
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(person, rawcontent)).has(content)
 && (new org.jmlspecs.b2jml.util.Total<Integer, Integer>(rawcontent, person)).has(owner)
 && (new org.jmlspecs.b2jml.util.Relation<JMLEqualsEqualsPair<Integer, String>, Integer>(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rawcontent, OPS), person)).has(act)
 && JMLEqualsSet.convertFrom(visible).isSubset(JMLEqualsSet.convertFrom(content))
 && content.domain().equals(person)
 && content.range().equals(rawcontent)
 && (\forall Integer rc;(rawcontent.has(rc))
 ==> ((\forall String op;(OPS.has(op))
 ==> (act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,op),owner.apply(rc)))))))
 && (\forall Integer rc;(rawcontent.has(rc))
 ==> (content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),rc))))
 && (\forall Integer rc;(rawcontent.has(rc))
 ==> ((\forall Integer pe;(person.has(pe))
 ==> ((visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))
 ==> (act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe)))))))
;*/

/*@ public invariant_redundantly 
(\forall Integer rc;(rawcontent.has(rc))
 ==> (JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)).isSubset(JMLEqualsSet.convertFrom(act.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))))))
;*/

/*@ public initially 
person.isEmpty()&&
rawcontent.isEmpty()&&
content.isEmpty()&&
owner.isEmpty()&&
act.isEmpty()&&
visible.isEmpty()
;*/

/*@ public normal_behavior
  requires true;
  assignable \nothing;
   ensures  (\exists Integer rw; 
(\old(rawcontent.isProperSubset(RAWCONTENT)) ==> ((\exists Integer rc;\old(RAWCONTENT.difference(rawcontent).has(rc)); rw==\old(rc))))
   && \result == rw); */ 
public abstract Integer create_rc();

/*@ public normal_behavior
  requires true;
  assignable person,rawcontent,owner,content,act;
   ensures  (\exists Integer per; 
(\old(person.isProperSubset(PERSON)
 && rawcontent.isProperSubset(RAWCONTENT)) ==> ((\exists Integer pe;(\exists JMLEqualsSet<Integer> rrc;\old(PERSON.difference(person).has(pe)
 && rrc.isSubset(RAWCONTENT.difference(rawcontent))
 && rawcontent.union(rrc).isSubset(RAWCONTENT)
 && !rrc.equals(JMLEqualsSet.EMPTY)); person.equals(\old(person.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
rawcontent.equals(\old(rawcontent.union(rrc)))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
content.equals(\old(content.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe), rrc))))&&
act.equals(\old(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
per==\old(pe)))))
   && \result == per); */ 
public abstract Integer create_content();


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(ow)
 && person.has(ow)
 && PERSON.has(pe)
 && person.has(pe)
 && ow.equals(owner.apply(rc))
 && !ow.equals(pe)
 && !content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe));
   assignable visible,content,act;
   ensures (\exists JMLEqualsSet<Integer> prs;\old(prs.isSubset(person)); visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), prs)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(ow)
 && person.has(ow)
 && PERSON.has(pe)
 && person.has(pe)
 && ow.equals(owner.apply(rc))
 && !ow.equals(pe)
 && !content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void transmit_rc(Integer ow, Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe));
   assignable visible;
   ensures visible.equals(\old(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void make_visible_rc(Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe));
   assignable visible;
   ensures visible.equals(\old(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void hide_rc(Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !pe.equals(owner.apply(rc));
   assignable visible,content,act,person;
   ensures (\old(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))).domain().has(pe)) ==> (visible.equals(\old(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
content.equals(\old(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
act.equals(\old(act.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"view"),pe))))))) && 
(\old(!content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))).domain().has(pe)) ==> (visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe),visible)))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe),content)))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(act,org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
person.equals(\old(person.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !pe.equals(owner.apply(rc)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_rc(Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc);
   assignable visible,content,act,rawcontent,owner,person;
   ensures (\exists JMLEqualsSet<Integer> rcs;\old(rcs.isSubset(RAWCONTENT)
 && rcs.isSubset(rawcontent)
 && !rcs.has(rc)); visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS)),act).restrictRangeTo(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS)),act).range().intersection(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).domain()))))&&
rawcontent.equals(\old(rawcontent.difference(rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)),owner)))&&
person.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,rcs.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).domain())));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_owned_rc(Integer rc);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"edit"),pe))
 && rawcontent.isProperSubset(RAWCONTENT);
   assignable rawcontent,content,visible,act,owner;
   ensures (\exists Integer newrc;(\exists JMLEqualsSet<Integer> prs;\old(RAWCONTENT.has(newrc)
 && !newrc.equals(rc)
 && !rawcontent.has(newrc)
 && prs.isSubset(person)); (\old(!pe.equals(owner.apply(rc))) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))).difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))).difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).difference(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe))))))) && 
(\old(pe.equals(owner.apply(rc))
 && visible.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).isSubset(prs)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(content.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(visible.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), org.jmlspecs.b2jml.util.ModelUtils.toSet("view")), prs))))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),owner)).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe)))))))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,"edit"),pe))
 && rawcontent.isProperSubset(RAWCONTENT));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void edit_rc(Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && RAWCONTENT.has(cmt)
 && !rawcontent.has(cmt)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc));
   assignable owner,rawcontent,act,content,visible;
   ensures (\exists JMLEqualsSet<Integer> prs;\old(prs.isSubset(person)
 && !prs.has(owner.apply(rc))); owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet("view")), prs))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && RAWCONTENT.has(cmt)
 && !rawcontent.has(cmt)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void comment_rc(Integer rc, Integer cmt, Integer pe);


/*@ public normal_behavior
   requires PERSON.has(pe)
 && person.has(pe)
 && PERSON.has(ow)
 && person.has(ow)
 && RAWCONTENT.has(cmt)
 && !rawcontent.has(cmt);
   assignable \nothing;
   ensures true;
also public exceptional_behavior
   requires !(PERSON.has(pe)
 && person.has(pe)
 && PERSON.has(ow)
 && person.has(ow)
 && RAWCONTENT.has(cmt)
 && !rawcontent.has(cmt));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void comment_wall(Integer cmt, Integer ow, Integer pe);


/*@ public normal_behavior
   requires PERSON.has(pe)
 && person.has(pe)
 && RAWCONTENT.has(cmt);
   assignable \nothing;
   ensures true;
also public exceptional_behavior
   requires !(PERSON.has(pe)
 && person.has(pe)
 && RAWCONTENT.has(cmt));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void hide_comment_wall(Integer cmt, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(cmt);
   assignable \nothing;
   ensures true;
also public exceptional_behavior
   requires !(RAWCONTENT.has(cmt));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_owned_comment_wall(Integer cmt);

}
