@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AddEditDetailView (
    id : Long,
    viewModel: WishViewModel,
    navController: NavController
){
    Scaffold (
        topBar = {
            AppBarView(
                title =
                if (id != 0L) "Update Wish" else "Add Wish"
            ){
                navController.navigateUp()
            }
        }
    ){
         Column(modifier = Modifier
             .padding(it)
             .wrapContentSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center)
         {
             Spacer(modifier = Modifier.height(10.dp))

             WishTextField(
                 label = "Title" ,
                 value = viewModel.wishTitleState ,
                 onValueChanged ={
                     viewModel.onWishTitleChanged(it)
                 } )
             WishTextField(
                 label = "Description" ,
                 value = viewModel.wishDescriptionState ,
                 onValueChanged ={
                     viewModel.onWishDescriptionChanged(it)
                 } )

             Button(onClick = {
                 if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                     //TODO update list
                 }else{
                     //TODO add a wish
                 }

             }) {

                 Text(
                     text = if( id !=0L) "Update Wish" else "Add Wish" ,
                     style = TextStyle(fontSize = 18.sp)
                 )

             }

         }
    }
}

@ExperimentalMaterial3Api
@Composable
fun WishTextField(
    label : String,
    value : String,
    onValueChanged : (String)-> Unit
){

    OutlinedTextField(
        value = value ,
        onValueChange = onValueChanged,
        label = { Text(text = label , color =  Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable

fun WishTextFieldPreview(){
    WishTextField(label = "Title", value ="text" , onValueChanged ={} )
}