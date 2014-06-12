package ch.jh_bd_rb_lebenslauf_app.test;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.BerufserfahrungData;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.gui.Berufserfahrung;
import android.test.ActivityInstrumentationTestCase2;

public class ActivityBerufserfahrungDBTest extends
		ActivityInstrumentationTestCase2<Berufserfahrung> {

	@SuppressWarnings("deprecation")
	public ActivityBerufserfahrungDBTest() {
		super("ch.jh_bd_rb_lebenslauf_app", Berufserfahrung.class);
	}

	@Override
	protected void setUp() throws Exception {

	}

	/**
	 * Fuegt einen Bildung datensatz in die DB und liest diesen wider aus.
	 */
	public void test_1DB_BerufserfahrungInsertLoudID() {
		BerufserfahrungData berufserfahrung = getBerufserfahrung();
		BerufserfahrungDB db = new BerufserfahrungDB(getActivity());
		db.open();
		berufserfahrung = db.insertBerufserfahrung(berufserfahrung);
		assertTrue(berufserfahrung.getID() > 0);

		BerufserfahrungData returnBerufserfahrung = new BerufserfahrungData(
				berufserfahrung.getID());
		returnBerufserfahrung = db.getBerufserfahrung(returnBerufserfahrung);
		if (returnBerufserfahrung != null) {
			assertTrue(berufserfahrung.getTxt_firma().equals(
					returnBerufserfahrung.getTxt_firma()));
			assertTrue(berufserfahrung.getPersID().equals(
					returnBerufserfahrung.getPersID()));
		} else {
			assertTrue(false);
		}

		db.deleteBerufserfahrung(returnBerufserfahrung);
		BerufserfahrungData berufserfahrungDelet = new BerufserfahrungData(
				returnBerufserfahrung.getID());
		berufserfahrungDelet = db.getBerufserfahrung(returnBerufserfahrung);
		if (berufserfahrungDelet != null) {
			assertTrue(true);
		}

		db.close();
	}

	/**
	 * Erzeugt 3 Datenbank eintraege mit Bildung und ueberprueft ob
	 * getAllBildungen mehr als 2 Eintraege zurueck giebt.
	 * 
	 */
	public void test_2DB_BildungAllBerufserfahrung() {
		int rows;
		BerufserfahrungDB db = new BerufserfahrungDB(getActivity());
		BerufserfahrungData berufserfahrung01 = getBerufserfahrung();
		BerufserfahrungData berufserfahrung02 = getBerufserfahrung();
		BerufserfahrungData berufserfahrung03 = getBerufserfahrung();

		db.open();

		rows = db.getAllBerufserfahrung().size();

		berufserfahrung01 = db.insertBerufserfahrung(berufserfahrung01);
		berufserfahrung02 = db.insertBerufserfahrung(berufserfahrung02);
		berufserfahrung03 = db.insertBerufserfahrung(berufserfahrung03);

		ArrayList<BerufserfahrungData> berufserfahrungen = db
				.getAllBerufserfahrung();
		int test = berufserfahrungen.size();
		assertTrue(test == rows + 3);

		db.deleteBerufserfahrung(berufserfahrung01);
		db.deleteBerufserfahrung(berufserfahrung02);
		db.deleteBerufserfahrung(berufserfahrung03);

		db.close();
	}

	/**
	 * Erzeugt 4 Datenbank einträge mit Bildung 3 mit Schulname "FFHS"und 1
	 * eintrag mit "TEKO" Es werden alle einträge mit "FFHS" geladen und
	 * überprüft ob 3 einträge mehr vorhanden sind und ob im ganzen 4 eingefügt
	 * wurden.
	 * 
	 */
	public void test_3DB_BerufserfahrungenGetRows() {
		int rows;
		int rowsGetRows;

		BerufserfahrungDB db = new BerufserfahrungDB(getActivity());
		BerufserfahrungData berufserfahrung01 = getBerufserfahrung();
		BerufserfahrungData berufserfahrung02 = getBerufserfahrung();
		BerufserfahrungData berufserfahrung03 = getBerufserfahrung();
		BerufserfahrungData berufserfahrung04 = getBerufserfahrung();
		berufserfahrung04.setTxt_adresse("Neue Adresse");

		db.open();

		rows = db.getAllBerufserfahrung().size();
		rowsGetRows = db.getBerufserfarungRows(berufserfahrung01,
				LebenslaufDB.BERUF_ADRESSE).size();

		berufserfahrung01 = db.insertBerufserfahrung(berufserfahrung01);
		berufserfahrung02 = db.insertBerufserfahrung(berufserfahrung02);
		berufserfahrung03 = db.insertBerufserfahrung(berufserfahrung03);
		berufserfahrung04 = db.insertBerufserfahrung(berufserfahrung04);

		ArrayList<BerufserfahrungData> berufserfahrungenRows = db
				.getBerufserfarungRows(berufserfahrung01,
						LebenslaufDB.BERUF_ADRESSE);
		int testRow = berufserfahrungenRows.size();
		assertTrue(testRow == rowsGetRows + 3);

		ArrayList<BerufserfahrungData> berufserfahrungen = db
				.getAllBerufserfahrung();
		int test = berufserfahrungen.size();
		assertTrue(test == rows + 4);

		db.deleteBerufserfahrung(berufserfahrung01);
		db.deleteBerufserfahrung(berufserfahrung02);
		db.deleteBerufserfahrung(berufserfahrung03);
		db.deleteBerufserfahrung(berufserfahrung04);

		db.close();
	}

	public void test_4DB_BerufserfahrungUpdate() {
		BerufserfahrungData berufserfahrung = getBerufserfahrung();
		BerufserfahrungDB db = new BerufserfahrungDB(getActivity());
		db.open();
		berufserfahrung = db.insertBerufserfahrung(berufserfahrung);
		assertTrue(berufserfahrung.getID() > 0);
		

		BerufserfahrungData updateBerufserfahrung = berufserfahrung.clone();

		updateBerufserfahrung.setTxt_adresse("Updatet Adresse");
		updateBerufserfahrung.setTxt_beschreibung("Updatet Beschreibung");


		updateBerufserfahrung = db.updateBerufserfarung(updateBerufserfahrung);

		if (updateBerufserfahrung != null) {
			assertTrue(!(berufserfahrung.getTxt_adresse().equals(updateBerufserfahrung.getTxt_adresse())));
			assertTrue(!(berufserfahrung.getTxt_adresse().equals(updateBerufserfahrung.getTxt_beschreibung())));

			assertTrue(berufserfahrung.getID().equals(updateBerufserfahrung.getID()));
		} else {
			assertTrue(false);
		}

		db.deleteBerufserfahrung(updateBerufserfahrung);
		BerufserfahrungData berufserfahrungDelet = new BerufserfahrungData(
				updateBerufserfahrung.getID());
		berufserfahrungDelet = db.getBerufserfahrung(berufserfahrungDelet);
		if (berufserfahrungDelet != null) {
			assertTrue(true);
		}

		db.close();
	}

	private BerufserfahrungData getBerufserfahrung() {
		BerufserfahrungData berufserfahrung = new BerufserfahrungData(
				"txt_firma", "txt_titel", "txt_taetigkeit", "txt_beschreibung",
				"txt_adresse", "txt_plz", "txt_ort", "btnSelectDateVon",
				"btnSelectDateBis");
		berufserfahrung.setPersID(new Long(4624));
		return berufserfahrung;
	}
}
