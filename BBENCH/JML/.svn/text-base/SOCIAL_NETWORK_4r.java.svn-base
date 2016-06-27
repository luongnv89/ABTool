import org.jmlspecs.models.*;
public abstract class SOCIAL_NETWORK_4r {
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


/*@ public initially 
person.isEmpty()&&
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
wallaccess.isEmpty()&&
rawcontent.isEmpty()
;*/

/*@ public invariant
friend.isSubset(person)
 && (\forall Integer pe;(friendship.domain().has(pe))
 ==> (act.restrictDomainTo(act.domain().intersection(org.jmlspecs.b2jml.util.ModelUtils.cartesian(owner.restrictRangeTo(owner.range().intersection(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).domain(), OPS))).inverse().image(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).isSubset(act.restrictDomainTo(act.domain().intersection(org.jmlspecs.b2jml.util.ModelUtils.cartesian(owner.restrictRangeTo(owner.range().intersection(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).domain(), OPS))).inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))))
 && (\forall Integer pe;(friendship.domain().has(pe))
 ==> ((\forall Integer bf;(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).has(bf))
 ==> ((\forall Integer sf;(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).has(sf))
 ==> ((\forall Integer rc;(rawcontent.has(rc))
 ==> ((\forall String ac;(OPS.has(ac))
 ==> ((pe.equals(owner.apply(rc))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac),pe))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(bf,rc))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(sf,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac),sf)))
 ==> (act.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(bf)).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac)))))))))))))
 && (\forall Integer pe;(friendship.domain().has(pe))
 ==> ((\forall Integer sf;(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).has(sf))
 ==> ((\forall Integer aq;(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).has(aq))
 ==> ((\forall Integer rc;(rawcontent.has(rc))
 ==> ((\forall String ac;(OPS.has(ac))
 ==> ((pe.equals(owner.apply(rc))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac),pe))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(sf,rc))
 && content.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aq,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac),aq)))
 ==> (act.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(sf)).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,ac)))))))))))))
 && (\forall Integer pe;(friendship.domain().has(pe))
 ==> ((!wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).intersection(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).equals(JMLEqualsSet.EMPTY))
 ==> (best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).isSubset(wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))))
 && (\forall Integer pe;(friendship.domain().has(pe))
 ==> ((!wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).intersection(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).equals(JMLEqualsSet.EMPTY))
 ==> (social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)).isSubset(wallaccess.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))))
;*/


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
   requires RAWCONTENT.has(rc)
 && rawcontent.has(rc)
 && PERSON.has(pe)
 && person.has(pe)
 && visible.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc))
 && act.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(org.jmlspecs.b2jml.util.ModelUtils.maplet(rc,edit),pe))
 && rawcontent.isProperSubset(RAWCONTENT);
   assignable rawcontent,rawcanvas,visible,content,canvas,act,owner,field,principal,prawcontent;
   ensures (\exists Integer newrc;\old(RAWCONTENT.has(newrc)
 && !newrc.equals(rc)
 && !rawcanvas.has(newrc)
 && rawcontent.has(rc)); (\old(!pe.equals(owner.apply(rc))
 && !principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,newrc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe)))))&&
field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,field.apply(rc)))))))) && 
(\old(pe.equals(owner.apply(rc))
 && principal.has(rc)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
rawcanvas.equals(\old(rawcanvas.difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(visible,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(visible.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(content,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(content.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(canvas,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(canvas.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(JMLEqualsSet.convertFrom(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)),org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), content.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))))))&&
owner.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc),owner)).union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(newrc,pe)))))&&
field.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(field,org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(field.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)), org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)))))&&
principal.equals(\old(principal.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc))))&&
(\old(prawcontent.has(rc)) ==> (prawcontent.equals(\old(prawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(newrc)).difference(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))))));
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
   ensures (\old(!rawwall.has(cmt)
 && friendship.inverse().image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).has(pe)) ==> ((\old(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).has(pe)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))))) && 
(\old(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).has(pe)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))))) && 
(\old(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).has(pe)) ==> (rawcontent.equals(\old(rawcontent.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))&&
owner.equals(\old(owner.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,owner.apply(rc))))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt), org.jmlspecs.b2jml.util.ModelUtils.toSet(view)), best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))))))))&&
rawcanvas.equals(\old(rawcanvas.union(org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(owner.apply(rc),cmt)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc))).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(owner.apply(rc)))), org.jmlspecs.b2jml.util.ModelUtils.toSet(cmt)))))))&&
(\old(principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,rc))))))) && 
(\old(!principal.has(rc)) ==> (field.equals(\old(field.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(cmt,field.apply(rc))))))))));
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
   ensures (\old(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).has(pe)
 && act.inverse().image(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).intersection(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)).equals(JMLEqualsSet.EMPTY)
 && act.inverse().image(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).intersection(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS)).equals(JMLEqualsSet.EMPTY)) ==> (visible.equals(\old(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
content.equals(\old(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
canvas.equals(\old(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))))&&
act.equals(\old(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe))))))) && 
(\old(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).has(pe)) ==> (visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))))))))) && 
(\old(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).has(pe)) ==> (visible.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(visible.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
content.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(content.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
canvas.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(canvas.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(pe,rc)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))), org.jmlspecs.b2jml.util.ModelUtils.toSet(rc)))))&&
act.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.toRel(act.union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), org.jmlspecs.b2jml.util.ModelUtils.toSet(pe)))).union(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.cartesian(org.jmlspecs.b2jml.util.ModelUtils.toSet(rc), OPS), best_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)).union(social_friends.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow))).union(acquaintances.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(ow)))))))));
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

}
