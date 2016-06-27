/* part of ABTOOLS Project */

/*
* DFA est un automate � �tat fini non-d�terministe
* L'automate � donc un nombre fini d'�tat. Cela est repr�sent� par le param�tre de la machine donnant le nombre maximum d'�tats.
*/

MACHINE DFA(dfa_size_max)
CONSTRAINTS
	dfa_size_max : INTEGER & dfa_size_max > 0

SEES
	FA, NFA

CONSTANTS
	dfa_transition
PROPERTIES
	/*
	* nfa_transition est l'ensemble des valeurs possibles pour un arc d'une transition dans un automate d�terministe.
	*/
	dfa_transition <<: INTEGER * INTEGER &
	dfa_transition = interval




VARIABLES
	dfa_states, dfa_finals, dfa_transitions,
	dfa_end_trans, dfa_end_path, dfa_end
INVARIANT
	/*
	* dfa_states est l'ensemble des �tats de l'automate. C'est un sous-ensemble de NATURAL.
	* On construit cet ensemble au fur et � mesure de telle sorte que les �tats sont tous num�rot�s � la suite � partir de 0
	*/ 
	dfa_states <: NATURAL &
	(dfa_states /= {} => dfa_states = 0..(card(dfa_states)-1)) &

	/*
	* dfa_finals est une relation entre les �tats de l'automate et l'ID du symbole (terminal d'une grammaire) accept� � cet �tat.
	* Si le couple (state|->sid) appartient � la relation, alors l'�tat state est un �tat final de l'automate qui reconnait alors � ce stade le terminal sid.
	* C'est une relation car un m�me �tat peut �tre final par rapport � plusieurs symboles (terminal de grammaire).
	*/
	dfa_finals : NATURAL <-> INTEGER &
	dom(dfa_finals) <: dfa_states &

	/*
	* dfa_transitions repr�sente les transitions entre les �tats.
	* Le couple ((start, value)|->end) appartenant � la relation indique qu'une transition depuis l'�tat start vers l'�tat end est possible avec une valeur value en entr�e.
	* Le domaine du domaine, c'est � dire les �tats dont l'arcs est sortant est un sous-ensemble de celui des �tats
	* Le codomaine est l'ensemble des �tats poss�dant une transition entrante
	*/
	dfa_transitions : (NATURAL * dfa_transition) +-> NATURAL &
	dom(dom(dfa_transitions)) <: dfa_states &
	ran(dfa_transitions) <: dfa_states &
	
	/*
	* Transition d'un ensemble d'�tats � partir d'une valeur en entr�e
	* Ensemble des intervals contenant la valeur en entr�e
	*	{ ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) }
	*/
	dfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
	!(states, value).(	states:POW(NATURAL) & states<:dfa_states & value:INTEGER
						=> dfa_end_trans(states, value) = ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| dfa_transitions )
	) &
	
	/*
	* Transition d'un ensemble d'�tats � partir d'une s�quence en entr�e.
	* Simule l'ensemble des chemins possible dans l'automate pour une entr�e donn�e.
	* La fonction associe � l'ensemble des �tats de d�part l'ensemble des �tats accessibles dans l'automate pour l'entr�e donn�e.
	* La fonction est d�finie r�cursivement � l'aide de la fonction de transition au dessus.
	* La r�cursivit� s'arr�te lorsque la s�quence en entr�e est vide.
	* NOTE : Pour chaque �tat : pour tout couple de transition, les intervalles doivent �tre soit �gaux ou avoir une intersection vide pour que l'automate soit d�terministe.
	* Cette contrainte n'est pas impos�e ici. Par cons�quent, les propri�t�s ici sont pour la fonction de transition sont similaires � celles pour un automate non-d�terministe.
	*/
	dfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
	!(start, data).(	start<:NATURAL & start<:dfa_states & data:input & data=[]
						=>
						dfa_end_path(start, data) = start
	) &
	!(start, data).(	start<:NATURAL & start<:dfa_states & data:input & data/=[]
						=>
						dfa_end_path(start, data) = dfa_end_path(
										dfa_end_trans(
												start,
												first(data)
										),
										tail(data)
									)
	) &
	
	/*
	* Simulation de l'automate au regard des symboles reconnus
	* La fonction associe � un ensemble d'�tats de depart et � une s�quence en entr�e donn�e les ID des symboles reconnus.
	*/
	dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
	!(states, data).(	states<:NATURAL & states<:dfa_states & data:input
						=>
						dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals )
	)


INITIALISATION
	dfa_states, dfa_finals, dfa_transitions,
	dfa_end_trans, dfa_end_path, dfa_end
	:(
		dfa_states = {} &
		dfa_finals = {} &
		dfa_transitions = {} &

		dfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
		!(states, value).(states:POW(NATURAL) & states<:dfa_states & value:INTEGER => dfa_end_trans(states, value) = ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| dfa_transitions )) &
		
		dfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
		!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data=[] => dfa_end_path(start, data) = start) &
		!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data/=[] => dfa_end_path(start, data) = dfa_end_path(dfa_end_trans(start, first(data)), tail(data))) &
		
		dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
		!(states, data).(states<:NATURAL & states<:dfa_states & data:input => dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals ))
	)


