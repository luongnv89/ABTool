/* part of ABTOOLS Project */

/* Sp�cification des ensembles communs aux 2 types d'automates */

MACHINE FA

CONSTANTS
	interval, epsilon, input
PROPERTIES
	/* interval est l'ensemble des couples de valeurs dans Z+ tel que ce couple d�finie un intervalle dans Z+ */
	/* ll et rr sont les bornes gauche et droite respectivement. */
	/* Les �l�ments de cet ensemble seront utilis� comme valeur pour des arcs de transition des automates. */
	interval <<: INTEGER * INTEGER &
	interval = {ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & ll>=0 & rr>=0 & ll<=rr & ii=(ll, rr)) } &
	
	/* epsilon est l'ensemble des couples de valeurs de Z tel que le couple ne d�finie pas un intervalle de Z. */
	/* ll et rr sont respectivement les bornes gauche et droite de l'"intervalle". */
	/* La borne rr est d�finie comme �galle � ll - 1 */
	epsilon <<: INTEGER * INTEGER &
	epsilon = {ii | #(ll, rr).(ll:INTEGER & rr:INTEGER & (rr - ll + 1) = 0 & ii=(ll, rr)) } &

	/* input est l'ensemble des entr�es possible pour un automate */
	/* C'est l'ensemble des s�quences d'entiers positifs ou nuls. */
	/* La s�quence peut �ventuellement �tre vide */
	input = {ss | ss:seq(INTEGER) & !ii.(ii:1..size(ss) => ss(ii) >= 0) } &

	/* Par construction on a cette propri�t� */
	interval /\ epsilon = {}

END
