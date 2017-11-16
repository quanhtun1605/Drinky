package com.example.minhd.drinky;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhd.drinky.api.SearchAutocompletePlaceReponse;

import static com.example.minhd.drinky.Activity.DirectActivity.edtOrigin;


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolderAddress> {
    private IAddressAdapter mInterf;
    public static String content;

    public AddressAdapter(IAddressAdapter interf) {
        mInterf = interf;
    }

    @Override
    public ViewHolderAddress onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolderAddress(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAddress holder, int position) {
        SearchAutocompletePlaceReponse.Prediction prediction = mInterf.getData(position);
        holder.tvAddress.setText(prediction.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = prediction.getDescription() ;
                edtOrigin.setText(content);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mInterf.getCount();
    }

    static class ViewHolderAddress extends RecyclerView.ViewHolder {
        private TextView tvAddress;

        public ViewHolderAddress(View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }

    public interface IAddressAdapter {
        int getCount();

        SearchAutocompletePlaceReponse.Prediction getData(int position);
    }
}
