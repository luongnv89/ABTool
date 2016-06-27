/* SOCIAL_NETWORK_3r
 * Author: 
 * Creation date: Fri Aug 21 2009
 */
REFINEMENT
   SOCIAL_NETWORK_3r
REFINES
   SOCIAL_NETWORK_2r

ABSTRACT_VARIABLES
   person ,
   rawcontent ,
   content , 
   owner ,
   act ,
   visible ,
   principal ,
   field ,
   prawcontent,
   rawwall,
   rawcanvas,
   wall,
   vinwall,
   canvas,
   wallowner, 
   wallaccess
   
INVARIANT
    rawwall <: RAWCONTENT &
    rawcanvas = rawcontent \/ rawwall &
    rawwall /\ rawcontent = {} & 
    
    wall : person <-> rawwall & 
    ran(wall) = rawwall &
    
    canvas : person <-> rawcanvas &
    canvas = content \/ wall &
     
    
    vinwall <: wall &
    
    wallowner : rawwall --> person &
    wallowner~ <: wall &
    
    wallaccess : person <-> person &
    id(person) <: wallaccess &
    
    !(rc,pe). ((rc: rawwall & pe:person) =>
        (pe |-> rc : wall => wallowner(rc) |-> pe : wallaccess)) 
    
    
ASSERTIONS
    content = canvas |> rawcontent &
	vinwall /\ visible = {} &
	wall /\ content = {} &
	!(rc).( rc : rawcontent - prawcontent => dom(content|>>(field~[{rc}]\/{rc})|>prawcontent) = dom(content|>prawcontent) ) &
	!(rc).( rc : rawcontent - prawcontent =>  dom(content|>>(field~[{rc}]\/{rc})) = person ) 

INITIALISATION
   person := {} ||
   rawcontent := {} ||
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
   wallaccess := {}   
   
