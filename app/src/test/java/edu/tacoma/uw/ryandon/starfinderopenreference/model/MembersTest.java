package edu.tacoma.uw.ryandon.starfinderopenreference.model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MembersTest {

    @Test
    public void testMembersFirstNameMatch() {
        String tempString = " I am a temporary string@";


       Members member =  new Members(tempString, tempString, tempString, tempString, tempString);
        assertEquals(tempString, member.getmFirstName());


    }



    @Test
    public void testMembersLastNameMatch() {
        String tempString = " I am a temporary string@";


        Members member =  new Members(tempString, tempString, tempString, tempString, tempString);
        assertEquals(tempString, member.getmLastName());


    }

    @Test
    public void testMembersEmailMatch() {
        String tempString = " I am a temporary string@";


        Members member =  new Members(tempString, tempString, tempString, tempString, tempString);
        assertEquals(tempString, member.getmEmail());


    }

    @Test
    public void testMembersPasswordMatch() {
        String tempString = " I am a temporary string@";


        Members member =  new Members(tempString, tempString, tempString, tempString, tempString);
        assertEquals(tempString, member.getmPassword());


    }

    @Test
    public void testMembersUserNameMatch() {
        String tempString = " I am a temporary string@";


        Members member =  new Members(tempString, tempString, tempString, tempString, tempString);
        assertEquals(tempString, member.getmUsername());


    }

    @Test
    public void testMembersEmailNull() {
        String tempString = " I am a temporary string@";

    try {
        Members member =  new Members(tempString, tempString, null, tempString, tempString);

    } catch(NullPointerException e){

        }



    }
    @Test
    public void testMembersPasswordNull() {
        String tempString = " I am a temporary string@";

        try {
            Members member =  new Members(tempString, tempString, tempString, tempString, null);

        } catch(NullPointerException e){

        }



    }
    @Test
    public void testMembersEmailLT6() {
        String tempString = " I am a temporary string@";

        try {
            Members member =  new Members(tempString, tempString, "asd@1", tempString, tempString);

        } catch(IllegalArgumentException e){

        }



    }

    @Test
    public void testMembersPassWordLT6() {
        String tempString = " I am a temporary string@";

        try {
            Members member =  new Members(tempString, tempString, tempString, tempString, "12345");

        } catch(IllegalArgumentException e){

        }



    }

    @Test
    public void testMembersEmailContains() {
        String tempString = " I am a temporary string@";

        try {
            Members member =  new Members(tempString, tempString, "asd451", tempString, tempString);

        } catch(IllegalArgumentException e){

        }



    }




}
