package com.example.pao.todolistfinal;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends ListActivity {

    private DataBaseToDo toDoC;
    private SimpleCursorAdapter mAdapter;
    ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Create a new DatabaseHelper
        toDoC = new DataBaseToDo(this);
        listView=getListView();


        // Create a cursor
        Cursor c = readToDoList();

        mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, c,
                toDoC.columns, new int[] { R.id._id, R.id.name},
                0);

        setListAdapter(mAdapter);
        registerForContextMenu(listView);

        Button fixButton = (Button) findViewById(R.id.add_button);
        fixButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // execute database operations
                EditText editTextToDo= (EditText)findViewById(R.id.txtAdd);
                String toDo= editTextToDo.getText().toString().trim();
                if(toDo.isEmpty())
                {
                    return;

                }
                insertToDo(toDo);

                // Redisplay data
                mAdapter.getCursor().requery();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        if(v.getId()!=listView.getId())
        {
            return;
        }
        menu.setHeaderTitle("What would you like to do");
        String [] options={"Delete Task", "Return",};
        for(String option: options)
        {
            menu.add(option);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {


        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int selectedIndex=info.position;
        if(item.getTitle().equals("Delete Task"))
        {
           toDoC.getWritableDatabase().delete(DataBaseToDo.TABLE_NAME, DataBaseToDo._ID+ "=?",new String[] { String.valueOf(selectedIndex) });
            mAdapter.getCursor().requery();
            mAdapter.notifyDataSetChanged();

        }
        return true;
    }




    private Cursor readToDoList() {
        return toDoC.getWritableDatabase().query(toDoC.TABLE_NAME,
                toDoC.columns, null, new String[] {}, null, null,
                null);
    }

    // Delete all records
    private void clearAll() {

        toDoC.getWritableDatabase().delete(toDoC.TABLE_NAME, null, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.list_item) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Insert several artist records
    private void insertToDo( String textToDo) {

        ContentValues values = new ContentValues();

        values.put(DataBaseToDo.TODO_TEXT, textToDo);
        toDoC.getWritableDatabase().insert(DataBaseToDo.TABLE_NAME, null, values);
    }
}
