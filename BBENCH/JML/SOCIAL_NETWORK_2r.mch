/* SOCIAL_NETWORK_2r
 * Author: 
 * Creation date: Thu Aug 20 2009
 */
 
REFINEMENT
   SOCIAL_NETWORK_2r
REFINES
   SOCIAL_NETWORK_r

ABSTRACT_VARIABLES
    person ,
    rawcontent, 
    content , 
    owner ,
    act ,
    visible,
    principal ,
    field ,
    prawcontent
    
    
INVARIANT
    prawcontent <: RAWCONTENT &
    prawcontent <: rawcontent  &
    
    prawcontent <: principal &
    
    ( prawcontent <<: rawcontent => prawcontent /= {} ) &
    
    dom(content |> prawcontent) = person 

INITIALISATION
    person := {} ||
    rawcontent := {} ||
    content := {} ||
    owner := {} ||
    act := {} ||
    visible := {} ||
    principal := {} ||
    field := {} ||
    prawcontent := {}
   
OPERATIONS
    
    per <-- create_content =
    SELECT
        person <<: PERSON & rawcontent <<: RAWCONTENT
    THEN
        ANY pe , rrc, prc
        WHERE 
            pe : PERSON - person & 
            rrc <: RAWCONTENT - rawcontent &
            prc <: RAWCONTENT - rawcontent &
            prc /= {} & 
            rawcontent \/ rrc \/ prc <: RAWCONTENT & rrc /= {} 
            & prc <: rrc  
        THEN
            person := person \/ { pe } ||
            rawcontent := rawcontent \/ rrc || 
            content := content \/ {pe} * rrc ||
            owner := owner \/ rrc * { pe } ||
            act := act \/ rrc * OPS * { pe } ||
            principal := principal \/ rrc ||
            per := pe ||
            prawcontent := prawcontent \/ prc
        END
    END;
    
    
  remove_rc ( rc , pe ) =
  PRE
      rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
      pe |-> rc : visible & pe /= owner ( rc )
  THEN
          SELECT pe: dom(content - { pe |-> rc }) & rc /: prawcontent  THEN
              visible := visible - { pe |-> rc } ||
              content := content - { pe |-> rc } ||
              act := act - { rc |-> view |-> pe }
          END
      END;
      
  remove_owned_rc ( rc ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent
   THEN
       SELECT  rc /: prawcontent THEN
           SELECT rc /: principal THEN
               rawcontent := rawcontent - {rc} ||
               visible := visible |>> {rc} ||
               content := content |>> {rc} ||
               act := ( {rc} * OPS ) <<| act  ||
               owner := {rc} <<| owner ||
               field := {rc} <<| field
           WHEN rc : principal & {rc} /= principal THEN
               rawcontent := rawcontent - ( {rc} \/ field~[{rc}] ) ||
               visible := visible |>> (field~[{rc}] \/ {rc}) ||
               content := content |>> (field~[{rc}] \/ {rc}) ||
               act := ( (field~[{rc}] \/ {rc}) * OPS ) <<| act ||
               owner := (field~[{rc}] \/ {rc}) <<| owner ||
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
        ANY newrc, prs
        WHERE
            newrc : RAWCONTENT & newrc /= rc & newrc /: rawcontent &
            prs <: person
        THEN
            SELECT pe /= owner(rc) & rc /: principal THEN
                rawcontent := rawcontent \/ {newrc} ||
                visible := ( visible \/ { pe |-> newrc } ) - { pe |-> rc } ||
                content := ( content \/ { pe |-> newrc } ) - { pe |-> rc } ||
                act := ( act \/ { newrc } * OPS * { pe } )  - { rc } * OPS * { pe }||
                owner := owner \/ { newrc |-> pe }  ||
                field := field \/ { newrc |-> field(rc) }
                    
            WHEN pe = owner(rc) & rc : principal & visible~[{rc}] <: prs THEN
                rawcontent := ( rawcontent \/ {newrc} ) - {rc} ||
                visible :=  (visible |>> {rc}) \/ (visible~[{rc}] * {newrc}) ||
                content :=  (content |>> {rc}) \/ (content~[{rc}] * {newrc}) ||
                act := ({rc}*OPS) <<|act \/ {newrc}*OPS*{pe} \/ {newrc}*{view}*prs ||
                owner := ({rc} <<| owner) \/ { newrc |-> pe } ||
                field := ( field |>> {rc} ) \/ ( field~[{rc}] * {newrc} ) ||
                principal := (principal \/ {newrc} ) - { rc } ||
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
       ANY prs
       WHERE
           prs <: person & /*prs /= {} &*/ owner(rc) /: prs
       THEN
           rawcontent := rawcontent \/ {cmt} ||
           visible := visible \/ {owner(rc) |-> cmt} \/prs*{cmt} ||
           content := content \/ {owner(rc) |-> cmt} \/ prs*{cmt} ||           
           owner := owner \/ {cmt |-> owner(rc)} ||
           act := act \/ {cmt} * OPS * {owner(rc)} \/ {cmt} * {view} * prs ||
           
           SELECT rc : principal THEN
               field := field \/ {cmt |-> rc}
           WHEN rc /: principal THEN
               field := field \/ {cmt |-> field(rc)}
           END
       END
   END
   
END
