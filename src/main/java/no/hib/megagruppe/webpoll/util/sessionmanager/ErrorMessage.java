package no.hib.megagruppe.webpoll.util.sessionmanager;

public enum ErrorMessage {

	NOT_LOGGED_IN(){
		@Override
		public String toString(){
			return "Du er ikke innlogget.";
		}
	},
	
	WRONG_USERNAME_OR_PASSWORD(){
		@Override
		public String toString(){
			return "Feil brukernavn eller passord.";
		}
	},
	
	NAME_CAN_NOT_BE_EMPTY(){
		@Override
		public String toString(){
			return "Navnet kan ikke være tomt.";
		}
	}, 
	
	SURVEY_NOT_READY_TO_BE_COMMITED(){
		@Override
		public String toString(){
			return "Du mangler noe i undersøkelsen.";
		}
	},
	
	MULTIPLECHOICE_QUESTION_DOES_NOT_HAVE_ATLEAST_ONE_OPTION(){
		@Override
		public String toString(){
			return "Du må legge til minst et alternativ.";
		}
	}, 
	
	NOT_VALID_CODE_ERROR_MESSAGE(){
		@Override
		public String toString(){
			return "Ugyldig kode!";
		}
	},
	
	EMPTY_CODE_ERROR_MESSAGE(){
		@Override
		public String toString(){
			return "Du må skrive inn en kode.";
		}
	}, 
	
	NO_SURVEY_IN_SESSION(){
		@Override
		public String toString(){
			return "Det finnes ikke en undersøkelse i sesjonen.";
		}
	}
	
}
