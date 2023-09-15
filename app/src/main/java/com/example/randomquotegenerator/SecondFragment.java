package com.example.randomquotegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private QuoteDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve the quote category from the arguments
        String quoteCategory = getArguments().getString("quoteCategory");

        // Display the quote category in a TextView
        TextView textViewQuoteCategory = view.findViewById(R.id.textView_quote_category);
        textViewQuoteCategory.setText("Quotes for " + quoteCategory);

        // Initialize the QuoteDatabaseHelper
        dbHelper = new QuoteDatabaseHelper(requireContext());

        // Retrieve the quotes for the selected category
        final List<String> quotes = getQuotesForCategory(quoteCategory);

        // Display the quotes in the UI
        displayQuotes(quotes);

        // Set an onClickListener on the "Back" button to navigate back to the FirstFragment
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        // Set an onClickListener on the Next button to trigger a new random quote display from the quote category
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayQuotes(quotes);
            }
        });

        // Find the share button
        ImageButton shareButton = view.findViewById(R.id.button_share);

        // Set an OnClickListener to handle the share button click
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote(); // Call the method to initiate the sharing process
            }
        });
    }

    private List<String> getQuotesForCategory(String category) {
        // Query the database or fetch quotes from the list of QuoteCategory objects
        // based on the selected category
        List<String> quotes = new ArrayList<>();

        // For demonstration purposes, let's assume you have a method in your QuoteDatabaseHelper
        // to retrieve quotes for a given category
        quotes = dbHelper.getQuotesForCategory(category);

        return quotes;
    }

    private void displayQuotes(List<String> quotes) {
        // Display a random quote in the UI (replace quotesTextView with your actual TextView)
        TextView quotesTextView = requireView().findViewById(R.id.textView_quote_category);

        if (!quotes.isEmpty()) {
            // Get a random index within the range of the quotes list
            int randomIndex = new Random().nextInt(quotes.size());

            // Get the random quote using the random index
            String randomQuote = quotes.get(randomIndex);

            // Set the random quote in the TextView
            quotesTextView.setText("- " + randomQuote);
        } else {
            quotesTextView.setText("No quotes available."); // Handle the case when the quotes list is empty
        }
    }
    // Method to share a quote on social media
    private void shareQuote() {
        // Get the current quote text from the TextView
        TextView textViewQuote = requireView().findViewById(R.id.textView_quote_category);
        String quoteText = textViewQuote.getText().toString();

        // Create a sharing intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quoteText);

        // Start the sharing activity
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }
}
