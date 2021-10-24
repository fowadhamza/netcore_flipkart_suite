package com.project.autotest.fun.utility;

public enum XpathConstants {
    SEARCH_BAR("//input[@name='q']"),
    LOGIN_OVERLAY_CLOSE_BUTTON("//button[@class='_2KpZ6l _2doB4z']"),
    LOGIN_BUTTON("//input[@value='Sign In']"),
    SEARCH_BUTTON("//button[@class='L0Z3Pu']"),
    PRODUCT_NAME_LINK("//div[@class='_4rR01T']"),
    PRODUCT_RATING_LINK("//div[@class='gUuXy-']/span/span/span[1]"),
    PRODUCT_PRICE_LINK("//div[@class='_30jeq3 _1_WHN1']"),
    SORT_LOW_HIGH_LINK("//div[text()='Price -- Low to High']"),
    SORT_LABEL("//span[@class='_2i7N3j']"),

    VALIDATE_HOME_PAGE("SEARCH_BAR"),
    VALIDATE_SEARCH_PAGE("PRODUCT_NAME_LINK");
    private final String xpath;
    XpathConstants(String xpath) {
        this.xpath = xpath;
    }
    public String getXpath() {
        return xpath;
    }

    public static String getXpathForKey(String key) {
        key = key.toUpperCase().replaceAll("\\s", "_");
        try {
            return XpathConstants.valueOf(key).getXpath();
        } catch (EnumConstantNotPresentException e) {
            return null;
        }
    }
}