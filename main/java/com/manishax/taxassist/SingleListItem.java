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


public class SingleListItem extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        // getting attached intent data
        String item = i.getStringExtra("item");
		int position = i.getIntExtra("position",0);
		if(position ==0){
			this.setContentView(R.layout.item_calculate_hra_exemption_layout);
		} else if(position ==1){
			this.setContentView(R.layout.item_calculate_tax_layout);
		} else {
			    this.setContentView(R.layout.item_default_layout);
				TextView txtView = (TextView) findViewById(R.id.item_default_view);
				txtView.setText(item);
		}      
    }
	
	public void calculateHRAExemption(View view) {
			
				Spinner spinnerCity;

				double basicSal=0,hra=0,rent=0,exemption=0;
				String city;
				spinnerCity=(Spinner)findViewById(R.id.select_city);
				
				basicSal=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_basic_sal)).getText().toString());
				hra=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_HRA)).getText().toString());
				rent=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_rent)).getText().toString());
				exemption=hra;
				city=String.valueOf(spinnerCity.getSelectedItem());
				if(Math.abs(rent-(0.1*basicSal))<exemption){
					exemption=Math.abs(rent-(0.1*basicSal));
				}
				if(0.4*basicSal<exemption && city.equals("Others")){
					exemption=0.4*basicSal;
				}
				if(0.5*basicSal<exemption && !city.equals("Others")){
					exemption=0.5*basicSal;
				}
				((TextView)findViewById(R.id.txt_exemption)).setText("You are exempted Rs."+exemption+" as HRA Exemption under section 10 A");
	}
	
	public void calculateTaxPayable(View view){
		double income=0, hraExemption=0, otherExemptions=0, profTax=0, totalExemptions=0, deduction80c=0,
		deduction80ccg=0, deduction80d=0,deduction80g=0, deduction80e=0, deduction80tta=0, benefit24=0, totalDeductionsBenefits=0,
		taxable_income=0,tax=0;
		
		income=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_income)).getText().toString());
		hraExemption=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_HRA_exemption)).getText().toString());
		otherExemptions=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_other_exemptions)).getText().toString());
		profTax=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_prof_tax)).getText().toString());
		totalExemptions=hraExemption + otherExemptions + profTax;
		deduction80c=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80c)).getText().toString());
		deduction80ccg=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80ccg)).getText().toString());
		deduction80d=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80d)).getText().toString());
		deduction80g=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80g)).getText().toString());
		deduction80e=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80e)).getText().toString());
		deduction80tta=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_deduction_80tta)).getText().toString());
		benefit24=Double.parseDouble(((EditText)findViewById(R.id.edit_txt_benefit_24)).getText().toString());
		totalDeductionsBenefits=deduction80c + deduction80ccg + deduction80d + deduction80g +	 deduction80e + deduction80tta + benefit24;
		((TextView)findViewById(R.id.txt_total_exemptions_val)).setText(totalExemptions+"");
		((TextView)findViewById(R.id.txt_total_deductions_benefits_val)).setText(totalDeductionsBenefits+"");
		
		taxable_income=income-totalExemptions-totalDeductionsBenefits;
		((TextView)findViewById(R.id.txt_total_taxable_income_val)).setText(taxable_income+"");

		if(taxable_income>1000000){
			tax+=(taxable_income-1000000)*0.3;	
			((TextView)findViewById(R.id.txt_taxable_income_4)).setText((taxable_income-1000000)+"");
			((TextView)findViewById(R.id.txt_payable_tax_4)).setText(((taxable_income-1000000)*0.3)+"");
			taxable_income=1000000;			
		}
		if(taxable_income>500000){
			tax+=(taxable_income-500000)*0.2;	
			((TextView)findViewById(R.id.txt_taxable_income_3)).setText((taxable_income-500000)+"");
			((TextView)findViewById(R.id.txt_payable_tax_3)).setText(((taxable_income-500000)*0.2)+"");
			taxable_income=500000;			
		}
		if(taxable_income>250000){
			tax+=(taxable_income-250000)*0.1;
			((TextView)findViewById(R.id.txt_taxable_income_2)).setText((taxable_income-250000)+"");
			((TextView)findViewById(R.id.txt_payable_tax_2)).setText(((taxable_income-250000)*0.1)+"");			
			taxable_income=250000;			
		}
		((TextView)findViewById(R.id.txt_taxable_income_1)).setText(taxable_income+"");
		
		((TextView)findViewById(R.id.txt_total_payable_tax_val)).setText(tax+"");
	
	}
}