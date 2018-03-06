package com.example.shivamvk.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    TextView quantity;
    CheckBox whippedCream;
    CheckBox chocolate;
    EditText name;
    String isWhippedCream;
    String isChocolate;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantity = (TextView)findViewById(R.id.quantity_text_view);
        whippedCream = (CheckBox)findViewById(R.id.whipped_cream);
        chocolate = (CheckBox)findViewById(R.id.chocolate);
        name = (EditText)findViewById(R.id.name);
    }

    public void increment(View view){
        String s = quantity.getText().toString();
        int quant = Integer.parseInt(s,10);
        quant++;
        quantity.setText(""+quant);
    }

    public void decrement(View view){
        String s = quantity.getText().toString();
        int quant = Integer.parseInt(s,10);
        quant--;
        quantity.setText(""+quant);
    }

    public void submitOrder(View view){
        String s = quantity.getText().toString();
        int quant = Integer.parseInt(s);
        if(whippedCream.isChecked()){
            isWhippedCream = "Yes";
        }
        else{
            isWhippedCream = "No";
        }
        if(chocolate.isChecked()){
            isChocolate = "Yes";
        }
        else{
            isChocolate = "No";
        }
        int price = getPrice(quant);
        userName = name.getText().toString();
        String orderSummary = getOrderSummary(price,quant);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        intent.putExtra(Intent.EXTRA_EMAIL, "shivam.vk529@gmail.com");
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        Toast.makeText(getApplicationContext(), "Order will be delivered in 5 mins ^_^",Toast.LENGTH_LONG).show();
    }

    public int getPrice(int quant){
        if(whippedCream.isChecked() && chocolate.isChecked()){
            return 8*quant;
        }
        else if(whippedCream.isChecked() && !chocolate.isChecked()){
            return 6*quant;
        }
        else if(!whippedCream.isChecked() && chocolate.isChecked()){
            return 7*quant;
        }
        else
            return 5*quant;
    }

    public String getOrderSummary(int price, int quant){
        String orderSummary = "Name: " + userName + "\nAdd Whipped Cream? " + isWhippedCream + "\nAdd Chocolate?" + isChocolate + "\nQuantity: " + quant + "\nTotal: $" + price + "\nThankyou!";
        return orderSummary;
    }

}
