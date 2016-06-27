/* 
   Description : Machine de test des definitions
   Author : Boulanger jean-louis
   Copyright 2000-2010 Jean-Louis Boulanger
*/

MACHINE
	def_7

DEFINITIONS
	Correct_Signal_ (p) ==
	(!si.( (si: {f_point_sig_s(p), f_point_sig_n(p), f_point_sig_r(p)} )
		=> ( (    	(f_signal$0(si) = TRUE) 
			& 	(free_track(track_associate(p)) = FALSE) )
			=> 
				(f_signal(si) = FALSE) )
	)	)	

END

