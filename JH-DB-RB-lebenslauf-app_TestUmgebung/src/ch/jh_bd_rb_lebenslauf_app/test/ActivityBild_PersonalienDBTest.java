package ch.jh_bd_rb_lebenslauf_app.test;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienData;
import ch.jh_bd_rb_lebenslauf_app.daten.PersonalienDB;
import ch.jh_bd_rb_lebenslauf_app.gui.BildActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ActivityBild_PersonalienDBTest extends
		ActivityInstrumentationTestCase2<BildActivity> {

	@SuppressWarnings("deprecation")
	public ActivityBild_PersonalienDBTest() {
		super("ch.jh_bd_rb_lebenslauf_app", BildActivity.class);
	}

	@Override
	protected void setUp() throws Exception {

	}

	/**
	 * Fuegt einen Bildung datensatz in die DB und liest diesen wider aus.
	 */
	public void test_1DB_PersonalienInsertLoudID() {
		PersonalienData pers = getPersonalien();
		PersonalienDB db = new PersonalienDB(getActivity());
		db.open();
		pers = db.insertPersonalieng(pers);
		assertTrue(pers.getID() > 0);

		PersonalienData returnPers = new PersonalienData(pers.getID());
		returnPers = db.getPersonalien(returnPers);

		if (returnPers != null) {
			assertTrue(pers.getAnrede().equals(returnPers.getAnrede()));
		} else {
			assertTrue(false);
		}

		db.deletePersonalien(returnPers);
		PersonalienData deletPers = new PersonalienData(returnPers.getID());
		deletPers = db.getPersonalien(deletPers);
		
		if (deletPers != null) {
			assertTrue(true);
		}

		db.close();
	}

	/**
	 * Erzeugt 3 Datenbank eintraege mit Personalien und ueberprueft ob
	 * getAllPersonalien die zusätzlichen Einträge zurück gibt.
	 * 
	 */
	public void test_2DB_BildungAllPersonalien() {
		int rows;
		PersonalienDB db = new PersonalienDB(getActivity());

		PersonalienData pers01 = getPersonalien();
		PersonalienData pers02 = getPersonalien();
		PersonalienData pers03 = getPersonalien();

		db.open();

		rows = db.getAllPersonalien().size();
		
		pers01 = db.insertPersonalieng(pers01);
		pers02 = db.insertPersonalieng(pers02);
		pers03 = db.insertPersonalieng(pers03);
		


		ArrayList<PersonalienData> personalien = db
				.getAllPersonalien();
		int test = personalien.size();
		assertTrue(test == rows + 3);

		db.deletePersonalien(pers01);
		db.deletePersonalien(pers02);
		db.deletePersonalien(pers03);

		db.close();
	}


	public void test_3DB_PersonalienGetRows() {
		int rows;
		int rowsGetRows;
		PersonalienDB db = new PersonalienDB(getActivity());

		PersonalienData pers01 = getPersonalien();
		PersonalienData pers02 = getPersonalien();
		PersonalienData pers03 = getPersonalien();
		PersonalienData pers04 = getPersonalien();
		pers04.setName("Ein anderer Name");

		db.open();

		rows = db.getAllPersonalien().size();
		rowsGetRows = db.getPersonalienRows(pers01, LebenslaufDB.PERS_NAME).size();
		
		pers01 = db.insertPersonalieng(pers01);
		pers02 = db.insertPersonalieng(pers02);
		pers03 = db.insertPersonalieng(pers03);
		pers04 = db.insertPersonalieng(pers04);
		

		ArrayList<PersonalienData> personalienRow = db
				.getPersonalienRows(pers01, LebenslaufDB.PERS_NAME);
		int testRow = personalienRow.size();
		assertTrue(testRow == rowsGetRows + 3);

		ArrayList<PersonalienData> personalien = db
				.getAllPersonalien();
		int test = personalien.size();
		assertTrue(test == rows + 4);

		db.deletePersonalien(pers01);
		db.deletePersonalien(pers02);
		db.deletePersonalien(pers03);
		db.deletePersonalien(pers04);

		db.close();
		
	}

	public void test_4DB_PersonalienUpdate() {
		PersonalienData pers = getPersonalien();
		PersonalienDB db = new PersonalienDB(getActivity());
		db.open();
		pers = db.insertPersonalieng(pers);
		assertTrue(pers.getID() > 0);
		
		PersonalienData updatPers = pers.clone();
		updatPers.setAnrede("Frau");
		updatPers.setName("test");
		
		db.updatePersonalien(updatPers);

		updatPers = db.getPersonalien(updatPers);

		if (updatPers != null) {
			assertTrue(!(pers.getAnrede().equals(updatPers.getAnrede())));
			assertTrue(pers.getOrt().equals(updatPers.getOrt()));
		} else {
			assertTrue(false);
		}

		db.deletePersonalien(updatPers);
		PersonalienData deletPers = new PersonalienData(updatPers.getID());
		deletPers = db.getPersonalien(deletPers);
		
		if (deletPers != null) {
			assertTrue(true);
		}

		db.close();
	}

	private PersonalienData getPersonalien() {
		PersonalienData pers = new PersonalienData("anrede", "name", "vorname",
				"strasse", "plz", "ort", "date", "bild");
		return pers;
	}
}
