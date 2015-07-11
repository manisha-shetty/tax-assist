package com.manishax.taxassist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class TaxAssist extends ListActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		//storing into array
		String[] taxMenuItems = getResources().getStringArray(R.array.tax_menu_items);
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item_layout, R.id.label, taxMenuItems));
		
		ListView lv=getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
               
              // selected item
              String item = ((TextView) view).getText().toString();
               
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("item", item);
			  i.putExtra("position", position);

              startActivity(i);
             
          }
			
		});
		
    }
}
