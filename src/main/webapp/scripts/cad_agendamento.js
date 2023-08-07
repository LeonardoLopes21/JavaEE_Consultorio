const select_filter = document.getElementById("input_filter")
const input_filter = document.getElementById("filter_text_input")


const filter_swapper = () => {
	
	if(select_filter.value == "by_date"){
		input_filter.style.display = "block"
		input_filter.type = "date"
	} else if(select_filter.value == "all_done" || select_filter.value == "all" || select_filter.value == "by_week"){
		input_filter.type = "text"
		input_filter.style.display = "none"
	} else {
		input_filter.type = "text"
		input_filter.style.display = "block"
	}

}