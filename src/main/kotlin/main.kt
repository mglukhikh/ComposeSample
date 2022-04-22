import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

@Composable
fun foo() {
    Text("My text")
}

@Composable
fun MessageCard(name: String, color: Color = MaterialTheme.colors.primary) {
    Text(text = "Hello $name!", color = color)
}

@Composable
fun App1() {
    var clicks by remember { mutableStateOf(listOf(0)) }
    fun foo() = clicks.last()
    var j = 0
    MaterialTheme(colors = darkColors(/*primary = Color(0, 0, 255), secondary = Color(0, 255, 0), onPrimary = Color(255, 0, 0)*/)) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            MessageCard("Teacher")
            Spacer(Modifier.fillMaxHeight(fraction = 0.5f))
            MessageCard("Student ${foo()}", color = MaterialTheme.colors.secondary)
            Button(onClick = {
                clicks += ++j
                println(clicks)
            }) {
                Text("Enter the room!")
            }
        }
    }
}

@Composable
fun FrameWindowScope.App2() {
    val dialogIndex = remember { mutableStateOf(0) }
    var buttonState by remember { mutableStateOf(listOf(true, true, true, true, true)) }
    LazyColumn {
        //for (index in 0..4) {
        items(5) { index ->
            if (buttonState[index]) {
                Button(onClick = {
                    dialogIndex.value = index + 1
                    val list = buttonState.toMutableList()
                    list[index] = false
                    buttonState = list.toList()
                }) {
                    Text("Button ${index + 1}")
                }
            }
        }
    }
    if (dialogIndex.value > 0) {
        Dialog(
            title = "Button pressed",
            state = rememberDialogState(width = 200.dp, height = 100.dp),
            onCloseRequest = {
                dialogIndex.value = 0
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("Button ${dialogIndex.value} pressed")
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { dialogIndex.value = 0 }
                ) {
                    Text("OK")
                }
            }
        }
    }
    MenuBar {
        Menu("First") {
            Item("Second", onClick = {})
        }
    }
}

fun main() = application {
    Window(
        title = "Sample",
        state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center), width = 500.dp, height = 300.dp),
        onCloseRequest = ::exitApplication
    ) {
        App2() // App(), App1()
    }
}
