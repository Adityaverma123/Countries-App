package com.example.countriesapp.view;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountyListAdapter extends RecyclerView.Adapter<CountyListAdapter.CountryViewHolder> {
    private List<CountryModel>countries;
    public CountyListAdapter(List<CountryModel>countries)
    {
        this.countries=countries;
    }
    public void update(List<CountryModel>newCountries)
    {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView)
        ImageView countryImages;
        @BindView(R.id.name)
        TextView countryName;
        @BindView(R.id.capital)
        TextView countryCapital;


        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(CountryModel country)
        {
            countryName.setText(country.getCountryName());
            countryCapital.setText(country.getCapital());
            Util.loadImage(countryImages,country.getFlag(),Util.getProgressDrawable(countryImages.getContext()));
        }
    }
}
