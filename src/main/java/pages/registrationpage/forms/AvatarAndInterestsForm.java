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

    private boolean isInterestsChecked(Map<String, ICheckBox> map, String nameChk) {
        return map.get(nameChk).isChecked();
    }

    private void interestsCheck(Map<String, ICheckBox> map, String nameChk) {
        map.get(nameChk).check();
    }

    public void interestCheck(String nameChk) {
        Map<String, ICheckBox> map = getMap();
        if (!this.isInterestsChecked(map, nameChk)) {
            this.interestsCheck(map, nameChk);
        }
    }

    public int getInterestsSize() {
        return getMap().keySet().size();
    }

    public Set<String> getInterestsName() {
        return getMap().keySet();
    }

    public void unloadAvatarBtnClick() {
        unloadAvatarBtn.click();
    }

    public void nextBtnClick() {
        nextBtn.click();
    }
}
