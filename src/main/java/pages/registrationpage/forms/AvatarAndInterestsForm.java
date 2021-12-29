package pages.registrationpage.forms;

import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class AvatarAndInterestsForm extends Form {
    protected final By XPATH_CHECK_BOX_INTEREST =
            By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[@class='checkbox__box']");
    protected final By XPATH_CHECK_BOX_TEXT_INTEREST =
            By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[not(contains(@class,'checkbox'))]");
    protected final By XPATH_BUTTON_UNLOAD_AVATAR =
            By.xpath("//a[@class='avatar-and-interests__upload-button']");
    protected final By XPATH_BUTTON_NEXT =
            By.xpath("//div[@class='align__cell']//button[contains(@class,'button--stroked')]");

    protected List<ICheckBox> checkBoxesInterests;
    protected List<ILabel> checkBoxesTextInterests;

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"),"Avatar and interests form");
    }
}
