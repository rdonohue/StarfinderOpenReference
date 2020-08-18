package edu.tacoma.uw.ryandon.starfinderopenreference.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spell implements Serializable {

    public static final String SPELL_NAME ="name";
    public static final String CLASS_NAME = "classname";
    public static final String LEVEL = "level";
    public static final String SCHOOL = "school";
    public static final String RANGE = "range";
    public static final String CAST_TIME = "casttime";
    public static final String DESC = "shortdesc";

    private String spellID;
    private String spellName;
    private String className;
    private String spellLevel;
    private String spellSchool;
    private String spellRange;
    private String spellCastTime;
    private String spellDescription;

    public String getSpellDescription() { return spellDescription; }

    public String getSpellID() {
        return spellID;
    }

    public String getSpellName() {
        return spellName;
    }

    public String getClassName() {
        return className;
    }

    public String getSpellLevel() {
        return spellLevel;
    }

    public String getSpellSchool() { return spellSchool; }

    public String getSpellRange() {
        return spellRange;
    }

    public String getSpellCastTime() {
        return spellCastTime;
    }

    public Spell(String theName, String theClass, String theLevel, String theSchool, String theRange, String theCastTime, String theDescription) {

        spellName = theName;
        className = theClass;
        spellLevel = theLevel;
        spellSchool = theSchool;
        spellRange = theRange;
        spellCastTime = theCastTime;
        spellDescription = theDescription;
    }

    public static List<Spell> parseSpellsJson(String spellsJson) throws JSONException {
        List<Spell> spellList = new ArrayList<>();

        if(spellsJson != null){
            JSONArray arr = new JSONArray(spellsJson);


            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                Spell spell = new Spell(
                        obj.getString(Spell.SPELL_NAME),
                        obj.getString(Spell.CLASS_NAME),
                        obj.getString(Spell.LEVEL),
                        obj.getString(Spell.SCHOOL),
                        obj.getString(Spell.RANGE),
                        obj.getString(Spell.CAST_TIME),
                        obj.getString(Spell.DESC));
                spellList.add(spell);
            }
        }

        return spellList;
    }
}
