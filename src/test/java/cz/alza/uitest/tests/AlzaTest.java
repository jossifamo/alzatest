package cz.alza.uitest.tests;

import cz.alza.uitest.pages.AlzaCartPage;
import cz.alza.uitest.pages.AlzaLandingPage;
import cz.alza.uitest.pages.AlzaPage;
import cz.alza.uitest.pages.AlzaMonitorsProfessionalPage;

import cz.alza.uitest.pages.AlzaSearchResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlzaTest {

    @Test
    public void addItemToCartTest() {
        int itemNo = 1;

        AlzaLandingPage landingPage = new AlzaLandingPage();
        landingPage
                .openPage()
                .selectCategory(AlzaPage.Category.MONITORS, AlzaPage.Category.PROFESSIONAL);

        AlzaMonitorsProfessionalPage monitorsPage = new AlzaMonitorsProfessionalPage();
        String itemName = monitorsPage
                .cheapestFirst()
                .selectNthExpensive(itemNo);

        AlzaCartPage cartPage = monitorsPage.goToCart();

        cartPage.cartElement().shouldHave(Condition.text("1"));
        cartPage.getFirstCartItem().shouldHave(Condition.text(itemName));
    }

    @Test
    public void searchResultsTest() {
        String searchText = "gameboy";
        String expectedText = String.format("Vyhledáno: %s", searchText);
        String expectedCount = "2 položky";

        AlzaLandingPage landingPage = new AlzaLandingPage();
        landingPage
                .openPage()
                .search(searchText);

        AlzaSearchResultsPage resultsPage = new AlzaSearchResultsPage();
        resultsPage.getSearchResultMsg().shouldHave(Condition.text(expectedText));
        resultsPage.getSearchResultCountLabel().shouldHave(Condition.text(expectedCount));
    }

    @Test
    public void searchNoResultTest() {
        String searchText = "fasdljfaklsdhgfklahfgkljadfhgkjaskdhf";
        String expectedText = String.format("Ať hledám jak hledám, „%s“ jsem nenašel.", searchText);

        AlzaLandingPage landingPage = new AlzaLandingPage();
        landingPage
                .openPage()
                .search(searchText);

        AlzaSearchResultsPage resultsPage = new AlzaSearchResultsPage();
        resultsPage.getNoResultMsg().shouldHave(Condition.text(expectedText));
    }

    @Test
    public void switchLanguageTest() {
        AlzaLandingPage landingPage = new AlzaLandingPage();
        landingPage.openPage();
        String ASSERT_MSG_TEMP = "Correct localization not loaded for: %s";
        assertTrue(landingPage.changeLanguage(AlzaPage.Language.CZ_ENG).isCurrentPage(), String.format(ASSERT_MSG_TEMP, AlzaPage.Language.CZ_ENG.getText()));
        assertTrue(landingPage.changeLanguage(AlzaPage.Language.CZ_CZ).isCurrentPage(), String.format(ASSERT_MSG_TEMP, AlzaPage.Language.CZ_CZ.getText()));
    }



    @AfterEach
    public void tearDown(){
        AlzaCartPage cartPage = new AlzaCartPage();
        if(!cartPage.isCurrentPage()){
            cartPage.openPage();
        }
        cartPage.cleanCart();

    }
}