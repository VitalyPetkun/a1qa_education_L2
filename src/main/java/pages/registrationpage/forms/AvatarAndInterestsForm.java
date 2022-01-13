package pages.registrationpage.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.*;

public class AvatarAndInterestsForm extends Form {

    private final IButton UNLOAD_AVATAR_BTN = getElementFactory().getButton(
            By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Button 'unload'");
    private final IButton NEXT_BTN = getElementFactory().getButton(
            By.xpath("//div[@class='align__cell']//button[contains(@class,'button--stroked')]"), "Button 'Next'");

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

    private Map<String, ICheckBox> interestsChk = new HashMap();

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"), "Avatar and interests form");
    }

    private void initializationMap() {
        for (int i = 0; i <= getInterestsTxtList().size() - 1; ++i)
            interestsChk.put(getInterestsTxtList().get(i).getText(), getInterestsList().get(i));
    }

    public boolean isInterestsChkChecked(String nameChk) {
        this.initializationMap();
        return interestsChk.get(nameChk).isChecked();
    }

    public void interestsChkCheck(String nameChk) {
        interestsChk.get(nameChk).check();
    }

    public int getChkSize() {
        return interestsChk.keySet().size();
    }

    public Set<String> getChkName() {
        return interestsChk.keySet();
    }

    public void unloadAvatarBtnClick() {
        UNLOAD_AVATAR_BTN.click();
    }

    public void nextBtnClick() {
        NEXT_BTN.click();
    }
}
