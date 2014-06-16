package ch.jh_bd_rb_lebenslauf_app.test;

import com.robotium.solo.Solo;


import ch.jh_bd_rb_lebenslauf_app.gui.BerufserfahrungActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.BildActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.BildungActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.FinishActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.StartActivity;
import ch.jh_bd_rb_lebenslauf_app.gui.ZusammenfassungActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ActivitysTest extends
		ActivityInstrumentationTestCase2<StartActivity> {

	private Solo mSimulator;

	@SuppressWarnings("deprecation")
	public ActivitysTest() {
		super("ch.jh_bd_rb_lebenslauf_app", StartActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		mSimulator = new Solo(getInstrumentation(), getActivity());
	}

	public void test_Activitys() throws Exception {
		assertTrue("Button // CV erfassen/ ist nicht vorhanden",
				mSimulator.searchButton("CV erfassen"));
		assertTrue("Text nicht richtig",
				mSimulator.searchText("CV schnell und einfach erfassen"));

		mSimulator.clickOnButton("CV erfassen");
		mSimulator.assertCurrentActivity("Activity Bild.class erwartet",
				BildActivity.class);
		assertTrue("Button // Berufserfahrung/ ist nicht vorhanden",
				mSimulator.searchButton("Berufserfahrung"));
		
		mSimulator.clickOnButton("Berufserfahrung");
		mSimulator.assertCurrentActivity(
				"Activity Berufserfahrung.class erwartet",
				BerufserfahrungActivity.class);
		assertTrue("Button // Bild/ ist nicht vorhanden",
				mSimulator.searchButton("Bild"));
		assertTrue("Button // Bildung/ ist nicht vorhanden",
				mSimulator.searchButton("Bildung"));

		mSimulator.clickOnButton("Bildung");
		mSimulator.assertCurrentActivity("Activity BildungActivity.class erwartet",
				BildungActivity.class);
		assertTrue("Button // Berufserfahrung/ ist nicht vorhanden",
				mSimulator.searchButton("Berufserfahrung"));
		assertTrue("Button // Skills/ ist nicht vorhanden",
				mSimulator.searchButton("Skills"));
		
		mSimulator.clickOnButton("Skills");
		mSimulator.assertCurrentActivity("Activity Skills.class erwartet",
				SkillsActivity.class);
		assertTrue("Button // Bildung/ ist nicht vorhanden",
				mSimulator.searchButton("Bildung"));
		assertTrue("Button // Zusammenfassung/ ist nicht vorhanden",
				mSimulator.searchButton("Zusammenfassung"));
		
		mSimulator.clickOnButton("Zusammenfassung");
		mSimulator.assertCurrentActivity("Activity Zusammenfassung.class erwartet",
				ZusammenfassungActivity.class);
		assertTrue("Button // Skills/ ist nicht vorhanden",
				mSimulator.searchButton("Skills"));
		assertTrue("Button // Finish/ ist nicht vorhanden",
				mSimulator.searchButton("Finish"));
		
		mSimulator.clickOnButton("Finish");
		mSimulator.assertCurrentActivity("Activity Finish.class erwartet",
				FinishActivity.class);
		assertTrue("Button // Zusammenfassung/ ist nicht vorhanden",
				mSimulator.searchButton("Zusammenfassung"));
		
		//Zurück Navigieren
		mSimulator.clickOnButton("Zusammenfassung");
		mSimulator.assertCurrentActivity("Activity Zusammenfassung.class erwartet",
				ZusammenfassungActivity.class);
		assertTrue("Button // Skills/ ist nicht vorhanden",
				mSimulator.searchButton("Skills"));
		assertTrue("Button // Finish/ ist nicht vorhanden",
				mSimulator.searchButton("Finish"));
		
		mSimulator.clickOnButton("Skills");
		mSimulator.assertCurrentActivity("Activity Skills.class erwartet",
				SkillsActivity.class);
		assertTrue("Button // Bildung/ ist nicht vorhanden",
				mSimulator.searchButton("Bildung"));
		assertTrue("Button // Zusammenfassung/ ist nicht vorhanden",
				mSimulator.searchButton("Zusammenfassung"));
		
		mSimulator.clickOnButton("Bildung");
		mSimulator.assertCurrentActivity("Activity BildungActivity.class erwartet",
				BildungActivity.class);
		assertTrue("Button // Berufserfahrung/ ist nicht vorhanden",
				mSimulator.searchButton("Berufserfahrung"));
		assertTrue("Button // Skills/ ist nicht vorhanden",
				mSimulator.searchButton("Skills"));
		
		mSimulator.clickOnButton("Berufserfahrung");
		mSimulator.assertCurrentActivity(
				"Activity Berufserfahrung.class erwartet",
				BerufserfahrungActivity.class);
		assertTrue("Button // Bild/ ist nicht vorhanden",
				mSimulator.searchButton("Bild"));
		assertTrue("Button // Bildung/ ist nicht vorhanden",
				mSimulator.searchButton("Bildung"));
		
		mSimulator.clickOnButton("Bild");
		mSimulator.assertCurrentActivity("Activity Bild.class erwartet",
				BildActivity.class);
		assertTrue("Button // Berufserfahrung/ ist nicht vorhanden",
				mSimulator.searchButton("Berufserfahrung"));
	}
}
