import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

/**
 * Created by ilyess on 26/06/2017.
 */
public class RoomBaseAction extends Base {
    HomePagePlatform homePagePlatform;
    RoomManagement roomManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
    homePagePlatform=new HomePagePlatform(this);
    roomManagement=new RoomManagement(this);
    }
    @Test
    public void Test01_AddRoomWithSeveralUsers(){


    }
}
