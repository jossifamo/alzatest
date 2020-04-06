package cz.alza.uitest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AlzaSearchResultsPage extends AlzaPage<AlzaSearchResultsPage> {

  private static final String SEARCH_REGEX = "\\/search.htm\\?exps=.*";
  private static final String PAGE_URL_REGEX = Pattern.quote(ALZA_URL) + SEARCH_REGEX;
  private static final String SEARCHED_TEXT_RESULT = "//*[@id=\"categoryAnotationBlock\"]//div/h1";
  private static final String SEARCHED_TEXT_NO_RESULT = "//*[@id=\"noresult\"]";
  private static final String SEARCHED_RESULT_COUNT = "//*[@id=\"lblNumberItem0\"]";

  public Boolean isCurrentPage() {
    return url().matches(PAGE_URL_REGEX);
  }

  public SelenideElement getSearchResultMsg() {
    return $(By.xpath(SEARCHED_TEXT_RESULT));
  }

  public SelenideElement getNoResultMsg() {
    return $(By.xpath(SEARCHED_TEXT_NO_RESULT));
  }


  public SelenideElement getSearchResultCountLabel() {
    return $(By.xpath(SEARCHED_RESULT_COUNT));
  }
}