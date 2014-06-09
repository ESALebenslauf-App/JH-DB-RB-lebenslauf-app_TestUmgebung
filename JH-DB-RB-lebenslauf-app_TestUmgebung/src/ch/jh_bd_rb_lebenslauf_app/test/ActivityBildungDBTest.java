package ch.jh_bd_rb_lebenslauf_app.test;

import java.text.SimpleDateFormat;

import com.robotium.solo.Solo;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.daten.BildungDB;
import ch.jh_bd_rb_lebenslauf_app.gui.BildungActivity;
import android.content.Context;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class ActivityBildungDBTest extends
		ActivityInstrumentationTestCase2<BildungActivity> {

	private Solo mSimulator;
	private EditText edt_bildung_schule;
	private EditText edt_bildung_adresse;
	long id = 0;

	@SuppressWarnings("deprecation")
	public ActivityBildungDBTest() {
		super("ch.jh_bd_rb_lebenslauf_app", BildungActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		mSimulator = new Solo(getInstrumentation(), getActivity());
		edt_bildung_schule = (EditText) getActivity().findViewById(
				R.id.edt_bildung_schule);
		edt_bildung_adresse = (EditText) getActivity().findViewById(
				R.id.edt_bildung_adresse);
	}
	
	public void test_1DB_BildungInsert(){
		BildungDB db = new BildungDB(getActivity());
		db.open();
		id = db.insertBildung("anrede", "bildungsart", "schulname", 4624, "ort", "von", "bis");
		assertTrue(id > 0);
		db.close();
	}
	
	public void test_2DB_BildungAllCursor(){
		BildungDB db = new BildungDB(getActivity());
		db.open();
		Cursor cursor = db.getAllCursor();
		int test = cursor.getCount();
		assertTrue(test > 0);

		db.close();
	}
	
	public void test_3DB_BildungLoudID(){
		int test = 0;
		BildungDB db = new BildungDB(getActivity());
		db.open();
		Cursor cursor = db.getBildung("2");
		if (cursor != null) {
			test = cursor.getCount();
		}
		assertTrue(test == 1);
		db.close();
	}

	
	

}
