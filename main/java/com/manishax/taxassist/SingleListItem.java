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


public class SingleListItem extends Activity implements OnItemSelectedListener{
		//all values for layout
		static double income=0, calcHraExemption=0, otherExemptions=0, profTax=0, totalExemptions=0, 
		deduction80c=0, 
		deduction80ccc=0, 
		deduction80ccd1=0,
		deductionsAllThree=0,calcDeductionsAllThree=0,
		deductions80ccd2=0, calcDeductions80ccd2=0, 
		deduction80tta=0, calcDeduction80tta=0,
		calcDeduction80gg=0, 
		deduction80e=0, 
		deduction80ee=0, calcDeduction80ee=0, 
		deduction80ccg=0,calcDeduction80ccg=0, 
		deduction80d=0,calcDeduction80d=0,
		deduction80dd=0, calcDeduction80dd=0,
		deduction80ddb=0, calcDeduction80ddb=0,
		deduction80u=0, calcDeduction80u=0,
		calcDeduction80g=0,
		deduction80ggc=0,
		deduction80rrb=0, calcDeduction80rrb=0,
		benefit24=0, totalDeductionsBenefits=0,
		taxable_income=0,tax=0;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        // getting attached intent data
        String item = i.getStringExtra("item");
		int position = i.getIntExtra("position",0);
		if(position == 0){
			this.setContentView(R.layout.item_calculate_gti_layout);
		} else if(position ==1){
			this.setContentView(R.layout.item_calculate_hra_exemption_layout);
		} else if(position ==2){
			this.setContentView(R.layout.item_calculate_80gg_deduction_layout);
		} else if(position ==3){
				this.setContentView(R.layout.item_calculate_80g_deduction_layout);
				Spinner spinnerDonationCategory = (Spinner) findViewById(R.id.select_donation_category);
				spinnerDonationCategory.setOnItemSelectedListener(this);
		}else if(position ==4){
				this.setContentView(R.layout.item_calculate_tax_layout);
				AlertDialog.Builder ab=new AlertDialog.Builder(this);
				ab.setTitle("Note");
				ab.setMessage("Some values will be brought in from other calculations on the home screen. Please make sure you have calculated them.");
				ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						dialog.cancel();					
					}
				 });
				ab.show();
				((TextView)findViewById(R.id.val_calc_HRA_exemption)).setText(calcHraExemption+"");
				((TextView)findViewById(R.id.val_deduction_80gg)).setText(calcDeduction80gg+"");
				((TextView)findViewById(R.id.val_calc_deduction_80gg)).setText(calcDeduction80gg+"");
				((TextView)findViewById(R.id.val_deduction_80g)).setText(calcDeduction80g+"");
				((TextView)findViewById(R.id.val_calc_deduction_80g)).setText(calcDeduction80g+"");
		} else {
			    this.setContentView(R.layout.item_default_layout);
				TextView txtView = (TextView) findViewById(R.id.item_default_view);
				txtView.setText(item);
		}      
    }
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
					Spinner spinnerDonation = (Spinner) findViewById(R.id.select_donation);
					int arrayId=0;
					if(pos==0){ //category 1
						arrayId=R.array.donations_100prcnt_no_limit_array;
					}else if(pos==1){ //category 2
						arrayId=R.array.donations_50prcnt_no_limit_array;
					} else if(pos ==2){ //category 3
						arrayId=R.array.donations_100prcnt_limited_array;
					} else if(pos==3){ //category 4
						arrayId=R.array.donations_50prcnt_limited_array;
					}
					// Create an ArrayAdapter using the string array and a default spinner layout
					ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
					arrayId, android.R.layout.simple_spinner_item);
					// Specify the layout to use when the list of choices appears
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// Apply the adapter to the spinner
					spinnerDonation.setAdapter(adapter);

	}
	
	public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
	
	public void calculateHRAExemption(View view) {
			
				Spinner spinnerCity;

				double basicSal=0,hra=0,rent=0;
				String city;
				spinnerCity=(Spinner)findViewById(R.id.select_city);
				
				basicSal=Double.parseDouble(((EditText)findViewById(R.id.val_basic_sal)).getText().toString());
				hra=Double.parseDouble(((EditText)findViewById(R.id.val_HRA)).getText().toString());
				rent=Double.parseDouble(((EditText)findViewById(R.id.val_rent)).getText().toString());
				calcHraExemption=hra;
				city=String.valueOf(spinnerCity.getSelectedItem());
				if(Math.abs(rent-(0.1*basicSal))<calcHraExemption){
					calcHraExemption=Math.abs(rent-(0.1*basicSal));
				}
				if(0.4*basicSal<calcHraExemption && city.equals("Others")){
					calcHraExemption=0.4*basicSal;
				}
				if(0.5*basicSal<calcHraExemption && !city.equals("Others")){
					calcHraExemption=0.5*basicSal;
				}
				((TextView)findViewById(R.id.lbl_exemption)).setText("You are exempted Rs."+calcHraExemption+" as HRA Exemption under section 10 A");
	}
	
	public void calculate80ggDeduction(View view) {
			

				double totalIncome=0,rent=0;				
				totalIncome=Double.parseDouble(((EditText)findViewById(R.id.val_total_income)).getText().toString());
				rent=Double.parseDouble(((EditText)findViewById(R.id.val_rent)).getText().toString());
				calcDeduction80gg=0.25*totalIncome;
				if(Math.abs(rent-(0.1*totalIncome))<calcDeduction80gg){
					calcDeduction80gg=Math.abs(rent-(0.1*totalIncome));
				}
				if(2000*12<calcDeduction80gg ){
					calcDeduction80gg=2000*12;
				}
				
				((TextView)findViewById(R.id.lbl_deduction)).setText("Deduction u/s 80GG Rs."+calcDeduction80gg);
	}
	
		public void calculate80gDeduction(View view) {

				double grossIncome=0;
				String category;
				grossIncome=Double.parseDouble(((EditText)findViewById(R.id.val_gross_income)).getText().toString());
				calcDeduction80g=Double.parseDouble(((EditText)findViewById(R.id.val_donation_amount)).getText().toString());
				Spinner spinnerDonationCategory=(Spinner) findViewById(R.id.select_donation_category);;
				category=String.valueOf(spinnerDonationCategory.getSelectedItem());
				if(category.equals("Category 1")){
					//100% of donation without limit
					calcDeduction80g=calcDeduction80g; 
				} else if(category.equals("Category 2")){
					//50% of donation without limit
					calcDeduction80g=calcDeduction80g*0.5; 
				} else if(category.equals("Category 3")){
					//100% of donation with limit of 10% of gross
					calcDeduction80g=(calcDeduction80g<grossIncome*0.1)?calcDeduction80g:grossIncome*0.1;  
				} else if (category.equals("Category 4")){
					//50% of donation with limit of 10% gross
					calcDeduction80g=(calcDeduction80g*0.5<grossIncome*0.1)?calcDeduction80g*0.5:grossIncome*0.1; 
				}
				
				((TextView)findViewById(R.id.lbl_deduction)).setText("Deduction u/s 80G Rs."+calcDeduction80g);
	}
	
	public void calculateTaxPayable(View view){
		

		/*
		income=Double.parseDouble(((EditText)findViewById(R.id.val_income)).getText().toString());
		hraExemption=Double.parseDouble(((EditText)findViewById(R.id.val_HRA_exemption)).getText().toString());
		otherExemptions=Double.parseDouble(((EditText)findViewById(R.id.val_other_exemptions)).getText().toString());
		profTax=Double.parseDouble(((EditText)findViewById(R.id.val_prof_tax)).getText().toString());
		totalExemptions=hraExemption + otherExemptions + profTax;
		deduction80c=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80c)).getText().toString());
		deduction80ccg=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80ccg)).getText().toString());
		deduction80d=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80d)).getText().toString());
		deduction80g=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80g)).getText().toString());
		deduction80e=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80e)).getText().toString());
		deduction80tta=Double.parseDouble(((EditText)findViewById(R.id.val_deduction_80tta)).getText().toString());
		benefit24=Double.parseDouble(((EditText)findViewById(R.id.val_benefit_24)).getText().toString());
		totalDeductionsBenefits=deduction80c + deduction80ccg + deduction80d + deduction80g +	 deduction80e + deduction80tta + benefit24;
		((TextView)findViewById(R.id.lbl_total_exemptions)).setText(totalExemptions+"");
		((TextView)findViewById(R.id.lbl_total_deductions_benefits)).setText(totalDeductionsBenefits+"");
		
		taxable_income=income-totalExemptions-totalDeductionsBenefits;
		((TextView)findViewById(R.id.lbl_total_taxable_income)).setText(taxable_income+"");

		if(taxable_income>1000000){
			tax+=(taxable_income-1000000)*0.3;	
			((TextView)findViewById(R.id.lbl_taxable_income_4)).setText((taxable_income-1000000)+"");
			((TextView)findViewById(R.id.lbl_payable_tax_4)).setText(((taxable_income-1000000)*0.3)+"");
			taxable_income=1000000;			
		}
		if(taxable_income>500000){
			tax+=(taxable_income-500000)*0.2;	
			((TextView)findViewById(R.id.lbl_taxable_income_3)).setText((taxable_income-500000)+"");
			((TextView)findViewById(R.id.lbl_payable_tax_3)).setText(((taxable_income-500000)*0.2)+"");
			taxable_income=500000;			
		}
		if(taxable_income>250000){
			tax+=(taxable_income-250000)*0.1;
			((TextView)findViewById(R.id.lbl_taxable_income_2)).setText((taxable_income-250000)+"");
			((TextView)findViewById(R.id.lbl_payable_tax_2)).setText(((taxable_income-250000)*0.1)+"");			
			taxable_income=250000;			
		}
		((TextView)findViewById(R.id.lbl_taxable_income_1)).setText(taxable_income+"");
		
		((TextView)findViewById(R.id.lbl_total_payable_tax)).setText(tax+"");
	*/
	} 
}