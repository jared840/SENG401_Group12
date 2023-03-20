

function sendEventToServer(event){
	console.log("asd")
		$.post("EventController", $.param(event), function(response) {
		    alert("xd")
		});
}



const EventTypes = {
  Add: "AddToCart",
  Remove: "RemoveFromCart",
};