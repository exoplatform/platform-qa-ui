package org.exoplatform.platform.qa.ui.social.smoke;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.Utils;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.SpaceManagement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;


public class SOC_Space_MemberManagement_Invite extends Base{
    HomePagePlatform homePagePlatform;
    NavigationToolbar navigationToolbar;
    UserAddManagement userAddManagement;
    UserAndGroupManagement userAndGroupManagement;
    ManageLogInOut manageLogInOut;
    SpaceManagement spaceManagement;
/**
*<li> Case ID:122310.</li>
*<li> Test Case Name: Add user into Space group.</li>
*<li> Pre-Condition: </li>
*<li> Post-Condition: </li>
*/
    @Test
    public  void test01_AddUserIntoSpaceGroup() {
        info("Test 1: Add user into Space group");
        String username1 = "username1" + getRandomNumber();
        String email1 = username1+"@gmail.com";
        String username2 = "username2" + getRandomNumber();
        String email2 = username2+"@gmail.com";
        String password="123456";
        info("Add new user");
        navigationToolbar.goToAddUser();
        userAddManagement.addUser(username1, password, email1, username1, username1);
        userAddManagement.addUser(username2, password, email2, username2, username2);
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.addUserAdmin(username1);
        manageLogInOut.signIn(username1,password);
        Utils.pause(3000);

        /*Step Number: 1
        *Step Name: Step 1: Create a new Space
        *Step Description:
            - Login and go to intranet home
            - Click "Join a new space" button
            - Input all required fields with valid data and click Save
        *Input Data:

        *Expected Outcome:
            - Add New Space form is appeared
            - Create new Space successfully.*/
        String space = "space"+getRandomNumber();

        info("Create a space");
        homePagePlatform.goToAllSpace();
        spaceManagement.addNewSpaceSimple(space,space);

        /*Step number: 2
        *Step Name: Step 2: Add user into space group
        *Step Description:
            - Login as admin
            - Go to Groups and Roles
            - Click Group Management tab
            - Select space group created at step 1
            - Select a user and select membership is member or manager
            - Click on Save button
        *Input Data:

        *Expected Outcome:
            - Group management form is shown
            - User becomes member or manage of space create at step 1*/
        navigationToolbar.goToUsersAndGroupsManagement();
        userAndGroupManagement.goToGroupTab();
         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
          String[] arrayGroupPath ={groups.get(0),space};
          String membership = membershipData.getContentByIndex(4);
        userAndGroupManagement.selectGroup(arrayGroupPath);
        userAndGroupManagement.addUsersToGroup(username2,membership,false,true);
     }

/**
*<li> Case ID:122312.</li>
*<li> Test Case Name: Invited user accept invitation.</li>
*<li> Pre-Condition: </li>
*<li> Post-Condition: </li>
*/
    @Test
    public  void test02_InvitedUserAcceptInvitation() {
        info("Test 2: Invited user accept invitation");
        String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
        String email1 = username1+"@gmail.com";
        String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
        String email2 = username2+"@gmail.com";
        String fullName2=username2+" "+username2;
        info("Add new user");
        navTool.goToAddUser();
        addUserPage.addUser(username1, password, email1, username1, username1);
        addUserPage.addUser(username2, password, email2, username2, username2);
        magAc.signIn(username1,password);
        Utils.pause(3000);

        /*Step Number: 1
        *Step Name: Step 1: Create a new Space
        *Step Description:
            - Sign in system
            - Select space page to view
            - Click on [Add new Space]
            - Enter all valid data
            - Click [Create]
        *Input Data:

        *Expected Outcome:
            - Create new Space successfully.*/
        String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

        info("Create a space");
        hp.goToAllSpace();
        spaMg.addNewSpaceSimple(space,space);

        /*Step number: 2
        *Step Name: Step 2: Send invitation
        *Step Description:
            - Creator selects the Space which he/she has created at step 1.1.
            - Click on [Edit space] icon or access the Space and select space settings portlet
            - Select [Members] tab
            - Enter valid user name or select a user from list
            - Click [invite]
        *Input Data:

        *Expected Outcome:
            Send invitation successfully:*/
        spaHome.goToSpaceSettingTab();
        setSpaceMg.goToMemberTab();
        setSpaceMg.inviteUser(username2,true,fullName2);

        /*Step number: 3
        *Step Name: Step 3: Invited user accepts invitation
        *Step Description:
            - Invited user signs in
            - Select space page
            - Select invitations space list
            - Select Space which has created at step 1.
            - Click on [Accept] button
        *Input Data:

        *Expected Outcome:
            User becomes member of the space:
            - On the screen of invited user: [Accept] & [Ignore] button are changed by button [leave].
              The space is removed from invitations list and added into my space list.
              Member can access the Space by click on displaying name of the space.
            - On screen of user who sends invitation:
              user name of invited user is removed from invited user list but it has been added in member list.
            - When user accesses the User list portlet, user will see profile of new member.*/
        magAc.signIn(username2,password);
        info("On the screen of invited user: [Accept] & [Ignore] button are changed by button [leave]");
        hp.goToAllSpace();
        spaMg.goToInvitationsReceivedTab();
        spaMg.acceptAInvitation(space);

        hp.goToMySpaces();
        waitForAndGetElement(spaMg.ELEMENT_SPACE_LEAVE_BTN.replace("${space}",space),2000,1);

        info("The space is removed from invitations list and added into my space list");
        spaMg.goToInvitationsReceivedTab();
        waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);

        info("Member can access the Space by click on displaying name of the space.");
        hp.goToSpecificSpace(space);

        magAc.signIn(username1,password);
        info("user name of invited user is removed from invited user list but it has been added in member list");
        hp.goToSpecificSpace(space);
        spaHome.goToSpaceSettingTab();
        setSpaceMg.goToMemberTab();
        waitForAndGetElement(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName2));
        waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}",fullName2));

        info("When user accesses the User list portlet, user will see profile of new member.");
        spaMg.goToMemberTab();
        waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2),2000,1);
        spaMg.goToUserProfilePage(fullName2);
        waitForAndGetElement(myProfile.ELEMENT_PROFILE_TITLE.replace("${fullName}",fullName2),2000,1);
     }


}