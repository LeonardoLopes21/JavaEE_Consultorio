const checkBoxers = document.getElementsByClassName("daysofweek")
const hidden_input = document.getElementById("hidden_input_for_weekdays")

const checkIfClicked = () => {
	let weektext = ""
	for(let i = 0; i < 7; i++){
		if(checkBoxers[i].checked){
			weektext += checkBoxers[i].value + " "
		}
	
	}
	hidden_input.value = weektext
	
}

