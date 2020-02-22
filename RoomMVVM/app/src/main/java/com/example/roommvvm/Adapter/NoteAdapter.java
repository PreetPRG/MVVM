package com.example.roommvvm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvvm.Entity.Note;
import com.example.roommvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> notes= new ArrayList<Note>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.mtextViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.mtextViewTitle.setText(currentNote.getTitle());
        holder.mtextViewDescription.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<Note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mtextViewTitle;
        private TextView mtextViewDescription;
        private TextView mtextViewPriority;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtextViewTitle=itemView.findViewById(R.id.text_view_title);
            mtextViewDescription=itemView.findViewById(R.id.text_view_description);
            mtextViewPriority=itemView.findViewById(R.id.textView_priority);
        }
    }
}
