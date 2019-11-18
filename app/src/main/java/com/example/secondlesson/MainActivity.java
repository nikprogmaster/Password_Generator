package com.example.secondlesson;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView resultTextView ;
    private TextView sourceTextView;
    private Translater helper;
    private View copyButton;
    private ImageView quality;
    private TextView q_textView;
    private CompoundButton useUpperCase;
    private CompoundButton useDigits;
    private CompoundButton useSymbols;
    private View generateButton;
    private View copyButton2;
    private SeekBar seekBar;
    private TextView generatedPassword;
    private TextView length;

    private boolean isUpper = false;
    private boolean isDigit = false;
    private boolean isSymbol = false;

    private int num = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Translater(
                getResources().getStringArray(R.array.russian),
                getResources().getStringArray(R.array.english));

        resultTextView = findViewById(R.id.result_text);
        sourceTextView = findViewById(R.id.source_text);
        copyButton = findViewById(R.id.button_copy);
        quality = findViewById(R.id.quality);
        q_textView = findViewById(R.id.quality_text);
        useUpperCase = findViewById(R.id.check_uppercase);
        useDigits = findViewById(R.id.check_numbers);
        useSymbols = findViewById(R.id.check_specsymbols);
        generateButton = findViewById(R.id.generate);
        copyButton2 = findViewById(R.id.button_copy2);
        seekBar = findViewById(R.id.seek_symbols);
        generatedPassword = findViewById(R.id.password);
        length = findViewById(R.id.length);

        copyButton.setEnabled(false);
        copyButton2.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                num = 8;
                num += i;
                String symbols = "Длина: 8 символов + "+ getResources().getQuantityString(R.plurals.length, i, i)
                        + " = " + getResources().getQuantityString(R.plurals.length, i+8, i+8);
                length.setText(symbols);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager =(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText(
                        MainActivity.this.getString(R.string.clipboardTitle),
                        resultTextView.getText()
                ));
                Toast.makeText(MainActivity.this, R.string.message_copy, Toast.LENGTH_SHORT).show();
            }
        });

        copyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager =(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText(
                        MainActivity.this.getString(R.string.Title2),
                        generatedPassword.getText()
                ));
                Toast.makeText(MainActivity.this, R.string.message_copy, Toast.LENGTH_SHORT).show();
            }
        });

        sourceTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                resultTextView.setText(helper.convert(charSequence));
                copyButton.setEnabled(charSequence.length() > 0);
                int q = helper.getQuality(charSequence);
                quality.setImageLevel(q*1000);
                q_textView.setText(getResources().getStringArray(R.array.qualities)[q]);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        useUpperCase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isUpper = b;
            }
        });

        useSymbols.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isSymbol = b;
            }
        });

        useDigits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isDigit = b;
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordRandomiser passwordRandomiser = new PasswordRandomiser(
                        getResources().getStringArray(R.array.letters),
                        getResources().getStringArray(R.array.special_symbols),
                        getResources().getStringArray(R.array.digits));
                String password = passwordRandomiser.randomize(isUpper,isDigit,isSymbol, num);
                generatedPassword.setText(password);
                copyButton2.setEnabled(true);
            }
        });
    }
}
