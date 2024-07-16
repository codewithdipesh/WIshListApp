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
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch


@Composable
fun AddEditDetailView (
    id : Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id != 0L){
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

       Scaffold (
        topBar = {
            AppBarView(
                title =
                if (id != 0L) "Update Wish" else "Add Wish"
            ){
                navController.navigateUp()
            }
        },
        scaffoldState = scaffoldState
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
                 if(viewModel.wishTitleState.isNotEmpty()
                     && viewModel.wishDescriptionState.isNotEmpty())
                 {
                     if(id != 0L){
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                         snackMessage.value = "Wish Has been Updated"
                     }else{
                        viewModel.addWish(
                            Wish(
                             title = viewModel.wishTitleState.trim(),
                             description = viewModel.wishDescriptionState.trim()
                             )
                        )
                         snackMessage.value = "Wish Has been created"
                     }
                 }else{
                     snackMessage.value = "Enter Fields to create a wish"
                 }

                 scope.launch {
                     scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                     navController.navigateUp()

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