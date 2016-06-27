/* SOCIAL_FRIENDS
 * Author: Nestor Catano, Camilo Rueda
 * Creation date: August 2 2009
 */
MACHINE
    SOCIAL_FRIENDS /* (FRIEND) */
    SETS FRIEND
    
        
VARIABLES
    friend, friendship, best_friends, social_friends, acquaintances
    
INVARIANT 
    friend <: FRIEND &
    friendship : friend <-> friend &
    friend = dom(friendship) \/ ran(friendship) &
    
    id(friend) /\ friendship = {} &
    
    best_friends : friend <-> friend &
    best_friends <: friendship &
    
    social_friends : friend <-> friend &
    social_friends <: friendship &
    
    acquaintances : friend <-> friend &
    acquaintances <: friendship &
    
    best_friends /\ social_friends = {} &
    best_friends /\ acquaintances = {} &
    social_friends /\ acquaintances = {} &
    best_friends \/ social_friends \/ acquaintances = friendship 
    
ASSERTIONS
    id(friend) /\ acquaintances = {} & 
    id(friend) /\ social_friends = {} & 
    id(friend) /\ best_friends = {}
    
/*ABSTRACT_CONSTANTS
    pfriends
    
PROPERTIES
    pfriends : NATURAL1 --> NATURAL &
    pfriends(1) = 0 &
    (!nn. (nn: NATURAL => pfriends(1+nn) = nn + pfriends(nn))) & 
    pfriends = %nn.(nn : NATURAL1 | SIGMA(ii).(ii : 1..nn-1|ii))
    */
    
INITIALISATION
    friend := {} ||
    friendship := {} ||
    best_friends := {} ||
    social_friends := {} ||
    acquaintances := {} 
    
OPERATIONS
    
    fr <-- get_new_friend =
    PRE friend <<: FRIEND 
    THEN
        ANY ab WHERE ab : FRIEND - friend 
        THEN
            fr := ab
        END
    END;
    
    remove_pair_friends(aa, bb) =
    PRE aa : FRIEND & aa : friend & bb : FRIEND & bb : friend &
        aa |-> bb : friendship
    THEN
        friend := dom(friendship - { aa|-> bb }) \/ ran(friendship - { aa|-> bb }) ||
        friendship := friendship - { aa|-> bb } ||
        best_friends := best_friends - {aa |-> bb} ||
        social_friends := social_friends - {aa |-> bb} ||
        acquaintances := acquaintances - {aa |-> bb}
    END;
    
    remove_friends(ffr) =
    PRE
        ffr <: FRIEND
    THEN
        friend := dom(ffr <<| friendship |>> ffr) \/ ran(ffr <<| friendship |>> ffr) ||
        friendship := ffr <<| friendship |>> ffr ||
        best_friends := ffr <<| best_friends |>> ffr ||
        social_friends:= ffr <<| social_friends |>> ffr || 
        acquaintances := ffr <<| acquaintances |>> ffr 
    END;
    
    restrict_friends(ffr) =
    PRE
        ffr <: FRIEND
    THEN
        friend := dom(ffr <| friendship |> ffr) \/ ran(ffr <| friendship |> ffr) ||
        friendship := ffr <| friendship |> ffr ||
        best_friends := ffr <| best_friends |> ffr ||
        social_friends:= ffr <| social_friends |> ffr || 
        acquaintances := ffr <| acquaintances |> ffr
    END;
    
    
    make_bestfriends(aa, bb) =
    PRE aa : FRIEND & aa : friend & bb : FRIEND & bb : friend & 
        (aa |-> bb) : friendship - best_friends
    THEN
        best_friends := best_friends \/ {aa |-> bb} ||
        social_friends := social_friends  - {aa |-> bb} ||
        acquaintances := acquaintances  - {aa |-> bb}
    END;
        
    make_socialfriends(aa, bb) =
    PRE aa : FRIEND & aa : friend & bb : FRIEND & bb: friend &
        (aa |-> bb) : friendship - social_friends
    THEN
        best_friends := best_friends - {aa |-> bb} ||
        social_friends := social_friends  \/ {aa |-> bb} ||
        acquaintances := acquaintances  - {aa |-> bb} 
    END;
    
    make_acquaintances(aa, bb) =
    PRE aa : FRIEND & aa : friend & bb : FRIEND & bb : friend &
        (aa |-> bb) : friendship - acquaintances
    THEN
        best_friends := best_friends - {aa |-> bb} ||
        social_friends := social_friends  - {aa |-> bb} ||
        acquaintances := acquaintances  \/ {aa |-> bb} 
    END;
    
    bl <-- are_bestfriends(aa, bb) = 
    PRE aa : FRIEND &  bb : FRIEND  
    THEN
        bl := bool( aa|-> bb : best_friends)
    END;
    
    bl <-- are_socialfriends(aa, bb) = 
    PRE aa : FRIEND & bb : FRIEND
    THEN
        bl := bool(aa |-> bb : social_friends)
    END;
    
    bl <-- are_acquaintances(aa, bb) = 
    PRE aa : FRIEND & bb : FRIEND
    THEN
        bl := bool(aa |-> bb : acquaintances)
    END;
    
    bl <-- are_frieds(aa, bb) = 
    PRE aa : FRIEND & bb : FRIEND
    THEN
        bl := bool(aa |-> bb : friendship)
    END;
    
    pfr, rfr <-- local_clustering(aa) =
    PRE aa : FRIEND & aa : friend
    THEN
       pfr := card( friendship[{aa}] * friendship [{aa}] - id(friendship[{aa}]) ) ||
       rfr := card( friendship[friendship[{aa}]] /\ friendship[{aa}] )
   END

END