package ch.jh_bd_rb_lebenslauf_app.test;

import com.robotium.solo.Solo;

import ch.jh_bd_rb_lebenslauf_app.R;
import ch.jh_bd_rb_lebenslauf_app.gui.BildungActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class ActivityBildungTest extends
		ActivityInstrumentationTestCase2<BildungActivity> {

	private Solo mSimulator;
	private EditText edt_bildung_schule;
	private EditText edt_bildung_adresse;
	long id = 0;

	@SuppressWarnings("deprecation")
	public ActivityBildungTest() {
		super("ch.jh_bd_rb_lebenslauf_app", BildungActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		mSimulator = new Solo(getInstrumentation(), getActivity());
		edt_bildung_schule = (EditText) getActivity().findViewById(
				R.id.edt_bildung_schule);
		edt_bildung_adresse = (EditText) getActivity().findViewById(
				R.id.edt_bildung_ort);
	}

	public void test_1Bildung() {
		mSimulator.assertCurrentActivity(
				"Activity BildungActivity.class erwartet",
				BildungActivity.class);
		assertTrue("Button // Berufserfahrung/ ist nicht vorhanden",
				mSimulator.searchButton("Berufserfahrung"));
		assertTrue("Button // Skills/ ist nicht vorhanden",
				mSimulator.searchButton("Skills"));
		assertTrue("Button // Bildung hinzufügen/ ist nicht vorhanden",
				mSimulator.searchButton("Bildung hinzufügen"));
		
		mSimulator.enterText(edt_bildung_adresse, "TEST Adresse Text");
		mSimulator.enterText(edt_bildung_schule, "TEST Schule Text");
		mSimulator.clickOnButton("Bildung hinzufügen");
		mSimulator.enterText(edt_bildung_adresse, "TEST Adresse2 Text");
		mSimulator.enterText(edt_bildung_schule, "TEST Schule2 Text");
		mSimulator.clickOnButton("Bildung hinzufügen");
		mSimulator.clickOnButton("Skills");
	}
	
	

}
