import org.jmlspecs.models.*;
public abstract class SOCIAL_NETWORK_r {
  //@ public model JMLEqualsSet<Integer> person;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> owner;
  //@ public model JMLEqualsToEqualsRelation<JMLEqualsEqualsPair<Integer, String>,Integer> act;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> visible;
  //@ public model JMLEqualsSet<Integer> principal;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> field;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> content;
  //@ public model JMLEqualsSet<Integer> rawcontent;


/*@ public invariant
principal.isSubset(RAWCONTENT)
 && principal.isSubset(rawcontent)
 && (principal.equals(JMLEqualsSet.EMPTY))
 ==> (rawcontent.equals(JMLEqualsSet.EMPTY))
 && (new org.jmlspecs.b2jml.util.Total<Integer, Integer>(rawcontent.difference(principal), principal)).has(field)
;*/

/*@ public initially 
person.isEmpty()&&
content.isEmpty()&&
owner.isEmpty()&&
act.isEmpty()&&
visible.isEmpty()&&
principal.isEmpty()&&
field.isEmpty()&&
rawcontent.isEmpty()
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
  assignable person,rawcontent,content,owner,act,principal;
   ensures  (\exists Integer per; 
(\old(person.isProperSubset(PERSON)
 && rawcontent.isProperSubset(RAWCONTENT)) ==> ((\exists Integer pe;(\exists JMLEqualsSet<Integer> rrc;\old(PERSON.difference(person).has(pe)
 && rrc.isSubset(RAWCONTENT.difference(rawcontent))
 && rawcontent.union(rrc).isSubset(RAWCONTENT)
 && !rrc.equals(JMLEqualsSet.EMPTY)); person.equals(\old(person.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
rawcontent.equals(\old(rawcontent.union(rrc)))&&
content.equals(\old(content.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe), rrc))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
act.equals(\old(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
principal.equals(\old(principal.union(rrc)))&&
per==\old(pe)))))
   && \result == per); */ 
public abstract Integer create_content();


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc);
   assignable rawcontent,visible,content,act,owner,person,field,principal;
   ensures (\old(!principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),act).restrictRangeTo(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),act).range().intersection(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).domain()))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),owner)))&&
person.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).domain()))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),field))))) && 
(\old(principal.has(rc)
 && !org.jmlspecs.b2jml.util.ModelUtils.toSet(rc).equals(principal)) ==> (rawcontent.equals(\old(rawcontent.difference(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS)),act).restrictRangeTo(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS)),act).range().intersection(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).domain()))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)),owner)))&&
person.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).domain()))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(field,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
principal.equals(\old(principal.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))));
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
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,edit),pe))
 && rawcontent.isProperSubset(RAWCONTENT);
   assignable rawcontent,visible,content,act,owner,field,principal;
   ensures (\exists Integer newrc;(\exists JMLEqualsSet<Integer> prs;\old(RAWCONTENT.has(newrc)
 && !newrc.equals(rc)
 && !rawcontent.has(newrc)
 && prs.isSubset(person)); (\old(!pe.equals(owner.apply(rc))
 && !principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))).difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))).difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe)))))&&
field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,field.apply(rc)))))))) && 
(\old(pe.equals(owner.apply(rc))
 && principal.has(rc)
 && visible.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).isSubset(prs)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(visible.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(content.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), prs))))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),owner)).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe)))))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(field,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
principal.equals(\old(principal.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,edit),pe))
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
   assignable rawcontent,visible,content,owner,act,field;
   ensures (\exists JMLEqualsSet<Integer> prs;\old(prs.isSubset(person)
 && !prs.has(owner.apply(rc))); rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), prs))))&&
(\old(principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,rc))))))) && 
(\old(!principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,field.apply(rc)))))))));
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
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe));
   assignable visible;
   ensures visible.equals(\old(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void make_visible_rc(Integer rc, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe));
   assignable visible;
   ensures visible.equals(\old(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void hide_rc(Integer rc, Integer pe);

}
