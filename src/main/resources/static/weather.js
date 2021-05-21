const form = document.querySelector(".top-banner form");
const input = document.querySelector(".top-banner input");
const msg = document.querySelector(".top-banner .msg");
const list = document.querySelector(".ajax-section .cities");

form.addEventListener("submit", e => {
 	e.preventDefault();
 	const listItems = list.querySelectorAll(".ajax-section .city");
 	const inputVal = input.value;
  
  	var hostname = window.location.hostname;
  	var port = window.location.port;
	  	
  	const url = `http://` + hostname + `:` + port + `/api/weather-forecast?cityName=` + inputVal;
 	
	fetch(url)
        .then(response => response.text()).then((data) => {
            console.log(data)
            const fail = "failure";
            if(data.localeCompare(fail) == 0) {
            	throw "error";
            }
			const li = document.createElement("li");
			li.classList.add("city");
			li.innerHTML = data;
			list.appendChild(li);
        })
        .catch(() => {
      msg.textContent = "Please search for a valid city";
      });
  
  msg.textContent = "";
  form.reset();
  input.focus();
});