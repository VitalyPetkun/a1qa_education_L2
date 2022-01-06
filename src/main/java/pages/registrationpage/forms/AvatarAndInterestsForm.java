package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class AvatarAndInterestsForm extends Form {

    private final IButton btnUnloadAvatar = this.getElementFactory().getButton(
            By.xpath("//a[@class='avatar-and-interests__upload-button']"),"Button 'unload'");
    private final IButton btnNext = this.getElementFactory().getButton(
            By.xpath("//div[@class='align__cell']//button[contains(@class,'button--stroked')]"),"Button 'Next'");

    private List<ICheckBox> getCheckBoxesInterestsList() {
        return this.getElementFactory().findElements(
                By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[@class='checkbox__box']"),
                ElementType.CHECKBOX);
    }

    private List<ILabel> getCheckBoxesTextInterestsList() {
        return this.getElementFactory().findElements(
                By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[not(contains(@class,'checkbox'))]"),
                ElementType.LABEL);
    }

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"),"Avatar and interests form");
    }

    public boolean isCheckBoxInterestsChecked(int index) {
        return this.getCheckBoxesInterestsList().get(index).isChecked();
    }

    public int getListCheckBoxInterestsSize() {
        return this.getCheckBoxesInterestsList().size();
    }

    public String getCheckBoxTextInterestsName(int index) {
        return this.getCheckBoxesTextInterestsList().get(index).getText();
    }

    public void checkBoxInterestsCheck(int index) {
        this.getCheckBoxesInterestsList().get(index).check();
    }

    public void btnUnloadAvatarClick() {
        btnUnloadAvatar.click();
    }

    public void btnNextClick() {
        btnNext.click();
    }
}
