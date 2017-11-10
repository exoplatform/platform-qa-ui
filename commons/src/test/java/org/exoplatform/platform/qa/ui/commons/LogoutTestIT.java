package org.exoplatform.platform.qa.ui.commons;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.pageobject.Login;
import org.exoplatform.platform.qa.ui.commons.pageobject.Platform;

/**
 * Created by ilyes on 24/10/17.
 */
public class LogoutTestIT extends Base {
  /**
   * Check that the user is not logged anymore after signout.
   * <p>
   * This method requires @Tag("smoke") to login before trying to sign out.
   * </p>
   */
  @Test
  @Tag("smoke")
  public void signOut() {
    // Init instance for signInTest
    Platform plf = new Platform();
    plf.open();
    plf.ensureLicenseIsAccepted().ensureRegisterSoftwareIsSkipped().ensureAccountSetupIsSkipped().ensureUserIsLoggedIn();

    assertTrue("User should not be logged anymore!", !new Login().signOut().isUserLogged());
  }
}
