package com.manishax.taxassist;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView.OnItemSelectedListener;


public class PageController extends Activity {
	static int position;
	// values from page_income_details_layout
	double	val_basic_salary=0, val_special_allowance=0, val_hra=0, val_conveyance_allowance=0, val_lta=0, val_medical_allowance=0,val_employer_pf=0; 
	//values from page_allowances_exemptions_layout
	double val_rent=0, val_calc_hra_exemption=0;
	//values from page_allowances_exemptions_layout
	double val_lta_claimed=0, val_travel_expenses=0, val_calc_lta_exemption=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        // getting attached intent data
		int position = i.getIntExtra("position",1);
		this.setContentView(R.layout.page_welcome_layout);
	}
	
	public void proceed(View view) {
        position++;
		if(position==2){
			this.setContentView(R.layout.page_income_details_layout);
		} if (position==3){
			saveIncomeDetails();
			this.setContentView(R.layout.page_allowances_exemptions_layout);
			((TextView)findViewById(R.id.val_conveyance_exempt)).setText(val_conveyance_allowance+"");
		}/*if (position==4){
			this.setContentView(R.layout.item_page_gross_income_layout);
		} if (position==5){
			this.setContentView(R.layout.item_page_deductions_layout);
		} if (position==6){
			this.setContentView(R.layout.item_page_taxable_income_layout);
		}*/
	}
	
	public void saveIncomeDetails() {
			val_basic_salary=Double.parseDouble(((EditText)findViewById(R.id.val_basic_salary)).getText().toString());
			val_special_allowance=Double.parseDouble(((EditText)findViewById(R.id.val_special_allowance)).getText().toString());
			val_hra=Double.parseDouble(((EditText)findViewById(R.id.val_hra)).getText().toString());
			val_conveyance_allowance=Double.parseDouble(((EditText)findViewById(R.id.val_conveyance_allowance)).getText().toString());
			val_lta=Double.parseDouble(((EditText)findViewById(R.id.val_lta)).getText().toString());
			val_medical_allowance=Double.parseDouble(((EditText)findViewById(R.id.val_medical_allowance)).getText().toString());
			val_employer_pf=Double.parseDouble(((EditText)findViewById(R.id.val_employer_pf)).getText().toString());
	}

	public void calculateHRAExemption(View view) {
			
			Spinner spinnerCity=(Spinner)findViewById(R.id.select_city);
			String city=String.valueOf(spinnerCity.getSelectedItem());
			val_rent=Double.parseDouble(((EditText)findViewById(R.id.val_rent)).getText().toString());
			val_calc_hra_exemption=val_hra;
			if(Math.abs(val_rent-(0.1*val_basic_salary))<val_calc_hra_exemption){
				val_calc_hra_exemption=Math.abs(val_rent-(0.1*val_basic_salary));
			}
			if(0.4*val_basic_salary<val_calc_hra_exemption && city.equals("Others")){
				val_calc_hra_exemption=0.4*val_basic_salary;
			}
			if(0.5*val_basic_salary<val_calc_hra_exemption && !city.equals("Others")){
				val_calc_hra_exemption=0.5*val_basic_salary;
			}
			((TextView)findViewById(R.id.val_calc_hra_exemption)).setText(val_calc_hra_exemption+"");
	}

	public void calculateLTAExemption(View view) {
			
			val_lta_claimed=Double.parseDouble(((EditText)findViewById(R.id.val_lta_claimed)).getText().toString());
			val_travel_expenses=Double.parseDouble(((EditText)findViewById(R.id.val_travel_expenses)).getText().toString());
			val_calc_lta_exemption=val_travel_expenses<val_lta_claimed?val_travel_expenses:val_lta_claimed;
			
			((TextView)findViewById(R.id.val_calc_lta_exemption)).setText(val_calc_lta_exemption+"");
	}
}