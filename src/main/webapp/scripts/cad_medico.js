const checkBoxers = document.getElementsByClassName("form-check-input")

const checkIfClicked = () => {
	
	let selected_specialties = ""
	
	for(let i = 0; i < checkBoxers.length; i++){
		if(checkBoxers[i].checked){
			
			selected_specialties += checkBoxers[i].value + "|";
			
		}
	}
	
	document.getElementById("hiddeninput").value = selected_specialties
	console.log(document.getElementById("hiddeninput").value)
	
}

