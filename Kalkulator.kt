package com.example.bljr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Kalkulator : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

    }
    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            var isEmptyFields = false
            var isInvalidDouble= false
            //untuk mengecek apakah suatu field empty atau tidak dan memberi warning
            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            val length: Double = (inputLength).toDouble()
            val width: Double = (inputWidth).toDouble()
            val height: Double = (inputHeight).toDouble()
            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "Nilai tidak valid"
            }
            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "Nilai tidak valid"
            }
            if (height == null) {
                isInvalidDouble = true
                edtHeight.error = "Nilai tidak valid"
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume =  height!!.toDouble() * length!!.toDouble() * length.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    private fun convertToDouble(str: String):Double?{
        return try {
            str.toDouble()
        }catch (e : NumberFormatException){
            null
        }


    }
    companion object{
        private const val STATE_RESULT = "state_result"
    }

}
}