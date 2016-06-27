import org.jmlspecs.models.*;
public abstract class SOCIAL_FRIENDS {
  //@ public final ghost JMLEqualsSet<Integer> FRIEND;
  //@ public model JMLEqualsSet<Integer> friend;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> friendship;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> best_friends;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> social_friends;
  //@ public model JMLEqualsToEqualsRelation<Integer,Integer> acquaintances;


/*@ public invariant
friend.isSubset(FRIEND)
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(friend, friend)).has(friendship)
 && friend.equals(friendship.domain().union(friendship.range()))
 && id.apply(friend).intersection(friendship).equals(JMLEqualsSet.EMPTY)
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(friend, friend)).has(best_friends)
 && JMLEqualsSet.convertFrom(best_friends).isSubset(JMLEqualsSet.convertFrom(friendship))
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(friend, friend)).has(social_friends)
 && JMLEqualsSet.convertFrom(social_friends).isSubset(JMLEqualsSet.convertFrom(friendship))
 && (new org.jmlspecs.b2jml.util.Relation<Integer, Integer>(friend, friend)).has(acquaintances)
 && JMLEqualsSet.convertFrom(acquaintances).isSubset(JMLEqualsSet.convertFrom(friendship))
 && best_friends.intersection(social_friends).equals(JMLEqualsSet.EMPTY)
 && best_friends.intersection(acquaintances).equals(JMLEqualsSet.EMPTY)
 && social_friends.intersection(acquaintances).equals(JMLEqualsSet.EMPTY)
 && best_friends.union(social_friends).union(acquaintances).equals(friendship)
;*/

/*@ public invariant_redundantly 
id.apply(friend).intersection(acquaintances).equals(JMLEqualsSet.EMPTY)
 && id.apply(friend).intersection(social_friends).equals(JMLEqualsSet.EMPTY)
 && id.apply(friend).intersection(best_friends).equals(JMLEqualsSet.EMPTY)
;*/

/*@ public initially 
friend.isEmpty()&&
friendship.isEmpty()&&
best_friends.isEmpty()&&
social_friends.isEmpty()&&
acquaintances.isEmpty()
;*/


/*@ public normal_behavior
   requires friend.isProperSubset(FRIEND);
   assignable \nothing;
   ensures (\exists Integer fr; (\exists Integer ab;\old(FRIEND.difference(friend).has(ab)); fr==\old(ab))
   && \result == fr);
also public exceptional_behavior
   requires !(friend.isProperSubset(FRIEND));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract Integer get_new_friend();


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb));
   assignable friend,friendship,best_friends,social_friends,acquaintances;
   ensures friend.equals(\old(friendship.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))).domain().union(friendship.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))).range())))&&
friendship.equals(\old(friendship.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
best_friends.equals(\old(best_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
social_friends.equals(\old(social_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
acquaintances.equals(\old(acquaintances.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))));
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_pair_friends(Integer aa, Integer bb);


/*@ public normal_behavior
   requires ffr.isSubset(FRIEND);
   assignable friend,friendship,best_friends,social_friends,acquaintances;
   ensures friend.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,friendship)),ffr).domain().union(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,friendship)),ffr).range())))&&
friendship.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,friendship)),ffr)))&&
best_friends.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,best_friends)),ffr)))&&
social_friends.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,social_friends)),ffr)))&&
acquaintances.equals(\old(org.jmlspecs.b2jml.util.ModelUtils.range_subtraction(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.domain_subtraction(ffr,acquaintances)),ffr)));
also public exceptional_behavior
   requires !(ffr.isSubset(FRIEND));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void remove_friends(JMLEqualsSet<Integer> ffr);


/*@ public normal_behavior
   requires ffr.isSubset(FRIEND);
   assignable friend,friendship,best_friends,social_friends,acquaintances;
   ensures friend.equals(\old(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).restrictRangeTo(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).range().intersection(ffr)).domain().union(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).restrictRangeTo(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).range().intersection(ffr)).range())))&&
friendship.equals(\old(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).restrictRangeTo(friendship.restrictDomainTo(friendship.domain().intersection(ffr)).range().intersection(ffr))))&&
best_friends.equals(\old(best_friends.restrictDomainTo(best_friends.domain().intersection(ffr)).restrictRangeTo(best_friends.restrictDomainTo(best_friends.domain().intersection(ffr)).range().intersection(ffr))))&&
social_friends.equals(\old(social_friends.restrictDomainTo(social_friends.domain().intersection(ffr)).restrictRangeTo(social_friends.restrictDomainTo(social_friends.domain().intersection(ffr)).range().intersection(ffr))))&&
acquaintances.equals(\old(acquaintances.restrictDomainTo(acquaintances.domain().intersection(ffr)).restrictRangeTo(acquaintances.restrictDomainTo(acquaintances.domain().intersection(ffr)).range().intersection(ffr))));
also public exceptional_behavior
   requires !(ffr.isSubset(FRIEND));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void restrict_friends(JMLEqualsSet<Integer> ffr);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(best_friends).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb));
   assignable best_friends,social_friends,acquaintances;
   ensures best_friends.equals(\old(best_friends.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
social_friends.equals(\old(social_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
acquaintances.equals(\old(acquaintances.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))));
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(best_friends).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void make_bestfriends(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(social_friends).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb));
   assignable best_friends,social_friends,acquaintances;
   ensures best_friends.equals(\old(best_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
social_friends.equals(\old(social_friends.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
acquaintances.equals(\old(acquaintances.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))));
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(social_friends).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void make_socialfriends(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(acquaintances).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb));
   assignable best_friends,social_friends,acquaintances;
   ensures best_friends.equals(\old(best_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
social_friends.equals(\old(social_friends.difference(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))))&&
acquaintances.equals(\old(acquaintances.union(org.jmlspecs.b2jml.util.ModelUtils.toRel(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)))));
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && friend.has(aa)
 && FRIEND.has(bb)
 && friend.has(bb)
 && friendship.difference(acquaintances).has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb)));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract void make_acquaintances(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && FRIEND.has(bb);
   assignable \nothing;
   ensures (\exists JMLEqualsToEqualsRelation<boolean,boolean> bl; bl.equals(\old(best_friends.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))))
   && \result == bl);
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && FRIEND.has(bb));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract JMLEqualsToEqualsRelation<boolean,boolean> are_bestfriends(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && FRIEND.has(bb);
   assignable \nothing;
   ensures (\exists JMLEqualsToEqualsRelation<boolean,boolean> bl; bl.equals(\old(social_friends.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))))
   && \result == bl);
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && FRIEND.has(bb));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract JMLEqualsToEqualsRelation<boolean,boolean> are_socialfriends(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && FRIEND.has(bb);
   assignable \nothing;
   ensures (\exists JMLEqualsToEqualsRelation<boolean,boolean> bl; bl.equals(\old(acquaintances.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))))
   && \result == bl);
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && FRIEND.has(bb));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract JMLEqualsToEqualsRelation<boolean,boolean> are_acquaintances(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && FRIEND.has(bb);
   assignable \nothing;
   ensures (\exists JMLEqualsToEqualsRelation<boolean,boolean> bl; bl.equals(\old(friendship.has(org.jmlspecs.b2jml.util.ModelUtils.maplet(aa,bb))))
   && \result == bl);
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && FRIEND.has(bb));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract JMLEqualsToEqualsRelation<boolean,boolean> are_frieds(Integer aa, Integer bb);


/*@ public normal_behavior
   requires FRIEND.has(aa)
 && friend.has(aa);
   assignable \nothing;
   ensures (\exists [Type_Error Typing ERROR(null) of class ABTOOLS.TYPING.Typing_ERROR] pfr; (\exists [Type_Error Typing ERROR(null) of class ABTOOLS.TYPING.Typing_ERROR] rfr; pfr==\old(org.jmlspecs.b2jml.util.ModelUtils.cartesian(friendship.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(aa)), friendship.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(aa)).difference(id.apply(friendship.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(aa))))).int_size())&&
rfr==\old(friendship.image(friendship.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(aa))).intersection(friendship.image(org.jmlspecs.b2jml.util.ModelUtils.toSet(aa))).int_size())
   &&\result[0] == pfr
   &&\result[1] == rfr));
also public exceptional_behavior
   requires !(FRIEND.has(aa)
 && friend.has(aa));
   assignable \nothing;
   signals (Exception) true;*/ 
public abstract Object [] local_clustering(Integer aa);

}
