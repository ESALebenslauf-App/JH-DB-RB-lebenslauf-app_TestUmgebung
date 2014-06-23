package ch.jh_bd_rb_lebenslauf_app.test;

import java.util.ArrayList;

import ch.jh_bd_rb_lebenslauf_app.daten.LebenslaufDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsDB;
import ch.jh_bd_rb_lebenslauf_app.daten.SkillsData;
import ch.jh_bd_rb_lebenslauf_app.gui.SkillsActivity;
import android.test.ActivityInstrumentationTestCase2;

public class ActivitySkillsDBTest extends
		ActivityInstrumentationTestCase2<SkillsActivity> {

	@SuppressWarnings("deprecation")
	public ActivitySkillsDBTest() {
		super("ch.jh_bd_rb_lebenslauf_app", SkillsActivity.class);
	}

	@Override
	protected void setUp() throws Exception {

	}

	/**
	 * Fuegt einen Bildung datensatz in die DB und liest diesen wider aus.
	 */
	public void test_1DB_SkillsInsertLoudID() {
		SkillsData sklill = getSkills();
		SkillsDB db = new SkillsDB(getActivity());
		db.open();
		sklill = db.insertSkills(sklill);
		assertTrue(sklill.getID() > 0);

		SkillsData returnSkillsData = new SkillsData(sklill.getID());
		returnSkillsData = db.getSkill(returnSkillsData);
		if (returnSkillsData != null) {
			assertTrue(sklill.getWas().equals(returnSkillsData.getWas()));
		} else {
			assertTrue(false);
		}

		db.deleteSkills(returnSkillsData);
		SkillsData deleatSkills = new SkillsData(returnSkillsData.getID());
		deleatSkills = db.getSkill(deleatSkills);
		if (deleatSkills != null) {
			assertTrue(true);
		}

		db.close();
	}

	/**
	 * Erzeugt 3 Datenbank eintraege mit Bildung und ueberprueft ob
	 * getAllBildungen mehr als 2 Eintraege zurueck giebt.
	 * 
	 */
	public void test_2DB_SkillsAllBildungen() {
		int rows;
		SkillsDB db = new SkillsDB(getActivity());
		SkillsData skill01;
		SkillsData skill02;
		SkillsData skill03;

		db.open();

		rows = db.getAllSkills().size();

		skill01 = db.insertSkills(getSkills());
		skill02 = db.insertSkills(getSkills());
		skill03 = db.insertSkills(getSkills());

		ArrayList<SkillsData> skills = db.getAllSkills();
		int test = skills.size();
		assertTrue(test == rows + 3);

		db.deleteSkills(skill01);
		db.deleteSkills(skill02);
		db.deleteSkills(skill03);

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

		int rows;
		int rowsGetRows;
		SkillsDB db = new SkillsDB(getActivity());
		SkillsData skill01 = getSkills();
		SkillsData skill02 = getSkills();
		SkillsData skill03 = getSkills();
		SkillsData skill04 = getSkills();
		skill04.setPers_id(new Long(999));

		db.open();

		rows = db.getAllSkills().size();
		rowsGetRows = db.getSkillsRows(skill01, LebenslaufDB.SKILLS_PERS_ID).size();

		skill01 = db.insertSkills(skill01);
		skill02 = db.insertSkills(skill02);
		skill03 = db.insertSkills(skill03);
		skill04 = db.insertSkills(skill04);

		ArrayList<SkillsData> skills = db.getAllSkills();
		int test = skills.size();
		assertTrue(test == rows + 4);
		
		ArrayList<SkillsData> skillsRow = db.getSkillsRows(skill01, LebenslaufDB.SKILLS_PERS_ID);
		int testRow = skillsRow.size();
		assertTrue(testRow == rowsGetRows + 3);


		db.deleteSkills(skill01);
		db.deleteSkills(skill02);
		db.deleteSkills(skill03);
		db.deleteSkills(skill04);

		db.close();
	}

	public void test_4DB_SkillsUpdate() {
		
		SkillsData sklill = getSkills();
		SkillsDB db = new SkillsDB(getActivity());
		db.open();
		sklill = db.insertSkills(sklill);
		assertTrue(sklill.getID() > 0);
		
		SkillsData updatSkill = sklill.clone();
		updatSkill.setWas("Hallo Welt");;
		
		db.updateSkills(updatSkill);

		updatSkill = db.getSkill(updatSkill);
		if (updatSkill != null) {
			assertTrue(!(sklill.getWas().equals(updatSkill.getWas())));
		} else {
			assertTrue(false);
		}

		db.deleteSkills(updatSkill);
		SkillsData deleatSkills = new SkillsData(updatSkill.getID());
		deleatSkills = db.getSkill(deleatSkills);
		if (deleatSkills != null) {
			assertTrue(true);
		}

		db.close();
	}

	private SkillsData getSkills() {
		String zert = "TestZertifikat";
		SkillsData skill = new SkillsData("was", "ausmass", zert);
		skill.setPers_id(new Long(4624));
		skill.setZertifikat(zert);
		return skill;
	}
}
