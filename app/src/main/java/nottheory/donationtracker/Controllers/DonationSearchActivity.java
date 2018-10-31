package nottheory.donationtracker.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import nottheory.donationtracker.Model.Donation;
import nottheory.donationtracker.Model.LoginManager;
import nottheory.donationtracker.R;

public class DonationSearchActivity extends AppCompatActivity {
    private Switch searchByCat;
    private TextView searchBox;
    private RecyclerView donationSearchList;
    private Spinner catSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_search);

        searchBox = findViewById(R.id.search_entry);
        searchBox.setVisibility(View.VISIBLE);

        catSpinner = findViewById(R.id.search_cat_spinner);
//        TODO: remove this hardcoded cat. list
        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("Clothing");
        categoryList.add("Hat");
        categoryList.add("Kitchen");
        categoryList.add("Electronics");
        categoryList.add("Household");
        categoryList.add("Other");
        catSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList));
        catSpinner.setVisibility(View.GONE);

        searchByCat = findViewById(R.id.search_switch);
        searchByCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchByCat.isChecked()) {
                    catSpinner.setVisibility(View.VISIBLE);
                    searchBox.setVisibility(View.GONE);
                } else {
                    catSpinner.setVisibility(View.GONE);
                    searchBox.setVisibility(View.VISIBLE);
                }
            }
        });

        donationSearchList = findViewById(R.id.donationSearchList);
        ArrayList<Donation> donationArray = LoginManager.locations.getLocationFromRow(getIntent().getIntExtra("pos", 1)).getDonations();
        donationSearchList.setAdapter(new DonationSearchActivity.DonationAdapter(this, donationArray.toArray()));
        donationSearchList.setLayoutManager(new LinearLayoutManager(this));
    }

    private class DonationAdapter extends RecyclerView.Adapter<DonationSearchActivity.DonationAdapter.DonationViewHolder> {
        private Object[] donations;
        private Context context;
        public class DonationViewHolder extends RecyclerView.ViewHolder {
            public Button donation;

            public DonationViewHolder(View view) {
                super(view);
                donation = view.findViewById(R.id.location_row_button);
            }
        }

        public DonationAdapter(Context context, Object[] donations) {
            this.donations = donations;
            this.context = context;
        }

        public DonationSearchActivity.DonationAdapter.DonationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.location_recyclerview_row, parent, false);
            return new DonationSearchActivity.DonationAdapter.DonationViewHolder(view);
        }

        public void onBindViewHolder(DonationSearchActivity.DonationAdapter.DonationViewHolder viewHolder, final int position) {
            viewHolder.donation.setText(donations[position].toString());
            viewHolder.donation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DonationSearchActivity.this, DonationInfoActivity.class);
                    intent.putExtra("dpos", position);
                    startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return donations.length;
        }
    }
}
