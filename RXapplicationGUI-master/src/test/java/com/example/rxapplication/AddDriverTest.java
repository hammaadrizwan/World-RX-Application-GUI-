package com.example.rxapplication;

import com.example.rxapplication.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddDriverTest {
    @Test
    public void emptyFirstnameShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("","Hansen",31,"Hansen World RX Team","Peugeot 208 RX1e",90));
    }
    @Test
    public void emptyLastnameShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","",31,"Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void emptyNameShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("","",31,"Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void emptyAgeShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen","","Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void emptyTeamShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen",31,"","Peugeot 208 RX1e",100));
    }
    @Test
    public void emptyCarShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen",31,"Hansen World RX Team","",100));
    }
    @Test
    public void emptyPointsShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen",31,"Hansen World RX Team","Peugeot 208 RX1e",""));
    }

    @Test
    public void firstnameWithASingleNumberShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Tim4my","Hansen",31,"Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void lastnameWithASingleNumberShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","H9ansen",31,"Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void nameWithOnlyNumbersShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("3432","2421",31,"Hansen World RX Team","Peugeot 208 RX1e",100));
    }

    @Test
    public void incorrectDataTypeForAgeShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen","thirty one","Hansen World RX Team","Peugeot 208 RX1e",100));
    }
    @Test
    public void incorrectDataTypeForPointsShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Timmy","Hansen",31,"Hansen World RX Team","Peugeot 208 RX1e","Hundred"));
    }

    @Test
    public void moreThanOneFirstNameInTheFirstnameFieldShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Ole Christian","Veiby",27,"Volkswagen Dealerteam BAUHAUS","Volkswagen Dealerteam BAUHAUS",100));
    }
    @Test
    public void moreThanOneNameInTheLastnameFieldShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateAddDriverDetails("Ole","Christian Veiby",27,"Volkswagen Dealerteam BAUHAUS","Volkswagen Dealerteam BAUHAUS",100));
    }

}