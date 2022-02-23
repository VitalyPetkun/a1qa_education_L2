package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.*;

public class AvatarAndInterestsForm extends Form {

    private final IButton unloadAvatarBtn = getElementFactory().getButton(
            By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Button 'unload'");
    private final IButton nextBtn = getElementFactory().getButton(
            By.xpath("//div[@class='align__cell']//button[contains(@class,'button--stroked')]"), "Button 'Next'");

    private Map<String, ICheckBox> interests = new HashMap();

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"), "Avatar and interests form");
    }

    private List<ICheckBox> getInterestsList() {
        return getElementFactory().findElements(
                By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[@class='checkbox__box']"),
                ElementType.CHECKBOX);
    }

    private List<ILabel> getInterestsTxtList() {
        return getElementFactory().findElements(
                By.xpath("//div[@class='avatar-and-interests__interests-list__item']//span[not(contains(@class,'checkbox'))]"),
                ElementType.LABEL);
    }

    private Map<String, ICheckBox> getMap() {
        if (interests.size() == 0) {
            for (int i = 0; i <= getInterestsTxtList().size() - 1; ++i) {
                interests.put(getInterestsTxtList().get(i).getText(), getInterestsList().get(i));
            }
        }

        return interests;
    }

    public void checkInterest(String nameChk) {
        Map<String, ICheckBox> map = getMap();
        if (!map.get(nameChk).isChecked()) {
            map.get(nameChk).check();
        }
    }

    public int getSizeInterests() {
        return getMap().keySet().size();
    }

    public Set<String> getNameInterests() {
        return getMap().keySet();
    }

    public void clickUnloadAvatarBtn() {
        unloadAvatarBtn.click();
    }

    public void clickNextBtn() {
        nextBtn.click();
    }
}
