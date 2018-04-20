package amirjaber.rogmax.mypoems.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import amirjaber.rogmax.mypoems.R;
import amirjaber.rogmax.mypoems.models.PoemModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<PoemModel> results;

    public RecyclerViewAdapter(Context context, List<PoemModel> list) {
        this.context = context;
        this.results = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PoemModel poemModel = results.get(position);

        holder.textViewName.setText(poemModel.getName());
        holder.name = poemModel.getName();
        holder.poem = poemModel.getPoem();

    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Long id;
        TextView textViewName;
        String name, poem;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.poem_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(context, "clicked on poem name " + name, Toast.LENGTH_SHORT).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.dialog_view_poem, null);
            builder.setView(customView);
            builder.setCancelable(true);

            TextView tvName = customView.findViewById(R.id.poem_name_d);
            TextView tvPoem = customView.findViewById(R.id.poem_text);

            tvName.setText(name);
            tvPoem.setText(poem);


            final AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}
