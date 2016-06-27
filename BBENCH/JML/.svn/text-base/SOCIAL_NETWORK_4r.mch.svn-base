/* SOCIAL_NETWORK_4r
 * Author: Nestor Catano and Camilo Rueda
 * Creation date: Fri Aug 28 2009
 */
REFINEMENT
   SOCIAL_NETWORK_4r
REFINES
   SOCIAL_NETWORK_3r
     
INCLUDES
    SOCIAL_FRIENDS /*(PERSON) */
   
ABSTRACT_VARIABLES
   person ,
   rawcontent ,
   content ,
   owner ,
   act ,
   visible,
   principal ,
   field ,
   prawcontent ,
   rawwall ,
   rawcanvas ,
   wall ,
   vinwall ,
   canvas ,
   wallowner ,
   wallaccess

INITIALISATION
   person := {} ||
   content := {} ||
   owner := {} ||
   act := {} ||
   visible := {} ||
   principal := {} ||
   field := {} ||
   prawcontent := {} ||
   rawwall := {} ||
   rawcanvas := {} ||
   wall := {} ||
   vinwall := {} ||
   canvas := {} ||
   wallowner := {} ||
   wallaccess := {} ||
   rawcontent := {} 
   
   
INVARIANT
	friend <: person &
		
!pe. ((pe : dom(friendship)) =>
		(dom(owner |> {pe}) * OPS <| act)~ [ best_friends[{pe}]]
			<:
			(dom(owner |> {pe}) * OPS <| act)~ [ {pe}] ) &
		
/*!pe. ((pe : dom(friendship)) =>
	!bs. ((bs : best_friends[{pe}]) =>
		((dom(owner |> {pe}) /\ ran({bs} <| content)) * OPS <| act)~ [ social_friends[{pe}]] 
			<:
			(dom(owner |> {pe}) * OPS <| act)~ [ {bs}] )) &*/
			
!pe. ((pe : dom(friendship)) =>
    !bf. ((bf : best_friends[{pe}]) =>
        !sf. ((sf: social_friends[{pe}]) =>
            !rc. ((rc : rawcontent) =>
                !ac. ((ac : OPS) =>
                   ( ( pe = owner(rc)  & pe |-> rc : content & rc |-> ac |-> pe : act & bf |-> rc : content &
                        sf |-> rc : content & rc |-> ac |-> sf : act ) => 
                        rc |-> ac: act~[{bf}] ))))) ) &

/*!pe. ((pe : dom(friendship)) =>
	!sf. ((sf : social_friends[{pe}]) =>
		((dom(owner |> {pe}) /\ ran({sf} <| content)) * OPS <| act)~ [ acquaintances[{pe}]]
			<:
			(dom(owner |> {pe}) * OPS <| act)~ [ {sf}] )) &	*/
			
!pe. ((pe : dom(friendship)) =>
    !sf. ((sf : social_friends[{pe}]) =>
        !aq. ((aq: acquaintances[{pe}]) =>
            !rc. ((rc : rawcontent) =>
                !ac. ((ac : OPS) =>
                   ( ( pe = owner(rc) & pe |-> rc : content & rc |-> ac |-> pe : act & sf |-> rc : content &
                        aq |-> rc : content & rc |-> ac |-> aq : act ) =>
                      rc |-> ac : act~[{sf}] ))))) ) &
			
!pe. ((pe : dom(friendship)) =>
	( wallaccess [{pe}] /\ social_friends [{pe}] /= {} =>
		best_friends[{pe}]
		<:
		wallaccess [{pe}]) ) &

!pe. ((pe : dom(friendship)) =>
	( wallaccess [{pe}] /\ acquaintances [{pe}] /= {} => social_friends[{pe}] <: wallaccess [{pe}]))


