package com.demotest.utils.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractions;

public class NavigationActions extends UIInteractions {
    @Step("Abrir la aplicación demo de puntonet")
    public void abrirAplicacionDemo() {
        Serenity.getDriver().navigate().to("http://localhost:4200/inicio-sesion");
        Serenity.takeScreenshot();
    }
}
