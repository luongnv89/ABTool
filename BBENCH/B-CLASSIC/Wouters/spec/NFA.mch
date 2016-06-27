/* part of ABTOOLS Project */


/*
* NFA est un automate � �tat fini non-d�terministe
* L'automate � donc un nombre fini d'�tat. Cela est repr�sent� par le param�tre de la machine donnant le nombre maximum d'�tats.
*/

MACHINE NFA(nfa_size_max)
CONSTRAINTS
	nfa_size_max : INTEGER & nfa_size_max > 0

SEES
	FA

CONSTANTS
	nfa_transition
PROPERTIES
	/*
	* nfa_transition est l'ensemble des valeurs possibles pour un arc d'une transition dans un automate non d�terministe.
	* nfa_transition est donc l'union des valeurs "standardes" et des epsilons.
	*/
	nfa_transition <<: INTEGER * INTEGER &
	nfa_transition = interval \/  epsilon


VARIABLES
	nfa_states, nfa_finals, nfa_transitions, nfa_marks,
	eps_closure_simple, eps_closure,
	nfa_end_trans, nfa_end_path, nfa_end
	
INVARIANT
	/*
	* nfa_states est l'ensemble des �tats de l'automate. C'est un sous-ensemble de NATURAL.
	* On construit cet ensemble au fur et � mesure de telle sorte que les �tats sont tous num�rot�s � la suite � partir de 0
	*/
	nfa_states <: NATURAL &

	/*
	* nfa_finals est une relation entre les �tats de l'automate et l'ID du symbole (terminal d'une grammaire) accept� � cet �tat.
	* Si le couple (state|->sid) appartient � la relation, alors l'�tat state est un �tat final de l'automate qui reconnait alors � ce stade le terminal sid.
	* C'est une relation car un m�me �tat peut �tre final par rapport � plusieurs symboles (terminal de grammaire).
	* Toutefois lors de la construction de l'automate � partir de l'arbre syntaxique de l'expression r�guli�re associ�e, le cas de figure ne peut pas se pr�senter.
	* Comme c'est une relation depuis l'ensemble des �tats, le domaine de la relation doit etre un sous-ensemble de celui des �tats.
	*/
	nfa_finals : NATURAL <-> INTEGER &
	dom(nfa_finals) <: nfa_states &

	/*
	* nfa_transitions repr�sente les transitions entre les �tats.
	* Le couple ((start, value)|->end) appartenant � la relation indique qu'une transition depuis l'�tat start vers l'�tat end est possible avec une valeur value en entr�e.
	* Si value appartient � l'ensemble epsilon, alors le couple repr�sente une epsilon-transition
	* Le domaine du domaine, c'est � dire les �tats dont l'arcs est sortant est un sous-ensemble de celui des �tats
	* Le codomaine est l'ensemble des �tats poss�dant une transition entrant
	*/
	nfa_transitions : (NATURAL * nfa_transition) <-> NATURAL &
	dom(dom(nfa_transitions)) <: nfa_states &
	ran(nfa_transitions) <: nfa_states &

	/*
	* L'injection partielle nfa_marks repr�sente le marquage d'un �tat
	* L'appartenance du couple (state|->mark) � la fonction indique que l'�tat state est marqu� par mark.
	* Les marques peuvent avoir n'importe quelle valeur de l'ensemble INTEGER mais vont toujours par paire d'oppos�.
	* e.g.: Si la marque +1 est pr�sente, alors -1 est forc�ment pr�sente, et r�ciproquement.
	*/
	nfa_marks : NATURAL >+> INTEGER  &
	dom(nfa_marks) <: nfa_states &

	/*
	* Pour tout couple (state_pos, mark) tel que state_pos est marqu� par mark il existe un autre �tat state_neg marqu� par l'oppos�.
	* state_pos et state_neg sont forc�ment diff�rents puisque nfa_marks est une injection (partielle).
	* nfa_marks est une injection (partielle) donc 2 �tats ne peuvent recevoir la m�me marque
	* => Le cardinal de la fonction nfa_marks est toujours divisible par 2.
	* Pour toute marque positive telle qu'un �tat est marqu� par celle-ci
	* => Excatement un �tat poss�de cette marque
	* => Exactement un �tat poss�de la marque oppos�e
	*/
	!mm.(	mm:ran(nfa_marks) & mm>0
		=>
		card(nfa_marks|>{mm}) = 1 &
		card(nfa_marks|>{-mm}) = 1
	) &

	/*
	* Epsilon-fermeture simple d'un ensemble d'�tats
	* L'epsilon-fermeture classique d'un ensemble d'�tats est une fonction de l'ensemble de �tats dans l'ensemble de �tats.
	* Pour tout ensemble d'�tats S :
	* => La fermeture de S contient S
	* => Les �tats accessibles depuis un �tat de la fermetude de S par une epsilon-transition sont aussi dans la fermeture de S
	* Si states est un ensemble d'�tats :
	* -> L'ensemble des epsilon-transitions partant de ces �tats est :
	* (states * epsilon /\ dom(nfa_transitions)) <| nfa_transitions
	*/
	eps_closure_simple : POW(NATURAL) +-> POW(NATURAL) &
	!states.(	states:POW(NATURAL) & states<:nfa_states
			=> states <: eps_closure_simple(states)
	) &
	!states.(	states:POW(NATURAL) & states<:nfa_states
			=> eps_closure_simple(states) = states \/ ran((states * epsilon /\ dom(nfa_transitions)) <| nfa_transitions)
	)&
	
	/*
	* Epsilon-fermeture avec marques d'un ensemble d'�tats
	* L'epsilon-fermeture avec marques d'un ensemble d'�tats est une fonction de l'ensemble de �tats dans l'ensemble de �tats.
	* Pour tout ensemble d'�tats S :
	* => Epsilon-fermeture simple de S inclus l'epsilon-fermeture avec marques de S
	* => Pour tout couple d'�tat (pos, neg) marqu�s respectivement positivement et n�gativement par la m�me marques
	*    La fermeture simple de {pos, neg} n'est pas incluse dans la fermeture avec marques de S
	*/
	eps_closure : POW(NATURAL) +-> POW(NATURAL) &
	!states.(	states:POW(NATURAL) & states<:nfa_states
			=> eps_closure(states) <: eps_closure_simple(states)
	) &
	!(pos, neg, states).(	states:POW(NATURAL) & states<:nfa_states &
					pos:NATURAL & neg:NATURAL & pos:nfa_states & neg:nfa_states &
					#mm.(mm:INTEGER & mm>0 & (pos|->mm):nfa_marks & (neg|->-mm):nfa_marks) &
					{pos, neg} <: eps_closure_simple(states)
				=>
				eps_closure_simple({pos, neg}) /<: eps_closure(states)
	) &
	
	/*
	* Transition d'un ensemble d'�tats � partir d'une valeur en entr�e
	* Ensemble des intervals contenant la valeur en entr�e
	*	{ ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) }
	*/
	nfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
	!(states, value).(	states:POW(NATURAL) & states<:nfa_states & value:INTEGER
				=> nfa_end_trans(states, value) = eps_closure(
						ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| nfa_transitions )
				)
	) &
	
	/*
	* Transition d'un ensemble d'�tats � partir d'une s�quence en entr�e.
	* Simule l'ensemble des chemins possible dans l'automate pour une entr�e donn�e.
	* La fonction associe � l'ensemble des �tats de d�part l'ensemble des �tats accessibles dans l'automate pour l'entr�e donn�e.
	* La fonction est d�finie r�cursivement � l'aide de la fonction de transition au dessus.
	* La r�cursivit� s'arr�te lorsque la s�quence en entr�e est vide.
	*/
	nfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
	!(start, data).(	start<:NATURAL & start<:nfa_states & data:input & data=[]
				=>
				nfa_end_path(start, data) = eps_closure(start)
	) &
	!(start, data).(	start<:NATURAL & start<:nfa_states & data:input & data/=[]
				=>
				nfa_end_path(start, data) = nfa_end_path(
									nfa_end_trans(
											eps_closure(start),
											first(data)
									),
									tail(data)
				)
	) &
	
	/*
	* Simulation de l'automate au regard des symboles reconnus
	* La fonction associe � un ensemble d'�tats de depart et � une s�quence en entr�e donn�e les ID des symboles reconnus.
	*/
	nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
	!(states, data).(	states<:NATURAL & states<:nfa_states & data:input
				=>
				nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals )
	)

