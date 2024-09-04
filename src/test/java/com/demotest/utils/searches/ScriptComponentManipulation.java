package com.demotest.utils.searches;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.UIInteractionSteps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ScriptComponentManipulation extends UIInteractionSteps {
    public void llenarPcalendarPorSelectorPorValorkey(String selector, String valorKey) {
        WebElement fechaNacCalendar = $(By.cssSelector(selector)).getElement();
        fechaNacCalendar.click();
        fechaNacCalendar.sendKeys(valorKey, Keys.ENTER);
    }

    public void llenarPdropdownPorXpathPorValorkey(String xpath, String valorKey) {
        WebElement estadoCivilDropdown = $(By.xpath(xpath)).getElement();
        estadoCivilDropdown.click();
        String scriptJSEstadoCivil =
                "var dropdown = arguments[0];" +
                        "var optionText = arguments[1];" +
                        "var items = dropdown.querySelectorAll('.p-dropdown-item');" +
                        "for (var i = 0; i < items.length; i++) {" +
                        "    if (items[i].textContent.trim() === optionText) {" +
                        "        items[i].click();" +
                        "        return true;" +
                        "    }" +
                        "}" +
                        "return false;";
        ((JavascriptExecutor) getDriver()).executeScript(scriptJSEstadoCivil, estadoCivilDropdown, valorKey);
    }
}
