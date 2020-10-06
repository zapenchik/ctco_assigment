package tests;

import org.junit.After;
import org.junit.Test;
import pageObjects.BaseFunc;
import pageObjects.NavigationBar;
import pageObjects.VacanciesPage;

public class CheckVacancyTestAutomationEngineerTest {

    private BaseFunc baseFunc;

    @After
    public void stopDriver() {
        baseFunc.closeWindow();
    }

    @Test
    public void checkVacancyTestAutomationEngineerTest(){
        baseFunc = new BaseFunc();
        NavigationBar navigationBar = new NavigationBar(baseFunc);
        navigationBar.openVacanciesPage();
        VacanciesPage vacanciesPage = new VacanciesPage(baseFunc);
        vacanciesPage.openVacancyPage();
        vacanciesPage.checkParagraphAndCountOfItemsInIt();
    }
}
