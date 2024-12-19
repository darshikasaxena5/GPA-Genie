package com.example.gpagenie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gpagenie.ui.theme.GPAGenieTheme

data class Semester(val grade:String, val credit: Int)
class MainActivity : ComponentActivity() {
    private var semesters: MutableList<Semester> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GPAGenieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
CGPA(semesters )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CGPA(semesters: MutableList<Semester>){

    var prevCGPA by remember { mutableStateOf("") }
    var prevTotalCredits by remember { mutableStateOf("") }

    var grade1 by remember{ mutableStateOf("") }
    var credit1 by remember{ mutableStateOf<Int?>(null) }
    var grade2 by remember{ mutableStateOf("") }
    var credit2 by remember{ mutableStateOf<Int?>(null) }
    var grade3 by remember{ mutableStateOf("") }
    var credit3 by remember{ mutableStateOf<Int?>(null) }
    var grade4 by remember{ mutableStateOf("") }
    var credit4 by remember{ mutableStateOf<Int?>(null) }
    var grade5 by remember{ mutableStateOf("") }
    var credit5 by remember{ mutableStateOf<Int?>(null) }
    var currentSGPA by remember { mutableStateOf(0.0) }
    var overallCGPA by remember { mutableStateOf(0.0) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text(
            text = "GPA Genie",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                color = Color(0xFF000000)
            )
        )
        Text(
            text = "Effortless Precision, Exceptional Results",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color(0xFF000000)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))


        // Add this Surface for previous semester details
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            color = Color(0xFFF3E5F5),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    "Previous Semester Details",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Color(0xFF4A148C)
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = prevCGPA,
                        onValueChange = { prevCGPA = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        label = { Text("Previous CGPA", fontSize = 12.sp) },
                        textStyle = TextStyle(fontSize = 14.sp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color(0xFF7E57C2),
                            unfocusedLabelColor = Color(0xFF7E57C2)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    TextField(
                        value = prevTotalCredits,
                        onValueChange = { prevTotalCredits = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        label = { Text("Previous Total Credits ", fontSize = 12.sp) },
                        textStyle = TextStyle(fontSize = 14.sp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color(0xFF7E57C2),
                            unfocusedLabelColor = Color(0xFF7E57C2)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }

        SubjectRow("Subject1", grade1, credit1,
            onGradeChange = { grade1 = it },
            onCreditChange = { credit1 = it }
        )

        SubjectRow("Subject2", grade2, credit2,
            onGradeChange = { grade2 = it },
            onCreditChange = { credit2 = it }
        )

        SubjectRow("Subject3", grade3, credit3,
            onGradeChange = { grade3 = it },
            onCreditChange = { credit3 = it }
        )

        SubjectRow("Subject4", grade4, credit4,
            onGradeChange = { grade4 = it },
            onCreditChange = { credit4 = it }
        )

        SubjectRow("Subject5", grade5, credit5,
            onGradeChange = { grade5 = it },
            onCreditChange = { credit5 = it }
        )



        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Column(
                modifier = Modifier.weight(0.4f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {

                        semesters.clear()

                        // Add all subjects for this semester
                        if (credit1 != null && grade1.isNotEmpty())
                            semesters.add(Semester(grade1, credit1!!))
                        if (credit2 != null && grade2.isNotEmpty())
                            semesters.add(Semester(grade2, credit2!!))
                        if (credit3 != null && grade3.isNotEmpty())
                            semesters.add(Semester(grade3, credit3!!))
                        if (credit4 != null && grade4.isNotEmpty())
                            semesters.add(Semester(grade4, credit4!!))
                        if (credit5 != null && grade5.isNotEmpty())
                            semesters.add(Semester(grade5, credit5!!))

                        // Calculate CGPA
                        val totalCredit = semesters.sumOf { it.credit }
                        val totalGradePoints =
                            semesters.sumOf { calculateGradePoints(it.grade, it.credit) }

                        currentSGPA = if (totalCredit > 0) {
                            // Round to 2 decimal places for better display
                            String.format("%.2f", totalGradePoints / totalCredit).toDouble()
                        } else {
                            0.0
                        }

                        // Calculate overall CGPA using the formula
                        val prevCgpaValue = prevCGPA.toDoubleOrNull() ?: 0.0
                        val prevCredits = prevTotalCredits.toIntOrNull() ?: 0

                        overallCGPA = if (totalCredit > 0) {
                            val totalCGPA =
                                ((prevCgpaValue * prevCredits) + (currentSGPA * totalCredit)) /
                                        (prevCredits + totalCredit)
                            String.format("%.2f", totalCGPA).toDouble()
                        } else 0.0

                        // Reset fields as before
                        grade1 = ""; credit1 = null
                        grade2 = ""; credit2 = null
                        grade3 = ""; credit3 = null
                        grade4 = ""; credit4 = null
                        grade5 = ""; credit5 = null


                    },


                    colors = ButtonDefaults.buttonColors(Color(0xFF002837)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Calculate CGPA",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
            //Results
            Surface(
                modifier = Modifier
                    .weight(0.6f)
                    .height(70.dp),
                color = Color(0xFF263238),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Current SGPA: ${String.format("%.2f", currentSGPA)}",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Overall CGPA: ${String.format("%.2f", overallCGPA)}",
                        color = Color(0xFF81C784),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                }
            }
        }




                    if (semesters.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))

                        // Display current SGPA
                        Text(
                            text = "Current SGPA: ${String.format("%.2f", currentSGPA)}",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Divider(color = Color.White.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(4.dp))

                        // Display Overall CGPA with emphasis
                        Text(
                            text = "Overall CGPA: ${String.format("%.2f", overallCGPA)}",
                            color = Color(0xFF81C784),  // Light green for emphasis
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold))
                        )
                    }
                }
            }








fun calculateGradePoints(grade: String, credit: Int):Double {

 return   when (grade.uppercase()){
        "AA"->10.0
        "AB"->9.0
        "BB"->8.0
        "BC"->7.0
        "CC"->6.0
        "CD"->5.0
        "DD"->4.0
        else ->0.0
    }*credit
}

@Composable
fun Spacer8dp(){
    Spacer(modifier = Modifier.padding(top = 8.dp))
}
@Composable
fun SubjectText(subject:String){

    Text(text = subject,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = Color(0xFF000000)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(
    grade: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = grade,
        onValueChange = { text -> onValueChange(text) },
        modifier = modifier,
        label = { Text(text = "Enter Grade", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            containerColor = Color(0xFF00668b)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(
    credit: Int?,
    onValueChange: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = credit?.toString() ?: "",
        onValueChange = { text -> onValueChange(text.toIntOrNull()) },
        modifier = modifier,
        label = { Text(text = "Enter Credit", color = Color.Black, fontSize = 10.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            containerColor = Color(0xFF7fb2c5)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 10.sp, color = Color.Black)
    )
}


@Composable
fun SubjectRow(
    subject: String,
    grade: String,
    credit: Int?,
    onGradeChange: (String) -> Unit,
    onCreditChange: (Int?) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = subject,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                color = Color(0xFF000000)
            ),
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GradeTextField(
                grade = grade,
                onValueChange = onGradeChange,
                modifier = Modifier
                    .weight(0.6f)
                    .height(44.dp)
            )
            CreditTextField(
                credit = credit,
                onValueChange = onCreditChange,
                modifier = Modifier
                    .weight(0.4f)
                    .height(44.dp)
            )
        }
    }
}




