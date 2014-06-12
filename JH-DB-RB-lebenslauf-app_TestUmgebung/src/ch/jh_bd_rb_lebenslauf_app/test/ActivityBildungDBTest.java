package ch.jh_bd_rb_lebenslauf_app.test;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.daten.Bildung;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.gui.BildungActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ActivityBildungDBTest extends
		ActivityInstrumentationTestCase2<BildungActivity> {

	@SuppressWarnings("deprecation")
	public ActivityBildungDBTest() {
		super("ch.jh_bd_rb_lebenslauf_app", BildungActivity.class);
	}

	@Override
	protected void setUp() throws Exception {

	}

	/**
	 * Fuegt einen Bildung datensatz in die DB und liest diesen wider aus.
	 */
	public void test_1DB_BildungInsertLoudID() {
		Bildung bildung = getBildung();
		BildungDB db = new BildungDB(getActivity());
		db.open();
		bildung = db.insertBildung(bildung);
		assertTrue(bildung.getId() > 0);

		Bildung returnBildung = new Bildung(bildung.getId());
		returnBildung = db.getBildung(returnBildung);
		if (returnBildung != null) {
			assertTrue(bildung.getNameschule().equals(
					returnBildung.getNameschule()));
			assertTrue(bildung.getPersID().equals(returnBildung.getPersID()));
		} else {
			assertTrue(false);
		}

		db.deleteBildung(returnBildung);
		Bildung bildungDelet = new Bildung(returnBildung.getId());
		bildungDelet = db.getBildung(bildungDelet);
		if (bildungDelet != null) {
			assertTrue(true);
		}

		db.close();
	}

	/**
	 * Erzeugt 3 Datenbank eintraege mit Bildung und ueberprueft ob
	 * getAllBildungen mehr als 2 Eintraege zurueck giebt.
	 * 
	 */
	public void test_2DB_BildungAllBildungen() {
		int rows;
		BildungDB db = new BildungDB(getActivity());
		Bildung bildung01;
		Bildung bildung02;
		Bildung bildung03;

		db.open();

		rows = db.getAllBildungen().size();

		bildung01 = db.insertBildung(getBildung());
		bildung02 = db.insertBildung(getBildung());
		bildung03 = db.insertBildung(getBildung());

		ArrayList<Bildung> bildungen = db.getAllBildungen();
		int test = bildungen.size();
		assertTrue(test == rows + 3);

		db.deleteBildung(bildung01);
		db.deleteBildung(bildung02);
		db.deleteBildung(bildung03);

		db.close();
	}

	/**
	 * Erzeugt 4 Datenbank einträge mit Bildung 3 mit Schulname "FFHS"und 1
	 * eintrag mit "TEKO" Es werden alle einträge mit "FFHS" geladen und
	 * überprüft ob 3 einträge mehr vorhanden sind und ob im ganzen 4 eingefügt
	 * wurden.
	 * 
	 */
	public void test_3DB_BildungGetRows() {
		int rowsAll;
		int rowsGetRows;
		BildungDB db = new BildungDB(getActivity());
		Bildung bildung01 = getBildung();
		Bildung bildung02 = getBildung();
		Bildung bildung03 = getBildung();
		Bildung bildung04 = getBildung();
		bildung04.setNameschule("TEKO");

		db.open();

		rowsAll = db.getAllBildungen().size();
		rowsGetRows = db.getBildungRows(bildung01,
				LebenslaufDB.BILDUNG_SCHULNAME).size();

		bildung01 = db.insertBildung(bildung01);
		bildung02 = db.insertBildung(bildung02);
		bildung03 = db.insertBildung(bildung03);
		bildung04 = db.insertBildung(bildung04);

		ArrayList<Bildung> bildungen = db.getBildungRows(bildung01,
				LebenslaufDB.BILDUNG_SCHULNAME);
		int test = bildungen.size();
		assertTrue(test == rowsGetRows + 3);

		ArrayList<Bildung> allBildungen = db.getAllBildungen();
		int testAll = allBildungen.size();
		assertTrue(testAll == rowsAll + 4);

		db.deleteBildung(bildung01);
		db.deleteBildung(bildung02);
		db.deleteBildung(bildung03);
		db.deleteBildung(bildung04);

		db.close();
	}

	public void test_4DB_BildungUpdate() {
		Bildung bildung = getBildung();
		BildungDB db = new BildungDB(getActivity());
		db.open();
		bildung = db.insertBildung(bildung);
		assertTrue(bildung.getId() > 0);

		Bildung updateBildung = bildung.clone();
		
		updateBildung.setNameschule("Test Update Name");
		updateBildung.setPersID(new Long(4923));
		
		updateBildung = db.updateBildung(updateBildung);
		if (updateBildung != null) {
			assertTrue(!(bildung.getNameschule().equals(updateBildung
					.getNameschule())));
			assertTrue(!(bildung.getPersID().equals(updateBildung.getPersID())));

			assertTrue(bildung.getAdresseSchule().equals(updateBildung.getAdresseSchule()));
		} else {
			assertTrue(false);
		}

		db.deleteBildung(updateBildung);
		Bildung bildungDelet = new Bildung(updateBildung.getId());
		bildungDelet = db.getBildung(bildungDelet);
		if (bildungDelet != null) {
			assertTrue(true);
		}

		db.close();
	}

	private Bildung getBildung() {
		Bildung bildung = new Bildung("bildungsart", "FFHS", "4624", "ort",
				"von", "bis");
		bildung.setPersID((long) 4624);
		return bildung;
	}

}
