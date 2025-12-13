package com.example.mindpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class ProfessionalAdapter extends ArrayAdapter<ProfItem> {

    public ProfessionalAdapter(Context context, List<ProfItem> professionals) {
        super(context, 0, professionals);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProfItem prof = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.proflistview, parent, false);
        }

        //sesuaikan dulu sama ProfItem baru masukkan di sini
        ImageView profImage = convertView.findViewById(R.id.profImage);
        TextView profName = convertView.findViewById(R.id.profName);
        TextView profReview = convertView.findViewById(R.id.profReview);

        if (prof != null) {
            profName.setText(prof.getName());
            profReview.setText(prof.getReview());
            profImage.setImageResource(prof.getImageResourceId());
        }

        return convertView;
    }
}