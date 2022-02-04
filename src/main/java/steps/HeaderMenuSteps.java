package steps;

import pages.forms.HeaderMenu;
import pages.forms.TopProfileMenu;

public class HeaderMenuSteps {

    private static final HeaderMenu headerMenu = new HeaderMenu();
    private static final TopProfileMenu topProfileMenu = new TopProfileMenu();

    private HeaderMenuSteps() {
    }

    public static void profileArrowCmbClick() {
        headerMenu.profileArrowCmbClick();
    }

    public static void signOutBtnClick() {
        topProfileMenu.signOutBtnClick();
    }
}
