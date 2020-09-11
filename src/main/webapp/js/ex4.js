
const cars = [
  { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
  { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
  { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
  { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
  { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];
const div = document.getElementById("fortable");

function toTable(array) {
    var res = "<table class=\"table\">";
    res += "<tr><th>ID</th><th>Year</th>";
    res += "<th>Make</th><th>Model</th><th>Price</th></tr>";
    
    for (var i = 0; i < array.length; i++) {
        res += "<tr><td>" + array[i].id + "</td><td>" 
                + array[i].year + "</td><td>" 
                + array[i].make + "</td><td>"
                + array[i].model + "</td><td>"
                + array[i].price + "</td></tr>";
    }

    res += "</table>";
    return res;
}

div.innerHTML = toTable(cars);;

function filterByPrice() {
    event.preventDefault();
    var input = document.getElementById("price");
    var test = input.value / 2;
    if (isNaN(test) || input.value.length === 0) {
        alert("Type a number");
        div.innerHTML = toTable(cars);
    } else {
        var filtered = cars.filter(car => car.price < input.value);
        div.innerHTML = toTable(filtered);
    }
}

document.getElementById("lessThan").addEventListener("click", filterByPrice, false);






