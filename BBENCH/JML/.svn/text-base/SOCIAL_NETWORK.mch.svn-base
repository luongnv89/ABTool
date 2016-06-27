/* SOCIAL_NETWORK_LAYER
 * Author: Nestor Catano, Camilo Rueda
 * Creation date: Aug 2 2009
 */
MACHINE SOCIAL_NETWORK
    
SETS
    PERSON;
  	OPS={view,edit};
  	RAWCONTENT

VARIABLES
    person,rawcontent,content,owner,act,visible
    
   
INVARIANT
    person <: PERSON &
    rawcontent <: RAWCONTENT &
    content : person <-> rawcontent &
    owner : rawcontent --> person &
    act : (rawcontent * OPS) <-> person  &
    visible <: content  &
    dom(content) = person &
    ran(content) = rawcontent &
    
    !rc. ((rc : rawcontent) => (!op. ((op : OPS) => rc |-> op |-> owner(rc) : act))) &
    !rc. (rc : rawcontent => owner(rc) |-> rc : content ) &
    
/*    !(rc,pe). ( (rc: rawcontent & pe:person) => ( (pe |-> rc : visible) => (rc|-> view|-> pe :  act)) ) */
    !rc. (rc: rawcontent => !pe.( pe:person => 
      ( (pe |-> rc : visible) => (rc|-> view|-> pe :  act)) ))
    
ASSERTIONS
    !rc. ((rc : rawcontent) => {rc}*OPS <: act~[{owner(rc)}])
        
INITIALISATION
    person := {} ||
    rawcontent := {} ||
    content := {} ||
    owner := {} ||
    act := {} ||
    visible := {}
  
