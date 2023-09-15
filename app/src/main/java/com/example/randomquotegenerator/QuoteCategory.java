package com.example.randomquotegenerator;

public class QuoteCategory {
    private String categoryName;
    private String quoteText;

    public QuoteCategory(String categoryName, String quoteText) {
        this.categoryName = categoryName;
        this.quoteText = quoteText;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getQuoteText() {
        return quoteText;
    }
}
