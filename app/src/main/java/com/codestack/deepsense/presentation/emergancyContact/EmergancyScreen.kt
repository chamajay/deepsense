package com.codestack.deepsense.presentation.emergancyContact

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EmergencyScreen() {
    // Define a list of contacts
    val contacts = remember { mutableStateListOf<Contact>() }
    val heading = "Emergency contact"


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = heading,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Go back to settings screen */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },

                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Add a new contact to the list
                    val newContact = Contact("", "")
                    contacts.add(newContact)
                },
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )

            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                val appBarHeight = 40.dp
                Spacer(modifier = Modifier.height(appBarHeight))
            }
            items(contacts.size) { index ->
                EmergencyContactCard(contact = contacts[index],
                    onContactDeleted = { contacts.removeAt(index) },
                    onContactPriorityChanged = { contacts[index].isPriority = true })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmergencyScreenPreview() {
    EmergencyScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactCard(
    contact: Contact,
    onContactDeleted: () -> Unit,
    onContactPriorityChanged: () -> Unit
) {
    var cardState by remember { mutableStateOf(CardState.Editing) }
    val nameState = remember { mutableStateOf(contact.name) }
    val phoneState = remember { mutableStateOf(contact.number) }

    ElevatedCard(
        
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),

        ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = "Contact Icon")
                Spacer(modifier = Modifier.width(8.dp))
                if (cardState == CardState.Editing) {
                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        label = { Text("Name") },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Phone, contentDescription = "Phone Icon")
                Spacer(modifier = Modifier.width(8.dp))
                if (cardState == CardState.Editing) {
                    OutlinedTextField(
                        value = phoneState.value,
                        onValueChange = { phoneState.value = it },
                        label = { Text("Phone Number") },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Text(text = contact.number, fontSize = 16.sp)
                }
            }
            if (cardState == CardState.Editing) {
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { cardState = CardState.Viewing }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            contact.name = nameState.value
                            contact.number = phoneState.value
                            cardState = CardState.Viewing
                        }
                    ) {
                        Text("Save")
                    }
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Button(
                        onClick = {
                            onContactPriorityChanged()
                        },
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            if (contact.isPriority) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(if (contact.isPriority) "Priority" else "Make Priority")
                    }


                    Row {

                        Button(
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            onClick = { cardState = CardState.Editing },
                            modifier = Modifier
                                .background(Color.Transparent),

                        ) {
                            Text(
                                text = "Edit",
                                fontSize = MaterialTheme.typography.labelLarge.fontSize
                            )
                        }


                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = onContactDeleted,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Contact",
                            )
                        }
                    }

                }
            }
        }
    }
}

enum class CardState {
    Editing,
    Viewing
}

data class Contact(
    var name: String,
    var number: String,
    var isPriority: Boolean = false
)