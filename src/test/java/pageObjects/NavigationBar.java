package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;

public class NavigationBar {
    private static final By NAVIGATION_BAR = By.xpath("//*[@class='navbar navbar-default']");
    private static final By CAREERS_MENU = By.id("menu-item-127");
    private static final By VACANCIES_ITEM = By.id("menu-item-131");
    public static final By VACANCIES_MENU = By.id("menu-main-1");
    public static final int TIME_OUT_IN_SECONDS = 5;
    private BaseFunc baseFunc;

    public NavigationBar(BaseFunc baseFunc1){
        baseFunc = baseFunc1;
        Assert.assertTrue("Vacancies page is not present", baseFunc1.isPresentElement(NAVIGATION_BAR));
    }

    /**
     * Go to Vacancies page
     */
    public void openVacanciesPage(){
        Assert.assertTrue("Don't see Careers item in navigation bar", baseFunc.isPresentElementAndChild(NAVIGATION_BAR, CAREERS_MENU));
        baseFunc.moveToElement(baseFunc.findElemWithDriver(NAVIGATION_BAR).findElement(CAREERS_MENU));
        baseFunc.explicitWaitVisibilityOfElementLocated(TIME_OUT_IN_SECONDS, VACANCIES_ITEM);
        baseFunc.findElemWithDriver(CAREERS_MENU).findElement(VACANCIES_ITEM).click();
        baseFunc.explicitWaitVisibilityOfElementLocated(TIME_OUT_IN_SECONDS, VACANCIES_MENU);
    }
}
