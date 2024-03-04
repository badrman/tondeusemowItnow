package com.mowitnow.tondeuse.cucumber.steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;

public class TondeuseBrowserEtapes
{
    @LocalServerPort
    int serverPort;

    WebDriver firstUserBrowser = null;

    String mainPageUrl = "/index";

    @Before
    public void initiate()
    {
        mainPageUrl ="http://localhost:" + serverPort + "/";
    }

    @Given("utilisateur ouvre le navigateur")
    public void firstUserBrowserIsOpen() {
        firstUserBrowser = openAndInitiateBrowser(new Point(0, 0));
        firstUserBrowser.navigate().to(mainPageUrl);
    }

    private WebDriver openAndInitiateBrowser(Point position) {
        WebDriver webDriver = new FirefoxDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        webDriver.manage().window().setPosition(position);
        webDriver.manage().window().setSize(new Dimension(800, 500));

        return webDriver;
    }

    @And("^utilisateur charge le fichier de coordonnées (.*)$")
    public void firstUserIsOnMainPage(String nomDeFichier) throws URISyntaxException {
        // FILE UPLOADING USING SENDKEYS ....
        WebElement browse = firstUserBrowser.findElement(By.xpath("//input[@id='fichierCoordonnees']"));
        //click on ‘Choose file’ to upload the desired file
        browse.sendKeys(Paths.get(getClass().getClassLoader().getResource("files/".concat(nomDeFichier)).toURI()).toFile().getAbsolutePath());
    }

    @When("^utilisateur clique sur (.*)$")
    public void utilisateurCliqueSurUnBouton(String elementClique) {
        firstUserBrowser.findElement(By.id(elementClique)).click();
    }

    @Then("resultat d'execution {string} est affiché")
    public void afficherResultat(String message)
    {
        var resultat = firstUserBrowser.findElement(By.id("detailtondeuses")).getText();
        Assertions.assertEquals(message, resultat);
    }

    @Then("message d'erreur {string} est affiché")
    public void afficherErreur(String message)
    {
        var resultat = firstUserBrowser.findElement(By.id("erreurMessage")).getText();
        Assertions.assertEquals(message, resultat);
    }

    @Then("utilisateur ferme navigateur")
    public void fermernavigateur()
    {
        firstUserBrowser.close();
    }
}
