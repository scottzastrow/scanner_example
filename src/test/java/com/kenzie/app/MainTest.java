package com.kenzie.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;

    private final String multipleAttendeesText = "Howard\rTom\rBill\r\r";;
    private final String twoAttendeesText = "Howard\rTom\r\r\r";;
    private final String oneAttendeeText = "Howard\r\r";;


    // Set up streams to test console input and output
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @BeforeEach
    public void setTestInput() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public String runMainAndGetOutput(String inputText) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputText.getBytes());
        System.setIn(in);
        Main.main(new String[]{});
        String output = outContent.toString();
        return output;
    }

    @Test
    public void containsYouHaveInvited() {
        String output = runMainAndGetOutput(multipleAttendeesText);

        assertEquals(true, output.indexOf("You have invited: ") > -1,"Reason: The output does not contain `You have invited`. Check all spacing and capitalization.");
    }

    @Test
    public void addMultipleAttendees() {
        String output = runMainAndGetOutput(multipleAttendeesText);
        assertEquals(true, output.indexOf("You have invited: ") > -1,"Reason: The output does not contain `You have invited`. Check all spacing and capitalization.");

        String attendeesOutput = output.substring(output.indexOf("You have invited: ") + 18);

        assertEquals(true, attendeesOutput.indexOf("Howard, Tom, and Bill") ==0, "Reason: Expected \"Howard, Tom, and Bill\". " +
                "Received: \"" + attendeesOutput + "\".  Output must contain the entered tests in order. Check spacing and punctuation.");
    }

    @Test
    public void addTwoAttendees() {
        String output = runMainAndGetOutput(twoAttendeesText);
        assertEquals(true, output.indexOf("You have invited: ") > -1,"Reason: The output does not contain `You have invited`. Check all spacing and capitalization.");

        String attendeesOutput = output.substring(output.indexOf("You have invited: ") + 18);

        assertEquals(true, attendeesOutput.indexOf("Howard and Tom") ==0,
                "Reason: Expected \"Howard and Tom\". Received: \"" + attendeesOutput + "\". Check spacing and punctuation");
    }

    @Test
    public void addOneAttendee() {
        String output = runMainAndGetOutput(oneAttendeeText);
        assertEquals(true, output.indexOf("You have invited: ") > -1,"Reason: The output does not contain `You have invited`. Check all spacing and capitalization.");
        String attendeesOutput = output.substring(output.indexOf("You have invited: ") + 18);

        assertEquals(true, attendeesOutput.indexOf("Howard")==0,
                "Reason: Expected \"Howard\". Received: \"" + attendeesOutput + "\". Check spacing and punctuation");

    }
}