INITIALISATION
	nfa_states, nfa_finals, nfa_transitions, nfa_marks,
	eps_closure_simple, eps_closure,
	nfa_end_trans, nfa_end_path, nfa_end
	:(
		nfa_states = {} &
		nfa_finals = {} &
		nfa_transitions = {} &
		nfa_marks = {} &

		/*
		* R�utilisation des hypoth�ses de l'invariant
		*/
		eps_closure_simple : POW(NATURAL) +-> POW(NATURAL) &
		!states.(	states:POW(NATURAL) & states<:nfa_states
				=> states <: eps_closure_simple(states)
		) &
		!states.(	states:POW(NATURAL) & states<:nfa_states
				=> eps_closure_simple(states) = states \/ ran((states * epsilon /\ dom(nfa_transitions)) <| nfa_transitions)
		)&
		
		eps_closure : POW(NATURAL) +-> POW(NATURAL) &
		!states.(	states:POW(NATURAL) & states<:nfa_states
					=> eps_closure(states) <: eps_closure_simple(states)
		) &
		!(pos, neg, states).(	states:POW(NATURAL) & states<:nfa_states &
								pos:NATURAL & neg:NATURAL & pos:nfa_states & neg:nfa_states &
								#mm.(mm:INTEGER & mm>0 & (pos|->mm):nfa_marks & (neg|->-mm):nfa_marks) &
								{pos, neg} <: eps_closure_simple(states)
								=>
								eps_closure_simple({pos, neg}) /<: eps_closure(states)
		) &
		
		nfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
		!(states, value).(	states:POW(NATURAL) & states<:nfa_states & value:INTEGER
					=> nfa_end_trans(states, value) = eps_closure(
							ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| nfa_transitions )
					)
		) &
		
		nfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
		!(start, data).(	start<:NATURAL & start<:nfa_states & data:input & data=[]
					=>
					nfa_end_path(start, data) = eps_closure(start)
		) &
		!(start, data).(	start<:NATURAL & start<:nfa_states & data:input & data/=[]
					=>
					nfa_end_path(start, data) = nfa_end_path(
										nfa_end_trans(
												eps_closure(start),
												first(data)
										),
										tail(data)
					)
		) &
		
		nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
		!(states, data).(	states<:NATURAL & states<:nfa_states & data:input
					=>
					nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals )
		)
	)