OPERATIONS
	/*
	* Ajout � l'automate d'un nouvel �tat.
	* Le num�ro du nouvel �tat est retourn�.
	*/
	state <-- DFA_AddState =
	PRE
		card(dfa_states) < dfa_size_max &
		card(dfa_states) /: dfa_states
	THEN
		state,
		dfa_states, dfa_end_trans, dfa_end_path, dfa_end
		:(
			state = card(dfa_states$0) &
			dfa_states = dfa_states$0 \/ {state} &
			dfa_states = 0..card(dfa_states$0) &
			
			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			dfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:dfa_states & value:INTEGER => dfa_end_trans(states, value) = ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| dfa_transitions )) &
		
			dfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data=[] => dfa_end_path(start, data) = start) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data/=[] => dfa_end_path(start, data) = dfa_end_path(dfa_end_trans(start, first(data)), tail(data))) &
		
			dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:dfa_states & data:input => dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals ))
		)
	END ;

	/*
	* Sp�cifie l'ID d'un symbol et un �tat.
	* L'�tat sp�cifi� devient ainsi un �tat final de l'automate, l'automate reconnaissant alors � cet �tat le symbol.
	*/
	DFA_AddFinal(state, sid) =
	PRE
		state : dfa_states &
		sid : INTEGER
	THEN
		dfa_finals, dfa_end
		:(
			dfa_finals = dfa_finals$0 \/ {state |-> sid} &
			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:dfa_states & data:input => dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals ))
		)
	END ;

	/*
	* Ajoute une transition � l'automate.
	* La transition part de l'�tat start pour arriv� � l'�tat end et a pour valeur value.
	*/
	DFA_AddTransition(start, end, value) =
	PRE
		start : dfa_states &
		end : dfa_states &
		value : dfa_transition &
		{start |-> value} <| dfa_transitions = {}
	THEN
		dfa_transitions, dfa_end_trans, dfa_end_path, dfa_end
		:(
			dfa_transitions = dfa_transitions$0 \/ {(start|->value)|->end} &

			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			dfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:dfa_states & value:INTEGER => dfa_end_trans(states, value) = ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| dfa_transitions )) &
		
			dfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data=[] => dfa_end_path(start, data) = start) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data/=[] => dfa_end_path(start, data) = dfa_end_path(dfa_end_trans(start, first(data)), tail(data))) &
		
			dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:dfa_states & data:input => dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals ))
		)
	END ;

	/*
	* Construit l'automate d�terministe � partir de l'automate non-d�terministe.
	* Les deux automates doivent finalement reconnaitre le m�me langage.
	* L'automate fini doit �tre vide au d�part.
	*/
	DFA_ConstructFromNFA =
	PRE
		dfa_states = {}
	THEN
		dfa_states, dfa_finals, dfa_transitions,
		dfa_end_trans, dfa_end_path, dfa_end
		:(
			/*
			* R�utilisation des hypoth�ses de l'invariant
			*/
			dfa_states <: NATURAL &
			(dfa_states /= {} => dfa_states = 0..(card(dfa_states)-1)) &

			dfa_finals : NATURAL <-> INTEGER &
			dom(dfa_finals) <: dfa_states &

			dfa_transitions : (NATURAL * dfa_transition) +-> NATURAL &
			dom(dom(dfa_transitions)) <: dfa_states &
			ran(dfa_transitions) <: dfa_states &
			
			dfa_end_trans : POW(NATURAL) * INTEGER +-> POW(NATURAL) &
			!(states, value).(states:POW(NATURAL) & states<:dfa_states & value:INTEGER => dfa_end_trans(states, value) = ran( states * { ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ii=(ll, rr) & ii:interval & ll<=value & value<=rr) } <| dfa_transitions )) &
		
			dfa_end_path : POW(NATURAL) * input +-> POW(NATURAL) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data=[] => dfa_end_path(start, data) = start) &
			!(start, data).(start<:NATURAL & start<:dfa_states & data:input & data/=[] => dfa_end_path(start, data) = dfa_end_path(dfa_end_trans(start, first(data)), tail(data))) &
		
			dfa_end : POW(NATURAL) * input +-> POW(INTEGER) &
			!(states, data).(states<:NATURAL & states<:dfa_states & data:input => dfa_end(states, data) = ran( dfa_end_path(states, data) <| dfa_finals )) &
			
			/*
			* Propri�t�s fondamentales d'�quivalence entre les automates :
			* => M�me ensemble de symboles reconnus
			* => Pour chaque entr�e, les resultats sont �gaux
			*/
			ran(dfa_finals) = ran(nfa_finals) &
			!data.(data:input => dfa_end({0}, data) = nfa_end({0}, data))
		)
	END
END
