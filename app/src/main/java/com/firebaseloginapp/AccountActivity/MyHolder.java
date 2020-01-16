package com.firebaseloginapp.AccountActivity;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;
import com.firebaseloginapp.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImeView;
    TextView mTitle,mDes,mDate,mCmnts,nProf;
    itemClickListner itemClickListner;
    MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImeView = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.TitleTv);
        this.mDes = itemView.findViewById(R.id.description);
        this.mDate = itemView.findViewById(R.id.date);
        this.mCmnts = itemView.findViewById(R.id.comments);
        this.nProf = itemView.findViewById(R.id.nomprof);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListner.onItemClickedListenner(v,getLayoutPosition());

    }
    public void setItemClickListner(itemClickListner icl){
        this.itemClickListner = icl;
    }
}
