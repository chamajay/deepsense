package com.codestack.deepsense.presentation.emergancyContact

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EmergencyScreen() {
    // Define a list of contacts
    val contacts = remember { mutableStateListOf<Contact>() }





    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Emergency Contact") },
                navigationIcon = {
                    IconButton(onClick = { /* Go back to settings screen */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Add a new contact to the list
                val newContact = Contact("", "")
                contacts.add(newContact)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

@Preview
@Composable
fun Heading() {
    val heading = "Emergency contact"
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Text(
                text = heading,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            )

        }
    }
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
        modifier = Modifier.fillMaxWidth(),
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
                    Button(onClick = { onContactDeleted() }) {
                        Text("Delete")
                    }
                    Row {
                        Button(
                            onClick = { cardState = CardState.Editing }
                        ) {
                            Text("Edit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                onContactPriorityChanged()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (contact.isPriority) Color.Red else MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(if (contact.isPriority) "Priority" else "Make Priority")
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