OPERATIONS
    
    rw <-- create_rc =
    SELECT 
        rawcontent <<: RAWCONTENT
    THEN
        ANY rc WHERE rc : RAWCONTENT - rawcontent 
        THEN
            rw := rc
        END
    END;
    
    per <-- create_content =
    SELECT
        person <<: PERSON & rawcontent <<: RAWCONTENT
    THEN
        ANY pe , rrc
        WHERE
            pe : PERSON - person & rrc <: RAWCONTENT - rawcontent & 
            rawcontent \/ rrc <: RAWCONTENT & rrc /= {}
        THEN
            person := person \/ { pe } ||
            rawcontent := rawcontent \/ rrc ||
            owner := owner \/ rrc * { pe } ||
            content := content \/ { pe } * rrc ||
            act := act \/ rrc * OPS * { pe } ||
            per := pe
        END
    END;
    
    transmit_rc (ow, rc , pe ) =
    PRE
        rc : RAWCONTENT & rc : rawcontent & ow : PERSON & ow : person &
        pe : PERSON & pe : person & ow = owner ( rc ) &
        ow /= pe & pe |-> rc /: content & rc|-> view |-> pe /: act /* remove rc|-> view |-> pe /: act*/
    THEN
        ANY prs
        WHERE
            prs <: person
        THEN
            visible := visible \/ {pe |-> rc} \/ prs*{rc} ||
            content := content \/ {pe |-> rc} \/ prs*{rc} ||
            act := act \/ {rc}*OPS*{pe} \/ {rc}*OPS*prs
        END
    END;
    
  /*upload_rc( pe, rc ) =
    PRE
    	pe: PERSON & pe: person 
        rc: RAWCONTENT - rawcontent &
 	THEN
 	    rawcontent := rawcontent \/ {rc} ||
 	    owner := owner \/ {rc}*{pe} ||
 	    content := content \/ {pe |-> rc} ||
 	    visible := visible \/ {pe |-> rc} ||
 	    act := act \/ {rc}*OPS*{pe} 
 	END;*/
    

   make_visible_rc ( rc , pe ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
       pe |-> rc : content & pe |-> rc /: visible & rc |-> view |-> pe : act
   THEN
       visible := visible \/ { pe |-> rc }
   END;

   hide_rc ( rc , pe ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
       pe |-> rc : visible & rc |-> view |-> pe : act
   THEN
       visible := visible - { pe |-> rc }
   END;
   
   remove_rc ( rc , pe ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
       pe |-> rc : visible & pe /= owner ( rc )
   THEN
       SELECT pe: dom(content - { pe |-> rc })  THEN
           visible := visible - { pe |-> rc } ||
           content := content - { pe |-> rc } ||
           act := act - { rc |-> view |-> pe }
       WHEN pe /: dom(content - { pe |-> rc }) THEN
           visible := {pe} <<| visible ||
           content := {pe} <<| content ||
           act := act |>> {pe} ||
           person := person - {pe}
       END
   END;
   
   remove_owned_rc ( rc ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent
   THEN
       ANY rcs WHERE rcs <: RAWCONTENT & rcs <: rawcontent & rc /: rcs 
       THEN
           visible := visible |>> (rcs \/ {rc}) ||
           content := content |>> (rcs \/ {rc}) ||
           act := ((rcs \/ {rc}) * OPS) <<| act |> dom(content |>> (rcs \/ {rc}))||
           rawcontent := rawcontent - (rcs \/ {rc}) ||
           owner := (rcs \/ {rc}) <<| owner ||
           person :=  dom(content |>> (rcs \/ {rc}))
       END
   END;
       
  edit_rc ( rc , pe ) =
  PRE
      rc : RAWCONTENT & rc : rawcontent & 
      pe : PERSON & pe : person &
      pe |-> rc : visible & rc |-> edit |-> pe : act &
      rawcontent <<: RAWCONTENT 
  THEN
      ANY newrc, prs
      WHERE
          newrc : RAWCONTENT & newrc /= rc &  newrc /: rawcontent &
          prs <: person
      THEN
          SELECT pe /= owner(rc) THEN
          rawcontent := rawcontent \/ {newrc} ||
          content := (content \/ { pe |-> newrc }) - { pe |-> rc } ||
          visible := (visible \/ { pe |-> newrc }) - { pe |-> rc } ||
          act := (act \/ { newrc } * OPS * { pe })  - ({ rc } * OPS * { pe }) ||
          owner := owner \/ { newrc |-> pe }
          WHEN pe = owner(rc) & visible~[{rc}] <: prs THEN
              rawcontent := ( rawcontent \/ { newrc } ) - { rc } ||
              content :=  (content |>> {rc}) \/ (content~[{rc}] * {newrc})||
              visible :=  (visible |>> {rc}) \/ (visible~[{rc}] * {newrc}) ||
              act := ({rc}*OPS) <<|act \/ {newrc}*OPS*{pe} \/ {newrc}*{view}*prs ||
              owner := ({rc} <<| owner) \/ {newrc |-> pe}
           END
        END
    END;
    
     
    /* res <-- request_permission (rc, pe, op) = 
     PRE
         rc : RAWCONTENT & pe : PERSON & pe : person & pe |-> rc : content &
         op : OPS & pe /= owner(rc)
     THEN
         CHOICE
             act := act \/ {rc |-> op |-> pe} ||
             res := TRUE
         OR
             res := FALSE
         END
     END;*/
     
     comment_rc(rc, cmt, pe) =
     PRE
         rc : RAWCONTENT & rc : rawcontent & cmt : RAWCONTENT & cmt /: rawcontent & 
         pe : PERSON & pe: person &
         pe |-> rc : visible 
     THEN
         ANY prs
         WHERE
             prs <: person & /*prs /= {} &*/ owner(rc) /: prs
         THEN
             owner := owner \/ {cmt |-> owner(rc)} ||
             rawcontent := rawcontent \/ {cmt} ||
             act := act \/ {cmt} * OPS * {owner(rc)} \/ {cmt} * {view} * prs ||
             content := content \/ {owner(rc) |-> cmt} \/ prs*{cmt} ||
             visible := visible \/ {owner(rc) |-> cmt} \/prs*{cmt} 
         END
     END;
     
     comment_wall (cmt, ow, pe) =
     PRE
         pe : PERSON & pe: person & ow : PERSON & ow : person &
         cmt : RAWCONTENT & cmt /: rawcontent
     THEN
        skip
     END;
     
     hide_comment_wall(cmt, pe) =
     PRE
         pe : PERSON & pe: person & cmt : RAWCONTENT 
     THEN
         skip
     END;
     
     remove_owned_comment_wall (cmt) =
     PRE
         cmt : RAWCONTENT
     THEN
       skip
     END
END
