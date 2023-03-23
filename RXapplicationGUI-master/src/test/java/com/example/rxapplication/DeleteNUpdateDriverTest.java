package com.example.rxapplication;

import com.example.rxapplication.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteNUpdateDriverTest {
    @Test
    public void onlyFirstnameSearchedShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateDeletingAndUpdatingDriverDetails("Timmy"));
    }
    @Test
    public void emptyFieldSearchedShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateDeletingAndUpdatingDriverDetails(""));
    }
    @Test
    public void moreThan2NamesSearchedShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateDeletingAndUpdatingDriverDetails("Ole Christian Veiby"));
    }
    @Test
    public void incorrectDataTypeEnteredForNameShouldBeFalse(){
        var controller = new Controller();
        assertEquals(false,controller.validateDeletingAndUpdatingDriverDetails("Timmy6 341"));
    }
}