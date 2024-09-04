package com.demotest.stepdefinitions;

import com.demotest.utils.daos.DaoUtil;
import com.demotest.utils.searches.ScriptComponentManipulation;
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

public class FuncionalidadPruebaDemoStepDefinitions extends UIInteractionSteps {

    NavigationActions navigate;
    DaoUtil daoUtil;
    ScriptComponentManipulation scriptComponentManipulation;

    @Before
    public void cargarAplicacion(){
        daoUtil = new DaoUtil();
    }

    @Dado("que el usuario accede a la página ingresa usuario {string} y contraseña {string} y realiza clic en el botón iniciar sesión")
    public void usuarioAccedeEIngresaCredenciales(String usuario, String contrasena) {
        navigate.abrirAplicacionDemo();
        $(By.id("user")).sendKeys(usuario);
        $(By.className("p-password-input")).sendKeys(contrasena);
        Serenity.takeScreenshot();
        daoUtil.waitTime(1.0);
        $(By.className("btn")).click();
    }

    @Cuando("el usuario ingresa nombre, apellido, cédula, fecha_nacimiento, teléfono, estado_civil, email, género y dirección en el formulario de registro")
    public void usuarioIngresaDatosFormulario(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> datos = dataTable.asMaps(String.class, String.class);

        for (java.util.Map<String, String> fila : datos) {
            $(By.id("nombre")).sendKeys(fila.get("nombre"));
            daoUtil.waitTime(0.5);

            $(By.id("apellido")).sendKeys(fila.get("apellido"));
            daoUtil.waitTime(0.5);

            $(By.id("cedula")).sendKeys(fila.get("cédula"));
            daoUtil.waitTime(0.5);

            scriptComponentManipulation.llenarPcalendarPorSelectorPorValorkey("#fecha input", fila.get("fecha_nacimiento"));
            daoUtil.waitTime(0.5);

            $(By.id("telefono")).sendKeys(fila.get("teléfono"));
            daoUtil.waitTime(0.5);

            scriptComponentManipulation.llenarPdropdownPorXpathPorValorkey("//p-dropdown[@id='estadoCivil']", fila.get("estado_civil"));
            daoUtil.waitTime(0.5);

            $(By.id("email")).sendKeys(fila.get("email"));
            daoUtil.waitTime(0.5);

            scriptComponentManipulation.llenarPdropdownPorXpathPorValorkey("//p-dropdown[@id='genero']", fila.get("género"));
            daoUtil.waitTime(0.5);

            $(By.id("direccion")).sendKeys(fila.get("dirección"));
            daoUtil.waitTime(0.5);
        }
    }

    @Y("realiza clic en el botón registrar")
    public void usuarioHaceClicEnRegistrar() {
        $(By.id("btnRegistrar")).click();
    }

    @Entonces("debería ver notificación de ingreso exitoso")
    public void usuarioVeNotificacionExitosa() {
        daoUtil.waitTime(2.0);
        String mensajeExito = $(By.className("p-toast-detail")).getText();
        assertThat(mensajeExito).isEqualTo("Cliente ingresado correctamente");
    }
}