OPERATIONS
    
    per <-- create_content =
    SELECT
        person <<: PERSON & rawcontent <<: RAWCONTENT
    THEN
        ANY pe , rrc, prc
        WHERE 
            pe : PERSON - person & rrc <: RAWCONTENT - rawcanvas &
            prc <: RAWCONTENT - rawcanvas & prc /= {} & 
            rawcanvas \/ rrc \/ prc <: RAWCONTENT & rrc /= {} &
            prc <: rrc 
        THEN
            person := person \/ { pe } ||
            rawcontent := rawcontent \/ rrc ||
            rawcanvas := rawcanvas \/ rrc ||
            content := content \/ {pe} * rrc ||
            canvas := canvas  \/ {pe} * rrc ||
            owner := owner \/ rrc * { pe } ||
            act := act \/ rrc * OPS * { pe } ||
            wallaccess := wallaccess \/ { pe |-> pe } ||
            principal := principal \/ rrc ||
            per := pe ||
            prawcontent := prawcontent \/ prc
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
            newrc : RAWCONTENT & newrc /= rc & newrc /: rawcanvas &
            rc : rawcontent &
            prs <: person
        THEN            
          
            SELECT  pe /= owner(rc) & rc /: principal THEN
                rawcontent := rawcontent \/ {newrc} ||
                rawcanvas := rawcanvas \/ {newrc} ||
                
                visible := ( visible - { pe |-> rc } ) \/ { pe |-> newrc } ||
                
                content := ( content - { pe |-> rc } ) \/ { pe |-> newrc } ||
                canvas := ( canvas - { pe |-> rc } ) \/ { pe |-> newrc } ||
                
                act := ( act \/ { newrc } * OPS * { pe } )  - { rc } * OPS * { pe }||
                owner := owner \/ { newrc |-> pe }  ||
                field := field \/ { newrc |-> field(rc) }
            WHEN  pe = owner(rc) & rc : principal & visible~[{rc}] <: prs THEN
                rawcontent := ( rawcontent \/ { newrc } ) - { rc } ||
                visible :=  (visible |>> {rc}) \/ (visible~[{rc}] * {newrc}) ||
                content :=  (content |>> {rc}) \/ (content~[{rc}] * {newrc})||
                act := ({rc}*OPS) <<|act \/ {newrc}*OPS*{pe} \/ {newrc}*{view}*prs ||
                owner := ({rc} <<| owner) \/ { newrc |-> pe } ||
                field := ( field |>> {rc} ) \/ ( field~[{rc}] * {newrc} ) ||
                principal := ( principal \/ {newrc} ) - { rc } ||
                
                rawcanvas := ( rawcanvas \/ { newrc } ) - { rc } ||
                canvas := (canvas~[{rc}] * {newrc}) \/ (canvas |>> {rc}) ||                
                
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
           SELECT cmt /: rawwall THEN
               rawcontent := rawcontent \/ {cmt} ||
               visible := visible \/ {owner(rc) |-> cmt} \/prs*{cmt} ||
               content := content \/ {owner(rc) |-> cmt} \/ prs*{cmt} ||
               owner := owner \/ {cmt |-> owner(rc)} ||
               act := act \/ {cmt} * OPS * {owner(rc)} \/ {cmt} * {view} * prs ||
               
               rawcanvas := rawcanvas \/ {cmt} ||
               canvas := canvas \/ {owner(rc) |-> cmt} \/ prs*{cmt} || 
               
               SELECT rc : principal THEN
                   field := field \/ {cmt |-> rc}
               WHEN rc /: principal THEN
                   field := field \/ {cmt |-> field(rc)}
               END
           END
       END
   END;
   
   
    transmit_rc ( ow , rc , pe ) =
    PRE
        rc : RAWCONTENT & rc : rawcontent & ow : PERSON & ow : person &
        pe : PERSON & pe : person & ow = owner ( rc ) &
        ow /= pe & pe |-> rc /: content & rc|-> view |-> pe /: act
    THEN
        ANY prs
        WHERE
            prs <: person
        THEN
            visible := visible \/ {pe |-> rc} \/ prs*{rc} ||
            
            content := content \/ {pe |-> rc} \/ prs*{rc} ||
            canvas := canvas \/ {pe |-> rc} \/ prs*{rc} ||
            
            act := act \/ {rc}*OPS*{pe} \/ {rc}*OPS*prs
        END
    END;
    
    make_visible_rc ( rc , pe ) =
    PRE
        rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
        pe |-> rc : content & pe |-> rc /: visible & rc |-> view |-> pe : act
    THEN
            visible := visible \/ { pe |-> rc }
    END;
    
   remove_rc ( rc , pe ) =
   PRE
       rc : RAWCONTENT & rc : rawcontent & pe : PERSON & pe : person & 
       pe |-> rc : visible & pe /= owner ( rc )
   THEN
           SELECT pe: dom(content - { pe |-> rc }) & rc /: prawcontent THEN
               visible := visible - { pe |-> rc } ||                
               content := content - { pe |-> rc } ||
               canvas := canvas - { pe |-> rc } ||               
               act := act - { rc |-> view |-> pe }
           END
       END;
       
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
       
    
    comment_wall ( cmt, ow, pe ) =
    PRE
        pe : PERSON & pe: person & ow : PERSON & ow : person &
        cmt : RAWCONTENT & cmt /: rawcontent
    THEN
        SELECT ow |-> pe : wallaccess & cmt /: rawcanvas 
        THEN
            rawwall := rawwall \/ {cmt} ||
            rawcanvas := rawcanvas \/ {cmt} ||
            
            vinwall := vinwall \/ (wallaccess[{ow}]*{cmt}) ||
            
            wall := wall \/ (wallaccess[{ow}]*{cmt})  ||
            canvas := canvas \/ (wallaccess[{ow}]*{cmt}) ||
            
            wallowner := wallowner \/ { cmt |-> ow } 
        END
    END;
    
    hide_comment_wall(cmt, pe) =
     PRE
         pe : PERSON & pe: person & cmt : RAWCONTENT 
     THEN
        SELECT pe |-> cmt : vinwall THEN
            vinwall := vinwall - { pe |-> cmt}
        END
    END;
    
    remove_owned_comment_wall (cmt) =
    PRE
        cmt : RAWCONTENT 
    THEN
        SELECT cmt : rawwall
        THEN
            rawwall := rawwall - {cmt} ||
            rawcanvas := rawcanvas - {cmt} ||
            
            vinwall := vinwall |>> {cmt} ||
            
            wall := wall |>> {cmt} ||
            canvas := canvas |>> {cmt} ||
            
            wallowner := {cmt} <<| wallowner
        END
    END
    
       
END