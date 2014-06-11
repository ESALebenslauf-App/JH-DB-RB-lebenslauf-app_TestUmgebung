package ch.jh_bd_rb_lebenslauf_app.test;

import ch.jh_bd_rb_lebenslauf_app.daten.Bildung;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.gui.BildungActivity;
import android.database.Cursor;
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
	
	public void test_1DB_BildungInsertLoudID(){
		Bildung bildung = getBildung();
		BildungDB db = new BildungDB(getActivity());
		db.open();
		bildung = db.insertBildung(bildung);
		assertTrue(bildung.getId() > 0);
		
		Bildung returnBildung = new Bildung(bildung.getId());
		returnBildung = db.getBildung(returnBildung);
		if (returnBildung != null) {
			assertTrue(bildung.getNameschule().equals(returnBildung.getNameschule()));
		}
		else {
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
	
	private Bildung getBildung() {
		Bildung bildung = new Bildung("bildungsart", "schulname", "4624", "ort", "von", "bis");
		return bildung;
	}



	//TODO Test überarbeiten und Liste von Bildungs Objekten zurücknhemen
	public void test_2DB_BildungAllCursor(){
		BildungDB db = new BildungDB(getActivity());
		db.open();
		Cursor cursor = db.getAllCursor();
		int test = cursor.getCount();
		assertTrue(test > 0);

		db.close();
	}
	

}
