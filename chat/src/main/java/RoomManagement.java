import org.exoplatform.platform.qa.ui.selenium.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;

/**
 * Created by ilyess on 26/06/2017.
 */
public class RoomManagement {
    private final TestBase testBase;
    public RoomManagement(TestBase testBase) {
        this.testBase = testBase;}

    public void addRoom(){
        $(byText("Rooms")).parent().find(byClassName("actionIcon")).click();
    }




}
