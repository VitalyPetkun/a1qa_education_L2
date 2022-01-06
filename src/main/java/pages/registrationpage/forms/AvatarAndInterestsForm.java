package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class AvatarAndInterestsForm extends Form {

    private final List<ICheckBox> checkBoxesInterests = this.getElementFactory().findElements(
            By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[@class='checkbox__box']"),
            ElementType.CHECKBOX);
    private final List<ILabel> checkBoxesTextInterests = this.getElementFactory().findElements(
            By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[not(contains(@class,'checkbox'))]"),
            ElementType.LABEL);

    private final IButton btnUnloadAvatar = this.getElementFactory().getButton(
            By.xpath("//a[@class='avatar-and-interests__upload-button']"),"Button 'unload'");
    private final IButton btnNext = this.getElementFactory().getButton(
            By.xpath("//div[@class='align__cell']//button[contains(@class,'button--stroked')]"),"Button 'Next'");

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"),"Avatar and interests form");
    }

    public boolean isCheckBoxInterestsChecked(int index) {
        return checkBoxesInterests.get(index).isChecked();
    }

    public int getListCheckBoxInterestsSize() {
        return checkBoxesInterests.size();
    }

    public String getCheckBoxTextInterestsName(int index) {
        return checkBoxesTextInterests.get(index).getText();
    }

    public void checkBoxInterestsCheck(int index) {
        checkBoxesInterests.get(index).check();
    }

    public void btnUnloadAvatarClick() {
        btnUnloadAvatar.click();
    }

    public void btnNextClick() {
        btnNext.click();
    }
}
