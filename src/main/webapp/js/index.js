// REMOTE URL
let url = "https://bencat.dk/movieproject/api/movie/";

// LOCAL URL
//let url = "http://localhost:8080/jpareststarter/api/movie/";

var controller = document.getElementById("controller");
var table = document.getElementById("fortable");
var buttons = document.querySelectorAll("input.options");
var viewAllNoDto = false;

buttons.forEach(function(element) {
    element.addEventListener("click", fetchData, false);
});

function toTable(fetchedData) {
 var tableString = "<table class=\"table\">";
 tableString += "<tr><th>Title</th><th>Description</th></tr>";

 if (typeof fetchedData.length === "undefined") {
     tableString += "<tr><td>" + fetchedData.title + "</td><td>"
             + fetchedData.description + "</td></tr>";
     
 } else if (viewAllNoDto) {
     tableString = tableString.slice(0, 59);
     tableString += "<th>ID</th><th>Release Year</th></tr>";
      for (var i = 0; i < fetchedData.length; i++) {
          tableString += "<tr><td>" + fetchedData[i].title + "</td><td>"
                  + fetchedData[i].description + "</td>"
                  + "<td>" + fetchedData[i].id + "</td>"
                  + "<td>" + fetchedData[i].releaseYear + "</td></tr>";
      }
      viewAllNoDto = false;
      
 } else {

 for (var i = 0; i < fetchedData.length; i++) {
     tableString += "<tr><td>" + fetchedData[i].title + "</td><td>"
             + fetchedData[i].description + "</td></tr>";
 }
}

 tableString += "</table>";
 table.innerHTML = tableString;
}

function determineEndpoint(element) {
    var input = document.getElementById("input");
    
    if (element.id === "getAll") {
        return "all";
    }
    if (element.id === "getById") {
        return "by_id/" + input.value;
    }
    if (element.id === "getByTitle") {
        return "by_title/" + input.value;
    }
    if (element.id === "getByYear") {
        return "by_year/" + input.value;
    }
    if (element.id === "getAllNoDto") {
        viewAllNoDto = true;
        return "all_no_dto";
    }
}

function fetchData() {
    event.preventDefault();
    event.stopPropagation();
    var choice = document.getElementById(event.target.id);
    tempUrl = url;
    
   tempUrl += determineEndpoint(choice);
    
   fetch(tempUrl) 
  .then(res => res.json()) 
  .then(data => {
      toTable(data);
    });
}
