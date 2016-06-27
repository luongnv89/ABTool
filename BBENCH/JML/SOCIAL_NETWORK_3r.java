import org.jmlspecs.models.*;
public abstract class SOCIAL_NETWORK_3r {
  //@ public model JMLEqualsSet<Integer> person;
  //@ public model JMLEqualsSet<Integer> rawcontent;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> content;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> owner;
  //@ public model JMLEqualsToEqualsRelation<JMLEqualsEqualsPair<Integer, String>,Integer> act;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> visible;
  //@ public model JMLEqualsSet<Integer> principal;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> field;
  //@ public model JMLEqualsSet<Integer> prawcontent;
  //@ public model JMLEqualsSet<Integer> rawwall;
  //@ public model JMLEqualsSet<Integer> rawcanvas;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> wall;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> vinwall;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> canvas;
  //@ public model JMLEqualsToEqualsMap<Integer,Integer> wallowner;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> wallaccess;


/*@ public invariant
rawwall.isSubset(RAWCONTENT)
 && rawcanvas.equals(rawcontent.union(rawwall))
 && rawwall.intersection(rawcontent).equals(JMLEqualsSet.EMPTY)
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(person, rawwall)).has(wall)
 && wall.range().equals(rawwall)
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(person, rawcanvas)).has(canvas)
 && canvas.equals(content.union(wall))
 && JMLEqualsSet.convertFrom(vinwall).isSubset(JMLEqualsSet.convertFrom(wall))
 && (new org.jmlspecs.b2jml.util.Total<Integer, Integer>(rawwall, person)).has(wallowner)
 && JMLEqualsSet.convertFrom(wallowner.inverse()).isSubset(JMLEqualsSet.convertFrom(wall))
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(person, person)).has(wallaccess)
 && JMLEqualsSet.convertFrom(id.apply(person)).isSubset(JMLEqualsSet.convertFrom(wallaccess))
 && (\forall Integer (rc,pe);(rawwall.has(rc)
 && person.has(pe))
 ==> ((wall.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))
 ==> (wallaccess.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(wallowner.apply(rc),pe)))))
;*/

/*@ public invariant_redundantly 
content.equals(canvas.restrictRangeTo(canvas.range().intersection(rawcontent)))
 && vinwall.intersection(visible).equals(JMLEqualsSet.EMPTY)
 && wall.intersection(content).equals(JMLEqualsSet.EMPTY)
 && (\forall Integer (rc);(rawcontent.difference(prawcontent).has(rc))
 ==> (org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).restrictRangeTo(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).range().intersection(prawcontent)).domain().equals(content.restrictRangeTo(content.range().intersection(prawcontent)).domain())))
 && (\forall Integer (rc);(rawcontent.difference(prawcontent).has(rc))
 ==> (org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))).domain().equals(person)))
;*/

/*@ public initially 
person.isEmpty()&&
rawcontent.isEmpty()&&
content.isEmpty()&&
owner.isEmpty()&&
act.isEmpty()&&
visible.isEmpty()&&
principal.isEmpty()&&
field.isEmpty()&&
prawcontent.isEmpty()&&
rawwall.isEmpty()&&
rawcanvas.isEmpty()&&
wall.isEmpty()&&
vinwall.isEmpty()&&
canvas.isEmpty()&&
wallowner.isEmpty()&&
wallaccess.isEmpty()
;*/

/*@ public normal_behavior
  requires true;
  assignable person,rawcontent,rawcanvas,content,canvas,owner,act,wallaccess,principal,prawcontent;
   ensures  (\exists Integer per; 
(\old(person.isProperSubset(PERSON)
 && rawcontent.isProperSubset(RAWCONTENT)) ==> ((\exists Integer pe;(\exists JMLEqualsSet<Integer> rrc;(\exists JMLEqualsSet<Integer> prc;\old(PERSON.difference(person).has(pe)
 && rrc.isSubset(RAWCONTENT.difference(rawcanvas))
 && prc.isSubset(RAWCONTENT.difference(rawcanvas))
 && !prc.equals(JMLEqualsSet.EMPTY)
 && rawcanvas.union(rrc).union(prc).isSubset(RAWCONTENT)
 && !rrc.equals(JMLEqualsSet.EMPTY)
 && prc.isSubset(rrc)); person.equals(\old(person.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
rawcontent.equals(\old(rawcontent.union(rrc)))&&
rawcanvas.equals(\old(rawcanvas.union(rrc)))&&
content.equals(\old(content.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe), rrc))))&&
canvas.equals(\old(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe), rrc))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
act.equals(\old(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(rrc, OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))&&
wallaccess.equals(\old(wallaccess.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,pe)))))&&
principal.equals(\old(principal.union(rrc)))&&
per==\old(pe)&&
prawcontent.equals(\old(prawcontent.union(prc))))))))
   && \result == per); */ 
public abstract Integer create_content();


/*@ public normal_behavior
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,edit),pe))
 && rawcontent.isProperSubset(RAWCONTENT);
   assignable rawcontent,rawcanvas,visible,content,canvas,act,owner,field,principal,prawcontent;
   ensures (\exists Integer newrc;(\exists JMLEqualsSet<Integer> prs;\old(RAWCONTENT.has(newrc)
 && !newrc.equals(rc)
 && !rawcanvas.has(newrc)
 && rawcontent.has(rc)
 && prs.isSubset(person)); (\old(!pe.equals(owner.apply(rc))
 && !principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
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
principal.equals(\old(principal.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.cartesian(canvas.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).union(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(canvas,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
(\old(prawcontent.has(rc)) ==> (prawcontent.equals(\old(prawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))))));
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
   assignable rawcontent,visible,content,owner,act,rawcanvas,canvas,field;
   ensures (\exists JMLEqualsSet<Integer> prs;\old(prs.isSubset(person)
 && !prs.has(owner.apply(rc))); (\old(!rawwall.has(cmt)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), prs))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
(\old(principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,rc))))))) && 
(\old(!principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,field.apply(rc)))))))))));
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
 && PERSON.has(ow)
 && person.has(ow)
 && PERSON.has(pe)
 && person.has(pe)
 && ow.equals(owner.apply(rc))
 && !ow.equals(pe)
 && !content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && !act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe));
   assignable visible,content,canvas,act;
   ensures (\exists JMLEqualsSet<Integer> prs;\old(prs.isSubset(person)); visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(prs, org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
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
 && !act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe)));
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
 && !pe.equals(owner.apply(rc));
   assignable visible,content,canvas,act;
   ensures (\old(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))).domain().has(pe)
 && !prawcontent.has(rc)) ==> (visible.equals(\old(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
content.equals(\old(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
canvas.equals(\old(canvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
act.equals(\old(act.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,view),pe)))))));
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
   assignable rawcontent,rawcanvas,visible,content,canvas,owner,act,field,principal;
   ensures (\old(!prawcontent.has(rc)) ==> ((\old(!principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
rawcanvas.equals(\old(rawcanvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(canvas,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),owner)))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),act)))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),field))))) && 
(\old(principal.has(rc)
 && !org.jmlspecs.b2jml.util.ModelUtils.toSet(rc).equals(principal)) ==> (rawcontent.equals(\old(rawcontent.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc).union(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))&&
rawcanvas.equals(\old(rawcanvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc).union(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(canvas,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc).union(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)),owner)))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS)),act)))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(field,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
principal.equals(\old(principal.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(rc)
 && rawcontent.has(rc));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_owned_rc(Integer rc);


/*@ public normal_behavior
   requires PERSON.has(pe)
 && person.has(pe)
 && PERSON.has(ow)
 && person.has(ow)
 && RAWCONTENT.has(cmt)
 && !rawcontent.has(cmt);
   assignable rawwall,rawcanvas,vinwall,wall,canvas,wallowner;
   ensures (\old(wallaccess.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(ow,pe))
 && !rawcanvas.has(cmt)) ==> (rawwall.equals(\old(rawwall.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
vinwall.equals(\old(vinwall.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
wall.equals(\old(wall.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
canvas.equals(\old(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
wallowner.equals(\old(wallowner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,ow)))))));
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
   assignable vinwall;
   ensures (\old(vinwall.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,cmt))) ==> (vinwall.equals(\old(vinwall.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,cmt)))))));
also public exceptional_behavior
   requires !(PERSON.has(pe)
 && person.has(pe)
 && RAWCONTENT.has(cmt));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void hide_comment_wall(Integer cmt, Integer pe);


/*@ public normal_behavior
   requires RAWCONTENT.has(cmt);
   assignable rawwall,rawcanvas,vinwall,wall,canvas,wallowner;
   ensures (\old(rawwall.has(cmt)) ==> (rawwall.equals(\old(rawwall.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
rawcanvas.equals(\old(rawcanvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
vinwall.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(vinwall,org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
wall.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(wall,org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(canvas,org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
wallowner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt),wallowner)))));
also public exceptional_behavior
   requires !(RAWCONTENT.has(cmt));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_owned_comment_wall(Integer cmt);

}