OPERATIONS
	/*
	* Ajout � l'automate d'un nouvel �tat.
	* Le num�ro du nouvel �tat est retourn�.
	*/
	state <-- NFA_AddState =
	PRE
		card(nfa_states) < nfa_size_max &
		card(nfa_states) /: nfa_states
	THEN
		state, nfa_states,
		eps_closure_simple, eps_closure, nfa_end_trans, nfa_end_path, nfa_end
		:(
			state = card(nfa_states$0) &
			nfa_states = nfa_states$0 \/ {state} &

			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			eps_closure_simple : POW(NATURAL) +-> POW(NATURAL) &
			!states.(states:POW(NATURAL) & states<:nfa_states => states <: eps_closure_simple(states) ) &
			!states.(states:POW(NATURAL) & states<:nfa_states => eps_closure_simple(states) = states \/ ran((states * epsilon /\ dom(nfa_transitions)) <| nfa_transitions))&

			eps_closure : POW(NATURAL) +-> POW(NATURAL) &
			!states.(states:POW(NATURAL) & states<:nfa_states => eps_closure(states) <: eps_closure_simple(states)) &
			!(pos, neg, states).(	states:POW(NATURAL) & states<:nfa_states &
						pos:NATURAL & neg:NATURAL & pos:nfa_states & neg:nfa_states &
						#mm.(mm:INTEGER & mm>0 & (pos|->mm):nfa_marks & (neg|->-mm):nfa_marks) &
						{pos, neg} <: eps_closure_simple(states)
						=>
						eps_closure_simple({pos, neg}) /<: eps_closure(states)) &
		
			nfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:nfa_states & value:INTEGER => nfa_end_trans(states, value) = eps_closure(ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| nfa_transitions ))) &
			nfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data=[] => nfa_end_path(start, data) = eps_closure(start)) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data/=[] => nfa_end_path(start, data) = nfa_end_path(nfa_end_trans(eps_closure(start),first(data)),tail(data))) &
			nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:nfa_states & data:input => nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals ))
		)
	END ;

	/*
	* Sp�cifie l'ID d'un symbol et un �tat.
	* L'�tat sp�cifi� devient ainsi un �tat final de l'automate, l'automate reconnaissant alors � cet �tat le symbol.
	*/
	NFA_AddFinal(state, sid) =
	PRE
		state : nfa_states &
		sid : INTEGER
	THEN
		nfa_finals, nfa_end
		:(
			nfa_finals = nfa_finals$0 \/ { state |-> sid } &
			nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:nfa_states & data:input => nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals ))
		)
	END ;

	/*
	* Ajoute une transition � l'automate.
	* La transition part de l'�tat start pour arriv� � l'�tat end et a pour valeur value.
	* Les �tats de doivent pas �tre marqu�s.
	*/
	NFA_AddTransition(start, end, value) =
	PRE
		start : nfa_states &
		end : nfa_states &
		value : nfa_transition &
		card({start}<|nfa_marks) = 0 &	/* start n'est pas marqu� */
		card({end}<|nfa_marks) = 0		/* start n'est pas marqu� */
	THEN
		nfa_transitions, eps_closure_simple, eps_closure, nfa_end_trans, nfa_end_path, nfa_end
		:(
			nfa_transitions = nfa_transitions$0 \/ {(start|->value)|->end} &

			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			eps_closure_simple : POW(NATURAL) +-> POW(NATURAL) &
			!states.(states:POW(NATURAL) & states<:nfa_states => states <: eps_closure_simple(states) ) &
			!states.(states:POW(NATURAL) & states<:nfa_states => eps_closure_simple(states) = states \/ ran((states * epsilon /\ dom(nfa_transitions)) <| nfa_transitions))&

			eps_closure : POW(NATURAL) +-> POW(NATURAL) &
			!states.(states:POW(NATURAL) & states<:nfa_states => eps_closure(states) <: eps_closure_simple(states)) &
			!(pos, neg, states).(	states:POW(NATURAL) & states<:nfa_states &
						pos:NATURAL & neg:NATURAL & pos:nfa_states & neg:nfa_states &
						#mm.(mm:INTEGER & mm>0 & (pos|->mm):nfa_marks & (neg|->-mm):nfa_marks) &
						{pos, neg} <: eps_closure_simple(states)
						=>
						eps_closure_simple({pos, neg}) /<: eps_closure(states)) &
		
			nfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:nfa_states & value:INTEGER => nfa_end_trans(states, value) = eps_closure(ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| nfa_transitions ))) &
			nfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data=[] => nfa_end_path(start, data) = eps_closure(start)) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data/=[] => nfa_end_path(start, data) = nfa_end_path(nfa_end_trans(eps_closure(start),first(data)),tail(data))) &
			nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:nfa_states & data:input => nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals ))
		)
	END ;

	/*
	* Ajoute 2 marques oppos�es dans l'automate.
	* Les marques repr�sentent un op�rateur de restriction (-) dans une expression r�guli�re.
	* L'�tat state_pos sera marqu� positivement et l'�tat state_neg sera marqu� n�gativement.
	*/
	NFA_AddMark(state_pos, state_neg, mark) =
	PRE
		state_pos : NATURAL & state_pos : nfa_states &
		state_neg : NATURAL & state_neg : nfa_states &
		mark : INTEGER &
		
		state_pos /= state_neg &		/* state_pos diff�rent de state_neg */
		mark > 0 & mark /= -mark &		/* mark est positif non nul (=> il existe une valeur oppos�e (n�gative)) */
		
		{state_pos} <| nfa_marks = {} &		/* state_pos n'est pas d�j� marqu� */
		{state_neg} <| nfa_marks = {} &		/* state_neg n'est pas d�j� marqu� */
		nfa_marks |> {mark} = {} &		/* La marque positive n'est pas pr�sente */
		nfa_marks |> {-mark} = {} &		/* La marque n�gative n'est pas pr�sente */
		
		card({state_pos}*nfa_transition <| nfa_transitions) = 1 &	/* une seule transition part de state_pos */
		card({state_pos}*epsilon <| nfa_transitions) = 1 &		/* et c'est une epsilon transition */
		card({state_neg}*nfa_transition <| nfa_transitions) = 0		/* Aucune transition ne part de state_neg */
	THEN
		nfa_marks, eps_closure, nfa_end_trans, nfa_end_path, nfa_end
		:(
			nfa_marks =  nfa_marks$0 \/ {state_pos|->mark} \/ {state_neg|->-mark} &
			!mm.(	mm:ran(nfa_marks) & mm>0 => card(nfa_marks|>{mm}) = 1 & card(nfa_marks|>{-mm}) = 1 ) &

			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			eps_closure : POW(NATURAL) +-> POW(NATURAL) &
			!states.(states:POW(NATURAL) & states<:nfa_states => eps_closure(states) <: eps_closure_simple(states)) &
			!(pos, neg, states).(	states:POW(NATURAL) & states<:nfa_states &
						pos:NATURAL & neg:NATURAL & pos:nfa_states & neg:nfa_states &
						#mm.(mm:INTEGER & mm>0 & (pos|->mm):nfa_marks & (neg|->-mm):nfa_marks) &
						{pos, neg} <: eps_closure_simple(states)
						=>
						eps_closure_simple({pos, neg}) /<: eps_closure(states)) &
		
			nfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:nfa_states & value:INTEGER => nfa_end_trans(states, value) = eps_closure(ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| nfa_transitions ))) &
			nfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data=[] => nfa_end_path(start, data) = eps_closure(start)) &
			!(start, data).(start<:NATURAL & start<:nfa_states & data:input & data/=[] => nfa_end_path(start, data) = nfa_end_path(nfa_end_trans(eps_closure(start),first(data)),tail(data))) &
			nfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:nfa_states & data:input => nfa_end(states, data) = ran( nfa_end_path(states, data) <| nfa_finals ))
		)
	END
END
