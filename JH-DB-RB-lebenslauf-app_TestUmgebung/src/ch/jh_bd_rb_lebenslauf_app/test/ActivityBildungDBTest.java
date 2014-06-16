package ch.jh_bd_rb_lebenslauf_app.test;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.daten.BildungData;
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
		BildungData bildung = getBildung();
		BildungDB db = new BildungDB(getActivity());
		db.open();
		bildung = db.insertBildung(bildung);
		assertTrue(bildung.getId() > 0);

		BildungData returnBildung = new BildungData(bildung.getId());
		returnBildung = db.getBildung(returnBildung);
		if (returnBildung != null) {
			assertTrue(bildung.getNameschule().equals(
					returnBildung.getNameschule()));
			assertTrue(bildung.getPersID().equals(returnBildung.getPersID()));
		} else {
			assertTrue(false);
		}

		db.deleteBildung(returnBildung);
		BildungData bildungDelet = new BildungData(returnBildung.getId());
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
		BildungData bildung01;
		BildungData bildung02;
		BildungData bildung03;

		db.open();

		rows = db.getAllBildungen().size();

		bildung01 = db.insertBildung(getBildung());
		bildung02 = db.insertBildung(getBildung());
		bildung03 = db.insertBildung(getBildung());

		ArrayList<BildungData> bildungen = db.getAllBildungen();
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
		BildungData bildung01 = getBildung();
		BildungData bildung02 = getBildung();
		BildungData bildung03 = getBildung();
		BildungData bildung04 = getBildung();
		bildung04.setNameschule("TEKO");

		db.open();

		rowsAll = db.getAllBildungen().size();
		rowsGetRows = db.getBildungRows(bildung01,
				LebenslaufDB.BILDUNG_SCHULNAME).size();

		bildung01 = db.insertBildung(bildung01);
		bildung02 = db.insertBildung(bildung02);
		bildung03 = db.insertBildung(bildung03);
		bildung04 = db.insertBildung(bildung04);

		ArrayList<BildungData> bildungen = db.getBildungRows(bildung01,
				LebenslaufDB.BILDUNG_SCHULNAME);
		int test = bildungen.size();
		assertTrue(test == rowsGetRows + 3);

		ArrayList<BildungData> allBildungen = db.getAllBildungen();
		int testAll = allBildungen.size();
		assertTrue(testAll == rowsAll + 4);

		db.deleteBildung(bildung01);
		db.deleteBildung(bildung02);
		db.deleteBildung(bildung03);
		db.deleteBildung(bildung04);

		db.close();
	}

	public void test_4DB_BildungUpdate() {
		BildungData bildung = getBildung();
		BildungDB db = new BildungDB(getActivity());
		db.open();
		bildung = db.insertBildung(bildung);
		assertTrue(bildung.getId() > 0);

		BildungData updateBildung = bildung.clone();
		
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
		BildungData bildungDelet = new BildungData(updateBildung.getId());
		bildungDelet = db.getBildung(bildungDelet);
		if (bildungDelet != null) {
			assertTrue(true);
		}

		db.close();
	}

	private BildungData getBildung() {
		BildungData bildung = new BildungData("bildungsart", "FFHS", "4624", "ort",
				"von", "bis");
		bildung.setPersID((long) 4624);
		return bildung;
	}

}
