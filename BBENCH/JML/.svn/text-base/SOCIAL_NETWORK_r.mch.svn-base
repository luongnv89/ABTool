/* SOCIAL_NETWORK_r
 * Author: Nestor Catano, Camilo Rueda
 * Creation date: Mon Aug 3 2009
 */
REFINEMENT
   SOCIAL_NETWORK_r
REFINES
   SOCIAL_NETWORK

ABSTRACT_VARIABLES
   person, owner ,  act, visible, principal, field, content, rawcontent
    
INVARIANT
    principal <: RAWCONTENT &
    principal <: rawcontent &
    
    ( principal = {} => rawcontent = {} ) &
    
    (field : ( rawcontent - principal ) --> principal)
        
INITIALISATION
   person := {} ||
   content := {} ||
   owner := {} ||
   act := {} ||
   visible := {} ||
   principal := {} ||
   field := {} ||
   rawcontent := {}

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
            content := content \/ {pe} * rrc ||
            owner := owner \/ rrc * { pe } ||
            act := act \/ rrc * OPS * { pe } ||
            principal := principal \/ rrc ||
            per := pe
        END 
    END;
    
    remove_owned_rc ( rc ) =
    PRE
        rc : RAWCONTENT & rc : rawcontent
    THEN
        SELECT rc /: principal THEN
            	rawcontent := rawcontent - {rc} ||
                visible := visible |>> {rc} ||
                content := content |>> {rc} ||
                act := ( {rc} * OPS ) <<| act |> dom(content |>> {rc}) ||
                owner := {rc} <<| owner ||
                person :=  dom(content |>> {rc}) ||
                field := {rc} <<| field
            WHEN rc : principal & {rc} /= principal THEN                
            	rawcontent := rawcontent - (field~[{rc}] \/ {rc}) ||
                visible := visible |>> (field~[{rc}] \/ {rc}) ||
                content := content |>> (field~[{rc}] \/ {rc}) ||
                act := ( (field~[{rc}] \/ {rc}) * OPS ) <<| act |> dom(content |>> (field~[{rc}] \/ {rc})) ||
                owner := (field~[{rc}] \/ {rc}) <<| owner ||
                person :=  dom(content |>> (field~[{rc}] \/ {rc})) ||	
                field := field |>> {rc} ||
                principal := principal - {rc} 
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
            newrc : RAWCONTENT & newrc /= rc &  newrc /: rawcontent &
            prs <: person
       THEN
           SELECT pe /= owner(rc) & rc /: principal THEN                   
               rawcontent := rawcontent \/ {newrc} ||
               visible := ( visible \/ { pe |-> newrc } ) - { pe |-> rc } ||
               content := ( content \/ { pe |-> newrc } ) - { pe |-> rc } ||
               act := ( act \/ { newrc } * OPS * { pe } )  - { rc } * OPS * { pe } ||
               owner := owner \/ { newrc |-> pe }  ||
               field := field \/ { newrc |-> field(rc) }
               
           WHEN pe = owner(rc) & rc : principal & visible~[{rc}] <: prs THEN
               rawcontent := ( rawcontent \/ {newrc} ) - {rc} ||
               visible :=  (visible |>> {rc}) \/ (visible~[{rc}] * {newrc}) ||
               content :=  (content |>> {rc}) \/ (content~[{rc}] * {newrc}) ||
               act := ({rc}*OPS) <<|act \/ {newrc}*OPS*{pe} \/ {newrc}*{view}*prs ||
               owner := ({rc} <<| owner) \/ { newrc |-> pe } ||
               field := ( field |>> {rc} ) \/ ( field~[{rc}] * {newrc} ) ||
               principal := (principal \/ {newrc} ) - { rc }
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
   END;

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
   END
END