package com.demotest.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.UIInteractionSteps;
import static org.assertj.core.api.Assertions.assertThat;
import com.demotest.utils.actions.NavigationActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class FuncionalidadPruebaDemoStepDefinitions extends UIInteractionSteps {

    NavigationActions navigate;

    @Before
    public void cargarAplicacion(){
        navigate.abrirAplicacionDemo();
    }

    @Dado("que el usuario accede a la página ingresa usuario {string} y contraseña {string} y realiza clic en el botón iniciar sesión")
    public void usuarioAccedeEIngresaCredenciales(String usuario, String contrasena) {
        $(By.id("user")).sendKeys(usuario);
        $(By.className("p-password-input")).sendKeys(contrasena);
        Serenity.takeScreenshot();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $(By.className("btn")).click();
    }

    @Cuando("el usuario ingresa nombre, apellido, cédula, fecha_nacimiento, teléfono, estado_civil, email, género y dirección en el formulario de registro")
    public void usuarioIngresaDatosFormulario(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> datos = dataTable.asMaps(String.class, String.class);

        for (java.util.Map<String, String> fila : datos) {
            $(By.id("nombre")).sendKeys(fila.get("nombre"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            $(By.id("apellido")).sendKeys(fila.get("apellido"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            $(By.id("cedula")).sendKeys(fila.get("cédula"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement fechaNacCalendar = $(By.cssSelector("#fecha input")).getElement();
            fechaNacCalendar.click();
            fechaNacCalendar.sendKeys(fila.get("fecha_nacimiento"), Keys.ENTER);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            $(By.id("telefono")).sendKeys(fila.get("teléfono"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement estadoCivilDropdown = $(By.xpath("//p-dropdown[@id='estadoCivil']")).getElement();
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
            ((JavascriptExecutor) getDriver()).executeScript(scriptJSEstadoCivil, estadoCivilDropdown, fila.get("estado_civil"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            $(By.id("email")).sendKeys(fila.get("email"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement generoDropdown = $(By.xpath("//p-dropdown[@id='genero']")).getElement();
            generoDropdown.click();
            String scriptJSGenero =
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
            ((JavascriptExecutor) getDriver()).executeScript(scriptJSGenero, generoDropdown,  fila.get("género"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            $(By.id("direccion")).sendKeys(fila.get("dirección"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Y("realiza clic en el botón registrar")
    public void usuarioHaceClicEnRegistrar() {
        $(By.id("btnRegistrar")).click();
    }

    @Entonces("debería ver notificación de ingreso exitoso")
    public void usuarioVeNotificacionExitosa() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mensajeExito = $(By.className("p-toast-detail")).getText();
        assertThat(mensajeExito).isEqualTo("Cliente creado correctamente");
    }

}
