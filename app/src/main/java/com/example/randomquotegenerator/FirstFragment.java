package com.example.randomquotegenerator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {
    // Declare a variable to store the selected quote category
    private String selectedQuoteCategory;
    private QuoteDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new QuoteDatabaseHelper(requireContext()); // Create the instance
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the RadioGroup and set a listener to capture the selected quote category
        RadioGroup radioGroup = view.findViewById(R.id.radio_group_theme);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected quote category from the RadioGroup
                selectedQuoteCategory = getSelectedQuoteCategory(checkedId);
            }
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a bundle to pass data to the SecondFragment
                Bundle bundle = new Bundle();
                bundle.putString("quoteCategory", selectedQuoteCategory);

                // Navigate to the SecondFragment and pass the bundle as arguments
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });

        view.findViewById(R.id.button_quote_of_the_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_quoteOfDayFragment);
            }
        });
    }

    // Helper method to get the selected quote category from the RadioGroup
    private String getSelectedQuoteCategory(int checkedId) {
        switch (checkedId) {
            case R.id.radio_theme_love:
                return "Love";
            case R.id.radio_theme_wisdom:
                return "Wisdom";
            case R.id.radio_theme_funny:
                return "Funny";
            case R.id.radio_theme_motivational:
                return "Motivational";
            default:
                return ""; // Return a default value if no category is selected
        }
    }
}
