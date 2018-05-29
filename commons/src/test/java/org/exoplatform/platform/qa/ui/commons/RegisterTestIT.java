package org.exoplatform.platform.qa.ui.commons;

import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;
import org.exoplatform.platform.qa.ui.commons.pageobject.Register;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Tag("atisRegister")
public class RegisterTestIT extends Base {

  public RegisterTestIT() {
    super();
  }

  @Test
  public void register() {
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped();
    Register register = new Register();
    assertFalse(register.isButtonEnabled());
    String email = System.getProperty("atis.email");
    String firstName = System.getProperty("atis.firstname");
    String lastName = System.getProperty("atis.lastname");
    String lang = System.getProperty("atis.language");
    String password = System.getProperty("atis.password");
    String tvaNum = System.getProperty("atis.tvaNum");
    assertTrue(new Register().newRegister(firstName, lastName, password, lang, email, tvaNum).isUserRegistered());
  }
}