OPERATIONS
    
    remove_owned_rc ( rc ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent
   THEN
       SELECT  rc /: prawcontent THEN
           SELECT rc /: principal  THEN
               rawcontent := rawcontent - {rc} ||
               rawcanvas := rawcanvas - {rc} || 
               
               visible := visible |>> {rc} ||
               
               content := content |>> {rc} ||
               canvas := canvas |>> {rc} ||
               
               owner := {rc} <<| owner ||	        
               act := ( {rc} * OPS ) <<| act  ||
               
               field := {rc} <<| field
               
           WHEN rc : principal & {rc} /= principal  THEN
               rawcontent := rawcontent - ( {rc} \/ field~[{rc}] ) ||
               rawcanvas := rawcanvas - ( {rc} \/ field~[{rc}] ) ||
               
               visible := visible |>> (field~[{rc}] \/ {rc}) ||
               
               content := content |>> (field~[{rc}] \/ {rc}) ||
               canvas :=  canvas |>> ( {rc} \/ field~[{rc}] ) ||
               
               owner := (field~[{rc}] \/ {rc}) <<| owner ||
               act := ( (field~[{rc}] \/ {rc}) * OPS ) <<| act  ||
               
               field := field |>> {rc} ||
               principal := principal - {rc} 
           END
       END
   END;

 
                
    edit_rc ( rc , pe ) =
    PRE
        rc : RAWCONTENT & rc : rawcontent & 
        pe : PERSON & pe : person &
        pe |-> rc : visible &  rc |-> edit |-> pe : act &
        rawcontent <<: RAWCONTENT 
    THEN
        ANY newrc
        WHERE 
            newrc : RAWCONTENT & newrc /= rc & newrc /: rawcanvas &
            rc : rawcontent 
        THEN            
          
            SELECT  pe /= owner(rc) & rc /: principal THEN
                rawcontent := rawcontent \/ {newrc} ||
                rawcanvas := rawcanvas \/ {newrc} ||
                
                visible := ( visible - { pe |-> rc } ) \/ { pe |-> newrc } ||
                
                content := ( content - { pe |-> rc } ) \/ { pe |-> newrc } ||
                canvas := ( canvas - { pe |-> rc } ) \/ { pe |-> newrc } ||
                
                act := ( act \/ { newrc } * OPS * { pe } ) - { rc } * OPS * { pe }||
                owner := owner \/ { newrc |-> pe }  ||
                field := field \/ { newrc |-> field(rc) }
                
            WHEN  pe = owner(rc) & rc : principal THEN
                rawcontent := ( rawcontent \/ { newrc } ) - { rc } ||
                rawcanvas := ( rawcanvas - { rc } ) \/ { newrc } ||
                
                visible :=  (visible |>> {rc}) \/ (visible~[{rc}] * {newrc}) ||
                
                content := (content |>> {rc}) \/ (content~[{rc}] * {newrc}) ||
                canvas := (canvas |>> {rc}) \/ (canvas~[{rc}] * {newrc}) ||
                
                act := ({rc} * OPS) <<|act \/ {newrc} * OPS * {pe} \/ {newrc}*{view}*content~[{rc}] ||
                
                owner := ({rc} <<| owner) \/ { newrc |-> pe } ||
              
              	field := ( field |>> {rc} ) \/ ( field~[{rc}] * {newrc} ) ||
              	principal := ( principal \/ {newrc} ) - { rc } ||
              
              	SELECT rc : prawcontent THEN
                  prawcontent := ( prawcontent \/ {newrc} ) - { rc }
              	END
            END
            
        END
    END;
   
   comment_rc(rc, cmt, pe) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & 
       cmt : RAWCONTENT & cmt /: rawcontent &
       pe : PERSON & pe: person &
       pe |-> rc : visible
   THEN
       SELECT cmt /: rawwall & pe : friendship~[{owner(rc)}] THEN
           SELECT pe : best_friends[{owner(rc)}] THEN
               rawcontent := rawcontent \/ {cmt} ||
               visible := visible \/ {owner(rc) |-> cmt} \/ best_friends[{owner(rc)}]*{cmt} ||
               content := content \/ {owner(rc) |-> cmt} \/ best_friends[{owner(rc)}]*{cmt} ||
               owner := owner \/ {cmt |-> owner(rc)} ||
               act := act \/ {cmt} * OPS * {owner(rc)} \/ {cmt} * {view} * best_friends[{owner(rc)}] ||
               
               rawcanvas := rawcanvas \/ {cmt} ||
               canvas := canvas \/ {owner(rc) |-> cmt} \/ best_friends[{owner(rc)}]*{cmt} 
           WHEN pe : social_friends[{owner(rc)}] THEN
               rawcontent := rawcontent \/ {cmt} ||
               visible := visible \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}])*{cmt} ||
               content := content \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}])*{cmt} ||
               owner := owner \/ {cmt |-> owner(rc)} ||
               act := act \/ {cmt} * OPS * {owner(rc)} \/ {cmt} * {view} * (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}]) ||
               
               rawcanvas := rawcanvas \/ {cmt} ||
               canvas := canvas \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}])*{cmt}  
           WHEN pe : acquaintances[{owner(rc)}] THEN
               rawcontent := rawcontent \/ {cmt} ||
               visible := visible \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}] \/ acquaintances[{owner(rc)}])*{cmt} ||
               content := content \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}] \/ acquaintances[{owner(rc)}])*{cmt} ||
               owner := owner \/ {cmt |-> owner(rc)} ||
               act := act \/ {cmt} * OPS * {owner(rc)} \/ 
               			 	 {cmt} * {view} * (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}] \/ acquaintances[{owner(rc)}]) ||
               
               rawcanvas := rawcanvas \/ {cmt} ||
               canvas := canvas \/ {owner(rc) |-> cmt} \/ (best_friends[{owner(rc)}] \/ social_friends[{owner(rc)}] \/ acquaintances[{owner(rc)}])*{cmt} 
           END ||
           
           SELECT rc : principal THEN
               field := field \/ {cmt |-> rc}
           WHEN rc /: principal THEN
               field := field \/ {cmt |-> field(rc)}
           END
       END
   END;
   
   transmit_rc ( ow , rc , pe ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & ow : PERSON & ow : person & 
       pe : PERSON & pe : person & ow = owner ( rc ) & 
       ow /= pe & pe |-> rc /: content & rc|-> view |-> pe /: act 
   THEN
       SELECT pe : best_friends[{ow}] & act~[social_friends[{ow}]] /\ ({rc} * OPS) = {} & act~[acquaintances[{ow}]] /\ ({rc} * OPS) = {} THEN  
           visible := visible \/ { pe |-> rc } ||
           
           content := content \/ { pe |-> rc } ||
           canvas := canvas \/ { pe |-> rc } ||
           
           act := act \/ {rc}*OPS*{pe}
           
       WHEN pe : social_friends[{ow}] THEN
           visible := visible \/ { pe |-> rc } \/ (best_friends[{ow}] \/ social_friends[{ow}])*{rc}  ||
           
           content := content \/ { pe |-> rc }  \/ (best_friends[{ow}] \/ social_friends[{ow}])*{rc} ||
           canvas := canvas \/ { pe |-> rc }  \/ (best_friends[{ow}] \/ social_friends[{ow}])*{rc} ||
           
           act := act \/ {rc}*OPS*{pe} \/ {rc}*OPS*(best_friends[{ow}] \/ social_friends[{ow}])
           
       WHEN pe : acquaintances[{ow}] THEN
           visible := visible \/ { pe |-> rc } \/ (best_friends[{ow}] \/ social_friends[{ow}] \/ acquaintances[{ow}])*{rc} ||
           
           content := content \/ { pe |-> rc }  \/ (best_friends[{ow}] \/ social_friends[{ow}] \/ acquaintances[{ow}]) *{rc} ||
           canvas := canvas \/ { pe |-> rc }  \/  (best_friends[{ow}] \/ social_friends[{ow}] \/ acquaintances[{ow}])*{rc} ||
           
           act := act \/ {rc}*OPS*{pe} \/ {rc}*OPS*(best_friends[{ow}] \/ social_friends[{ow}] \/ acquaintances[{ow}])
       END
   END
     
END