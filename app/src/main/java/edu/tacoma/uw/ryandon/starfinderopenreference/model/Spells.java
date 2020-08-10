package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spells implements Serializable {

    public static final String SPELL_NAME ="name";
    public static final String CLASS_NAME = "class";
    public static final String LEVEL = "level";
    public static final String SCHOOL = "school";
    public static final String RANGE = "range";
    public static final String CAST_TIME = "cast time";

    private String spellID;
    private String spellName;
    private String className;
    private String spellLevel;
    private String spellSchool;
    private String spellRange;
    private String spellCastTime;

    public String getSpellID() {
        return spellID;
    }

    public void setSpellID(String spellID) {
        this.spellID = spellID;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(String spellLevel) {
        this.spellLevel = spellLevel;
    }

    public String getSpellSchool() { return spellSchool; }

    public void setSpellSchool(String spellSchool) { this.spellSchool = spellSchool; }

    public String getSpellRange() {
        return spellRange;
    }

    public void setSpellRange(String spellRange) {
        this.spellRange = spellRange;
    }

    public String getSpellCastTime() {
        return spellCastTime;
    }

    public void setSpellCastTime(String spellCastTime) {
        this.spellCastTime = spellCastTime;
    }

    public Spells(String theSpell, String theClass, String theLevel, String theSchool, String theRange, String theCastTime) {

        spellName = theSpell;
        className = theClass;
        spellLevel = theLevel;
        spellRange = theSchool;
        spellLevel = theRange;
        spellCastTime = theCastTime;

    }

    public static List<Spells> parseCourseJson(String spellsJson) throws JSONException {
        List<Spells> spellsList = new ArrayList<>();

        if(spellsJson != null){
            JSONArray arr = new JSONArray(spellsJson);


            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Spells spell = new Spells(
                        obj.getString(Spells.SPELL_NAME),
                        obj.getString(Spells.CLASS_NAME),
                        obj.getString(Spells.LEVEL),
                        obj.getString(Spells.SCHOOL),
                        obj.getString(Spells.RANGE),
                        obj.getString(Spells.CAST_TIME));
                spellsList.add(spell);
            }
        }

        return spellsList;
    }
}
