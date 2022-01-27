package steps;

import pages.forms.HeaderMenu;
import pages.forms.TopProfileMenu;

public class HeaderMenuSteps {

    private static final HeaderMenu HEADER_MENU = new HeaderMenu();
    private static final TopProfileMenu TOP_PROFILE_MENU = new TopProfileMenu();

    public static void profileArrowCmbClick() {
        HEADER_MENU.profileArrowCmbClick();
    }

    public static void signOutBtnClick() {
        TOP_PROFILE_MENU.signOutBtnClick();
    }
}
