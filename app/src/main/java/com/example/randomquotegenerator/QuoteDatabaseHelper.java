package com.example.randomquotegenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class QuoteDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quotes.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_QUOTE_CATEGORIES = "quote_categories";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CATEGORY_NAME = "category_name";
    private static final String COLUMN_QUOTE_TEXT = "quote_text";

    // Create the table query
    private static final String CREATE_TABLE_QUOTE_CATEGORIES =
            "CREATE TABLE " + TABLE_QUOTE_CATEGORIES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CATEGORY_NAME + " TEXT, " +
                    COLUMN_QUOTE_TEXT + " TEXT);";

    public QuoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUOTE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    // Add methods for inserting and retrieving data from the database
    // (e.g., insertQuoteCategory, getAllQuoteCategories, etc.)


    // Method to populate the database with initial quote genres and quotes
    private void populateDatabaseWithInitialData(SQLiteDatabase db) {
        // Assuming you have a list of quote genres and their quotes
        // Replace this with your actual data
        List<QuoteCategory> quoteCategories = getInitialQuoteCategories();

        ContentValues values = new ContentValues();
        for (QuoteCategory category : quoteCategories) {
            values.put(COLUMN_CATEGORY_NAME, category.getCategoryName());
            values.put(COLUMN_QUOTE_TEXT, category.getQuoteText());
            db.insert(TABLE_QUOTE_CATEGORIES, null, values);
        }
    }

    // Method to retrieve the initial list of quote genres and quotes
     List<QuoteCategory> getInitialQuoteCategories() {
        List<QuoteCategory> quoteCategories = new ArrayList<>();
        // Add your quote genres and quotes here
        // Love Category
        quoteCategories.add(new QuoteCategory("Love", "Love is all you need."));
        quoteCategories.add(new QuoteCategory("Love", "You know you're in love when you can't fall asleep because reality is finally better than your dreams."));
        quoteCategories.add(new QuoteCategory("Love", "In dreams and in love, there are no impossibilities."));
        quoteCategories.add(new QuoteCategory("Love", "The best thing to hold onto in life is each other."));
        quoteCategories.add(new QuoteCategory("Love", "A successful marriage requires falling in love many times, always with the same person."));

        //Wisom category
        quoteCategories.add(new QuoteCategory("Wisdom", "Wisdom is the supreme part of happiness."));
        quoteCategories.add(new QuoteCategory("Wisdom", "The only true wisdom is in knowing you know nothing."));
        quoteCategories.add(new QuoteCategory("Wisdom", "The only way to do great work is to love what you do."));
        quoteCategories.add(new QuoteCategory("Wisdom", "The mind is everything. What you think you become."));
        quoteCategories.add(new QuoteCategory("Wisdom", "Wisdom is not a product of schooling but of the lifelong attempt to acquire it."));

        //Motivational
        quoteCategories.add(new QuoteCategory("Motivational", "Your time is limited, don't waste it living someone else's life."));
        quoteCategories.add(new QuoteCategory("Motivational", "Believe you can and you're halfway there."));
        quoteCategories.add(new QuoteCategory("Motivational", "The only way to achieve the impossible is to believe it is possible."));
        quoteCategories.add(new QuoteCategory("Motivational", "The future belongs to those who believe in the beauty of their dreams."));

        quoteCategories.add(new QuoteCategory("Funny", "I always wanted to be somebody, but now I realize I should have been more specific."));
        quoteCategories.add(new QuoteCategory("Funny", "My therapist says I have a preoccupation with vengeance. We'll see about that."));
        quoteCategories.add(new QuoteCategory("Funny", "I'm not lazy, I'm on energy-saving mode."));
        quoteCategories.add(new QuoteCategory("Funny", "I would lose weight, but I hate losing."));

        return quoteCategories;
    }
    List<String> getQuotesForCategory(String category) {
        List<String> quotes = new ArrayList<>();
        List<QuoteCategory> quoteCategories = getInitialQuoteCategories();

        for (QuoteCategory quoteCategory : quoteCategories) {
            if (quoteCategory.getCategoryName().equals(category)) {
                quotes.add(quoteCategory.getQuoteText());
            }
        }

        return quotes;
    }


}
