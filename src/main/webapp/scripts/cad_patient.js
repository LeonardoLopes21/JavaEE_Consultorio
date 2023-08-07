const selectSex = document.getElementById("selectSex")
const selectOther = document.getElementById("optionalInput")
const genInput = document.getElementById("inputSexOther")
const filter_text_input = document.getElementById("filter_text_input")
const select_filter = document.getElementById("select_filter")

let showGen = "none";

const gen_swapper = () => {
	
	if(selectSex.value == "outro"){
		selectOther.style.display = "block"
		genInput.value = ""
	} else {
		selectOther.style.display = "none"
		genInput.value = selectSex.value
		console.log(genInput.value)
	}
	
	
}

const filter_swapper = () => {
	
	if(select_filter.value == "sex-filter"){
		
		filter_text_input.style.display = "block"
			
	} else {
		filter_text_input.style.display = "none"
	}
	
}



gen_swapper()