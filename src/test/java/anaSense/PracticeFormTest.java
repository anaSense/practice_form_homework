package anaSense;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    private final String firstName = "Teresa";
    private final String lastName = "Thompson";
    private final String email = "teresa.thompson@gmail.com";
    private final String phoneNumber = "9134664355";
    private final String gender = "Female";
    private final String address = "71 Queen Street, WESTERN CENTRAL, LONDON, WC0 8ZU";
    private final String month = "September";
    private final String year = "1968";
    private final String hobby = "Reading";
    private final String day = "5";
    private final String subject = "Math";
    private final String filename = "kitty-cat.jpeg";
    private final String state = "Haryana";
    private final String city = "Panipat";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        //close banners
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //fill simple textfields
        $(byId("firstName")).setValue(firstName);
        $(byId("lastName")).setValue(lastName);
        $(byId("userEmail")).setValue(email);
        $(byId("userNumber")).setValue(phoneNumber);
        $(byId("currentAddress")).setValue(address);

        //choose radio button
        $$("#genterWrapper div.col-md-9 label.custom-control-label")
                .findBy(Condition.text(gender))
                .click();

        //choose date
        $(byId("dateOfBirthInput")).click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        if(Integer.parseInt(day) <= 9) {
            $(".react-datepicker__day--00" + day).click();
        } else {
            $(".react-datepicker__day--0" + day).click();
        }

        //fill autocomplete control
        $(byId("subjectsInput")).setValue(subject).pressEnter();

        //choose checkbox
        $$("#hobbiesWrapper div.custom-control label.custom-control-label")
                .findBy(Condition.text(hobby))
                .click();

        //choose picture
        $(byId("uploadPicture")).uploadFromClasspath(filename);

        //choose state dropdown
        $("#stateCity-wrapper #state .css-yk16xz-control").click();
        $(byId("react-select-3-input")).setValue(state).pressEnter();

        //choose city dropdown
        $("#stateCity-wrapper #city .css-yk16xz-control").click();
        $(byId("react-select-4-input")).setValue(city).pressEnter();

        $(byId("submit")).click();

        //check data
        $(".table").shouldHave(text(firstName + " " + lastName));
        $(".table").shouldHave(text(email));
        $(".table").shouldHave(text(gender));
        $(".table").shouldHave(text(phoneNumber));
        $(".table").shouldHave(text(day + " " + month + "," + year));
        $(".table").shouldHave(text(subject));
        $(".table").shouldHave(text(hobby));
        $(".table").shouldHave(text(filename));
        $(".table").shouldHave(text(address));
        $(".table").shouldHave(text(state + " " + city));
    }
}
